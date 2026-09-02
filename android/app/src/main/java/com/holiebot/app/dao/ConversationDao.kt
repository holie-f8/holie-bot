package com.holiebot.app.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.holiebot.app.entities.ConversationEntity

@Dao
interface ConversationDao {
    @Insert
    suspend fun insert(msg: ConversationEntity)

    @Query("SELECT * FROM conversations ORDER BY timestamp ASC LIMIT :limit")
    suspend fun getRecent(limit: Int): List<ConversationEntity>

    @Query("DELETE FROM conversations")
    suspend fun deleteAll()
}
