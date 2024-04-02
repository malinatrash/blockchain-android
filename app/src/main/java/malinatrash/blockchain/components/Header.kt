import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import malinatrash.blockchain.viewModels.BlockchainViewModel
import malinatrash.blockchain.viewModels.WalletViewModel

@Composable
fun Header() {
    val viewModel: WalletViewModel = viewModel()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Blockchain", style = MaterialTheme.typography.titleLarge)

        Row {
            val onClick = {
                viewModel.fetchWallet()
            }
            MyButton(onClick = onClick, text = "Create")
        }
    }
}
