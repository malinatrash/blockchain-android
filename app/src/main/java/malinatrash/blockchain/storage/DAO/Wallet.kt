package malinatrash.blockchain.storage.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import malinatrash.blockchain.models.WalletData

@Dao
interface WalletDao {
    @Query("SELECT * FROM wallet")
    fun getAllWallets(): Flow<List<WalletData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWallet(wallet: WalletData)
}
