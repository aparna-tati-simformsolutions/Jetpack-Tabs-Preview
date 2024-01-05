package com.distalreality.jetpack_tabs_preview

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
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
fun NavGraphBuilder.tabPreviewGraph(navController: NavController) {
    var xValue = 0f
    var yValue = 0f

    val selectedPerson: (Int, Float, Float) -> Unit = { personId: Int, x: Float, y: Float ->
        xValue = x
        yValue = y
        navController.navigate("${Screens.TabDetail.route}/$personId")
    }

    navigation(
        startDestination = Screens.TabPreview.route,
        route = NavRoutes.TabPreviewRoute.name
    ) {
        composable(route = Screens.TabPreview.route) {
            TabsPreview(selectedPerson = selectedPerson, navController)
        }

        composable(
            route = "${Screens.TabDetail.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            ), enterTransition = {
                scaleIn(tween(1000), transformOrigin = TransformOrigin(xValue, yValue))
            }, exitTransition = {
                scaleOut(tween(1000), transformOrigin = TransformOrigin(xValue, yValue))
            }
        ) {
            val arguments = requireNotNull(it.arguments)
            TabPreviewDetail(navController = navController, id = arguments.getInt("id"))
        }
    }
}
