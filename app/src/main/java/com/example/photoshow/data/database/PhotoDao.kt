package com.example.photoshow.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("SELECT * FROM Photo")
    fun getAllPhotos(): Flow<List<Photo>>

    @Query("SELECT COUNT(id) FROM photo")
    suspend fun photosCount(): Int

    @Query("SELECT COUNT(idToDelete) FROM DeletePhoto")
    suspend fun photosIdsCount(): Int

    @Query("SELECT * FROM Photo WHERE id = :id")
    fun findById(id: Int): Flow<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(movies: List<Photo>)

    @Query("DELETE FROM Photo WHERE id = :id")
    suspend fun deletePhotoById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoId(photosId: DeletePhoto)

    @Query("SELECT * FROM DeletePhoto")
    fun getPhotosIdsToDelete(): Flow<List<DeletePhoto>>

    @Query("DELETE FROM DeletePhoto WHERE idToDelete = :id")
    suspend fun deletePhotoId(id: Int)
}
