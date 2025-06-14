package org.christophertwo.eva.ui.screens.eva

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.christophertwo.eva.chat.textfield.AITextField
import com.christophertwo.eva.chat.textfield.AITextFieldEvent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EvaRoot(
    viewModel: EvaViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    EvaScreen(
        state = state,
        onAction = viewModel::onAction,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun EvaScreen(
    state: EvaState,
    onEvent: (AITextFieldEvent) -> Unit,
    onAction: (EvaAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorScheme.background),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                content = {
                    AITextField(
                        value = state.valueTextFiled,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(16.dp),
                        onEvent = {
                            when(it) {
                                is AITextFieldEvent.OnTextChanged -> { onEvent(it) }
                                else -> {}
                            }
                        }
                    )
                }
            )
        }
    )
}