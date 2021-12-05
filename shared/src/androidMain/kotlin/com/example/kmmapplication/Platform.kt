package com.example.kmmapplication

actual class Platform actual constructor() {
    actual val osVersion: String = android.os.Build.VERSION.SDK_INT.toString()
    actual val platform: String = "Android $osVersion"
}