package malinatrash.blockchain.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import malinatrash.blockchain.storage.LocalStorage
import malinatrash.blockchain.viewModels.wallet.WalletState
import malinatrash.blockchain.viewModels.wallet.WalletViewModel

@Composable
fun WalletInfo() {
    val context = LocalContext.current
    val localStorage = LocalStorage(context)
    val viewModel = WalletViewModel(localStorage)
    when (viewModel.walletState.value) {
        is WalletState.Success -> {
            Text(text = "Your wallet address: ${(viewModel.walletState.value as WalletState.Success).data.address}")
        }
        else -> {
            Text(text = "Войдите в систему")
        }
    }
}