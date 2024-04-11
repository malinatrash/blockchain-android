package malinatrash.blockchain.viewModels.wallet

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import malinatrash.blockchain.api.WalletService
import malinatrash.blockchain.api.retrofit
import malinatrash.blockchain.models.WalletData
import malinatrash.blockchain.storage.LocalStorage


class WalletViewModel(private val localStorage: LocalStorage) : ViewModel() {
    private val _walletState = MutableLiveData<WalletState>()
    val walletState: LiveData<WalletState> = _walletState

    private val walletService = retrofit.create(WalletService::class.java)

    var alertIsShown = mutableStateOf(false)

    init {
        val savedAddress = localStorage.getAddress()
        if (!savedAddress.isNullOrEmpty()) {
            _walletState.value = WalletState.Success(WalletData(savedAddress))
        }
    }

    fun fetchWallet() {
        viewModelScope.launch {
            try {
                val wallet = withContext(Dispatchers.IO) {
                    walletService.getWallet()
                }

                _walletState.value = WalletState.Success(wallet)
                localStorage.saveAddress(wallet.address)
                alertIsShown.value = true
            } catch (e: Exception) {
                _walletState.value = WalletState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}
