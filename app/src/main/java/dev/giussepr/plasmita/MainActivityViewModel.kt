package dev.giussepr.plasmita

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainActivityViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun onIntent(intent: ViewIntent) {
        when (intent) {
            is ViewIntent.Increment -> {
                _uiState.update {
                    it.copy(counter = it.counter + 1)
                }
            }
        }
    }

}

data class UiState(
    val counter: Int = 0
)

sealed interface ViewIntent {
    data object Increment : ViewIntent
}
