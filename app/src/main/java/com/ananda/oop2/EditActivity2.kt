package com.ananda.oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ananda.oop2.Database.Constant
import com.ananda.oop2.Database.Mahasiswa
import com.ananda.oop2.Database.MahasiswaRoomDatabase
import kotlinx.android.synthetic.main.activity_edit2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity2 : AppCompatActivity() {

    val db by lazy { MahasiswaRoomDatabase(this) }
    private var mahasiswaId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit2)
        setupListener()
        setupView()
    }

    fun setupListener(){
        button_save2.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.mahasiswaDao().addMahasiswa(
                    Mahasiswa(0, Integer.parseInt(edit_nim.text.toString()), edit_nama2.text.toString(), edit_prodi.text.toString())
                )
                finish()
            }
        }
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {

            }
            Constant.TYPE_READ -> {
                button_save2.visibility = View.GONE
                getDosen()
            }
        }
    }

    fun getDosen() {
        mahasiswaId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val mahasiswas =  db.mahasiswaDao().getMahasiswa( mahasiswaId )[0]
            edit_nim.setText( mahasiswas.nim.toString() )
            edit_nama2.setText( mahasiswas.nama )
            edit_prodi.setText( mahasiswas.prodi )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}