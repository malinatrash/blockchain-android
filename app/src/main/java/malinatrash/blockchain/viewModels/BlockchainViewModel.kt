package malinatrash.blockchain.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import malinatrash.blockchain.models.BlockchainData
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface BlockchainService {
    @GET("/chain")
    suspend fun getBlockchain(): BlockchainData
}

sealed class BlockchainState {
    data object Loading : BlockchainState()
    data class Success(val data: BlockchainData) : BlockchainState()
    data class Error(val message: String) : BlockchainState()
}

class BlockchainViewModel : ViewModel() {
    private val _blockchainState = MutableLiveData<BlockchainState>()
    val blockchainState: LiveData<BlockchainState> = _blockchainState

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://92.51.45.202:8080")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

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