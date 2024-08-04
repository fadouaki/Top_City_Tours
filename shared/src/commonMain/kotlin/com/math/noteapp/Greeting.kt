package com.math.noteapp

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello 23, ${platform.name}!"
    }
}