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
    var mostrarSelecAsistencias by remember { mutableStateOf(false) }
    var registeredNombre by remember { mutableStateOf("") }
    var registeredEmpresa by remember { mutableStateOf("") }
    var registeredTelefono by remember { mutableStateOf("") } // Nuevo estado para el teléfono
    var verificationCode by remember { mutableStateOf("") }
    var registeredEmail by remember { mutableStateOf("") } // Nuevo estado para el correo electrónico

    NavHost(navController = navController, startDestination = "registro") {
        composable("registro") {
            RegistroInicialScreen(onNavigateToVerification = { nombre, empresa, telefono, email, codigo ->
                registeredNombre = nombre
                registeredEmpresa = empresa
                registeredTelefono = telefono
                registeredEmail = email
                verificationCode = codigo
                navController.navigate("verificacion")
            })
        }
        composable("verificacion") {
            VerificationCodeScreen(
                nombre = registeredNombre,
                empresa = registeredEmpresa,
                telefono = registeredTelefono, // Pasamos el teléfono
                email = registeredEmail, // Pasamos el correo electrónico
                codigoGenerado = verificationCode,
                onVerificationSuccess = { navController.navigate("principal") },
                onResendCode = { currentCode ->
                    val newCode = generateVerificationCode()
                    println("Reenviando código: $newCode al correo $registeredEmail")
                    verificationCode = newCode
                    newCode
                }
            )
        }
        composable("principal") {
            PrincipalView(
                onNavigateToView = {
                    mostrarSelecAsistencias = true
                    navController.navigate("selecAsistencias")
                }
            )
        }
        composable("selecAsistencias") {
            AnimatedVisibility(
                visible = mostrarSelecAsistencias,
                enter = fadeIn(animationSpec = spring()),
                exit = fadeOut(animationSpec = spring()),
            ) {
                SelecAsistenciaView(navController)
            }
        }
    }
}