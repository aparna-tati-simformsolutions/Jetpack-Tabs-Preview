package com.distalreality.jetpack_tabs_preview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.distalreality.jetpack_tabs_preview.ui.theme.JetpackTabsPreviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTabsPreviewTheme {
//                HomeScreen()
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

//@Composable
//fun HomeScreen() {
////    val data = getPersonList()
////
////    LazyColumn {
////        items(data) {
////            TabPreviewContainer(modifier = Modifier, person = it)
////        }
////    }
//}
//
//@Composable
//fun TabPreviewContainer(modifier: Modifier = Modifier, person: Person) {
//    var personID = 0
//    var containerState by remember { mutableStateOf(TabPreviewState.TabPreview) }
//    val transition = updateTransition(containerState, label = "container transform")
//    val animatedColor by transition.animateColor(
//        label = "color",
//    ) { state ->
//        when (state) {
//            TabPreviewState.TabPreview -> MaterialTheme.colorScheme.primaryContainer
//            TabPreviewState.FullTabPreview -> MaterialTheme.colorScheme.surface
//        }
//    }
//
//    val cornerRadius by transition.animateDp(
//        label = "corner radius",
//        transitionSpec = {
//            when (targetState) {
//                TabPreviewState.TabPreview -> tween(
//                    durationMillis = 400,
//                    easing = EaseOutCubic,
//                )
//
//                TabPreviewState.FullTabPreview -> tween(
//                    durationMillis = 200,
//                    easing = EaseInCubic,
//                )
//            }
//        }
//    ) { state ->
//        when (state) {
//            TabPreviewState.TabPreview -> 22.dp
//            TabPreviewState.FullTabPreview -> 0.dp
//        }
//    }
//
//    val elevation by transition.animateDp(
//        label = "elevation",
//        transitionSpec = {
//            when (targetState) {
//                TabPreviewState.TabPreview -> tween(
//                    durationMillis = 400,
//                    easing = EaseOutCubic,
//                )
//
//                TabPreviewState.FullTabPreview -> tween(
//                    durationMillis = 200,
//                    easing = EaseOutCubic,
//                )
//            }
//        }
//    ) { state ->
//        when (state) {
//            TabPreviewState.TabPreview -> 6.dp
//            TabPreviewState.FullTabPreview -> 0.dp
//        }
//    }
//    val padding by transition.animateDp(
//        label = "padding",
//    ) { state ->
//        when (state) {
//            TabPreviewState.TabPreview -> 16.dp
//            TabPreviewState.FullTabPreview -> 0.dp
//        }
//    }
//
//    transition.AnimatedContent(
//        contentAlignment = Alignment.Center,
//        modifier = modifier
//            .padding(end = padding, bottom = padding)
//            .shadow(
//                elevation = elevation,
//                shape = RoundedCornerShape(cornerRadius)
//            )
//            .drawBehind { drawRect(animatedColor) },
//        transitionSpec = {
//            (
//                    fadeIn(animationSpec = tween(220, delayMillis = 90)) +
//                            scaleIn(initialScale = 0.92f, animationSpec = tween(220, delayMillis = 90))
//                    )
//                .togetherWith(fadeOut(animationSpec = tween(90)))
//                .using(SizeTransform(clip = false, sizeAnimationSpec = { _, _ ->
//                    tween(
//                        durationMillis = 500,
//                        easing = FastOutSlowInEasing
//                    )
//                }))
//        }
//    ) { state ->
//        when (state) {
//            TabPreviewState.TabPreview -> {
//                TabPreview(
//                    onClick = { personId ->
//                        personID = personId
//                        containerState = TabPreviewState.FullTabPreview
//                    },
//                    person = person
//                )
//            }
//
//            TabPreviewState.FullTabPreview -> {
////                TabPreviewDetail(
////                    onBack = {
////                        containerState = TabPreviewState.TabPreview
////                    },
////                    id = personID
////                )
//            }
//        }
//    }
//}

enum class NavRoutes {
    TabPreviewRoute
}
//
//enum class TabPreviewState {
//    TabPreview,
//    FullTabPreview
//}
//
//@Composable
//fun TabPreview(modifier: Modifier = Modifier, onClick: (Int) -> Unit, person: Person) {
//
//    val paddingValues = PaddingValues(horizontal = 5.dp, vertical = 10.dp)
//
//    Card(
//        modifier = modifier
//            .padding(paddingValues = paddingValues)
//            .background(Color.Transparent)
//            .clickable {
//                onClick(person.id)
//            }
//    ) {
//        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
//            Card(
//                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = person.profile),
//                    contentDescription = "Profile",
//                    modifier = Modifier
//                        .size(100.dp)
//                        .align(Alignment.CenterHorizontally)
//                )
//
//                Text(
//                    text = person.name,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.W900,
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                Text(
//                    text = person.profession,
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.W700,
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun TabPreviewDetail(onBack: () -> Unit, id: Int) {
//    val person = getPerson(id)
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        Column(modifier = Modifier.fillMaxSize()) {
//            Image(
//                painter = painterResource(id = R.drawable.baseline_receipt_24),
//                contentDescription = null,
//                modifier = Modifier
//                    .padding(10.dp)
//                    .align(Alignment.CenterHorizontally)
//                    .clickable {
//                        onBack()
//                    }
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Image(
//                painter = painterResource(id = person.profile),
//                contentDescription = null,
//                modifier = Modifier
//                    .padding(10.dp)
//                    .size(200.dp)
//                    .shadow(10.dp)
//                    .align(Alignment.CenterHorizontally)
//            )
//
//            Text(
//                text = person.name,
//                fontSize = 24.sp,
//                fontFamily = FontFamily.Serif,
//                fontWeight = FontWeight.W900,
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Text(
//                text = person.profession,
//                fontSize = 18.sp,
//                fontFamily = FontFamily.Serif,
//                fontWeight = FontWeight.W700,
//                fontStyle = FontStyle.Italic,
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
//        }
//    }
//}