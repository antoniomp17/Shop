package com.mp98.shop.core.data.database.bbdd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mp98.shop.core.data.database.model.ProductCartEntity

@Database(
    entities =
    [
        ProductCartEntity::class,
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val dao: AppDao
}