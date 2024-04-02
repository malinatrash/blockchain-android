import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import malinatrash.blockchain.components.BlockItem
import malinatrash.blockchain.viewModels.BlockchainState
import malinatrash.blockchain.models.Block
import malinatrash.blockchain.viewModels.BlockchainViewModel

@Composable
fun Blockchain(modifier: Modifier) {
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


