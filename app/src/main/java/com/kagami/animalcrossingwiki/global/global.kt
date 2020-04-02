package com.kagami.animalcrossingwiki.global

fun String.splitAsIntList() =
    split(" ")
    .map { it.toIntOrNull() ?: 0 }