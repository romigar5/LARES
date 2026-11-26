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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lares.R
import com.example.lares.ui.components.CabeceraSeccion
import com.example.lares.ui.components.CabeceraSeccionData
import com.example.lares.ui.components.TarjetaFamiliarData
import com.example.lares.ui.components.TarjetaFamiliarItem
import com.example.lares.ui.theme.AppTheme


/*Data class que recibe PaginaListado con la lista de familiares y las acciones */
data class PaginaListadoData(
    val familiares: List<TarjetaFamiliarData>
)

//Pantalla de listado de familiares. Utilizamos un LazyColumn para pintar las tarjetas en una lista dinámica
@Composable
fun PaginaListado(
    data: PaginaListadoData,       //Datos que necesita la pantalla para pintar las tarjetas
    modifier: Modifier = Modifier  //Modificador externo
){
    //Contenedor Column vertical que ocupa toda la pantalla
    Column(
        modifier = Modifier
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
            items(data.familiares){familiar ->
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
                PaginaListado(
                    data = PaginaListadoData(
                        familiares = listOf(
                            TarjetaFamiliarData(
                                nombre = "Juana (hija)",
                                imagenResId = R.drawable.juana_hija,
                                onLlamarClick = {},
                                onVideollamadaClick = {}
                            ),
                            TarjetaFamiliarData(
                                nombre = "Pablo (hijo)",
                                imagenResId = R.drawable.pablo_hijo,
                                //Aquí no introducimos nada porque es solo para ver el diseño
                                onLlamarClick = {},
                                onVideollamadaClick = {}
                            )
                        )
                    )
                )
            }
        }