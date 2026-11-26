package com.example.lares.ui.pages

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow



//UIState. Es el estado de la pantalla detalle con los datos que necesita pintar la UI
data class PaginaDetalleUIState(
    val nombreUsuario: String = "",
    val telefonoCentroMedico: String = "",
    val telefonoFamiliares: String = "",
    //Ponemos '?' porque si no hay ningún mensaje que mostrar, valdrá 'null', de lo contrario, guarda un String
    val mensajeAviso: String? = null

)

//ViewModel de PaginaDetalle: carga los datos de prueba y gestiona las acciones de los botones
class PaginaDetalleVM : ViewModel(){
    //Aquí el ViewModel guarda y actualiza los datos. Ponemos private para que nadie externo pueda modificarla.
    private val _uiState = MutableStateFlow(PaginaDetalleUIState())
    //Es un flujo público, pero solo permite lectura, no se puede modificar
    val uiState: StateFlow<PaginaDetalleUIState> = _uiState.asStateFlow()

    //Bloque que se ejecuta nada más crearse el ViewModel
    init{
        //Con esto cargamos los datos iniciales de prueba
        load()
    }
    //Carga los datos de prueba con una simulación
    fun load(){
        //Hacemos una copia del estado actual, solo cambiando campos que nos interesan
        _uiState.value = _uiState.value.copy(
            nombreUsuario = "Juan",
            telefonoCentroMedico = "986123652",
            telefonoFamiliares = "652369874"
        )
    }

    //Acción al pulsar el botón de llamar al centro médico
    fun onLlamarCentroMedico(){
        //Aquí lepasamos el teléfono guardado en load()
        val telefono = _uiState.value.telefonoCentroMedico

        //Mostramos en Logcat que se ha pulsado
        Log.d(TAG, "acción de llamar centro médico: $telefono")

        //Rellenamos el aviso para que salte el Snackbar
        _uiState.value =_uiState.value.copy(
            mensajeAviso = "Llamando al Centro de Salud ($telefono)..."
        )
    }

    //Función que se ejecuta al pulsar el botón de llamar a familiares
    fun onLlamarFamiliares(){
        //Sacamos el teléfono de familiares guardado en load()
        val telefono = _uiState.value.telefonoFamiliares

        //Lo registramos en Logcat
        Log.d(TAG, "acción de llamar familiares: $telefono")

        //Rellenamos el aviso para que salte el SnackBar
        _uiState.value = _uiState.value.copy(
            mensajeAviso = "Llamando a familiares ($telefono)..."
        )

    }

    //Reseteamos mensajeAviso a null una vez que el SnackBar se ha mostrado en la pantalla
    fun onAvisoMostrado(){
        _uiState.value = _uiState.value.copy(mensajeAviso = null)
    }




}