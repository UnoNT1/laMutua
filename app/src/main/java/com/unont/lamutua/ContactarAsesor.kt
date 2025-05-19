package com.unont.lamutua

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import com.unont.lamutua.components.HeaderComponent
import com.unont.lamutua.ui.theme.BluePrimario
import com.unont.lamutua.ui.theme.poppinsFontFamily
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@Composable
fun ContactarAsesorView(navController: NavController) {
    val context = LocalContext.current
    val assetPath = "folleto_cobertura.pdf" // Nombre de tu archivo en assets

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {
        HeaderComponent(navController, "Contactar Asesor")

        Text(
            text = "Contactar Asesor",
            style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (ejemploAsesor.isNotEmpty()) {
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Asesor",
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = "Asesor: ${ejemploAsesor["nombre"] ?: "N/A"}",
                        style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
                    )
                    Text(
                        text = "Teléfono: ${ejemploAsesor["telefono"] ?: "N/A"}",
                        style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
                    )
                    Text(
                        text = "Email: ${ejemploAsesor["email"] ?: "N/A"}",
                        style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
                    )
                    Text(
                        text = "Horario: ${ejemploAsesor["horario"] ?: "N/A"}",
                        style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("¿Necesitas más información?", fontWeight = FontWeight.Bold,
                style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
                )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    openPdfFromAssets(context, assetPath)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimario)
            ) {
                Text("Ver Folleto de Cobertura y Asistencia", color = Color.White,
                    style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
                    )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("O contáctanos directamente:", fontWeight = FontWeight.Bold,
                style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
                )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Phone, contentDescription = "Teléfono General")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Llamar a Atención al Cliente",
                    style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
                    )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.MailOutline, contentDescription = "Email General")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Enviar un Correo Electrónico",
                    style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal)
                    )
            }
        }
    }
}
// Ejemplo de datos del asesor
val ejemploAsesor = mapOf(
    "nombre" to "Ana Rodríguez",
    "telefono" to "+54 9 351 1234567",
    "email" to "ana.rodriguez@lamutua.com",
    "horario" to "Lunes a Viernes, 9:00 - 18:00"
)
//funcion para descargar el pdf
fun openPdfFromAssets(context: Context, assetPath: String) {
    Log.d("PDF_VIEWER", "Intentando abrir PDF desde assets: $assetPath (usando FileProvider)")
    try {
        val inputStream: InputStream? = context.assets.open(assetPath)
        val outputFile = File(context.cacheDir, assetPath) // Guarda temporalmente en la caché
        FileOutputStream(outputFile).use { outputStream ->
            inputStream?.copyTo(outputStream)
        }
        inputStream?.close()

        val uri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider", // Usa la misma autoridad que en el Manifest
            outputFile
        )

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "application/pdf")
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NO_HISTORY

        ContextCompat.startActivity(context, intent, null)

    } catch (e: Exception) {
        Log.e("PDF_VIEWER", "Error al abrir PDF con FileProvider", e)
        // Manejar el error
        // Toast.makeText(context, "Error al abrir el PDF.", Toast.LENGTH_LONG).show()
    }
}