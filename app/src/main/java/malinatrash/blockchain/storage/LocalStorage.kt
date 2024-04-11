package malinatrash.blockchain.storage

import android.content.Context

class LocalStorage(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val ADDRESS_KEY = "address"

    fun saveAddress(address: String) {
        sharedPreferences.edit().putString(ADDRESS_KEY, address).apply()
    }

    fun getAddress(): String? {
        return sharedPreferences.getString(ADDRESS_KEY, null)
    }
}
