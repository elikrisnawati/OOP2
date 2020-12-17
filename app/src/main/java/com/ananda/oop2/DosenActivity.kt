package com.ananda.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ananda.oop2.Database.Constant
import com.ananda.oop2.Database.Dosen
import com.ananda.oop2.Database.MahasiswaRoomDatabase
import kotlinx.android.synthetic.main.activity_dosen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DosenActivity : AppCompatActivity() {

    val db by lazy { MahasiswaRoomDatabase(this) }
    lateinit var dosenAdapter: DosenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dosen)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val allDosen = db.dosenDao().getAllDosen()
            Log.d("DosenActivity", "dbResponse: $allDosen")
            withContext(Dispatchers.Main) {
                dosenAdapter.setData(allDosen)
            }
        }
    }

    fun setupListener() {
        button_create.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        dosenAdapter = DosenAdapter(arrayListOf(), object: DosenAdapter.OnAdapterListener {
            override fun onClick(dosen: Dosen) {
                intentEdit(dosen.id, Constant.TYPE_READ)
            }

        })
        list_dosen.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = dosenAdapter
        }
    }

    fun intentEdit(helmId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id", helmId)
                .putExtra("intent_type", intentType)
        )
    }
}