package fr.bmartel.pusherevents

import android.app.Application
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.PrivateChannel
import com.pusher.client.channel.PrivateChannelEventListener
import com.pusher.client.util.HttpAuthorizer

class PusherTestApplication : Application() {

    lateinit var pusher: Pusher
    lateinit var channel: PrivateChannel

    override fun onCreate() {
        super.onCreate()
        val options = PusherOptions()
        val authorizer = HttpAuthorizer(getString(R.string.auth_endpoint))
        options.authorizer = authorizer
        options.setCluster("eu")

        pusher = Pusher(getString(R.string.pusher_apikey), options)
        channel = pusher.subscribePrivate(getString(R.string.channel_name), object : PrivateChannelEventListener {
            override fun onEvent(p0: String?, p1: String?, p2: String?) {
            }

            override fun onAuthenticationFailure(p0: String?, p1: Exception?) {
                println("onAuthenticationFailure")
            }

            override fun onSubscriptionSucceeded(p0: String?) {
                println("onSubscriptionSucceeded")
                channel.bind(getString(R.string.web_event), object : PrivateChannelEventListener {
                    override fun onSubscriptionSucceeded(channelName: String) {
                        println("onSubscriptionSucceeded")
                    }

                    override fun onAuthenticationFailure(channelName: String, p1: Exception) {
                        println("onAuthenticationFailure")
                    }

                    override fun onEvent(channelName: String, eventName: String, data: String) {
                        println("${getString(R.string.web_event)} $data")
                    }
                })
            }
        })
        pusher.connect()
    }

    fun bindEvents(listener: PrivateChannelEventListener) {
        channel.bind(getString(R.string.android_event), listener)

    }

    fun unbindEvents(listener: PrivateChannelEventListener) {
        channel.unbind(getString(R.string.android_event), listener)
    }

    fun sendTrigger() {
        channel.trigger(getString(R.string.web_event), "{ \"data\": \"hello from android\"}")
    }
}