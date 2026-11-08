package com.example.lares.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

//-----------------------------------------------------
//   VISTA DE DETALLE AISLADA (SIN scaffold, menudrawer ni navegación)
//-----------------------------------------------------

//Pasan los datos a los componentes
data class TarjetaEstadoData(
    val estado: String,
    val nombre: String,
    val mensaje: String
)

//Ambas tarjetas de acción son reutilizables por sus componentes
data class TarjetaAccionData(
    val categoria: String,
    val titulo: String,
    val subtitulo: String,
    val textoBoton: String,
    val iconoHeader: ImageVector,
    val colorBoton: Color,
    val onLlamarClick: () -> Unit
)


@Composable
fun PantallaDetalle(
    onLlamarCentroMedicoClick: () -> Unit = {},
    onLlamarFamiliaresClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Creamos objetos con los datos de las tarjetas
    val estadoData = TarjetaEstadoData(
        estado = "• ESTADO: ENFERMO",
        nombre = "Juan",
        mensaje = "Estamos aquí para ayudarte de inmediato. Elige qué necesitas:"
    )

    val atencionData = TarjetaAccionData(
        categoria = "ATENCIÓN MÉDICA",
        titulo = "Centro de Salud",
        subtitulo = "Consultorio San Rafael (24h)",
        textoBoton = "Llamar centro médico",
        iconoHeader = Icons.Default.LocalHospital,
        colorBoton = Color(0xFF7A291E),
        onLlamarClick = onLlamarCentroMedicoClick
    )

    val familiaresData = TarjetaAccionData(
        categoria = "RED DE APOYO",
        titulo = "Familiares",
        subtitulo = "Contactos frecuentes: Carmen e Isabel",
        textoBoton = "Llamar familiares",
        iconoHeader = Icons.Default.Group,
        colorBoton = Color(0xFF3E120D),
        onLlamarClick = onLlamarFamiliaresClick
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8F6))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ){
        //1. Tarjeta de estado
        item{ TarjetaEstado(data = estadoData)
        }

        //2. Tarjeta de Atención Médica (tarjeta de acción)
        item{TarjetaAccion(data = atencionData)}

        //3. Tarjeta de Familiares (segunda de acción)
        item{TarjetaAccion(data = familiaresData)}

        //4. Tarjeta de Aviso final
        item{
            TarjetaAviso()}
    }
}
//Componentes auxiliares
// 3. COMPONENTES REUTILIZABLES BASADOS EN SUS DATA CLASSES
@Composable
fun TarjetaEstado(data: TarjetaEstadoData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFBE8E5))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Box para el icono/emoji
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF3C7C0)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🤒", fontSize = 28.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Columna con los textos
            Column {
                // Box para la etiqueta de estado (badge)
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(0xFF7A291E))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = data.estado,
                        color = Color(0xFF7A291E),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${data.nombre}, no estás solo.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = data.mensaje, fontSize = 14.sp)
            }
        }
    }
}

    @Composable
    fun TarjetaAccion(data: TarjetaAccionData) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = data.iconoHeader,
                        contentDescription = null,
                        tint = Color(0xFF7A291E)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = data.categoria,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF8C4339)
                        )
                        Text(text = data.titulo, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = data.subtitulo, fontSize = 12.sp, color = Color.Gray)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = data.onLlamarClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = data.colorBoton)
                ) {
                    Icon(Icons.Default.Phone, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = data.textoBoton)
                }
            }
        }
    }


    @Composable
    fun TarjetaAviso() {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEEF7F2))
        ) {
            Text(
                text = "Tu ubicación y ficha de salud se compartirán automáticamente al pulsar cualquier botón de llamada.",
                modifier = Modifier.padding(16.dp),
                fontSize = 12.sp,
                color = Color(0xFF2E5936)
            )
        }
    }