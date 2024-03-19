package com.distalreality.jetpack_tabs_preview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.distalreality.jetpack_tabs_preview.ui.theme.JetpackTabsPreviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTabsPreviewTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
