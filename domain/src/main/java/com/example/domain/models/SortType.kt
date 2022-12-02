package com.example.domain.models

sealed class SortType(val name: String) {
    object Alphabet : SortType(Alphabet::class.java.simpleName)
    object ConfirmedAsc : SortType(ConfirmedAsc::class.java.simpleName)

    companion object {
        @JvmStatic
        fun valueOf(name: String): SortType {
            return when (name) {
                Alphabet.name -> Alphabet
                ConfirmedAsc.name -> ConfirmedAsc
                else -> {
                    throw IllegalArgumentException()
                }
            }
        }
        @JvmStatic
        fun nextSort(name: String): SortType {
            return when (name) {
                Alphabet.name -> ConfirmedAsc
                ConfirmedAsc.name -> Alphabet
                else -> {
                    throw IllegalArgumentException()
                }
            }
        }
    }
}