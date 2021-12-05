package com.example.kmmapplication

import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val osVersion: String = UIDevice.currentDevice.systemVersion
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + osVersion
}