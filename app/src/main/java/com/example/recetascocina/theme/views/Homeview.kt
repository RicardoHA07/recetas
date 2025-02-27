package com.example.ejemplo1.ui.theme.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemplo1.components.ActionButton
import com.example.ejemplo1.components.TitleBar
import com.example.recetascocina.R

// Lista de recetas con imagen y ruta
data class Receta(val nombre: String, val imageRes: Int, val ruta: String)

val recetasList = listOf(
    Receta("Pizza", R.drawable.pizza, "pizzaView"),
    Receta("Hamburguesa", R.drawable.hamburguesa, "hamburguesaView"),
    Receta("Chocomil", R.drawable.chocomilk, "chocoView"),
    //Receta("Ensalada", R.drawable.ensalada, "ensaladaView"),
    //Receta("Postre", R.drawable.postre, "postreView")
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleBar("Home View") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Red
                )
            )
        },
        floatingActionButton = {
            ActionButton(Color.Red)
        }
    ) {
        ContentView(onNavigate)
    }
}

@Composable
private fun ContentView(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Recetas", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(recetasList) { receta ->
                RecetaItem(receta, onNavigate)
            }
        }
    }
}

@Composable
fun RecetaItem(receta: Receta, onNavigate: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigate(receta.ruta) }
    ) {
        Image(
            painter = painterResource(id = receta.imageRes),
            contentDescription = receta.nombre,
            modifier = Modifier
                .size(150.dp)
                .padding(8.dp)
                .clickable { onNavigate(receta.ruta) }
        )
        Text(text = receta.nombre, style = MaterialTheme.typography.bodyLarge)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeView() {
    HomeView(onNavigate = {})
}
