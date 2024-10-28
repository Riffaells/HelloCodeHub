package com.riffaells.hellocodehub.domain.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import hellocodehub.composeapp.generated.resources.*
import hellocodehub.composeapp.generated.resources.Res
import hellocodehub.composeapp.generated.resources.kotlin
import hellocodehub.composeapp.generated.resources.python
import hellocodehub.composeapp.generated.resources.rust
import kotlinx.serialization.Serializable

@Immutable
@Serializable()
data class ProgrammingLanguage(
    val logo: String,
    val name: String,
    val url: String = "",
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
    val community: Map<String, String>,
    val future: String,
) {

    fun getLogo() = when (logo) {
        "cpp" -> Res.drawable.cpp
        "golang" -> Res.drawable.golang
        "kotlin" -> Res.drawable.kotlin
        "python" -> Res.drawable.python
        "rust" -> Res.drawable.rust

        "csharp" -> Res.drawable.csharp
        "java" -> Res.drawable.java
        "javascript" -> Res.drawable.javascript
        "php" -> Res.drawable.php
        "ruby" -> Res.drawable.ruby
        "c" -> Res.drawable.c
        else -> Res.drawable.kotlin
    }

    fun getColors() = when (logo) {

        "cpp" -> listOf("#064E87", "#5C9BCC", "#74ACD9", "#74ACD9").toColors()
        "golang" -> listOf("#00A9D3", "#00AAD3").toColors()
        "kotlin" -> listOf("#7f52ff", "#c711e1", "#e44857").toColors()
        "python" -> listOf("#FCD054", "#FCC44C", "#2C7CAC", "#2C72A0").toColors()
        "rust" -> listOf("#B02C14", "#E33414", "#D6361C").toColors()


        "csharp" -> listOf("#874286", "#CCA4C4", "#CCA4C4").toColors()
        "java" -> listOf("#4C839C", "#EC741C").toColors()
        "javascript" -> listOf("#FBDC43", "#938629", "#FCFB4B").toColors()
        "php" -> listOf("#47586F","#5A7BB1", "#245494", "#6490CC").toColors()
        "ruby" -> listOf("#A21F0E", "#CB2D1B", "#E4513C").toColors()
        "c" -> listOf("#055088", "#5C9BCC", "#74ACDE", "#BDD4E7").toColors()

        else -> listOf("#659ad2", "#00599c").toColors()
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
