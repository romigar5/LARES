package com.example.lares.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lares.R
import com.example.lares.ui.theme.AppTheme

/**
 * Componente reutilizable TarjetaFamiliarItem
 * Representa cada tarjeta dentro de un LazyColumn y cada contacto guardado dentro de la app
 * Se utiliza LazyColumn porque se proyecta una lista de varios familiares, no un tamaño fijo
 */


data class TarjetaFamiliarData(
    val nombre: String,
    val imagenResId: Int,
    val onLlamarClick: () -> Unit,
    val onVideollamadaClick: () -> Unit
)
@Composable

//Parámetros que recibe la función de la tarjeta

fun TarjetaFamiliarItem(
    data: TarjetaFamiliarData,
    modifier: Modifier = Modifier

){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        //Llamamos directamente a nuestro tema propio para implementar los colores
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ){
            // 1. Box: Foto de perfil 1
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape) //Forma
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center //Alineado al centro
            ){
                Image(
                    painter = painterResource(id= data.imagenResId),
                    contentDescription = stringResource(R.string.desc_foto_familiar,data.nombre),
                    modifier = Modifier.fillMaxSize()
                )
            }

            //2. Text: Nombre y parentesco del familiar
            Text(
                text = data.nombre, //Dato dinámico
                //Implementamos la tipografía del theme propio
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            //Espaciador vertical
            Spacer(modifier = Modifier.height(4.dp))

            //3. Button: Acción de llamada (principal)
            Button(
                onClick = data.onLlamarClick, //Acción del botón
                modifier = Modifier
                    .fillMaxWidth() //Ocupa el ancho de columna
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    //Relleno del button
                    containerColor = MaterialTheme.colorScheme.primary,
                    //Texto e icono que van dentro
                    contentColor = MaterialTheme.colorScheme.onPrimary),
                shape = RoundedCornerShape(12.dp)  //Esquinas redondeadas

            ){
                //Inicio del contenido del Button
                Row(
                    verticalAlignment = Alignment.CenterVertically,   //Centra verticalmente el icono y texto
                    horizontalArrangement = Arrangement.Center   //Centrado horizontalmente
                ){
                    //Inicio del contenido dentro de Row
                    //Icono del teléfono y texto
                    Icon(
                        imageVector = Icons.Default.Phone, //cargamos el vector de teléfono
                        contentDescription = stringResource(R.string.desc_icono_telefono),  //Descripción para accesibilidad

                    )

                    //Introducimos un spacer
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        //Llamamos directamente al recurso de strings.mxl
                        text = stringResource(R.string.texto_llamar),
                        //Aplicamos la fuente de nuestro theme
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,


                    )
                } //Cerramos el row
            } //Cerramos Button

            //4. OutlinedButton: Botón secundario para videollamada (opcional)
            OutlinedButton(
                onClick = data.onVideollamadaClick,
                modifier = Modifier
                    .fillMaxWidth() //Ocupa ancho del contenedor
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary)

            ) {
                //Row que contiene el OutlinedButton
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                //Icono de videollamada y texto dentro del Row
                Icon(
                    imageVector = Icons.Default.Videocam,
                    contentDescription = stringResource(R.string.desc_icono_camara),

                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(R.string.texto_videollamada),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                }

            }

        }
    }

}
@Preview(showBackground = true)
@Composable
//Función sin parámetros
fun TarjetaFamiliarItemPreview(){
    //La envolvemos en nuestro theme
    AppTheme{
        //Llamamos al componente real pasándole datos de prueba
        TarjetaFamiliarItem(
            data = TarjetaFamiliarData(
                nombre = "Juana (hija)",
                imagenResId = R.drawable.juana_hija,
                onLlamarClick = {},
                onVideollamadaClick = {}
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}





