package com.ananda.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ananda.oop2.Database.Constant
import com.ananda.oop2.Database.Mahasiswa
import com.ananda.oop2.Database.MahasiswaRoomDatabase
import kotlinx.android.synthetic.main.activity_mahasiswa.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MahasiswaActivity : AppCompatActivity() {

    val db by lazy { MahasiswaRoomDatabase(this) }
    lateinit var mahasiswaAdapter: MahasiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswa)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val allMahasiswa = db.mahasiswaDao().getAllMahasiswa()
            Log.d("MahasiswaActivity", "dbResponse: $allMahasiswa")
            withContext(Dispatchers.Main) {
                mahasiswaAdapter.setData(allMahasiswa)
            }
        }
    }

    fun setupListener() {
        button_create2.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        mahasiswaAdapter = MahasiswaAdapter(arrayListOf(), object: MahasiswaAdapter.OnAdapterListener {
            override fun onClick(mahasiswa: Mahasiswa) {
                intentEdit(mahasiswa.id, Constant.TYPE_READ)
            }

        })
        list_mahasiswa.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mahasiswaAdapter
        }
    }

    fun intentEdit(mahasiswaId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditActivity2::class.java)
                .putExtra("intent_id", mahasiswaId)
                .putExtra("intent_type", intentType)
        )
    }
}