package com.ananda.oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananda.oop2.Database.Dosen
import com.ananda.oop2.Database.MahasiswaRoomDatabase
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy { MahasiswaRoomDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupListener()
    }

    fun setupListener(){
        button_save.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.dosenDao().addDosen(
                    Dosen (0, Integer.parseInt(edit_nipy.text.toString()), edit_nama.text.toString(), edit_pengampu.text.toString()  )
                )
                finish()
            }
        }
    }
}