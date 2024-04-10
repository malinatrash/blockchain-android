package malinatrash.blockchain.viewModels.wallet

import android.content.Context
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
import androidx.compose.runtime.mutableStateOf
import malinatrash.blockchain.storage.repos.WalletRepository


class WalletViewModel(private val repository: WalletRepository) : ViewModel() {
    private val _walletState = MutableLiveData<WalletState>()
    val walletState: LiveData<WalletState> = _walletState

    private val walletService = retrofit.create(WalletService::class.java)

    var alertIsShown = mutableStateOf(false)

    val wallets = repository.getAllWallets()

    fun addWallet(wallet: WalletData) {
        viewModelScope.launch {
            repository.insertWallet(wallet)
        }
    }

    fun fetchWallet() {
        viewModelScope.launch {
            try {
                val wallet = withContext(Dispatchers.IO) {
                    walletService.getWallet()
                }

                _walletState.value = WalletState.Success(wallet)
                alertIsShown.value = true
            } catch (e: Exception) {
                _walletState.value = WalletState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}