package com.project.swing.presentation.feed.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.swing.presentation.theme.Purple80
import com.project.swing.presentation.theme.PurpleGrey40
import kotlinx.coroutines.delay

@Composable
fun FeedSearchTextField(
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    readOnly: Boolean = false,
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester = FocusRequester(),
    setText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit = {},
    onSearchTextChange: (String) -> Unit = {}
) {
    val isFocused = remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose {
            focusManager.clearFocus()
        }
    }

    LaunchedEffect(setText) {
        delay(300L)
        onSearchTextChange(setText)
    }

    Column {
        OutlinedTextField(
            value = setText,
            onValueChange = {
                onValueChange(it)
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            placeholder = {
                Text(
                    text = placeHolder,
                    style = typography.bodyLarge,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .height(64.dp)
                .focusRequester(focusRequester)
                .clip(RoundedCornerShape(12.dp))
                .background(Purple80)
                .border(
                    width = 1.dp,
                    color = Purple80,
                    shape = RoundedCornerShape(12.dp)
                )
                .onFocusChanged {
                    isFocused.value = it.isFocused
                },
            maxLines = maxLines,
            singleLine = singleLine,
            textStyle = typography.bodyMedium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = PurpleGrey40
            ),
            trailingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            readOnly = readOnly,
            visualTransformation = visualTransformation
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FeedSearchTextFieldPreView() {
    FeedSearchTextField(setText = "검색..") {}
}