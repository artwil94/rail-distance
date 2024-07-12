package com.example.raildistance.composable

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.raildistance.domain.model.TrainStation
import com.example.raildistance.ui.theme.KoTheme

@ExperimentalMaterial3Api
@Composable
fun AutoCompleteSearchBar(
    modifier: Modifier = Modifier,
    items: List<TrainStation>,
    @DrawableRes trailingIcon: Int? = null,
    @DrawableRes leadingIcon: Int? = null,
    placeholder: String? = null,
    onItemClick: (TrainStation) -> Unit = {},
    onValueChange: (String) -> Unit,
    searchBarType: SearchBarType = SearchBarType.DropdownList,
) {
    var input by remember { mutableStateOf("") }
    val heightTextFields by remember { mutableStateOf(55.dp) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .then(modifier)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            )
    ) {
        TextField(
            modifier = Modifier
                .then(modifier)
                .height(heightTextFields)
                .clip(shape = KoTheme.koShapes.inputField)
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .focusRequester(FocusRequester())
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            value = input,
            onValueChange = {
                input = it
                expanded = true
                onValueChange.invoke(it)
            },
            placeholder = {
                if (placeholder != null) {
                    Text(
                        text = placeholder,
                        style = KoTheme.koTypography.searchBarPlaceHolder
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                disabledContainerColor = Color.White
            ),
            textStyle = KoTheme.koTypography.inputField,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
            singleLine = true,
            trailingIcon = {
                if (input.isNotBlank() && isFocused) {
                    trailingIcon?.let {
                        Icon(
                            painter = painterResource(id = trailingIcon),
                            contentDescription = null,
                            tint = KoTheme.koColors.textStandard,
                            modifier = Modifier
                                .size(KoTheme.koDimensions.searchBarCloseIcon)
                                .clickable {
                                    input = ""
                                }
                        )
                    }
                }
            },
            leadingIcon = {
                leadingIcon?.let {
                    Icon(
                        painter = painterResource(id = leadingIcon),
                        contentDescription = null,
                        tint = KoTheme.koColors.textStandard,
                        modifier = Modifier.size(KoTheme.koDimensions.icon)
                    )
                }
            }
        )
        if (searchBarType == SearchBarType.DropdownList) {
            DropdownItemList(
                items = items,
                expanded = expanded,
                modifier = Modifier.width(textFieldSize.width.dp),
                onItemClick = { station ->
                    input = station.name
                    onItemClick.invoke(station)
                    focusManager.clearFocus()
                    expanded = false
                })
        }
    }
}

@Composable
fun DropdownItemList(
    items: List<TrainStation>,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onItemClick: (TrainStation) -> Unit
) {
    AnimatedVisibility(visible = expanded) {
        Card(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .then(modifier),
            shape = RoundedCornerShape(10.dp)
        ) {
            LazyColumn(
                modifier = Modifier.heightIn(max = 150.dp),
            ) {
                items(items)
                { trainStation ->
                    SearchItem(title = trainStation.name) { title ->
                        onItemClick.invoke(trainStation)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchItem(
    title: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }
}

enum class SearchBarType {
    DropdownList,
    WithoutContent
}