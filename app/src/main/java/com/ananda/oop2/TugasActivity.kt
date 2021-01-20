package com.ananda.oop2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_tugas.*

class TugasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tugas)

        val database = FirebaseDatabase.getInstance()

        var  myRef : DatabaseReference? = database.getReference("homeworks")

        // Read Data
        myRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // looping ketika mengambil data
                val dataArray = ArrayList<Tugas>()
                for (i in dataSnapshot.children){
                    val data = i.getValue(Tugas::class.java)
                    data?.key = i.key
                    data?.let { dataArray.add(it) }
                }
                rvListTugas.adapter = TugasAdapter(dataArray, object : TugasAdapter.OnClick {
                    override fun edit(tugas: Tugas?) {
                        val intent = Intent(this@TugasActivity, FormTugasActivity::class.java)
                        intent.putExtra("tugas", tugas)
                        startActivity(intent)
                    }

                    override fun delete(key: String?) {
                        AlertDialog.Builder(this@TugasActivity).apply {
                            setTitle("Hapus ?")
                            setPositiveButton("Ya") { dialogInterface: DialogInterface, i: Int ->
                                myRef?.child(key.toString())?.removeValue()
//                                Toast.makeText(this@MainActivity, key, Toast.LENGTH_SHORT).show()
                            }
                            setNegativeButton("Tidak", { dialogInterface: DialogInterface, i: Int -> })
                        }.show()
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException())
            }
        })

        btAddTugas.setOnClickListener {
            startActivity(Intent(this@TugasActivity, FormTugasActivity::class.java))
        }
    }
}