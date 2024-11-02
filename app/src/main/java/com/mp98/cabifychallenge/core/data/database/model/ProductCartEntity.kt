package com.mp98.cabifychallenge.core.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductCartEntity")
data class ProductCartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val code: String
)