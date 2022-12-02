package com.example.domain.usecase

class OpenMap {

    fun execute(openMap: () -> Unit) {
        openMap()
    }
}