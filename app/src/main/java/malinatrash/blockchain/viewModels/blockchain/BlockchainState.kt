package malinatrash.blockchain.viewModels.blockchain

import malinatrash.blockchain.models.BlockchainData

sealed class BlockchainState {
    data object Loading : BlockchainState()
    data class Success(val data: BlockchainData) : BlockchainState()
    data class Error(val message: String) : BlockchainState()
}