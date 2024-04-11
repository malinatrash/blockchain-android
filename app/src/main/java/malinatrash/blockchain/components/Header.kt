import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import malinatrash.blockchain.storage.LocalStorage
import malinatrash.blockchain.viewModels.wallet.WalletState
import malinatrash.blockchain.viewModels.wallet.WalletViewModel

@Composable
fun Header() {
    val context = LocalContext.current
    val localStorage = LocalStorage(context)
    val viewModel = WalletViewModel(localStorage)


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Blockchain", style = MaterialTheme.typography.titleLarge)

        when (viewModel.alertIsShown.value) {
            true -> AlertDialog(
                onDismissRequest = {
                    viewModel.alertIsShown.value = false
                },
                title = { Text(text = "Wallet fetched successfully") },
                text = { Text(text = "Welcome ${(viewModel.walletState.value as WalletState.Success).data.address}") },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.alertIsShown.value = false
                        }
                    ) {
                        Text(text = "OK")
                    }
                }
            )

            false -> Unit
        }

        when (viewModel.walletState.value) {
            is WalletState.Loading -> {
                Text(text = "Loading...")
            }
            is WalletState.Success -> {

                Text(text = "Balance: 0")

            }
            is WalletState.Error -> {
                Text(text = "Error: ${(viewModel.walletState.value as WalletState.Error).message}")
            }
            else -> {
                val onClick = {
                    viewModel.fetchWallet()
                }
                MyButton(onClick = onClick, text = "Create")
            }
        }
    }
}


