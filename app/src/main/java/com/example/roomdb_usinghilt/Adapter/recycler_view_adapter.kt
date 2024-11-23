package com.example.roomdb_usinghilt.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb_usinghilt.R
import com.example.roomdb_usinghilt.RoomDB.Entities.RoomEntities
import javax.inject.Inject

class recycler_view_adapter @Inject constructor(private val context: Context) :
    RecyclerView.Adapter<recycler_view_adapter.myViewHolder>() {
    lateinit var dataList: List<RoomEntities>

    private var deleteRoomCallback: ((RoomEntities) -> Unit)? = null
    fun setData(dataList: List<RoomEntities>?) {
        if (dataList != null) {
            this.dataList = dataList
        }
    }

    fun setDeleteUserCallback(callback: (RoomEntities) -> Unit) {
        this.deleteRoomCallback = callback
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val email = itemView.findViewById<TextView>(R.id.email)
        val close = itemView.findViewById<TextView>(R.id.close)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.list_data_single_row_xml, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.name.text = dataList[position].name
        holder.email.text = dataList[position].email
        holder.close.setOnClickListener {
            if (deleteRoomCallback != null) {
                deleteRoomCallback!!.invoke(dataList[position])
            }
        }
    }
}