package com.example.lunchtray.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun StartOrder(onStartOrderPressed: ()->Unit){

    Column( verticalArrangement= Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(5.dp).fillMaxSize()){
        Button(modifier = Modifier.fillMaxWidth().padding(start =54.dp,end = 54.dp) ,
            onClick ={onStartOrderPressed()}
        ){
            Text("Start Order")
        }
    }

}