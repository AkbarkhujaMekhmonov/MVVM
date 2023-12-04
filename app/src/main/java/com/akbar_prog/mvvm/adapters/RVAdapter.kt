package com.akbar_prog.mvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akbar_prog.mvvm.R
import com.akbar_prog.mvvm.database.entity.User

class RVAdapter(var list: List<User>): RecyclerView.Adapter<RVAdapter.MyViewHolder>() {
    inner class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(user: User) {

            val name = view.findViewById<TextView>(R.id.name)
            val contact = view.findViewById<TextView>(R.id.contact)
            name.text= "${user.name} ${user.username}"
            contact.text = "${user.phone} ${user.email}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)


        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVAdapter.MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}