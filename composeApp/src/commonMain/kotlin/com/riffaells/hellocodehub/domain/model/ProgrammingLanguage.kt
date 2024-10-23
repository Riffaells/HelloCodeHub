package com.riffaells.hellocodehub.domain.model

import androidx.compose.runtime.Immutable
import hellocodehub.composeapp.generated.resources.Res
import hellocodehub.composeapp.generated.resources.kotlin
import hellocodehub.composeapp.generated.resources.python
import kotlinx.serialization.Serializable

@Immutable
@Serializable()
data class ProgrammingLanguage(
    val logo: String,
    val name: String,
    val typeLang: Int,
    val description: String,
    val history: History,
    val code: String,
    val developmentStartYear: Int,
    val firstVersionYear: Int,
    val creators: List<String>,
    val designStyle: String,
    val fileExtensions: Map<String, String>,

    val paradigms: Paradigms,

    val typing: String,
    val useCases: List<String>,
    val compatibility: List<String>,
    val popularFrameworks: List<String>,
    val features: List<String>,
    val prosAndCons: ProsAndCons,
    val relatedLanguages: List<String>,
    val ideSupport: List<String>,
    val platforms: List<String>,
    val tooling: Map<String, List<String>>,
    val community: Map<String, String>,
    val future: String,
) {

    fun getLogo() = when (logo) {
        "kotlin" -> Res.drawable.kotlin
        "python" -> Res.drawable.python
        else -> Res.drawable.python
    }




}
