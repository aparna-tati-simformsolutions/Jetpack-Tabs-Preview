package com.distalreality.jetpack_tabs_preview

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@SuppressLint("ComposableNavGraphInComposeScope")
@Composable
fun AppNavHost(navController: NavHostController, viewModel: TabPreviewViewModel) {
    NavHost(navController = navController, startDestination = Screens.TabPreview.route) {
        composable(route = Screens.TabPreview.route) {
            TabsPreview(viewModel = viewModel, navController)
        }

        composable(
            route = "${Screens.TabDetail.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            ), enterTransition = {
                viewModel.selectedPerson.value?.let {
                    TransformOrigin(
                        it.second, it.third)
                }?.let { scaleIn(tween(1000), transformOrigin = it) }
            }, exitTransition = {
                viewModel.selectedPerson.value?.let {
                    TransformOrigin(
                        it.second, it.third)
                }?.let {
                    viewModel.clearSelectedPerson()
                    fadeOut(
                        animationSpec = tween(
                            350, easing = LinearEasing
                        )
                    ) + scaleOut(tween(1000), transformOrigin = it)
                }
            }
        ) {
            viewModel.selectedPerson.value?.first?.let { it1 ->
                TabPreviewDetail(viewModel = viewModel, navController = navController, id = it1)
            }
        }
    }
}
