package com.unont.lamutua

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun VerificationCodeScreen(
    nombre: String,
    empresa: String, // Recibimos el teléfono
    email: String, // Recibimos el correo electrónico
    codigoGenerado: String,
    onVerificationSuccess: () -> Unit,
    onResendCode: (String) -> String
) {
    var codigoIngresado by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var nuevoCodigo by remember { mutableStateOf(codigoGenerado) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Verificación de Código", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Se ha enviado un código a tu correo electrónico: $email. Ingrésalo a continuación.")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = codigoIngresado,
            onValueChange = { codigoIngresado = it },
            label = { Text("Código de Verificación") },
            modifier = Modifier.fillMaxWidth()
        )
        if (errorMessage.isNotBlank()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (codigoIngresado == nuevoCodigo) {
                    println("Usuario registrado: Nombre=$nombre, Email = $email")
                    onVerificationSuccess() // Navegar a la siguiente pantalla
                } else {
                    errorMessage = "Código incorrecto. Inténtalo de nuevo."
                }
            },
            enabled = codigoIngresado.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Verificar Código")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { nuevoCodigo = onResendCode(nuevoCodigo) }) {
            Text("Reenviar código")
        }
    }
}

//funcion para generar el codigo aleatorio que se va a enviar
fun generateVerificationCode(length: Int = 6): String {
    return (1..length)
        .map { Random.nextInt(0, 10) }
        .joinToString("")
}