package com.distalreality.jetpack_tabs_preview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.distalreality.jetpack_tabs_preview.ui.theme.JetpackTabsPreviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTabsPreviewTheme {
                val navController = rememberNavController()
                val viewModel = viewModel<TabPreviewViewModel>()
                NavHost(
                    navController = navController,
                    startDestination = NavRoutes.TabPreviewRoute.name
                ) {
                    tabPreviewGraph(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}

enum class NavRoutes {
    TabPreviewRoute
}
