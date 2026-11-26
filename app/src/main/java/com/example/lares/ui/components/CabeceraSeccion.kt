package com.example.lares.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lares.ui.theme.AppTheme


//Datos que necesita la cabecera para reutilizarla en otras secciones
data class CabeceraSeccionData(
    val titulo: String,     //Título principal de la sección
    val subtitulo: String   //Subtítulo o pregunta
)
//Componente reutilizable para encabezar pantallas
@Composable
fun CabeceraSeccion(
    data: CabeceraSeccionData,
    modifier: Modifier = Modifier
){
    //Bloque superior fijo con el título, subtítulo y línea espaciadora
    Column(
        modifier = modifier
            //Todo el ancho
            .fillMaxWidth()
            //Márgenes interiores en la cabecera
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,  //Centramos horizontalmente los textos
        verticalArrangement = Arrangement.spacedBy(4.dp)    //Separación entre elementos
    ) {
        //Título principal de la pantalla ("Tus familiares")
        Text(
            //En este caso aplicamos 'data' para poder reutilizarlo en otras pantallas con otros datos.
            //De lo contrario, si llamásemos a strings.xml, no podríamos reutilizarlo, aparecería texto fijo
            text = data.titulo,
            //Aplicamos tipografía para titulo de tipo pequeño
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            //Aplicamos nuestro color sobre el fondo
            color = MaterialTheme.colorScheme.onBackground,
            //Ponemos el texto centrado
            textAlign = TextAlign.Center

        )
        //Subtítulo explicativo ("¿Con quién quieres hablar hoy?")
        Text(
            text = data.subtitulo,
            //Aplicamos nuestra tipografía de cuerpo tamaño medio
            style = MaterialTheme.typography.bodyMedium,
            //Aplicamos el color secundario más suave
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        //Línea fina horizontal para separar la cabecera de la lista de tarjetas
        HorizontalDivider(
            modifier = Modifier.padding(top = 12.dp),
            color = MaterialTheme.colorScheme.outlineVariant
        )
    }

}

@Preview(showBackground = true)
@Composable
fun CabeceraSeccionPreview(){
    AppTheme(false) {
        CabeceraSeccion(
            data = CabeceraSeccionData(
                titulo = "Tus familiares",
                subtitulo = "¿Con quién quieres hablar hoy?"
            )
        )
    }
}