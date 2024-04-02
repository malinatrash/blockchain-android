package malinatrash.blockchain.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import malinatrash.blockchain.models.WalletData
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface WalletService {
    @GET("/wallet")
    suspend fun getWallet(): WalletData
}

sealed class WalletState {
    object Loading : WalletState()
    data class Success(val data: WalletData) : WalletState()
    data class Error(val message: String) : WalletState()
}

class WalletViewModel : ViewModel() {
    private val _walletState = MutableLiveData<WalletState>()
    val walletState: LiveData<WalletState> = _walletState

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://92.51.45.202:8080")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val walletService = retrofit.create(WalletService::class.java)

    fun fetchWallet() {
        _walletState.value = WalletState.Loading
        viewModelScope.launch {
            try {
                val wallet = withContext(Dispatchers.IO) {
                    walletService.getWallet()
                }
                _walletState.value = WalletState.Success(wallet)
            } catch (e: Exception) {
                _walletState.value = WalletState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}
