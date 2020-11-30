package com.ananda.oop2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DosenDao {
    @Query("SELECT * FROM dosen")
    fun getAll(): List<Dosen>

    @Query("SELECT * FROM dosen WHERE nipy IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Dosen>

    @Query("SELECT * FROM dosen WHERE nama LIKE :nama AND " +
            "pengampu LIKE :pengampu LIMIT 1")
    fun findByName(nama: String, pengampu: String): Dosen

    @Insert
    fun insertAll(vararg dosen: Dosen)

    @Delete
    fun delete(dosen: Dosen)
}
