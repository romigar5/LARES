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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lares.R

/**
 * Componente reutilizable TarjetaFamiliarItem
 * Representa cada tarjeta dentro de la LazyColumn y cada contacto guardado dentro de la app
 */

@Composable

//Parámetros que recibe la función de la tarjeta
fun TarjetaFamiliarItem(
    nombre: String,
    imagenResId: Int,
    onLlamarClick: () -> Unit,
    onVideollamadaClick: () -> Unit,
    modifier: Modifier = Modifier

){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
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
                    .background(Color(0xFFF3D5CF)),
                contentAlignment = Alignment.Center //Alineado al centro
            ){
                Image(
                    painter = painterResource(id = imagenResId),
                    contentDescription = "Foto de Juana",
                    modifier = Modifier.fillMaxSize()
                )
            }

            //2. Text: Nombre y parentesco del familiar
            Text(
                text = nombre,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E120D)
            )
            //Espaciador vertical
            Spacer(modifier = Modifier.height(4.dp))

            //3. Button: Acción de llamada (principal)
            Button(
                onClick = onLlamarClick, //Acción del botón
                modifier = Modifier
                    .fillMaxWidth() //Ocupa el ancho de columna
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A1813)),
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
                        contentDescription = "Icono teléfono",  //Descripción para accesibilidad
                        tint = Color.White  //Coloreamos de blanco el icono
                    )

                    Text(
                        text = "Llamar",
                        fontSize = 16.sp,  //fuentes con .sp
                        fontWeight = FontWeight.Bold,
                        color = Color.White

                    )
                } //Cerramos el row
            } //Cerramos Button

            //4. OutlinedButton: Botón secundario para videollamada (opcional)
            OutlinedButton(
                onClick = onVideollamadaClick,
                modifier = Modifier
                    .fillMaxWidth() //Ocupa ancho del contenedor
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF4A1813))

            ) {
                //Row que contiene el OutlinedButton
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                //Icono de videollamada y texto dentro del Row
                Icon(
                    imageVector = Icons.Default.Videocam,
                    contentDescription = "Icono cámara",
                    tint = Color(0xFF4A1813)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Videollamada",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                }

            }

        }
    }

}

//Función para mostrar la vista en el panel de diseño de Android Studio
@Preview(showBackground = true)
@Composable
fun TarjetaFamiliarItemPreview(){
    TarjetaFamiliarItem(
        nombre = "Juana (Hija)",
        imagenResId = R.drawable.ic_launcher_background,
        onLlamarClick = {},
        onVideollamadaClick = {}
    )
}





