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
    val designedBy: List<String>,
    val developer: List<String>,
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

        "coffeescript" -> Res.drawable.coffeescript
        "dart" -> Res.drawable.dart
        "elixir" -> Res.drawable.elixir
        "fsharp" -> Res.drawable.fsharp
        "groovy" -> Res.drawable.groovy
        "objective-c" -> Res.drawable.objective_c
        "perl" -> Res.drawable.perl
        "scala" -> Res.drawable.scala
        "smalltalk" -> Res.drawable.smalltalk
        "swift" -> Res.drawable.swift
        "typescript" -> Res.drawable.typescript
        "vbnet" -> Res.drawable.vbnet
        else -> Res.drawable.kotlin
    }

    fun getColors() = when (logo) {

        "c" -> listOf("#055088", "#5C9BCC", "#74ACDE", "#BDD4E7").toColors()
        "cpp" -> listOf("#064E87", "#5C9BCC", "#74ACD9", "#74ACD9").toColors()
        "csharp" -> listOf("#874286", "#CCA4C4", "#CCA4C4").toColors()
        "golang" -> listOf("#00A9D3", "#00AAD3").toColors()
        "java" -> listOf("#4C839C", "#EC741C").toColors()
        "javascript" -> listOf("#FBDC43", "#D4BC3C", "#DCC13C").toColors()
        "kotlin" -> listOf("#7f52ff", "#c711e1", "#e44857").toColors()
        "php" -> listOf("#47586F", "#5A7BB1", "#245494", "#6490CC").toColors()
        "python" -> listOf("#FCD054", "#FCC44C", "#2C7CAC", "#2C72A0").toColors()
        "ruby" -> listOf("#A21F0E", "#CB2D1B", "#E4513C").toColors()
        "rust" -> listOf("#8C1C1B", "#E43414", "#E33414", "#D6361C").toColors()

        "coffeescript" -> listOf("#CFD0D7","#283048", "#30344C").toColors()
        "dart" -> listOf("#045B93", "#1384BE", "#04BAF7", "#43C4F4").toColors()
        "elixir" -> listOf("#39194F", "#654A73", "#90759F", "#A58EBC", "#B4A3BA").toColors()
        "fsharp" -> listOf("#248BB4", "#2890C4", "#2890C4", "#04B4D4").toColors()
        "groovy" -> listOf("#548494", "#5C96A6", "#64AEC5").toColors()
        "perl" -> listOf("#606186", "#6A6C7F", "#818294", "#CFD0D7").toColors()
        "objective-c" -> listOf("#CFD0D7", "#005A97", "#005B97").toColors()
        "scala" -> listOf("#821B23", "#A42C2C", "#E7433E", "#EC3C34").toColors()
        "smalltalk" -> listOf("#305C5E", "#D8D7C2", "#D1C470", "#82BFD7").toColors()
        "swift" -> listOf("#ED4028", "#EF6C3D", "#F48767", "#F9A14B", "#F8C098").toColors()
        "typescript" -> listOf("#2474C4", "#3684C4", "#0668B7", "#8BB4DC").toColors()
        "vbnet" -> listOf("#04588C", "#3C7CA4", "#7CACC4", "#2E74A4").toColors()

        else -> listOf(Color.Red, Color.Blue)
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
