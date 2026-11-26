package com.example.lares.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lares.R
import com.example.lares.ui.theme.AppTheme

//Modelo de datos que desacopla el mensaje del componente visual
data class TarjetaAvisoData(
    //Texto explicativo de seguridad
    val mensaje: String
)

/*Tarjeta informativa de aviso*/
@Composable
fun TarjetaAviso(
    data: TarjetaAvisoData,
    //'Modifier' para ajustar ancho o márgenes desde contenedor padre
    modifier: Modifier = Modifier
) {
    Card(
        // 'modifier' en minúscula para respetar ajustes externos
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            //Aplicamos 'tertiaryContainer' porque transmite tranquilidad sobre el resto de la paleta
            containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ){
        //Row alinea horizontalmente el icono de confirmación con el texto
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Icono del check
            Icon(
                imageVector = Icons.Default.CheckCircle,
                //Descripción accesible en strings.xml
                contentDescription = stringResource(R.string.desc_icono_aviso),
                //Aplicamos 'onTertiaryContainer' para que destaque sobre 'tertiaryContainer'
                tint = MaterialTheme.colorScheme.onTertiaryContainer
            )
            //Añadimos espacio entre elementos
            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = data.mensaje,
                //Aplicamos tipografía pequeña para cuerpo del contenido
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

//Vista previa aislada envuelta en AppTheme
@Preview(showBackground = true)
@Composable

fun TarjetaAvisoPreview(){
    AppTheme {
        TarjetaAviso(
            data = TarjetaAvisoData(
                mensaje = "Tu ubicación y ficha de salud se compartirán automáticamente al pulsar cualquier botón de llamada."
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}