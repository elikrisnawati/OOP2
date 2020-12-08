package com.ananda.oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananda.oop2.Database.Mahasiswa
import com.ananda.oop2.Database.MahasiswaRoomDatabase
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_edit.button_save
import kotlinx.android.synthetic.main.activity_edit2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity2 : AppCompatActivity() {

    val db by lazy { MahasiswaRoomDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit2)
        setupListener()
    }

    fun setupListener(){
        button_save.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.mahasiswaDao().addMahasiswa(
                    Mahasiswa(0, Integer.parseInt(edit_nim.text.toString()), edit_nama2.text.toString(), edit_prodi.text.toString())
                )
                finish()
            }
        }
    }
}