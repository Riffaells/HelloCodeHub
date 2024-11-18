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
        "c" -> Res.drawable.c
        "clojure" -> Res.drawable.clojure
        "coffeescript" -> Res.drawable.coffeescript
        "cpp" -> Res.drawable.cpp
        "csharp" -> Res.drawable.csharp
        "dart" -> Res.drawable.dart
        "delphi" -> Res.drawable.delphi
        "elixir" -> Res.drawable.elixir
        "erlang" -> Res.drawable.erlang
        "flow" -> Res.drawable.flow
        "fsharp" -> Res.drawable.fsharp
        "golang" -> Res.drawable.golang
        "groovy" -> Res.drawable.groovy
        "haskell" -> Res.drawable.haskell
        "java" -> Res.drawable.java
        "javascript" -> Res.drawable.javascript
        "kotlin" -> Res.drawable.kotlin
        "objective-c" -> Res.drawable.objective_c
        "ocaml" -> Res.drawable.ocaml
        "perl" -> Res.drawable.perl
        "php" -> Res.drawable.php
        "python" -> Res.drawable.python
        "ruby" -> Res.drawable.ruby
        "rust" -> Res.drawable.rust
        "scala" -> Res.drawable.scala
        "smalltalk" -> Res.drawable.smalltalk
        "swift" -> Res.drawable.swift
        "tcl" -> Res.drawable.tcl
        "typescript" -> Res.drawable.typescript
        "vbnet" -> Res.drawable.vbnet

        "assembly" -> Res.drawable.assembly
        "lisp" -> Res.drawable.lisp
        "lua" -> Res.drawable.lua
        "pascal" -> Res.drawable.pascal
        "reason" -> Res.drawable.reason
        "scheme" -> Res.drawable.scheme
        "sml" -> Res.drawable.sml
        "webassembly" -> Res.drawable.webassembly


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

        "coffeescript" -> listOf("#2f2625", "#2f2625", "#30344C").toColors()
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


        "clojure" -> listOf("#73D44B", "#42A333", "#76B865", "#4D80D6", "#1E52BC", "#83A2DE").toColors()
        "delphi" -> listOf("#9E1C16", "#D0221B", "#F15646", "#F18C81").toColors()
        "erlang" -> listOf("#A3143B", "#B42854", "#BC3C63", "#C7607F").toColors()
        "flow" -> listOf("#FC8B24", "#FC992D", "#FCB436", "#FCC43C", "#FCD05E", "#FCDC5F").toColors()
        "haskell" -> listOf("#915087", "#473C60", "#5F5182").toColors()
        "ocaml" -> listOf("#F45F1C", "#F47F25", "#FA9449", "#FCA454").toColors()
        "tcl" -> listOf("#095CAC", "#249CEC", "#FB4B33", "#F42C14", "#FCEB3E").toColors()


        "assembly" -> listOf("#3358A2", "#567CB4", "#7B93C4", "#8CACD4", "#C4CDE3").toColors()
        "lisp" -> listOf("#CA1F11", "#CB2011", "#CB1F12").toColors()
        "lua" -> listOf("#8C8CC4", "#5354A2", "#3C3C93", "#2C2E8C", "#04047B").toColors()
        "pascal" -> listOf("#060B7B", "#6062B4", "#5E5F7D").toColors()
        "reason" -> listOf("#DC3320", "#DC3C24", "#E4533B", "#EC7464", "#F7C4BE").toColors()
        "scheme" -> listOf("#1E4EDB", "#4685E2", "#7480E3", "#8CACEB", "#CED2F3").toColors()
        "sml" -> listOf("#9B7B7B", "#7B626F", "#C3C4C8").toColors()
        "webassembly" -> listOf("#6050E8", "#6854EC", "#6854EC").toColors()


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
