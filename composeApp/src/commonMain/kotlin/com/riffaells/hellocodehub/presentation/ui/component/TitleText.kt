package com.riffaells.hellocodehub.presentation.ui.component

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.riffaells.hellocodehub.presentation.theme.RIcons
import org.jetbrains.compose.resources.painterResource

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    title: String,
    style: TextStyle = MaterialTheme.typography.headlineMedium,
    content: @Composable () -> Unit
) {

    var visible by remember { mutableStateOf(true) }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .clickable {
                visible = !visible
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .padding(start = 4.dp, top = 8.dp, bottom = 8.dp),
            text = title,
            style = style,
            fontWeight = FontWeight.Bold,
        )

        Image(
            modifier = Modifier
                .padding(8.dp),
            imageVector = if (visible) RIcons.Visibility else RIcons.VisibilityOff,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
    }

    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(),
        exit = shrinkVertically(

        )
    ) {
        content()
    }
}

