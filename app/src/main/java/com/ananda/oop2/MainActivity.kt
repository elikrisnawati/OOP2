package com.ananda.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_mahasiswa.setOnClickListener{
            val intent = Intent(this, MahasiswaActivity::class.java)
            startActivity(intent)
        }

        button_dosen.setOnClickListener{
            val intent = Intent(this, DosenActivity::class.java)
            startActivity(intent)
        }

        button_tugas.setOnClickListener{
            val intent = Intent(this, TugasActivity::class.java)
            startActivity(intent)
        }
    }

}