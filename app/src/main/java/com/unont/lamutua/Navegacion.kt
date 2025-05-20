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
    var registeredNombre by remember { mutableStateOf("") }
    var registeredEmpresa by remember { mutableStateOf("") } // Nuevo estado para el teléfono
    var verificationCode by remember { mutableStateOf("") }
    var registeredEmail by remember { mutableStateOf("") } // Nuevo estado para el correo electrónico

    NavHost(navController = navController, startDestination = "registro") {
        composable("registro") {
            RegistroInicialScreen(onNavigateToVerification = { nombre,empresa, email, codigo ->
                registeredNombre = nombre
                registeredEmpresa = empresa
                registeredEmail = email
                verificationCode = codigo
                navController.navigate("verificacion")
            },
                onNavigateToPrincipal = {
                    // Navega a tu PrincipalView
                    navController.navigate("principal") {
                        // Opcional: Limpia el backstack si no quieres que el usuario vuelva a la pantalla de registro
                        popUpTo("registro") { inclusive = true }
                    }
                }
            )
        }
        composable("verificacion") {
            VerificationCodeScreen(
                nombre = registeredNombre,
                 empresa = registeredEmpresa, // Pasamos el teléfono
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
                    navController.navigate("selecAsistencias")
                },
                onNavigateAsesor = { navController.navigate("contactarAsesor") },
                navController = navController // Pasa el navController a PrincipalView
            )
        }
        composable("sobreNosotros") {
            SobreNosotrosView(navController) // Llama a tu vista de "Sobre Nosotros"
        }
        composable("selecAsistencias") {
                SelecAsistenciaView(navController)
        }
        composable("contactarAsesor") { ContactarAsesorView(navController) }
    }
}