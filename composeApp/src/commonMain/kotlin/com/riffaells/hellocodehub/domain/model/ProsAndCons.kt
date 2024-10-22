package com.riffaells.hellocodehub.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProsAndCons(
    val pros: List<String>,
    val cons: List<String>
)