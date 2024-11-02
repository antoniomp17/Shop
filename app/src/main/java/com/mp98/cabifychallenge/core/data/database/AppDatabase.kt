package com.mp98.cabifychallenge.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mp98.cabifychallenge.core.data.model.ProductCartEntity

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