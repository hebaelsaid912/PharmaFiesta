package com.example.pharmafiesta.ui.chatbot.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.chatbot.model.MessageModal

class MessageRVAdapter(
    private val messageModalArrayList: ArrayList<MessageModal>,
    private val context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        // Switch layout type along with view holder.
        return when (viewType) {
            0 -> {
                // Inflating user message layout.
                view = LayoutInflater.from(parent.context).inflate(R.layout.user_msg, parent, false)
                UserViewHolder(view)
            }

            1 -> {
                // Inflating bot message layout.
                view = LayoutInflater.from(parent.context).inflate(R.layout.bot_msg, parent, false)
                BotViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Set data to the layout file.
        val modal = messageModalArrayList[position]
        when (modal.sender) {
            "user" -> {
                // Set text to the text view of user layout.
                (holder as UserViewHolder).userTV.text = modal.message
            }

            "bot" -> {
                // Set text to the text view of bot layout.
                (holder as BotViewHolder).botTV.text = modal.message
            }
        }
    }

    override fun getItemCount(): Int {
        // Return the size of the array list.
        return messageModalArrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        // Set position.
        return when (messageModalArrayList[position].sender) {
            "user" -> 0
            "bot" -> 1
            else -> -1
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Variable for the text view.
        val userTV: TextView = itemView.findViewById(R.id.idTVUser)
    }

    class BotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Variable for the text view.
        val botTV: TextView = itemView.findViewById(R.id.idTVBot)
    }
}
