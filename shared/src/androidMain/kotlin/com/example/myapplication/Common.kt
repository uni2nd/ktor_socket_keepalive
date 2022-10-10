package com.example.myapplication

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.NetworkInterface

suspend fun hostnames(): List<String> {
    return withContext(Dispatchers.IO) {
        NetworkInterface.getNetworkInterfaces().toList().filter {
            !it.isVirtual && !it.isLoopback
        }.flatMap { networkInterface ->
            networkInterface.inetAddresses.toList().filter {
                !it.isLoopbackAddress && (it.isLinkLocalAddress || it.isSiteLocalAddress)
            }.map { inetAddress ->
                inetAddress.hostAddress
            }
        }
    }
}