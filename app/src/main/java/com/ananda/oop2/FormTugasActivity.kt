package com.ananda.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_form_tugas.*

class FormTugasActivity : AppCompatActivity() {
    var tugas: Tugas? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_tugas)

        val data = intent.getSerializableExtra("tugas")
        var edit = true

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("homeworks")

        if (data != null) {
            tugas = data as Tugas
            etTugasNameEdit.setText(tugas?.mk)
            etTugasDescriptionEdit.setText(tugas?.tugas)

            btActForm.setText("Edit")
        } else {
            btActForm.setText("Tambah")
            edit = false
        }

        btActForm.setOnClickListener {
            if (edit) {
                val changeData = HashMap<String, Any>()
                changeData.put("mk", etTugasNameEdit.text.toString())
                changeData.put("tugas", etTugasDescriptionEdit.text.toString())

                myRef.child(tugas?.key.toString()).updateChildren(changeData)
                finish()
                startActivity(Intent(this, TugasActivity::class.java))
            } else {
                val key = myRef.push().key

                val newTugas = Tugas()
                newTugas.mk = etTugasNameEdit.text.toString()
                newTugas.tugas = etTugasDescriptionEdit.text.toString()

                myRef.child(key.toString()).setValue(newTugas)
                finish()
                startActivity(Intent(this, TugasActivity::class.java))
            }
        }
    }
}