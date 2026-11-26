package com.example.lares.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sick
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lares.R
import com.example.lares.ui.theme.AppTheme


/*Data class que encapsula los datos necesarios para pintar la tarjeta*/
data class TarjetaEstadoData(
    val estado: String,
    val nombre: String,
    val mensaje: String
)

/*Componente reutilizable para mostrar el estado del usuario
* Al meterlo en ui/componentes, permitimos Previews aisladas*/
@Composable
fun TarjetaEstado(
    data: TarjetaEstadoData,
    modifier: Modifier = Modifier) //Permite personalizar tamaño y margen desde contenedor padre
    {
    //Contenedor elevado que agrupa información
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp), //Esquinas redondeadas
        colors = CardDefaults.cardColors(
            //Usamos los colores de nuestro theme propio, transmite urgencia
            containerColor = MaterialTheme.colorScheme.errorContainer
        )
    ) {
        //Row organiza los elementos en horizontal
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Box para el icono/emoji
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                //Sustituimos el emoji para mantener accesibilidad
                Icon(
                    imageVector = Icons.Default.Sick,
                    //Descripción accesible en strings
                    contentDescription = stringResource(R.string.desc_icono_estado),
                    //Teñinos el vector con el color primary
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp) //Tamaño del icono dentro de la caja
                )
            }
            //Espacio horizontal de separación entre icono y columna de textos
            Spacer(modifier = Modifier.width(16.dp))

            // Columna vertical con los textos y el estado
            Column {
                // Box con forma de píldora para la etiqueta de estado
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        //Fondo con color primary
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 10.dp, vertical = 4.dp)  //Ponemos márgenes para que el texto se adapte visualmente

                ) {
                    Text(
                        //Muestra el estado recibido
                        text = data.estado,
                        //Utilizamos onPrimary para que destaque sobre el color de fondo
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                //Separación vertical entre la píldora de estado y el título
                Spacer(modifier = Modifier.height(6.dp))

                //Título principal de la tarjeta con el nombre de usuario
                Text(
                    //Como es una variable,
                    text = stringResource(R.string.saludo_detalle,data.nombre),
                    style = MaterialTheme.typography.titleMedium, //Tipografía propio para subtítulos
                    fontWeight = FontWeight.Bold, //negrita
                    color = MaterialTheme.colorScheme.onErrorContainer
                )

                //Texto secundario con instrucciones para el usuario
                Text(
                    text = data.mensaje,
                    style = MaterialTheme.typography.bodyMedium, //tipografía para cuerpo de lectuta
                    color = MaterialTheme.colorScheme.onErrorContainer //mantiene coherencia y contraste
                )
            }
        }
    }
}

//Vista previa del diseño
@Preview(showBackground = true) //Muestra el fondo blanco detrás de la tarjeta para apreciar los bordes
@Composable
fun TarjetaEstadoPreview(){
    //Envolvemos el componente en AppTheme (nuestro theme propio)
    AppTheme {
            TarjetaEstado(
                data = TarjetaEstadoData(
                    estado = "• ESTADO: ENFERMO",
                    nombre = "Juan",
                    mensaje = "Estamos aquí para ayudarte de inmediato. Elige qué necesitas:"

                ),
                //Añadimos un margen para no tocar los bordes de la ventana
                modifier = Modifier.padding(16.dp)
            )
    }
}