package com.example.tweetsy.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tweetsy.R
import com.example.tweetsy.viewmodels.CategoryViewModel

//@Preview
@Composable
fun CategoryScreen(onClick : (category : String) -> Unit) {
    /*
    When using Navigation Compose, always use the hiltViewModel composable
     function to obtain an instance of your @HiltViewModel annotated ViewModel.
     This works with fragments or activities that are annotated with @AndroidEntryPoint.
     */
    val cateViewModel: CategoryViewModel = hiltViewModel()
    val categories: State<List<String>> =
        cateViewModel.category.collectAsState()  // collecting as state because every time when category changes we want to render the compose

    if(categories.value.isEmpty()){
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", color = Color.Blue)
        }
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), 
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ){
        items(categories.value.distinct()){
            CategoryItem(category = it, onClick)
        }
    }

}


@Composable
fun CategoryItem(category: String , onClick : (category : String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClick(category)
            }
            .paint(
                painter = painterResource(id = R.drawable.category_view),
                contentScale = ContentScale.Crop
            )
            .border(3.dp, Color.Blue),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = category,
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}