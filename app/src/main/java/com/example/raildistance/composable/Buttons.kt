package com.example.raildistance.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.raildistance.ui.theme.KoTheme

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = KoTheme.koColors.actionButton,
    inverted: Boolean = false,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier
            .height(50.dp)
            .then(modifier),
        colors = ButtonDefaults.buttonColors(color),
        contentPadding = PaddingValues(
            start = 16.dp,
            top = 8.dp,
            end = 16.dp,
            bottom = 8.dp
        ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (leadingIcon != null) {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    tint = if (inverted) Color.White else KoTheme.koColors.textStandard
                )
                Spacer(modifier = Modifier.width(KoTheme.koDimensions.paddingXs))
            }
            Text(
                text = text.uppercase(),
                style = if (inverted) KoTheme.koTypography.actionButtonWhite else KoTheme.koTypography.actionButton
            )
            trailingIcon?.let {
                Spacer(modifier = Modifier.width(KoTheme.koDimensions.padding))
                Icon(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = text,
                    tint = if (inverted) Color.White else KoTheme.koColors.textStandard
                )
            }
        }
    }
}

@Preview
@Composable
fun ActionButtonPreview() {
    ActionButton(text = "Delete") {}
}