package malinatrash.blockchain.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import malinatrash.blockchain.models.Block

@Composable
fun BlockItem(block: Block) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Block ID: ${block.id}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Previous Hash: ${block.previousHash}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Proof: ${block.proof}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Timestamp: ${block.timestamp}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            block.transactions?.let { transactions ->
                Text(
                    text = "Transactions:",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                transactions.forEach { transaction ->
                    Text(
                        text = "Sender: ${transaction.sender}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Recipient: ${transaction.recipient}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Amount: ${transaction.amount}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}