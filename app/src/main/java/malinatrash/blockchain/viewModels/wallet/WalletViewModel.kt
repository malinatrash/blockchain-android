package malinatrash.blockchain.viewModels.wallet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import malinatrash.blockchain.api.WalletService
import malinatrash.blockchain.api.retrofit

class WalletViewModel : ViewModel() {
    private val _walletState = MutableLiveData<WalletState>()
//    val walletState: LiveData<WalletState> = _walletState

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
