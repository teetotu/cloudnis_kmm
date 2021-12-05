package com.example.kmmapplication

expect class Platform() {
    val osVersion: String
    val platform: String
}