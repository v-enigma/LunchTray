package com.example.lunchtray.ui

import androidx.lifecycle.ViewModel
import com.example.lunchtray.Model.MenuItem
import com.example.lunchtray.Model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel: ViewModel() {
      private val taxPercentage = 0.01
      private var _uiState : MutableStateFlow<OrderUiState> = MutableStateFlow( OrderUiState())
      val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

      fun setEntreeItem(entreeItem: MenuItem.EntreeItem){
          val updatedPrice = getUpdatedPrice(entreeItem.price)

          _uiState.update { currentState ->  currentState.copy(entree = entreeItem , itemTotalPrice = updatedPrice) }

      }
      fun setSideDishItem(sideDishItem: MenuItem.SideDishItem){
            val updatedPrice = getUpdatedPrice(sideDishItem.price)
            _uiState.update { currentState -> currentState.copy(sideDish = sideDishItem, itemTotalPrice = updatedPrice) }
      }

    fun setAccompaniment(accompanimentItem: MenuItem.AccompanimentItem){
           val updatedPrice = getUpdatedPrice(accompanimentItem.price)
           _uiState.update { currentState -> currentState.copy(accompaniment = accompanimentItem, itemTotalPrice = updatedPrice) }
    }
    private fun getUpdatedPrice(amount:Double):Double{
        return _uiState.value.itemTotalPrice+amount
    }
    fun resetUiState(){
        _uiState.update{currentState ->
             currentState.copy(entree = null, sideDish = null, accompaniment = null, itemTotalPrice = 0.0, orderTax = 0.0, orderTotalPrice = 0.0)

        }
    }
    fun setMenuItemState(menuItem: MenuItem){
        when(menuItem){
            is MenuItem.EntreeItem -> setEntreeItem(menuItem)
            is MenuItem.SideDishItem -> setSideDishItem(menuItem)
            else  -> setAccompaniment(menuItem as MenuItem.AccompanimentItem)
        }

    }

    fun calculateTotalAmount(){
        val totalAmount = _uiState.value.itemTotalPrice
        val taxAmount = totalAmount* taxPercentage
        val amountAfterTax = totalAmount+ taxAmount
        _uiState.update{ currentState -> currentState.copy( orderTax = taxAmount, orderTotalPrice = amountAfterTax)}
    }
}