package malinatrash.blockchain.storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet")
data class Wallet(
    @PrimaryKey val id: Int,
    val address: String
)
