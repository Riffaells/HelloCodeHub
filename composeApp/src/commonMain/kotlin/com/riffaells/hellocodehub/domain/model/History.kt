package com.riffaells.hellocodehub.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class History(
    val nameOrigin: String,
    val developmentStart: String,
    val firstRelease: String,
) {
}