package malinatrash.blockchain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import malinatrash.blockchain.ui.theme.BlockchainTheme
import malinatrash.blockchain.views.Home
import malinatrash.blockchain.views.WalletInfo


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlockchainApp()
        }
    }
}

@Composable
fun BlockchainApp() {
    val navController = rememberNavController()

    BlockchainTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),

            color = Color(0xffa2fdd9)
        ) {
            NavHost(navController, startDestination = "home") {
                composable("home") { Home() }
                composable("wallet") { WalletInfo() }
            }
        }
    }
}
