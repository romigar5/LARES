package com.example.lares.ui.navigation


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Sos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import com.example.lares.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lares.ui.pages.PaginaDetalle
import com.example.lares.ui.pages.PaginaListado
import com.example.lares.ui.theme.AppTheme
import kotlinx.serialization.Serializable

//RUTAS TIPADAS (@Serializable)
//Definimos la ruta tipada para identificar la pantalla de detalle y marcamos el objeto como
//serializable para que Navigation Compose lo reconozca
@Serializable
object RutaDetalle

//Definimos la ruta tipada para identificar la pantalla de listado y marcamos el objeto como
//serializable para que Navigation Compose lo reconozca
@Serializable
object RutaListado

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Función que contiene el Scaffold y la navegación
fun AppNavegacion(){
    //Creamos y recordamos el NavController que gestiona el backStack de las pantallas que visitamos
    val nav = rememberNavController()

    //Creamos y recordamos el estado único del Snackbar elevado al Scaffold
    val snackbarHostState = remember { SnackbarHostState() }

    //Observamos cuál es la pantalla activa en la pila de navegación
    val navBackStackEntry by nav.currentBackStackEntryAsState()

    //Extraemos el objeto NavDestination (ruta actual) de la entrada superior del bacstack
    val destinoActual = navBackStackEntry?.destination

    //Calculamos si estamos en la pantalla inicial (true si aún no hay destino o si estamos en RutaDetalle)
    val esPantallaInicial = destinoActual == null || destinoActual.hasRoute<RutaDetalle>()

    //Leemos desde strings.xml los textos de los botones inferiores y superior
    val textoUrgencias = stringResource(R.string.nav_urgencias)
    val textoFamiliares = stringResource(R.string.nav_familiares)
    val textoVolver = stringResource(R.string.nav_atras)

    //Creamos el Scaffold de toda la aplicación
    Scaffold(
        //Hacemos que el Scaffold ocupa todo el ancho y alto del dispositivo
        modifier = Modifier.fillMaxSize(),

        //Configuramos la barra superior fija de la aplicación
        topBar = {
            //Componente TopAppBar
            TopAppBar(
                //Título visible en la barra superior
                title = {
                    //Mostramos el nombre de la app ("Lares")
                    Text(text = stringResource(R.string.nombreApp))
                },
                //Espacio reservado a la izquierda del título para el icono de navegación
                navigationIcon = {
                    //Solo dibujamos la flechqa de atrás si no estamos en la pantalla de inicio
                    if (!esPantallaInicial) {
                        //Al pulsar la flecha, llamamos a nav.navigateUp()
                        IconButton(onClick = { nav.navigateUp() }) {
                            Icon(
                                //Imagen de la flecha
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = textoVolver
                            )
                        }
                    }
                }

            )

        },
        //Configuramos la barra de navegación inferior para cambiar entre pantallas
        bottomBar = {
            NavigationBar {
                //Pestaña 1: Pantalla de Detalle / SOS Urgencias
                NavigationBarItem(
                    //Iluminamos la pestaña si la pantalla actual es RutaDetalle
                    selected = destinoActual?.hasRoute<RutaDetalle>() == true,
                    onClick = {
                        //Navegamos a RutaDetalle y no acumulamos rutas
                        nav.navigate(RutaDetalle){
                            //Limpiamos la pila de pantallas
                            popUpTo(nav.graph.startDestinationId){
                                //Guardamos el estado en memoria
                                saveState = true
                            }
                            //Evitamos apilar una copia duplicada si ya estamos en RutaDetalle
                            launchSingleTop = true
                            //Recuperamos el estado guardado anteriormente
                            restoreState = true
                        }
                    },
                    //Bloque que dibuja el icono de la primera pestaña
                    icon = {
                        Icon(
                            //Seleccionamos el icono de sos para urgencias
                            imageVector = Icons.Default.Sos,
                            //Asignamos la descripción accesible desde strings.xml
                            contentDescription = textoUrgencias
                        )
                    },
                    //Etiqueta de texto que se muestra debajo del icono
                    label = {Text(text = textoUrgencias)}
                )
                // Segunda pestaña de la barra inferior: Pantalla de Listado de Familiares
                NavigationBarItem(
                    // Comprueba si el destino actual en pantalla es RutaListado para resaltar la pestaña
                    selected = destinoActual?.hasRoute<RutaListado>() == true,
                    // Acción que se dispara cuando el usuario pulsa esta segunda pestaña
                    onClick = {
                        // Ejecutamos la transición hacia el destino RutaListado
                        nav.navigate(RutaListado) {
                            // Limpiamos destinos intermedios hasta el inicio del grafo de navegación
                            popUpTo(nav.graph.startDestinationId) {
                                // Guardamos el estado de la pantalla anterior antes de salir de ella
                                saveState = true
                            }
                            // Evitamos crear múltiples instancias de RutaListado si se pulsa repetidamente
                            launchSingleTop = true
                            // Restauramos el estado (como el scroll del listado) si ya se visitó antes
                            restoreState = true
                        }
                    },
                    // Bloque visual que dibuja el icono de la segunda pestaña
                    icon = {
                        // Componente Icon que renderiza el vector de grupo de personas
                        Icon(
                            // Seleccionamos el icono de grupo para representar los contactos familiares
                            imageVector = Icons.Default.Group,
                            // Asignamos la descripción accesible desde strings.xml
                            contentDescription = textoFamiliares
                        )
                    },
                    // Etiqueta de texto que se muestra debajo del icono de la segunda pestaña
                    label = { Text(text = textoFamiliares) }
                )
            }
        },

        // Zona reservada del Scaffold donde se dibujarán los avisos emergentes, Snackbar
        snackbarHost = {
            // Enlazamos el SnackbarHost con nuestro controlador elevado 'snackbarHostState'
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        // 'innerPadding' recibe los márgenes exactos de la TopAppBar y NavigationBar.
        // Creamos el NavHost (contenedor dinámico que intercambia pantallas según la ruta activa)
        NavHost(
            // Conectamos el NavHost con nuestro  controlador de navegación 'nav'
            navController = nav,
            // Definimos que la primera pantalla que se mostrará al arrancar la app sea RutaDetalle
            startDestination = RutaDetalle,
            // Aplicamos 'innerPadding' aquí para que las barras superior e inferior nunca tapen el contenido
            modifier = Modifier.padding(innerPadding)
        ) {
            // Registramos el primer destino del grafo asociado a la ruta tipada RutaDetalle
            composable<RutaDetalle> {
                // Dibujamos PaginaDetalle pasándole solo el snackbarHostState (nunca le pasamos 'nav')
                PaginaDetalle(snackbarHostState = snackbarHostState)
            }

            // Registramos el segundo destino del grafo asociado a la ruta tipada RutaListado
            composable<RutaListado> {
                // Dibujamos PaginaListado (usa su ViewModel por defecto y queda desacoplada de 'nav')
                PaginaListado()
            }
        }
    }
}

//Vista previa completa
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppNavegacionPreview(){
    //Envolvemos la vista previa en nuestro tema
    AppTheme {
        //Le pasamos toda la estructura con el Scaffolds, bara superior e inferior
        AppNavegacion()
    }
}









