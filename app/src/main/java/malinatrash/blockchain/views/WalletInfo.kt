package malinatrash.blockchain.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import malinatrash.blockchain.viewModels.wallet.WalletState
import malinatrash.blockchain.viewModels.wallet.WalletViewModel

@Composable
fun WalletInfo() {
    val viewModel: WalletViewModel = viewModel()
    when (viewModel.walletState.value) {
        is WalletState.Success -> {
            Text(text = "Your wallet address: ${(viewModel.walletState.value as WalletState.Success).data.address}")
        }
        else -> {
            Text(text = "Войдите в систему")
        }
    }
}