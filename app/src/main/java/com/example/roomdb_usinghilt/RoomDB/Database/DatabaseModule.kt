package com.example.roomdb_usinghilt.RoomDB.Database

import android.content.Context
import androidx.room.Room
import com.example.roomdb_usinghilt.RoomDB.Doa.UserDao
import com.example.roomdb_usinghilt.RoomDB.Entities.RoomEntities
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(database: UserDatabase): UserDao {
        return database.userDao()
    }
}