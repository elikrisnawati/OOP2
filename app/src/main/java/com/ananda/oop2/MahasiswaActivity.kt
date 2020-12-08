package com.ananda.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ananda.oop2.Database.MahasiswaRoomDatabase
import kotlinx.android.synthetic.main.activity_mahasiswa.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MahasiswaActivity : AppCompatActivity() {

    val db by lazy { MahasiswaRoomDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswa)
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val allMahasiswa = db.mahasiswaDao().getAllMahasiswa()
            Log.d("MahasiswaActivity", "dbResponse: $allMahasiswa")
        }
    }

    fun setupListener() {
        button_create2.setOnClickListener {
            startActivity(Intent(this, EditActivity2::class.java))
        }
    }
}