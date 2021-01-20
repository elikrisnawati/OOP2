package com.ananda.oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_tugas.view.*

class TugasAdapter(val tugas : ArrayList<Tugas>, val onClick : OnClick) : RecyclerView.Adapter<TugasAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tugas, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = tugas.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(tugas.get(position))
        holder.itemView.btDeleteTugas.setOnClickListener {
            onClick.delete(tugas.get(position).key)
        }
        holder.itemView.setOnClickListener {
            onClick.edit(tugas.get(position))
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tugas: Tugas){
            itemView.tvTugasName.text = tugas.mk
            itemView.tvTugasDescription.text = tugas.tugas
        }
    }

    interface OnClick {
        fun delete(key : String?)
        fun edit(tugas: Tugas?)
    }
}