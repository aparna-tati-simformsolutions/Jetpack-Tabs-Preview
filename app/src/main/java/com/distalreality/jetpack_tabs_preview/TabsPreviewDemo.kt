package com.distalreality.jetpack_tabs_preview

import android.os.Parcelable
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.collectAsState
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    var id: Int,
    val profile: Int,
    val name: String,
    val profession: String
) : Parcelable

data class SelectedPerson(val person: Person, val adjustedWidth: Float, val adjustedHeight: Float)

fun getPersonList() = listOf(
    Person(0,R.drawable.avatar_1, name = "Name1", profession = "SE1"),
    Person(1,R.drawable.avatar_2, name = "Name2", profession = "SE2"),
    Person(2,R.drawable.avatar_3, name = "Name3", profession = "SE3"),
    Person(3,R.drawable.avatar_4, name = "Name4", profession = "SE4"),
    Person(4,R.drawable.avatar_5, name = "Name5", profession = "SE5"),
    Person(5,R.drawable.avatar_6, name = "Name6", profession = "SE6"),
    Person(6,R.drawable.avatar_7, name = "Name7", profession = "SE7"),
    Person(7,R.drawable.avatar_8, name = "Name8", profession = "SE8"),
    Person(8,R.drawable.avatar_9, name = "Name9", profession = "SE9"),
    Person(9,R.drawable.avatar_10, name = "Name10", profession = "SE10"),
    Person(10,R.drawable.avatar_11, name = "Name11", profession = "SE11"),
    Person(11,R.drawable.avatar_12, name = "Name12", profession = "SE12"),
    Person(12,R.drawable.avatar_13, name = "Name13", profession = "SE13"),
    Person(13,R.drawable.avatar_14, name = "Name14", profession = "SE14")
)

fun getPerson(id: Int): Person = getPersonList().find { it.id == id }!!

@Composable
fun CardWithClickPosition(
    person: Person,
    selectedPersonState: StateFlow<SelectedPerson?>,
    navController: NavController,
    isBackgroundIndicatorShown: MutableStateFlow<SelectedPerson?>
) {
    var position by remember { mutableStateOf<Offset?>(null) }
    val screenSize = LocalContext.current.resources.displayMetrics

    Card(
        modifier = Modifier
            .alpha(if (selectedPersonState.collectAsState().value?.person?.id == person.id) 0.0f else 1.0f)
            .padding(paddingValues = PaddingValues(horizontal = 5.dp, vertical = 5.dp))
            .onGloballyPositioned {
                val card = it.boundsInParent()
                position = Offset(card.center.x, card.center.y)
            }
            .clickable {
                position?.let {
                    val adjustedHeight = ((it.y * 100) / screenSize.heightPixels) / 100
                    val adjustedWidth = ((it.x * 100) / screenSize.widthPixels) / 100
                    (selectedPersonState as MutableStateFlow<SelectedPerson?>).value =
                        SelectedPerson(person, adjustedWidth, adjustedHeight)
                    isBackgroundIndicatorShown.value =
                        SelectedPerson(person, adjustedWidth, adjustedHeight)
                    navController.navigate("${Screens.TabDetail.route}/${person.id}") {
                        popUpTo(Screens.TabPreview.route) {
                            saveState = true
                        }
                    }
                }
            },
        colors = if (isBackgroundIndicatorShown.collectAsState().value?.person?.id == person.id) {
            CardDefaults.cardColors(Color.LightGray)
        } else {
            CardDefaults.cardColors(Color.White)
        },
        border = BorderStroke(1.dp, color = if (isBackgroundIndicatorShown.collectAsState().value?.person?.id == person.id) Color.White else Color.Black)
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
            text = if (isBackgroundIndicatorShown.collectAsState().value?.person?.id == person.id) {
                person.name + " " + "Selected"
            } else {
                person.name + " " + "Not Selected"
            },
            fontSize = 14.sp,
            fontWeight = FontWeight.W700,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun TabsPreview(navController: NavController, selectedPerson: MutableStateFlow<SelectedPerson?>, isBackgroundIndicatorShown: MutableStateFlow<SelectedPerson?>) {
    val data = getPersonList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(data) { person ->
            CardWithClickPosition(
                person = person,
                selectedPerson,
                navController,
                isBackgroundIndicatorShown
            )
        }
    }
}
