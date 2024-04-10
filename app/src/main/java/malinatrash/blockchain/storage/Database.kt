package malinatrash.blockchain.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import malinatrash.blockchain.models.WalletData
import malinatrash.blockchain.storage.DAO.WalletDao

@Database(entities = [WalletData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao
}
