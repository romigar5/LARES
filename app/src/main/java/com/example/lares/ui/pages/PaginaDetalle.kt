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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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
    //ViewModel de la pantalla de detalle
    vm: PaginaDetalleVM = viewModel(),

    //Recibe el SanckbarHostState del Scaffols para poder lanzar avisos emergentes
    // Es anulable (? = null) para que @Preview pueda llamar a PaginaDetalle() sin necesitar un Scaffold real
    //Está conectado con mensajeAviso, que podía ser null o lanzar mensaje
    snackbarHostState: SnackbarHostState? =null,

    //Modificador externo que recibe el innerPadding del Scaffold
    modifier: Modifier = Modifier
) {

    //Suscribimos la pantalla al StateFlow del ViewModel
    //'collectAsState()' convierte el flujo en un State para redibujar si algo cambia
    //'val state by' permite leer directamente el 'state.nombreUsuario' sin escribir cada vez '.value'
    val state by vm.uiState.collectAsState()

    //Disparamos LaunchedEffect cada vez que cambia 'state.mensajeAviso'
    LaunchedEffect(state.mensajeAviso) {
        //Si 'mensajeAviso' no es null (el usuario pulsó el botón de llamar en el VM)
        state.mensajeAviso?.let { mensaje ->
            //Mostramos el mensaje emergente que se generó con el VM
            snackbarHostState?.showSnackbar(mensaje)
            //Avisamos al VM de que el aviso ya fue mostrado para que vuelva a ponerse 'mensajeAviso = null'
            vm.onAvisoMostrado()
        }
    }


    // Creamos objetos con los datos de las tarjetas, extrayendo los textos desde string.xml
    val estadoData = TarjetaEstadoData(
        estado = stringResource(R.string.estado_enfermo),
        nombre = state.nombreUsuario,
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
        //Ejecutamos la función del VM en vez de recibirla desde fuera como antes
        onLlamarClick = { vm.onLlamarCentroMedico()}
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
        //Ejecutamos la función del VM en vez de recibirla desde fuera como antes
        onLlamarClick = {vm.onLlamarFamiliares()}
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
        //Cambiamos los datos fijos anteriores por su VM (ViewModel)
        PaginaDetalle()

    }
}

