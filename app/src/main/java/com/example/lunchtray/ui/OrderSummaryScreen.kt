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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchtray.Model.MenuItem
import com.example.lunchtray.Model.OrderUiState
import com.example.lunchtray.R
import java.text.NumberFormat


@Composable
fun OrderSummary(
    onSubmitSelected: ()->Unit,
    uiState: OrderUiState,
    onCancelSelected:() ->Unit
){
    Column(Modifier.padding(start = 20.dp, end = 20.dp)){
        Text("Order Summary", fontWeight = FontWeight.Bold, fontSize = 30.sp)
        ItemSummary(uiState.entree, Modifier.fillMaxWidth())
        ItemSummary(uiState.sideDish, Modifier.fillMaxWidth())
        ItemSummary(uiState.accompaniment, Modifier.fillMaxWidth())
        Divider(modifier = Modifier.height(10.dp).padding(top = 3.dp, bottom = 5.dp))
        OrderSummary(text = R.string.subtotal, uiState.itemTotalPrice, Modifier,false)
        OrderSummary(text = R.string.tax, uiState.orderTax, Modifier,false)
        OrderSummary(text = R.string.total, uiState.orderTotalPrice,Modifier,true)
        Spacer(Modifier.height(15.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()){
            OutlinedButton( onClick ={onCancelSelected()}){
                Text( "Cancel".uppercase())
            }
            Button(onClick ={onSubmitSelected()}){
                Text("Submit".uppercase())
            }


        }
    }
}

@Composable
fun ItemSummary(item : MenuItem?, modifier : Modifier){
    Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = modifier.fillMaxWidth().padding(top= 8.dp,bottom = 8.dp)){
        Text(item?.name?:"", )
        Text(item?.getFormattedPrice() ?: "")
    }
}

@Composable
fun OrderSummary(text:Int, currency:Double, modifier: Modifier, isBold:Boolean){

       Text(textAlign = TextAlign.End, modifier = modifier.fillMaxWidth().padding(5.dp),text = stringResource( text, currency.formatPrice(), ), fontWeight =  if(isBold) FontWeight.Bold else FontWeight.Normal)

}
fun Double.formatPrice() :String = NumberFormat.getCurrencyInstance().format(this)