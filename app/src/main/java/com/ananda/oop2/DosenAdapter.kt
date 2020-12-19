package com.ananda.oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ananda.oop2.Database.Dosen
import kotlinx.android.synthetic.main.adapter_dosen.view.*

class DosenAdapter (private val AllDosen: ArrayList<Dosen>, private val listener: OnAdapterListener) : RecyclerView.Adapter<DosenAdapter.DosenViewHolder >() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DosenViewHolder {
        return DosenViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_dosen, parent, false)
        )
    }

    override fun getItemCount() = AllDosen.size

    override fun onBindViewHolder(holder: DosenViewHolder, position: Int) {
        val dosen = AllDosen[position]
        holder.view.text_nama.text = dosen.nama
        holder.view.text_nama.setOnClickListener {
            listener.onClick(dosen)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(dosen)
        }
    }

    class DosenViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Dosen>) {
        AllDosen.clear()
        AllDosen.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(dosen: Dosen)
        fun onDelete(dosen: Dosen)
    }
}