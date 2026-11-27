package com.example.lares.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lares.R
import com.example.lares.ui.components.CabeceraSeccion
import com.example.lares.ui.components.CabeceraSeccionData
import com.example.lares.ui.components.TarjetaFamiliarItem
import com.example.lares.ui.theme.AppTheme




//Pantalla de listado de familiares. Utilizamos un LazyColumn para pintar las tarjetas en una lista dinámica
@Composable
fun PaginaListado(
    //ViewModel de la pantalla de listado
    vm: PaginaListadoVM = viewModel(),
    //Modificador externo que recibe innerPadding
    modifier: Modifier = Modifier
)

{
    //Dispara la cargar inicial de datos en el ViewModel al entrar en la pantalla (una única vez)
    LaunchedEffect(Unit){
        vm.load()
    }

    //Observamos el estado del ViewModel
    //'val state by' permite acceder directamente a los datos escribiendo state.familiares o state.nombreUsuario
    //'collectAsState' Convierte el StateFlow del ViewModel en State para atender cualquier cambio dentro del VM
    val state by vm.uiState.collectAsState()

    //Contenedor Column vertical que ocupa toda la pantalla
    //'modifier' (en minúscula) para respetar los márgenes del Scaffold

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        //Llamamos al componente reutilizable de la cabecera con textos de strings.xml
        CabeceraSeccion(
            data = CabeceraSeccionData(
                titulo = stringResource(R.string.titulo_familiares),
                subtitulo = stringResource(R.string.subtitulo_familiares)
            )
        )

        //Lista dinñamica con el scroll vertical
        LazyColumn(
            //Ocupa todo el espacio debajo de la cabecera
            modifier = Modifier.fillMaxSize(),
            //Aplicamos márgenes concretos a la lista
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
            //Separación vertical
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //Recorremos la lista de familiares y pintamos una TarjetaFamiliarItem por cada uno
           //Los datos vienen del StateFlow del ViewModel
            items(state.familiares){familiar ->
                TarjetaFamiliarItem(data = familiar)

            }
        }

    }
}



        //Vista previa de la pantalla completa
@Preview(showBackground = true, showSystemUi = true)
@Composable
        fun PaginaListadoPreview(){
            AppTheme(darkTheme = false) {
                PaginaListado()

            }
        }