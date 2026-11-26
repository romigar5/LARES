package com.example.lares.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lares.ui.theme.AppTheme


/*Data class que nos permite utilizar el mismo diseño de tarjeta para acción médica
* y para familiares, cambiando solo sus datos*/
data class TarjetaAccionData(
    val categoria: String,              //Etiqueta de categoría
    val titulo: String,                 //Título principal del servicio
    val subtitulo: String,              //Información adicional
    val textoBoton: String,             //Texto dentro del botón
    val iconoHeader: ImageVector,       //Icono dinámico
    val colorBoton: Color,              //Color del botón
    val onLlamarClick: () -> Unit       //Delega el click dentro de la tarjeta
)

/*Componente genérico y reutilizable*/
@Composable
fun TarjetaAccion(
    data: TarjetaAccionData,
    modifier: Modifier = Modifier //Permite ajustar márgenes o ancho desde el contenedor
) {
    //Card con elevación para separarla del fondo de la pantalla
    Card(
        //Ponemos 'modifier' en minúscula para respetar ajustes externos
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp), //esquinas redondeadas
        colors = CardDefaults.cardColors(
            //Fondo neutro de 'surface' para que destaque el botón de acción
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

        ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = data.iconoHeader,
                    //Icono decorativo: el texto contiguo ya describe la acción, por eso ponemos 'null'
                    contentDescription = null,
                    //Aplicamos el color principal
                    tint = MaterialTheme.colorScheme.primary
                )
                //Añadimos un espacio para márgenes
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = data.categoria,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        //Aplicamos nuestro color secundario
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = data.titulo,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface)
                    Text(
                        text = data.subtitulo,
                        //Aplicamos tipografía pequeña para el contenido del cuerpo
                        style = MaterialTheme.typography.bodySmall,
                        //Aplicamos un tono suave de nuestro tema
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            //Espaciado entre elementos
            Spacer(modifier = Modifier.height(12.dp))

            //Botón de llamar
            Button(
                onClick = data.onLlamarClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = data.colorBoton,
                    //Aplicamos 'onPrimary' para que destaque sobre el principal
                    contentColor = MaterialTheme.colorScheme.onPrimary)
            ) {
                //Icono decorativo: el texto del botón ya describe la acción por eso ponemos 'null'
                Icon(Icons.Default.Phone, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = data.textoBoton,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}

//Vista previa aislada envuelta en nuestro AppTheme
@Preview(showBackground = true)
@Composable
fun TarjetaAccionPreview(){
    AppTheme {
        TarjetaAccion(
            data = TarjetaAccionData(
                categoria = "ATENCIÓN MÉDICA",
                titulo = "Centro de Salud",
                subtitulo = "Consultorio San Rafael (24h)",
                textoBoton = "Llamar centro médico",
                iconoHeader = Icons.Default.LocalHospital,
                colorBoton = MaterialTheme.colorScheme.primary,
                onLlamarClick = {}

            ),
            //Para aplicar el margen, llamamos a 'Modifier' en mayúsculas
            modifier = Modifier.padding(16.dp)
        )
    }
}