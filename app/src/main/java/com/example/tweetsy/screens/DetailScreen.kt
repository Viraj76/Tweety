package com.example.tweetsy.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tweetsy.viewmodels.TweetsViewModel

@Composable
fun DetailScreen(){
    val tweetsViewModel : TweetsViewModel= viewModel()
    val tweets  = tweetsViewModel.tweets.collectAsState()
    LazyColumn(
        content = {
            items(tweets.value){
                TweeListItem(tweets = it.text)
            }
        }
    )
}

@Composable
fun TweeListItem(tweets : String){
    Card(
        modifier = Modifier
            .padding(7.dp)
            .fillMaxWidth(),
        content = {
            Text(
                text = tweets,
                fontSize = 14.sp,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(15.dp)
            )
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp)
    )
}