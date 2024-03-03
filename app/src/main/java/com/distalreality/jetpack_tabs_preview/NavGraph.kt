package com.distalreality.jetpack_tabs_preview

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument

@SuppressLint("ComposableNavGraphInComposeScope")
fun NavGraphBuilder.tabPreviewGraph(navController: NavController, viewModel: TabPreviewViewModel) {
    navigation(
        startDestination = Screens.TabPreview.route,
        route = NavRoutes.TabPreviewRoute.name
    ) {
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
