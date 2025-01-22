package com.example.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Type {
    FIRE, FLOOD,  NATURAL_DISASTER, GASLEAK, OTHER
}