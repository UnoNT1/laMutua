package com.unont.lamutua

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navegacion() {
    val navController = rememberNavController()
    var mostrarSelecAsistencias by remember { mutableStateOf(false) } // Estado para controlar la visibilidad


    NavHost(navController = navController, startDestination = "principal") {
        composable("principal") {
            PrincipalView(
                onNavigateToView = {
                    mostrarSelecAsistencias = true // Actualiza el estado al navegar
                    navController.navigate("selecAsistencias")
                }
            )
        }
        composable("selecAsistencias") {
            AnimatedVisibility(
                visible = mostrarSelecAsistencias,
                enter = fadeIn(animationSpec = spring()), // Transición de entrada rápida,
                exit = fadeOut(animationSpec = spring()),
            ) {
                SelecAsistenciaView(navController)
            }
        }
    }
}