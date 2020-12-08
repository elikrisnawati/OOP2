package com.ananda.oop2.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ananda.oop2.Database.Mahasiswa

@Dao
interface MahasiswaDao {
    @Query("SELECT * FROM mahasiswa")
    fun getAll(): LiveData<List<Mahasiswa>>

    @Query("SELECT * FROM mahasiswa WHERE nim IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Mahasiswa>

    @Query("SELECT * FROM mahasiswa WHERE nama LIKE :nama AND " +
            "prodi LIKE :prodi LIMIT 1")
    fun findByName(nama: String, prodi: String): Mahasiswa

    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    suspend fun insert(mahasiswa: Mahasiswa)

    @Delete
    fun delete(mahasiswa: Mahasiswa)
}
