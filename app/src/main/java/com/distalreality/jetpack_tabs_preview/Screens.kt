package com.distalreality.jetpack_tabs_preview

sealed class Screens(val route: String) {
    object TabPreview: Screens(route = "Tab_Preview")
    object TabDetail: Screens(route = "Tab_Detail")
}