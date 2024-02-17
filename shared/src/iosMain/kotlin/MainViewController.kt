import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import feature.main.MainScreen

fun MainViewController() = ComposeUIViewController { Navigator(MainScreen) }
