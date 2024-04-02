package malinatrash.blockchain.viewModels.wallet

import malinatrash.blockchain.models.WalletData

sealed class WalletState {
    data object Loading : WalletState()
    data class Success(val data: WalletData) : WalletState()
    data class Error(val message: String) : WalletState()
}