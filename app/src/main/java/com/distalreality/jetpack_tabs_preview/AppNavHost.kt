package com.distalreality.jetpack_tabs_preview

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@SuppressLint("ComposableNavGraphInComposeScope")
@Composable
fun AppNavHost(navController: NavHostController) {
    val selectedPerson = remember { MutableStateFlow<SelectedPerson?>(null) }
    val isPersonSelected = rememberSaveable {
        mutableStateOf(false)
    }

   LaunchedEffect(key1 = isPersonSelected.value) {
       if (isPersonSelected.value) {
           delay(350)
           selectedPerson.update {
               isPersonSelected.value = false
               null
           }
       }
   }

    NavHost(navController = navController, startDestination = Screens.TabPreview.route) {
        composable(route = Screens.TabPreview.route) {
            TabsPreview(navController, selectedPerson)
        }

        composable(
            route = "${Screens.TabDetail.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            ), enterTransition = {
                selectedPerson.value?.let {
                    TransformOrigin(it.adjustedWidth, it.adjustedHeight)
                }?.let { scaleIn(tween(100), transformOrigin = it) }
            }, exitTransition = {
                selectedPerson.value?.let {
                    TransformOrigin(it.adjustedWidth, it.adjustedHeight)
                }?.let {
                    isPersonSelected.value = true
                    fadeOut(
                        animationSpec = tween(
                            350, easing = LinearEasing
                        )
                    ) + scaleOut(tween(1000), transformOrigin = it)
                }
            }
        ) {
            selectedPerson.collectAsState().value?.let {
                TabPreviewDetail(navController = navController, id = it.person.id)
            }
        }
    }
}
