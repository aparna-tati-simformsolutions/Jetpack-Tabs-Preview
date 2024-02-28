package com.distalreality.jetpack_tabs_preview

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TabPreviewDetail(viewModel: TabPreviewViewModel, navController: NavController, id: Int) {
    val person = viewModel.getPerson(id)

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.baseline_receipt_24),
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    navController.popBackStack(route = Screens.TabPreview.route, inclusive = false, saveState = true)
                }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = person.profile),
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .size(200.dp)
                .shadow(10.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = person.name,
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.W900,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = person.profession,
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.W700,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}