package com.example.nzheng2.myapplication.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nzheng2.myapplication.R
import com.example.nzheng2.myapplication.room.Person

class PersonListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<PersonListAdapter.PersonViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items = emptyList<Person>()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PersonViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, p0, false)
        return PersonViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: PersonViewHolder, p1: Int) {
        p0.name.text = items[p1].firstName
    }

    fun setItems(items: List<Person>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_name)
    }
}