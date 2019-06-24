package fr.bmartel.pusherevents

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.pusher.client.channel.PrivateChannelEventListener


class MainActivity : AppCompatActivity() {

    lateinit var listView: ListView
    val messages = arrayListOf<String>()
    lateinit var handler: Handler
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.send_button).setOnClickListener {
            (application as PusherTestApplication).sendTrigger()
        }
        listView = findViewById(R.id.messages_list)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, messages)
        listView.adapter = adapter
        handler = Handler()
        (application as PusherTestApplication).bindEvents(eventListener)
    }

    val eventListener = object: PrivateChannelEventListener {
        override fun onSubscriptionSucceeded(channelName: String) {
            println("onSubscriptionSucceeded")
        }

        override fun onAuthenticationFailure(channelName: String, p1: Exception) {
            println("onAuthenticationFailure")
        }

        override fun onEvent(channelName: String, eventName: String, data: String) {
            messages.add("[client-android] $data")
            handler.post {
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as PusherTestApplication).unbindEvents(eventListener)
    }
}
