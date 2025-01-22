package com.example.common.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Status {
    NEW, IN_PROGRESS, CLOSED
}