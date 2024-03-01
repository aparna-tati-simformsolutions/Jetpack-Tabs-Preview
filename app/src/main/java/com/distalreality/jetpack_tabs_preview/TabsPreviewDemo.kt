package com.distalreality.jetpack_tabs_preview

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int,
    val profile: Int,
    val name: String,
    val profession: String
) : Parcelable

@Composable
fun CardWithClickPosition(
    person: Person,
    viewModel: TabPreviewViewModel,
    navController: NavController
) {
    var position by remember { mutableStateOf<Offset?>(null) }
    val paddingValues = PaddingValues(horizontal = 5.dp, vertical = 5.dp)
    val screenSize = LocalContext.current.resources.displayMetrics

    Card(
        modifier = Modifier
            .alpha(if (viewModel.selectedPerson.value?.first == person.id) 0.0f else 1.0f)
            .padding(paddingValues = paddingValues)
            .onGloballyPositioned {
                val card = it.boundsInParent()
                position = Offset(card.center.x, card.center.y)
            }
            .clickable {
                position?.let {
                    val height = screenSize.heightPixels
                    val width = screenSize.widthPixels
                    val adjustedHeight = ((it.y * 100) / height) / 100
                    val adjustedWidth = ((it.x * 100) / width) / 100
                    viewModel.selectPerson(person, adjustedWidth, adjustedHeight)
                    navController.navigate("${Screens.TabDetail.route}/${viewModel.selectedPerson.value?.first}") {
                        popUpTo(Screens.TabPreview.route) {
                            saveState = true
                            inclusive = false
                        }
                        restoreState = true
                    }
                }
            },
        colors = if (viewModel.selectedPerson.value?.first == person.id) {
            CardDefaults.cardColors(Color.Magenta)
        } else {
            CardDefaults.cardColors(Color.LightGray)
        }
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = person.profile),
            contentDescription = "Profile",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = person.profession,
            fontSize = 16.sp,
            fontWeight = FontWeight.W900,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = if (viewModel.selectedPerson.value?.first == person.id) {
                person.name + "Selected"
            } else {
                person.name + "Not Selected"
            },
            fontSize = 14.sp,
            fontWeight = FontWeight.W700,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun TabsPreview(viewModel: TabPreviewViewModel, navController: NavController) {
    val data = viewModel.getPersonList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(data) { person ->
            CardWithClickPosition(
                person = person,
                viewModel = viewModel,
                navController
            )
        }
    }
}
