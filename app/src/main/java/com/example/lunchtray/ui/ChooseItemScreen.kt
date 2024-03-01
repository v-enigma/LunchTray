package com.example.lunchtray.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchtray.Model.MenuItem


@Composable
fun ChooseItem(

    onCancelSelected:()->Unit,
    onNextSelected:()->Unit,
    onItemSelected:(MenuItem)->Unit,
    items: List<MenuItem>

){
    var selectedValue: MenuItem? by rememberSaveable{ mutableStateOf( null) }
    Column {

        items.forEach {
            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                RadioButton(
                    selected = it == selectedValue,
                    onClick = {  selectedValue = it ; onItemSelected(it)}
                )
                Column(modifier = Modifier.padding(5.dp)) {
                    Text( text =it.name,  fontWeight = FontWeight.Bold, modifier = Modifier.padding(5.dp), fontSize = 20.sp)
                    Text(it.description, modifier = Modifier.padding(5.dp))
                    Text("$${it.price}", modifier = Modifier.padding(top =1.dp , bottom = 5.dp))
                    Divider(modifier = Modifier.height(2.dp))
                }
            }

        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            OutlinedButton(onClick ={onCancelSelected()} ) {
                Text(modifier = Modifier.padding(start = 25.dp, end = 25.dp), text = "CANCEL")
            }
            Button( onClick ={onNextSelected()}, enabled = selectedValue?.name?.isNotEmpty()?: false) {
                Text(modifier = Modifier.padding(start = 25.dp, end = 25.dp), text = "NEXT")
            }

        }


    }


}