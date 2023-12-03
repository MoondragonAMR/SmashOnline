package com.example.smashonline.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import com.example.smashonline.Batalla
import com.example.smashonline.R
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun Creacion() {
    var seleccionado by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("Mario") }
    var rival by remember { mutableStateOf("Kirby") }
    var debut by remember { mutableStateOf("64") }
    var tiempo by remember { mutableStateOf("1:00") }
    var puntos by remember { mutableStateOf(0f) }
    var fecha by remember { mutableStateOf("28/11/2023") }
    var imagen by remember { mutableStateOf(R.drawable.__mario)}
    var imagencompleta = painterResource(id = imagen)
        Column() {
            Text("Introduce el nombre del personaje:")
            DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }) {
                DropdownMenuItem(
                    text = { "Mario" },
                    onClick = { nombre = "Mario"; imagen = R.drawable.__mario })
                DropdownMenuItem(
                    text = { "Kirby" },
                    onClick = { nombre = "Kirby"; imagen = R.drawable.__kirby })
                DropdownMenuItem(
                    text = { "Zelda" },
                    onClick = { nombre = "Zelda"; imagen = R.drawable._9_zelda })
                DropdownMenuItem(
                    text = { "Roy" },
                    onClick = { nombre = "Roy"; imagen = R.drawable._8_roy })
                DropdownMenuItem(
                    text = { "Sonic" },
                    onClick = { nombre = "Sonic"; imagen = R.drawable._1_sonic })
                DropdownMenuItem(
                    text = { "Samus Zero" },
                    onClick = { nombre = "Samus Zero"; imagen = R.drawable._4_samus_zero })
                DropdownMenuItem(
                    text = { "Palutena" },
                    onClick = { nombre = "Palutena"; imagen = R.drawable._4_palutena })
                DropdownMenuItem(
                    text = { "Pac-Man" },
                    onClick = { nombre = "Pac-Man"; imagen = R.drawable._5_pac })
                DropdownMenuItem(
                    text = { "Ryu" },
                    onClick = { nombre = "Ryu"; imagen = R.drawable._0_ryu })
                DropdownMenuItem(
                    text = { "Cloud" },
                    onClick = { nombre = "Cloud"; imagen = R.drawable._2_cloud })
                DropdownMenuItem(
                    text = { "Inkling" },
                    onClick = { nombre = "Inkling"; imagen = R.drawable._5_inkling_girl })
                DropdownMenuItem(
                    text = { "Incineroar" },
                    onClick = { nombre = "Incineroar"; imagen = R.drawable._1_incineroar })
                DropdownMenuItem(
                    text = { "Pyra" },
                    onClick = { nombre = "Pyra"; imagen = R.drawable._4_pyra_ssbu })
                DropdownMenuItem(
                    text = { "Sora" },
                    onClick = { nombre = "Sora"; imagen = R.drawable._6_sora_ssbu })
            }
            Text("El juego de debut:")
            DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }) {
                DropdownMenuItem(text = { "64" }, onClick = { debut = "64" })
                DropdownMenuItem(text = { "Melee" }, onClick = { debut = "Melee" })
                DropdownMenuItem(text = { "Brawl" }, onClick = { debut = "Brawl" })
                DropdownMenuItem(text = { "4" }, onClick = { debut = "4 " })
                DropdownMenuItem(text = { "4 DLC" }, onClick = { debut = "4 DLC" })
                DropdownMenuItem(text = { "Ultimate" }, onClick = { debut = "Ultimate" })
                DropdownMenuItem(text = { "Ultimate DLC" }, onClick = { debut = "Ultimate DLC" })
            }
            Text("El tiempo (en minutos) que duró la batalla:")
            Column(Modifier.selectableGroup()) {
                RadioButtons(texto = "1:00", seleccionado == "1:00") {
                    seleccionado = "1:00"; tiempo = seleccionado
                }
                RadioButtons(texto = "1:30", seleccionado == "1:30") {
                    seleccionado = "1:30"; tiempo = seleccionado
                }
                RadioButtons(texto = "2:00", seleccionado == "2:00") {
                    seleccionado = "2:00"; tiempo = seleccionado
                }
                RadioButtons(texto = "2:30", seleccionado == "2:30") {
                    seleccionado = "2:30"; tiempo = seleccionado
                }
                RadioButtons(texto = "3:00", seleccionado == "3:00") {
                    seleccionado = "3:00"; tiempo = seleccionado
                }
            }
            Text("El número de puntos que consiguió en la batalla:")
            Slider(
                value = puntos,
                onValueChange = { puntos = it },
                valueRange = 0f..10f,
                steps = 10
            )
            Text("El personaje contra el que jugó:")
            DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }) {
                DropdownMenuItem(text = { "Mario" }, onClick = { rival = "Mario" })
                DropdownMenuItem(text = { "Kirby" }, onClick = { rival = "Kirby" })
                DropdownMenuItem(text = { "Zelda" }, onClick = { rival = "Zelda" })
                DropdownMenuItem(text = { "Roy" }, onClick = { rival = "Roy" })
                DropdownMenuItem(text = { "Sonic" }, onClick = { rival = "Sonic" })
                DropdownMenuItem(text = { "Samus Zero" }, onClick = { rival = "Samus Zero" })
                DropdownMenuItem(text = { "Palutena" }, onClick = { rival = "Palutena" })
                DropdownMenuItem(text = { "Pac-Man" }, onClick = { rival = "Pac-Man" })
                DropdownMenuItem(text = { "Ryu" }, onClick = { rival = "Ryu" })
                DropdownMenuItem(text = { "Cloud" }, onClick = { rival = "Cloud" })
                DropdownMenuItem(text = { "Inkling" }, onClick = { rival = "Inkling" })
                DropdownMenuItem(text = { "Incineroar" }, onClick = { rival = "Incineroar" })
                DropdownMenuItem(text = { "Pyra" }, onClick = { rival = "Pyra" })
                DropdownMenuItem(text = { "Sora" }, onClick = { rival = "Sora" })
            }
            datePickerScreen(onDateSelection = { fecha = it.toString() })
        }
}

@Composable
fun RadioButtons(texto : String, seleccionado : Boolean, accion : () -> Unit) {
    Row(horizontalArrangement = Arrangement.Start) {
        RadioButton(selected = seleccionado, onClick = accion)
        Text(texto)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun datePickerScreen(onDateSelection : (LocalDate) -> Unit) {

    val dateTime = LocalDateTime.now()

    val datePickerState = remember {
        DatePickerState(
            yearRange = (2023..2024),
            initialSelectedDateMillis = null,
            initialDisplayMode = DisplayMode.Picker,
            initialDisplayedMonthMillis = null
        )
    }
    DatePicker(state = datePickerState)

    val date = datePickerState.selectedDateMillis
    date?.let {
        onDateSelection(Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate())
    }
}

