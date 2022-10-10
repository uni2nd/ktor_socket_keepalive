package com.example.myapplication

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Host {

    suspend fun start() {
        hostnames().forEach { host ->
            val serverSocket = aSocket(SelectorManager(Dispatchers.IO)).tcp().bind(host, 9002)
            CoroutineScope(Dispatchers.IO).launch {
                val socket = serverSocket.accept()
                socket.openReadChannel().awaitContent()
                println("Couldn't detect disconnection")
            }
        }
    }

}