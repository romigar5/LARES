package com.example.lares.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//-----------------------------------------------------
//   VISTA DE DETALLE AISLADA (SIN scaffold, menudrawer ni navegación)
//-----------------------------------------------------
@Composable
fun PantallaDetalle(
    onLlamarCentroMedicoClick: () -> Unit = {},
    onLlamarFamiliaresClick: () -> Unit = {},

    //Permite personalizar la pantalla desde fuera
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8F6))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ){
        //1. Tarjeta de estado
        item{TarjetaEstado()

        }

        //2. Tarjeta de Atención Médica (tarjeta de acción)
        item{
            TarjetaAccion(
                categoria = "ATENCIÓN MÉDICA",
                titulo = "Centro de Salud",
                subtitulo = "Consultorio San Rafael (24h)",
                textoBoton = "Llamar centro médico",
                iconoHeader = Icons.Default.LocalHospital,
                colorBoton = Color(0xFF7A291E),
                onLlamarClick = onLlamarCentroMedicoClick //Acción que realiza
            )
        }

        //3. Tarjeta de Familiares (segunda de acción)
        item{
            TarjetaAccion(
                categoria = "RED DE APOYO",
                titulo = "Familiares",
                subtitulo = "Contactos frecuentes: Carmen e Isabel",
                textoBoton = "Llamar familiares",
                subtextoBoton = "Avisar a tus seres queridos",
                iconoHeader = Icons.Default.Group,
                colorBoton = Color(0xFF3E120D),
                onLlamarClick = onLlamarFamiliaresClick
        }

        //4. Tarjeta de Aviso final
        item{
            TarjetaAvico()
        }
    }

}
//Componentes auxiliares
