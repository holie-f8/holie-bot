package com.holiebot.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.holiebot.app.dao.ConversationDao
import com.holiebot.app.dao.NoteDao
import com.holiebot.app.entities.ConversationEntity
import com.holiebot.app.entities.NoteEntity

@Database(
    entities = [NoteEntity::class, ConversationEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun conversationDao(): ConversationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "holie_bot.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
