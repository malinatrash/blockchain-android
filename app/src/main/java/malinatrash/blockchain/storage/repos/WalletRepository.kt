package malinatrash.blockchain.storage.repos

import kotlinx.coroutines.flow.Flow
import malinatrash.blockchain.models.WalletData
import malinatrash.blockchain.storage.DAO.WalletDao

class WalletRepository(private val walletDao: WalletDao) {

    fun getAllWallets(): Flow<List<WalletData>> {
        return walletDao.getAllWallets()
    }

    suspend fun insertWallet(wallet: WalletData) {
        walletDao.insertWallet(wallet)
    }
}
