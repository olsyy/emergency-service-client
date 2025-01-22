package com.example.core.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.common.response.*


@Composable
fun <T : Any> HandleContentState(
    state: ApiResponse<T>,
    renderContent: @Composable (T) -> Unit
) {
    when (state) {
        is Loading -> AppLoader()
        is Success -> renderContent(state.data)
        is Error -> {
            Text(text = "Error")
        }

        is Empty -> {
            Text(text = "Empty")
        }

        is Exception -> Text(text = "Exception")
    }
}