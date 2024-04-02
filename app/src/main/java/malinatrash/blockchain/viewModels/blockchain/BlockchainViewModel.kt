package malinatrash.blockchain.viewModels.blockchain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import malinatrash.blockchain.api.BlockchainService
import malinatrash.blockchain.api.retrofit

class BlockchainViewModel : ViewModel() {
    private val _blockchainState = MutableLiveData<BlockchainState>()
    val blockchainState: LiveData<BlockchainState> = _blockchainState

    private val blockchainService = retrofit.create(BlockchainService::class.java)

    fun fetchBlockchain() {
        _blockchainState.value = BlockchainState.Loading
        viewModelScope.launch {
            try {
                val blockchain = withContext(Dispatchers.IO) {
                    blockchainService.getBlockchain()
                }
                _blockchainState.value = BlockchainState.Success(blockchain)
                println(_blockchainState.value)
            } catch (e: Exception) {
                _blockchainState.value =
                    BlockchainState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}