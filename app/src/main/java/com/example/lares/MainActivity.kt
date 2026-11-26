package com.example.lares

import android.R.attr.data
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lares.ui.pages.PaginaDetalle
import com.example.lares.ui.pages.PaginaDetalleData
import com.example.lares.ui.theme.AppTheme

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            //Envolvemos toda la App en nuestro AppTheme para aplicar paleta y tipografía propios
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
    }
}