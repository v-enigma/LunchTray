package com.example.lunchtray.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.Model.MenuItem
import com.example.lunchtray.R
import com.example.lunchtray.dataSource.DataSource

enum class AppScreen(val title:Int){
    Start(title = R.string.start ),
    EntreeMenu(title = R.string.Entree),
    SideDishMenu(title = R.string.SideDish),
    AccompanimentMenu(title = R.string.Accompaniment),
    Summary(title = R.string.summary)

}
@Composable
fun LunchTrayApp(
    navController : NavHostController = rememberNavController(),
    viewModel: OrderViewModel = viewModel()
    ) {
    val backStackEntry  by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
                backStackEntry?.destination?.route ?: AppScreen.Start.name
    )
  Scaffold(
      topBar = { LunchTrayAppBar(currentScreen = currentScreen, canNavigateBack = currentScreen.name != AppScreen.Start.name,  onNavigate = {navController.navigateUp()})}
  ) { paddingValues ->
         val uiState by viewModel.uiState.collectAsState()
         NavHost(
             navController = navController,
             startDestination = AppScreen.Start.name,
             modifier = Modifier.padding(paddingValues)
         ){
             composable(AppScreen.Start.name){
                    StartOrder(){ navController.navigate( AppScreen.EntreeMenu.name) }
             }
             composable(AppScreen.EntreeMenu.name){
                     ChooseItem(
                         onCancelSelected = { cancelAndNavigate(viewModel, navController) },
                         onNextSelected = {navController.navigate(AppScreen.SideDishMenu.name) },
                         onItemSelected = { viewModel.setEntreeItem(it as MenuItem.EntreeItem)},
                         items = DataSource.entreeItems
                     )
             }
             composable(AppScreen.SideDishMenu.name){
                     ChooseItem(
                         onCancelSelected = { cancelAndNavigate(viewModel, navController) },
                         onNextSelected = {navController.navigate(AppScreen.AccompanimentMenu.name)},
                         onItemSelected = { viewModel.setSideDishItem(it as MenuItem.SideDishItem) },
                         items = DataSource.sideDishItems
                     )
             }
             composable(AppScreen.AccompanimentMenu.name){
                    ChooseItem(
                        onCancelSelected = { cancelAndNavigate(viewModel, navController) },
                        onNextSelected = { navController.navigate(AppScreen.Summary.name);viewModel.calculateTotalAmount();},
                        onItemSelected = {viewModel.setAccompaniment(accompanimentItem = it as MenuItem.AccompanimentItem) },
                        items = DataSource.accompanimentMenuItem
                    )
             }
             composable(AppScreen.Summary.name){
                    OrderSummary(
                        onSubmitSelected = { cancelAndNavigate(viewModel,navController) },
                        uiState = uiState,
                        onCancelSelected = { cancelAndNavigate(viewModel, navController) }
                    )
             }
         }
  }

}
private fun cancelAndNavigate(viewModel: OrderViewModel, navController: NavHostController){
    viewModel.resetUiState()
    navController.popBackStack( AppScreen.Start.name, inclusive = false)

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    canNavigateBack : Boolean,
    currentScreen: AppScreen,
    onNavigate :() ->Unit,

){
    CenterAlignedTopAppBar(title = { Text( text = stringResource(id = currentScreen.title))},
        modifier =Modifier.fillMaxWidth(),
        navigationIcon = {
            if(canNavigateBack){
                   IconButton(onClick = onNavigate) {
                       Icon(Icons.Default.ArrowBack, "")
                   }
               }
            }
    )
}

