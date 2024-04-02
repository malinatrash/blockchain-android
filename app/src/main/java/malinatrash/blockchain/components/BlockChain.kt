import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import malinatrash.blockchain.components.BlockItem
import malinatrash.blockchain.viewModels.blockchain.BlockchainState
import malinatrash.blockchain.viewModels.blockchain.BlockchainViewModel

@Composable
fun Blockchain() {
    val blockchainViewModel: BlockchainViewModel = viewModel()
    val blockchainState: BlockchainState? by blockchainViewModel.blockchainState.observeAsState()

    LaunchedEffect(key1 = true) {
        blockchainViewModel.fetchBlockchain()
    }

    when (val state = blockchainState) {
        is BlockchainState.Loading -> {
            Text(text = "Loading...")
        }
        is BlockchainState.Success -> {
            val blockchainData = state.data
            LazyColumn {
                items(blockchainData.chain) { block ->
                    BlockItem(block = block)
                }
            }
        }
        is BlockchainState.Error -> {
            Text(text = "Error: ${state.message}")
        }
        else -> {
            Text(text = "Unknown state")
        }
    }
}


