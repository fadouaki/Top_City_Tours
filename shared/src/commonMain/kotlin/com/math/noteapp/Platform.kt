package com.math.noteapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform