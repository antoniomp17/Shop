package com.mp98.cabifychallenge.core.data.di

import android.content.Context
import androidx.room.Room
import com.mp98.cabifychallenge.core.data.database.AppDao
import com.mp98.cabifychallenge.core.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "CartDDBB"
        ).createFromAsset("CartDDBB.db").build()
    }

    @Provides
    @Singleton
    fun provideAppDao(db: AppDatabase): AppDao {
        return db.dao
    }

}