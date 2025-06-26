package dev.giussepr.plasmita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.giussepr.plasmita.ui.theme.PlasmitaTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by lazy {
        MainActivityViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            PlasmitaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        StatelessCounter(
                            counter = uiState.counter,
                            onIncrement = {
                                viewModel.onIntent(ViewIntent.Increment)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatelessCounter(modifier: Modifier = Modifier, counter: Int, onIncrement: () -> Unit) {
    Button(onClick = onIncrement) {
        Text(
            text = "Counter: $counter",
            modifier = modifier
        )
    }
}