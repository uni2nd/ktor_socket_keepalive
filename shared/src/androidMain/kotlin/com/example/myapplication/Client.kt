package com.example.myapplication

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import kotlinx.coroutines.Dispatchers

class Client {

    suspend fun connect() {
        hostnames().forEach { host ->
            val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(host, 9002) {
                keepAlive = true
            }
            val writeChannel = socket.openWriteChannel(autoFlush = false)
            writeChannel.write {
                it.put("test".toByteArray())
            }
            writeChannel.flush()
        }
    }
}