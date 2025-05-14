package com.unont.lamutua

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.unont.lamutua.components.HeaderComponent
import com.unont.lamutua.components.ListaSeleccionable
import java.net.URLEncoder

@Composable
fun SelecAsistenciaView(navController: NavController) {
    val context = LocalContext.current
    val listaDeElementos = listOf(
        "Accidentes",
        "Consultas medicas",
        "Asistencia Funeraria"
    )

    val iconos = listOf(
        Icons.Filled.MedicalServices,
        Icons.Filled.LocalHospital,
        Icons.Filled.LocalFlorist
    )//array de iconos
    var seleccion by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {
        HeaderComponent(navController, titulo = "Solicitar asistencia")

        Text(
            text = "Elige el tipo de asistencia: ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Column() {
            Text(text = "Op. seleccionada: ${seleccion ?: "Ninguna"}")
            Spacer(modifier = Modifier.height(16.dp))
            ListaSeleccionable(
                listaDeElementos = listaDeElementos,
                iconos = iconos,
                onOpcionSeleccionada = { i ->
                    seleccion = i
                    println("Opción seleccionada: $i") // Puedes hacer algo con la selección aquí
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Puede solicitar su asistencia a través de:",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 8.dp),
            textAlign = TextAlign.Start
        )

        // Vías de Solicitud
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Botón de WhatsApp
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .clickable {
                        val telefonoWhatsApp =
                            "5493517581858" // Reemplaza con el número de WhatsApp de la mutua
                        val mensaje = "Hola, necesito solicitar $seleccion"
                        openWhatsApp(context, telefonoWhatsApp, mensaje)
                    },
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.Green, // Color de WhatsApp
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Message,
                        contentDescription = "WhatsApp",
                        tint = Color(0xFFFFFFFF),
                        modifier = Modifier.size(40.dp)

                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "WhatsApp",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Botón de Llamada
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .clickable {
                        val numeroTelefono =
                            "3517634566" // Reemplaza con el número de teléfono de la mutua
                        openDialer(context, numeroTelefono)
                    },
                shape = RoundedCornerShape(8.dp),
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Filled.Call,
                        contentDescription = "Llamar",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Llamada",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    }
}

// Función para abrir WhatsApp
fun openWhatsApp(context: Context, phoneNumber: String, message: String) {
    val uri = "https://wa.me/$phoneNumber?text=${URLEncoder.encode(message, "UTF-8")}".toUri()
    android.util.Log.d("WhatsAppIntent", "URI: $uri") // Verifica la URI en Logcat
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("com.whatsapp") // Especifica el paquete de WhatsApp
    try {
        context.startActivity(intent)
    } catch (e: android.content.ActivityNotFoundException) {
        // Manejar el caso en que WhatsApp no esté instalado
        // Puedes mostrar un mensaje al usuario
    }
}

// Función para abrir el dialer del teléfono
fun openDialer(context: Context, phoneNumber: String) {
    val uri = "tel:$phoneNumber".toUri()
    val intent = Intent(Intent.ACTION_DIAL, uri)
    context.startActivity(intent)
}