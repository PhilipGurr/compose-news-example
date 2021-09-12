package com.philipgurr.composenews.data

import com.philipgurr.composenews.domain.NewsPost
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

inline fun <reified T> T.toNavArg(): String {
    val jsonString = Json.encodeToString(this)
    return Base64.getEncoder().encodeToString(jsonString.toByteArray())
}

inline fun <reified T> String.fromNavArg(): T {
    val json = String(Base64.getDecoder().decode(this))
    return Json.decodeFromString(json)
}