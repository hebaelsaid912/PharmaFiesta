package com.example.pharmafiesta.ui.chatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pharmafiesta.R
import com.example.pharmafiesta.ui.chatbot.adapter.MessageRVAdapter
import com.example.pharmafiesta.ui.chatbot.model.MessageModal
import com.example.pharmafiesta.ui.home.BottomNavigationActivity
import org.json.JSONException

class ActivityChatBot : AppCompatActivity() {

    // creating variables for our
    // widgets in xml file.
    private lateinit var chatsRV: RecyclerView
    private lateinit var sendMsgIB: ImageButton
    private lateinit var imgBack: ImageView
    private lateinit var userMsgEdt: EditText
    private val USER_KEY = "user"
    private val BOT_KEY = "bot"

    // creating a variable for
    // our volley request queue.
    private lateinit var mRequestQueue: RequestQueue

    // creating a variable for array list and adapter class.
    private var messageModalArrayList: ArrayList<MessageModal> = ArrayList()
    private lateinit var messageRVAdapter: MessageRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)

        // on below line we are initializing all our views.
        chatsRV = findViewById(R.id.idRVChats)
        sendMsgIB = findViewById(R.id.idIBSend)
        userMsgEdt = findViewById(R.id.idEdtMessage)
        imgBack = findViewById(R.id.imgBack)

        imgBack.setOnClickListener {
            val intent = Intent(this,BottomNavigationActivity::class.java)
            startActivity(intent)
            finish()
        }

        // below line is to initialize our request queue.
        mRequestQueue = Volley.newRequestQueue(this)
        mRequestQueue.cache.clear()

        // creating a new array list
        messageModalArrayList = ArrayList()

        // adding on click listener for send message button.
        sendMsgIB.setOnClickListener {
            // checking if the message entered
            // by the user is empty or not.
            if (userMsgEdt.text.toString().isEmpty()) {
                // if the edit text is empty display a toast message.
                Toast.makeText(this, "Please enter your message..", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // calling a method to send a message
            // to our bot to get a response.
            sendMessage(userMsgEdt.text.toString())

            // below line we are setting text in our edit text as empty
            userMsgEdt.setText("")
        }

        // on below line we are initializing our adapter class and passing our array list to it.
        messageRVAdapter = MessageRVAdapter(messageModalArrayList, this)

        // below line we are creating a variable for our linear layout manager.
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // below line is to set layout
        // manager to our recycler view.
        chatsRV.layoutManager = linearLayoutManager

        // below line we are setting
        // adapter to our recycler view.
        chatsRV.adapter = messageRVAdapter
    }

    private fun sendMessage(userMsg: String) {
        // below line is to pass a message to our
        // array list which is entered by the user.
        messageModalArrayList.add(MessageModal(userMsg, USER_KEY))
        messageRVAdapter.notifyDataSetChanged()

        // url for our brain
        // make sure to add mshape for uid.
        // make sure to add your URL.
        val uid = "user123"  // Replace with the actual user identifier

        // Construct the API URL with user-specific values
        val url = "http://api.brainshop.ai/get?bid=179311&key=Uzf0JbNWtjBxaouQ&uid=$uid&msg=$userMsg"

        // creating a variable for our request queue.
        val queue = Volley.newRequestQueue(this)

        // on below line we are making a json object request for a get request and passing our URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    // in on response method we are extracting data
                    // from json response and adding this response to our array list.
                    val botResponse = response.getString("cnt")
                    messageModalArrayList.add(MessageModal(botResponse, BOT_KEY))

                    // notifying our adapter as data changed.
                    messageRVAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()

                    // handling error response from the bot.
                    messageModalArrayList.add(MessageModal("No response", BOT_KEY))
                    messageRVAdapter.notifyDataSetChanged()
                }
            },
            {
                // error handling.
                messageModalArrayList.add(MessageModal("Sorry no response found", BOT_KEY))
                Toast.makeText(this, "No response from the bot..", Toast.LENGTH_SHORT).show()
            })

        // at last adding json object
        // request to our queue.
        queue.add(jsonObjectRequest)
    }

    override fun onBackPressed() {
        val intent = Intent(this,BottomNavigationActivity::class.java)
        startActivity(intent)
        finish()
    }
}