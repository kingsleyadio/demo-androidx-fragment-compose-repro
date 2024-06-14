package com.example.demo_androidx_fragment_compose_repro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.fragment.compose.content
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.demo_androidx_fragment_compose_repro.ui.theme.DemoandroidxfragmentcomposereproTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {
        DemoandroidxfragmentcomposereproTheme {
            Scaffold { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    val state by viewModel.state.collectAsState()
                    Column(Modifier.padding(16.dp)) {
                        Text("Value: $state")
                        Spacer(Modifier.size(32.dp))
                        Button(viewModel::increment) { Text("Increment") }
                        Button(onClick = { findNavController().navigate("detail") }) {
                            Text("Open Detail")
                        }
                    }
                }
            }
        }
    }
}

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {
        DemoandroidxfragmentcomposereproTheme {
            Scaffold { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    Text("Hello world")
                }
            }
        }
    }
}

class MainViewModel : ViewModel() {

    val state = MutableStateFlow(0)

    fun increment() = state.update { it + 1 }
}
