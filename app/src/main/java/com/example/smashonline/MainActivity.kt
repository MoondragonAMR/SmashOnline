package com.example.smashonline

import android.media.Image
import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.smashonline.ui.theme.Creacion
import com.example.smashonline.ui.theme.SmashOnlineTheme
import org.intellij.lang.annotations.JdkConstants
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmashOnlineTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SmashOnline()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmashOnline() {
    var batallas = mutableListOf<Batalla>()
    batallas.add(Batalla(painterResource(id = R.drawable.__mario), "Mario", "Kirby", "28/11/2023", "1:00", "64", 2f))
    var sagas = remember { mutableStateListOf("64", "Melee", "Brawl", "4 ", "4 DLC", "Ultimate", "Ultimate DLC") }
    var estado by remember { mutableStateOf(false) }
    var busqueda by remember { mutableStateOf("") }
    var filtro by remember { mutableStateOf("") }
    var color by remember { mutableStateOf(Color.Blue) }
    var pulsado by remember { mutableStateOf(false)}
    var visible by remember { mutableStateOf(false)}
    var dialogo by remember { mutableStateOf(false)}
    var borrar = mutableListOf<Int>()
    var nombre by remember { mutableStateOf("Mario") }
    var rival by remember { mutableStateOf("Kirby") }
    var debut by remember { mutableStateOf("64") }
    var tiempo by remember { mutableStateOf("1:00") }
    var puntos by remember { mutableStateOf(0f) }
    var fecha by remember { mutableStateOf("28/11/2023") }
    var imagen by remember { mutableStateOf(R.drawable.__mario)}
    Column(Modifier.fillMaxHeight()) {
        Text("Bienvenid@ a Smash Online", fontWeight = FontWeight.Bold)
        SearchBar(placeholder = {Text("Busca personajes por debut")},query = busqueda,
            onQueryChange = {textoIntroducido -> busqueda = textoIntroducido},
            onSearch = { filtro = it; estado = false}, active = estado, onActiveChange = { estado = !estado }) {
           // hacer un bucle y recorrer los personasjes / consolas

            // 1 - Crear un array con personajes (valores estaticos) - OK

            // 2 - Recorrer dicho array y crear un LI para cada personaje - OK

            // 3 - Lo de arriba OK, probamos a sacar los LI para cada personaje QUE
            // cumplan el criterio de busqueda (empiecen por busqueda)
            // ¿Que pasa si busqueda esta vacia? - OK

            // 4 - Cuando se quiere buscar, filtro LA COLUMNA DE ABAJO! -OK

            for(i in 0..sagas.lastIndex) {
                if (sagas[i].contains(busqueda) || (busqueda.isBlank())) {
                    ListItem(headlineContent = { Text(sagas[i]) })
                }
            }
        }
        LazyColumn() {
            items(batallas.size) {
                color = when (batallas[it].debut) {
                    "64" -> Color.Yellow
                    "Melee" -> Color.Cyan
                    "Brawl" -> Color.Red
                    "4 " -> Color.Blue
                    "4 DLC" -> Color.Magenta
                    "Ultimate" -> Color.Gray
                    else -> Color.LightGray
                }
                if (batallas[it].debut.equals(filtro) || (filtro.isBlank())) {
                    var seleccionado by remember { mutableStateOf(false) }
                    Row(Modifier.background(color = color), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.Top) {
                        Column(Modifier.fillMaxHeight()) {
                            Image(batallas[it].imagen, "")
                        }
                        Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                            Text(batallas[it].nombre)
                            val rival = batallas[it].rival
                            val fecha = batallas[it].fecha
                            Text("VS $rival , $fecha")
                            Text(batallas[it].tiempo)
                        }
                        if (visible) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Checkbox(checked = seleccionado, onCheckedChange = { checking -> seleccionado = !seleccionado; if (seleccionado) {
                                    borrar.add(it)
                                } else {
                                    if (borrar.contains(it)) {
                                        borrar.remove(it)
                                    }
                                }
                                })
                            }
                        }
                    }
                }
            }
        }
        Row {
            ExtendedFloatingActionButton(onClick = { dialogo = true }) {
                Text("Añadir")
                Icon(Icons.Default.Add, "")
            }
            ExtendedFloatingActionButton(onClick = {
                if (!pulsado) {
                    pulsado = true
                    visible = true
                } else if (pulsado) {
                    pulsado = false
                    for (i in 0..batallas.lastIndex) {
                        if (borrar.contains(i)) {
                            batallas.removeAt(i)
                            borrar.remove(i)
                        }
                    }
                    visible = false
                }
            }) {
                Text("Borrar")
                Icon(Icons.Default.Delete, "")
            }
        }
    }
    if (dialogo) {
        Dialog(onDismissRequest = { dialogo = !dialogo}, content = { Creacion() })
    }
}

@Preview
@Composable
fun SmashOnlinePreview(){
    SmashOnline()
}

data class Batalla(val imagen: Painter, val nombre: String, val rival: String, val fecha: String, val tiempo: String, val debut: String, val puntos: Float )