package malinatrash.blockchain.api

import malinatrash.blockchain.models.WalletData
import retrofit2.http.GET

interface WalletService {
    @GET("/wallet")
    suspend fun getWallet(): WalletData
}