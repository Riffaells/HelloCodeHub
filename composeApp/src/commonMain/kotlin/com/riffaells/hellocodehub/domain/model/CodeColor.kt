package com.riffaells.hellocodehub.domain.model

import androidx.compose.ui.graphics.Color

data class CodeColor(
    val defaultColor: Color = Color(0xFFABB2BF),
    val background: Color = Color(0xFF282C34),
    val keywords: Color = Color(0xFFC678DD),
    val function: Color = Color(0xFF61AFEF),
    val strings: Color = Color(0xFF98C379),
    val nums: Color = Color(0xFFE5C07B),
    val static: Color = Color(0xFFE06C75),
) {
}