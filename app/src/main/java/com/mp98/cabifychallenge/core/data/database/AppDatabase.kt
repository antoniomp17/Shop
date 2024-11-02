package com.mp98.cabifychallenge.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mp98.cabifychallenge.core.data.model.ProductEntity

@Database(
    entities =
    [
        ProductEntity::class,
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val dao: AppDao
}