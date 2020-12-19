package com.ananda.oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ananda.oop2.Database.Mahasiswa
import kotlinx.android.synthetic.main.adapter_mahasiswa.view.*

class MahasiswaAdapter (private val AllMahasiswa: ArrayList<Mahasiswa>, private val listener: OnAdapterListener) : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder >() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        return MahasiswaViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_mahasiswa, parent, false)
        )
    }

    override fun getItemCount() = AllMahasiswa.size

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val mahasiswa = AllMahasiswa[position]
        holder.view.text_nama2.text = mahasiswa.nama
        holder.view.text_nama2.setOnClickListener {
            listener.onClick(mahasiswa)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(mahasiswa)
        }
    }

    class MahasiswaViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Mahasiswa>) {
        AllMahasiswa.clear()
        AllMahasiswa.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(mahasiswa: Mahasiswa)
        fun onDelete(mahasiswa: Mahasiswa)
    }
}