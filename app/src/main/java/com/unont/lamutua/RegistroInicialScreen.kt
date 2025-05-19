package com.unont.lamutua

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unont.lamutua.ui.theme.BluePrimario
import com.unont.lamutua.ui.theme.poppinsFontFamily

@Composable
fun RegistroInicialScreen(
    onNavigateToVerification: (String, String, String, String) -> Unit,
    onNavigateToPrincipal: () -> Unit // Nueva lambda para navegar a PrincipalView
) {
    var nombre by remember { mutableStateOf("") }
    var empresa by remember { mutableStateOf("") } // Nuevo estado para el teléfono
    var email by remember { mutableStateOf("") } // Nuevo estado para el correo electrónico
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Registro", style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal),
            fontSize = 30.sp
            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = {
                Text(
                "Nombre",
                style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal
                ))
                    },
            textStyle = TextStyle(color = BluePrimario),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BluePrimario,
                focusedLabelColor = BluePrimario,
                cursorColor = BluePrimario

            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = empresa,
            onValueChange = { empresa = it },
            label = { Text("Empresa",
                style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
            ) },
            textStyle = TextStyle(color = BluePrimario),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BluePrimario,
                focusedLabelColor = BluePrimario,
                cursorColor = BluePrimario
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico",
                style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
            ) },
            textStyle = TextStyle(color = BluePrimario),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BluePrimario,
                focusedLabelColor = BluePrimario,
                cursorColor = BluePrimario

            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val codigo = generateVerificationCode()
                // Aquí iría la lógica real para enviar el código al número de teléfono
                println("Enviando código: $codigo al email $email")
                onNavigateToVerification(nombre,empresa,email, codigo)
            },
            enabled = nombre.isNotBlank() && empresa.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = BluePrimario,
                contentColor = Color.White,
                disabledContentColor = Color.DarkGray

            )
        ) {
            Text("Registrarse",
                style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
            )
        }
        // Nuevo botón para "Ingresar sin registrarse"
        OutlinedButton(
            onClick = {
                onNavigateToPrincipal() // Llama a la función de navegación
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = BluePrimario // Color del texto y el borde
            )
        ) {
            Text("Ingresar sin registrarse",
                style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
            )
        }
    }
}