package com.distalreality.jetpack_tabs_preview

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
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
    selectedPerson: (id: Int, xFraction: Float, yFraction: Float) -> Unit
) {
    var position by remember { mutableStateOf<Offset?>(null) }
    val paddingValues = PaddingValues(horizontal = 5.dp, vertical = 10.dp)
    val screenSize = LocalContext.current.resources.displayMetrics

    Card(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .background(Color.Transparent)
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
                    selectedPerson(person.id, adjustedWidth, adjustedHeight)
                }
            }
    ) {
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Card(
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = person.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = person.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W900,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = person.profession,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun TabsPreview(selectedPerson: (Int, Float, Float) -> Unit, navController: NavController) {
    val data = getPersonList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(data) { person ->
            CardWithClickPosition(
                person = person,
                selectedPerson = selectedPerson
            )
        }
    }
}

fun getPersonList() = listOf(
    Person(0,R.drawable.baseline_person_2_24, name = "Name1", profession = "SE1"),
    Person(1,R.drawable.baseline_person_2_24, name = "Name2", profession = "SE2"),
    Person(2,R.drawable.baseline_person_2_24, name = "Name3", profession = "SE3"),
    Person(3,R.drawable.baseline_person_2_24, name = "Name4", profession = "SE4"),
    Person(4,R.drawable.baseline_person_2_24, name = "Name5", profession = "SE5"),
    Person(5,R.drawable.baseline_person_2_24, name = "Name6", profession = "SE6"),
    Person(6,R.drawable.baseline_person_2_24, name = "Name7", profession = "SE7"),
    Person(7,R.drawable.baseline_person_2_24, name = "Name8", profession = "SE8"),
    Person(8,R.drawable.baseline_person_2_24, name = "Name9", profession = "SE9"),
    Person(9,R.drawable.baseline_person_2_24, name = "Name10", profession = "SE10"),
    Person(10,R.drawable.baseline_person_2_24, name = "Name11", profession = "SE11")
)