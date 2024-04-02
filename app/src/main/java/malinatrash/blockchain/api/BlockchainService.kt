package malinatrash.blockchain.api

import malinatrash.blockchain.models.BlockchainData
import retrofit2.http.GET

interface BlockchainService {
    @GET("/chain")
    suspend fun getBlockchain(): BlockchainData
}