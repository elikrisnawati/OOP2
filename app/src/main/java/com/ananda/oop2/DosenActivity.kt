package com.ananda.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ananda.oop2.Database.MahasiswaRoomDatabase
import kotlinx.android.synthetic.main.activity_dosen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DosenActivity : AppCompatActivity() {

    val db by lazy { MahasiswaRoomDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dosen)
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val allDosen = db.dosenDao().getAllDosen()
            Log.d("DosenActivity", "dbResponse: $allDosen")
        }
    }

    fun setupListener() {
        button_create.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }
    }
}