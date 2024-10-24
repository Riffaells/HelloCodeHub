package com.riffaells.hellocodehub.domain.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
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

    fun getColors() = when (logo) {

        "kotlin" -> listOf("#7f52ff", "#c711e1", "#e44857").toColors()
        "python" -> listOf("#387EB8", "#366994", "#FFE052", "#FFC331").toColors()

        else -> emptyList<Color>()
    }

    private fun List<String>.toColors() = map {
        hexToColor(it)
    }

    companion object {
        fun hexToColor(hex: String): Color {
            return if (hex.length == 7) {
                // Преобразуем строку без альфа-канала (RRGGBB)
                val red = hex.substring(1, 3).toInt(16)
                val green = hex.substring(3, 5).toInt(16)
                val blue = hex.substring(5, 7).toInt(16)
                Color(red, green, blue)
            } else {
                // Преобразуем строку с альфа-каналом (RRGGBBAA)
                val red = hex.substring(1, 3).toInt(16)
                val green = hex.substring(3, 5).toInt(16)
                val blue = hex.substring(5, 7).toInt(16)
                val alpha = hex.substring(7, 9).toInt(16)
                Color(red, green, blue, alpha)
            }
        }
    }
}
