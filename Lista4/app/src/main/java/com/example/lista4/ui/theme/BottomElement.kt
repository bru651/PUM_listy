package com.example.lista4.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomElement (
    val route: String,
    val title: String,
    val icon: ImageVector
)
{
    object E1 : BottomElement(
        route = "r1",
        title = "Listy",
        icon = Icons.Default.MailOutline
    )

    object E2 : BottomElement(
        route = "r2",
        title = "Przedmioty",
        icon = Icons.Default.Star
    )
    object E3 : BottomElement(
        route = "r3",
        title = "aaaaaaaaa",
        icon = Icons.Default.Send
    )
}