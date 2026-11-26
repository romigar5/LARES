package com.example.lares.ui.pages

import android.util.Log
import com.example.lares.R
import androidx.lifecycle.ViewModel
import com.example.lares.ui.components.TarjetaFamiliarData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

//Estado observable de la pantalla de listado con la lista de tarjetas de familiares
data class PaginaListadoUIState(
    val familiares: List<TarjetaFamiliarData> = listOf()
)

//Constante privada para filtrar los mensajes de este ViewModel en Logcat
private const val TAG = "PaginaListadoVM"

//ViewModel de la pantalla de listado
class PaginaListadoVM: ViewModel(){

    //Estado interno privado y modificable: solo el ViewModel puede cambiarlo
    private val _uiState = MutableStateFlow(PaginaListadoUIState())

    //Versión pública, pero de solo lectura. La pantalla PaginaListado puede leerlo y observarlo,
    //pero no puede alterarlo, por accidente, desde la interfaz
    val uiState = _uiState.asStateFlow()

    //Se ejecuta nada más crearse el VewModel para cargar los contactos iniciales
    init{
        load()
    }

    //Carga los familiares de prueba simulando los datos de prueba
    fun load(){
        //Reemplazamos el valor del flujo (_uiState.value) creando una nueva copia
        _uiState.value = _uiState.value.copy(
            //Asignamos al campo 'familiares' del estado una lista inmutable (listOf)
            familiares = listOf(
                //Primer familiar de prueba
                TarjetaFamiliarData(
                    nombre = "Juan (hija)",
                    //Fijarse siempre en el import de 'R'
                    imagenResId = R.drawable.juana_hija,
                    //Pasamos el evento hacia arriba (al ViewModel)
                    onLlamarClick = { onLlamar("Juana (hija")},
                    onVideollamadaClick = { onVideollamada("Juana (hija")}

                ),
                //Segundo familiar de prueba
                TarjetaFamiliarData(
                    nombre = "Pablo(hijo)",
                    imagenResId = R.drawable.pablo_hijo,
                    //Pasamos el evento hacia arriba (al ViewModel)
                    onLlamarClick = { onLlamar("Pablo (hijo")},
                    onVideollamadaClick = { onVideollamada("Pablo (hijo")}
                )
            )
        )
    }

    //Acción al pulsar el botón de Llamar en una tarjeta
    fun onLlamar(nombre: String){
        Log.d(TAG, "acción llamar $nombre")
    }

    //Acción al pulsar el botón de videollamada de una tarjeta
    fun onVideollamada(nombre: String){
        Log.d(TAG, "acción videollamada $nombre")
    }

}