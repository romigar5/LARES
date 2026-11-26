package com.example.lares.ui.pages

import android.R.attr.data
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lares.R
import com.example.lares.ui.components.TarjetaAccion
import com.example.lares.ui.components.TarjetaAccionData
import com.example.lares.ui.components.TarjetaAviso
import com.example.lares.ui.components.TarjetaAvisoData
import com.example.lares.ui.components.TarjetaEstado
import com.example.lares.ui.components.TarjetaEstadoData
import com.example.lares.ui.theme.AppTheme

/*Data class que agrupa estado y eventos de toda la pantalla*/
data class PaginaDetalleData(
    val nombreUsuario: String,
    val onLlamarCentroMedicoClick: () -> Unit,
    val onLlamarFamiliaresClick: () -> Unit
)

/*Pantalla de detalle de asistencia urgente
* Aquí juntamos componentes reutilizables*/
@Composable
fun PaginaDetalle(
    data: PaginaDetalleData,
    modifier: Modifier = Modifier
) {
    // Creamos objetos con los datos de las tarjetas, extrayendo los textos desde string.xml
    val estadoData = TarjetaEstadoData(
        estado = stringResource(R.string.estado_enfermo),
        nombre = data.nombreUsuario,
        mensaje = stringResource(R.string.mensaje_estado_detalle)
    )

    //Tarjeta de Atención Médica
    val atencionData = TarjetaAccionData(
        categoria = stringResource(R.string.categoria_at_medica),
        titulo = stringResource(R.string.titulo_centro_salud),
        subtitulo = stringResource(R.string.subtitulo_centro_salud),
        textoBoton = stringResource(R.string.btn_llamar_centro_salud),
        iconoHeader = Icons.Default.LocalHospital,
        //Aplicamos el color 'primary' porque la acción requiere urgencia
        colorBoton = MaterialTheme.colorScheme.primary,
        onLlamarClick = data.onLlamarCentroMedicoClick
    )
    //Tarjeta de Familiares
    val familiaresData = TarjetaAccionData(
        categoria = stringResource(R.string.categori_red_apoyo),
        titulo = stringResource(R.string.titulo_familiares),
        subtitulo = stringResource(R.string.subtitulo_familiares),
        textoBoton = stringResource(R.string.btn_llamar_familiares),
        iconoHeader = Icons.Default.Group,
        //Aplicamos color 'secondary' para diferenciar las redes de apoyo
        colorBoton = MaterialTheme.colorScheme.secondary,
        onLlamarClick = data.onLlamarFamiliaresClick
    )

    //Tarjeta de Aviso
    val avisoData = TarjetaAvisoData(
        mensaje = stringResource(R.string.texto_seguridad)
    )




    //Column con scroll vertical: se escoge porque existe un número fijo y pequeño de elementos.
    Column(
        modifier = modifier
            .fillMaxSize()
            //Aplicamos el fondo general de nuestro AppTheme
            .background(MaterialTheme.colorScheme.background)
            //Habilitamos el scroll vertical y recordamos la posición
            .verticalScroll(rememberScrollState())
            .padding(16.dp),

        //Aplicamos un espaciado entre tarjetas
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ){
        //1. Tarjeta de estado del usuario
        TarjetaEstado(data = estadoData)

        //2. Tarjeta de Atención Médica (tarjeta de acción)
        TarjetaAccion(data = atencionData)

        //3. Tarjeta de Familiares (segunda de acción)
        TarjetaAccion(data = familiaresData)

        //4. Tarjeta de Aviso final
        TarjetaAviso(data = avisoData)
    }
}

//Vista previa de pantalla completa envuelta en nuestro AppTheme
@Preview(showBackground = true)
@Composable
fun PantallaDetallePreview(){
    AppTheme {
        PaginaDetalle(
            data = PaginaDetalleData(
                nombreUsuario = "Juan",
                onLlamarCentroMedicoClick = {},
                onLlamarFamiliaresClick = {}
            )

        )

    }
}

