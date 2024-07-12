package com.example.raildistance.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.raildistance.ui.theme.KoTheme

@Composable
fun SearchInputField(
    text: String,
    onValueChange: (String) -> Unit = {},
    inputFieldType: InputFieldType = InputFieldType.Default,
    onClick: () -> Unit = {},
    @DrawableRes trailingIcon: Int? = null,
    @DrawableRes leadingIcon: Int? = null,
    placeHolder: String? = null,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .clip(shape = KoTheme.koShapes.inputField)
            .border(width = 0.dp, color = Color.Black, shape = KoTheme.koShapes.inputField)
            .height(KoTheme.koDimensions.inputFieldHeight)
            .clickable {
                if (inputFieldType == InputFieldType.Disabled) {
                    onClick.invoke()
                }
            },
        value = text,
        onValueChange = onValueChange,
        textStyle = KoTheme.koTypography.inputField,
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            disabledContainerColor = Color.White
        ),
        singleLine = true,
        readOnly = inputFieldType == InputFieldType.Disabled,
        enabled = inputFieldType != InputFieldType.Disabled,
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    tint = KoTheme.koColors.textStandard,
                    modifier = Modifier.size(KoTheme.koDimensions.icon)
                )
            }
        },
        trailingIcon = {
            trailingIcon?.let {
                Icon(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = null,
                    tint = KoTheme.koColors.textStandard
                )
            }
        },
        placeholder = {
            placeHolder?.let {
                Text(text = it, style = KoTheme.koTypography.inputField)
            }
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun InputFieldWithLabelPreview() {
    SearchInputField(text = "")
}

enum class InputFieldType {
    Default,
    Disabled
}