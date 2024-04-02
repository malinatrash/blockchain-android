package malinatrash.blockchain.models

data class BlockchainData(
    val chain: List<Block>,
    val length: Int
)

data class Block(
    val id: Int,
    val previousHash: String,
    val proof: Long,
    val timestamp: String,
    val transactions: List<Transaction>?
)

data class Transaction(
    val amount: Int,
    val recipient: String,
    val sender: String
)
