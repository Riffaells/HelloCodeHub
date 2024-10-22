package com.riffaells.hellocodehub.domain.model

import hello_code_hub.composeapp.generated.resources.Res
import hello_code_hub.composeapp.generated.resources.kotlin
import hello_code_hub.composeapp.generated.resources.python
import kotlinx.serialization.Serializable


@Serializable
data class ProgrammingLanguage(
    val logo: String,
    val name: String,
    val description: String,
    val code: String,
    val developmentStartYear: Int,
    val firstVersionYear: Int,
    val creators: List<String>,
    val paradigms: List<String>,
    val typing: String,
    val useCases: List<String>,
    val compatibility: List<String>,
    val popularFrameworks: List<String>,
    val features: List<String>,
    val prosAndCons: ProsAndCons,
    val relatedLanguages: List<String>,
    val ideSupport: List<String>
) {

    fun getLogo() = when (logo) {
        "kotlin" -> Res.drawable.kotlin
        "python" -> Res.drawable.python
        else -> Res.drawable.python
    }




}
