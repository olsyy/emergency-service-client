package com.example.officersclient.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.domain.entities.Zone
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ZoneNavType {

    val ZoneType = object : NavType<Zone>(
        isNullableAllowed = false
    ) {
        override fun get(
            bundle: Bundle,
            key: String,
        ): Zone? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Zone {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Zone): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: Zone,
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}