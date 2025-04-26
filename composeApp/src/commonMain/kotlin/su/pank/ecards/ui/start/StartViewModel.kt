package su.pank.ecards.ui.start

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class StartViewModel: ViewModel() {

    var name: String by mutableStateOf("")
}