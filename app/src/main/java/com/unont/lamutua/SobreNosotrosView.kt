package com.unont.lamutua

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Fax
import androidx.compose.material.icons.filled.Functions
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.PhoneCallback
import androidx.compose.material.icons.filled.PregnantWoman
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unont.lamutua.cards.ExpandableCategoryCard
import com.unont.lamutua.components.BottomNavigationBar
import com.unont.lamutua.ui.theme.BluePrimario
import com.unont.lamutua.ui.theme.poppinsFontFamily

// --- Estructuras de Datos ---

data class ServicioDetalle(
    val numero: Int,
    val titulo: String,
    val descripcion: String,
    val icono: ImageVector? = null // Opcional: si quieres un icono para cada sub-servicio
)

data class CategoriaServicios(
    val titulo: String,
    val icono: ImageVector, // Icono para la categoría principal
    val servicios: List<ServicioDetalle>
)

// --- Composable Principal de la Vista ---

@Composable
fun SobreNosotrosView(navController: NavController) {
    val scrollState = rememberScrollState()

    val categorias = remember {
        listOf(
            CategoriaServicios(
                titulo = "Coberturas por Accidentes y Diagnósticos",
                icono = Icons.Default.MedicalServices,
                servicios = listOf(
                    ServicioDetalle(1, "Pérdida Orgánica", "Cobertura para la pérdida de órganos según la escala establecida."),
                    ServicioDetalle(2, "Invalidez por Accidente", "Protección financiera en caso de invalidez resultante de un accidente."),
                    ServicioDetalle(3, "Primer Diagnóstico de Cáncer", "Cobertura para el diagnóstico inicial de cáncer, asegurando atención inmediata."),
                    ServicioDetalle(4, "Enfermedades Graves", "Asistencia para el tratamiento de enfermedades graves, garantizando el acceso a la atención necesaria.")
                )
            ),
            CategoriaServicios(
                titulo = "Coberturas Dentales",
                icono = Icons.Default.PermIdentity, // Icono para dental
                servicios = listOf(
                    ServicioDetalle(5, "Consulta Dental", "Brindamos acceso a consultas dentales para el diagnóstico y tratamiento de problemas bucodentales."),
                    ServicioDetalle(6, "Tratamiento Dental por Accidente", "Cobertura para tratamientos dentales resultantes de accidentes, asegurando la atención oportuna.")
                )
            ),
            CategoriaServicios(
                titulo = "Coberturas Especiales",
                icono = Icons.Default.Stars, // Icono para especial
                servicios = listOf(
                    ServicioDetalle(7, "VIH/SIDA", "Cobertura específica para el tratamiento y seguimiento de personas con VIH/SIDA."),
                    ServicioDetalle(8, "Terapia física y rehabilitación", "Apoyo para tratamientos de terapia física y rehabilitación, promoviendo la recuperación de nuestros afiliados."),
                    ServicioDetalle(9, "Respaldo hospitalario (Q500 diarios por hasta 10 días)", "Asistencia económica para gastos hospitalarios, asegurando el bienestar del paciente."),
                    ServicioDetalle(10, "Cobertura para Actividades y Deportes Peligrosos", "Protección para aquellos que participan en actividades y deportes considerados peligrosos.")
                )
            ),
            CategoriaServicios(
                titulo = "Cobertura por Maternidad y Recién Nacido",
                icono = Icons.Default.PregnantWoman, // Icono para maternidad
                servicios = listOf(
                    ServicioDetalle(11, "Cobertura por Maternidad", "Cobertura integral para el cuidado de la madre durante el embarazo y el parto."),
                    ServicioDetalle(12, "Gastos por Atención del Recién Nacido (Mat)", "Asistencia para los gastos médicos del recién nacido tras el parto."),
                    ServicioDetalle(13, "Gastos por Atención del Recién Nacido Prematuro (Mat)", "Cobertura adicional para los gastos médicos de recién nacidos prematuros.")
                )
            ),
            CategoriaServicios(
                titulo = "Gastos Ambulatorios",
                icono = Icons.Default.LocalPharmacy, // Icono para ambulatorio
                servicios = listOf(
                    ServicioDetalle(14, "Gastos ambulatorios", "Cobertura para gastos relacionados con tratamientos ambulatorios, facilitando el acceso a la atención médica necesaria.")
                )
            ),
            CategoriaServicios(
                titulo = "Coberturas por Fallecimiento y Asistencia",
                icono = Icons.Default.Functions, // Icono para fallecimiento (requiere Material Icons Extended)
                servicios = listOf(
                    ServicioDetalle(15, "Muerte por Accidente", "Cobertura financiera en caso de fallecimiento por accidente."),
                    ServicioDetalle(16, "Muerte Natural", "Asistencia en caso de fallecimiento natural, brindando apoyo a los beneficiarios."),
                    ServicioDetalle(17, "Asistencia Funeraria", "Servicios de asistencia funeraria para facilitar los trámites en momentos difíciles."),
                    ServicioDetalle(18, "Gastos Médicos por Accidente", "Cobertura para gastos médicos derivados de accidentes, asegurando atención oportuna.")
                )
            ),
            CategoriaServicios(
                titulo = "Orientaciones Telefónicas",
                icono = Icons.Default.PhoneCallback, // Icono para teléfono
                servicios = listOf(
                    ServicioDetalle(19, "Orientación Médica Telefónica", "Acceso a consultas médicas telefónicas para resolver dudas de salud."),
                    ServicioDetalle(20, "Orientación Nutricional Telefónica", "Asesoría nutricional a través de consultas telefónicas."),
                    ServicioDetalle(21, "Orientación Psicológica Telefónica", "Apoyo psicológico disponible por teléfono para nuestros afiliados.")
                )
            ),
            CategoriaServicios(
                titulo = "Asistencias Generales",
                icono = Icons.Default.Build, // Icono para general/hogar
                servicios = listOf(
                    ServicioDetalle(22, "Asistencia Hogar", "Servicios de asistencia para el hogar, brindando apoyo en situaciones de emergencia."),
                    ServicioDetalle(23, "Servicio de Grúa", "Asistencia en caso de accidentes vehiculares, incluyendo el servicio de grúa."),
                    ServicioDetalle(24, "Auxilio Vial", "Cobertura para asistencia en carretera, garantizando la seguridad de nuestros afiliados.")
                )
            ),
            CategoriaServicios(
                titulo = "Coberturas Visuales",
                icono = Icons.Default.Visibility, // Icono para visual
                servicios = listOf(
                    ServicioDetalle(25, "Consulta Oftalmológica / Optometrista", "Cobertura para consultas oftalmológicas y optometría."),
                    ServicioDetalle(26, "Armazón y Micas Graduadas NO Cosméticos", "Asistencia para la adquisición de armazones y micas graduadas, excluyendo opciones cosméticas.")
                )
            ),
            CategoriaServicios(
                titulo = "Asistencias Médicas",
                icono = Icons.Default.HealthAndSafety, // Icono para asistencias médicas
                servicios = listOf(
                    ServicioDetalle(27, "Estudios de Diagnóstico", "Cobertura para estudios diagnósticos necesarios para el tratamiento de enfermedades."),
                    ServicioDetalle(28, "Video Consulta", "Acceso a consultas médicas a través de videollamada, facilitando la atención médica."),
                    ServicioDetalle(29, "Ambulancia Terrestre", "Servicio de ambulancia terrestre para emergencias médicas."),
                    ServicioDetalle(30, "Consultas Médicas de Primer Contacto", "Cobertura para consultas iniciales con médicos generales."),
                    ServicioDetalle(31, "Consulta con Especialidad", "Acceso a consultas con médicos especialistas según la necesidad del paciente."),
                    ServicioDetalle(32, "Medicamentos Prescritos", "Cobertura para medicamentos prescritos por profesionales de la salud.")
                )
            )
        )
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState) // Hace que la columna sea deslizable
                .background(MaterialTheme.colorScheme.background) // Fondo de la vista
                .padding(paddingValues)
        ) {
                Text(
                    text = "Sobre La Mutua Guatemala",
                    style = TextStyle(
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp
                    ),
                    fontWeight = FontWeight.Bold,
                    color = BluePrimario,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .padding(top = 14.dp),
                    textAlign = TextAlign.Center
                )

            Text(
                text = "En La Mutua Guatemala, estamos comprometidos con tu bienestar y tranquilidad. Ofrecemos una amplia gama de coberturas y asistencias diseñadas para protegerte a ti y a tu familia en todo momento, brindando soluciones integrales y un respaldo confiable.",
                style = TextStyle(
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Justify, // Ajusta a Justify para mejor lectura en párrafos largos
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            )
            Text(
                text = "Nuestra Misión: Proteger tu futuro y el de tu familia con servicios de asistencia innovadores y accesibles.",
                style = TextStyle(
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )


            // --- Sección "Nuestros Servicios" (con Acordeones) ---
            Text(
                text = "Nuestros Servicios y Coberturas",
                style = TextStyle(
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal, fontSize = 22.sp
                ),
                fontWeight = FontWeight.Bold,
                color = BluePrimario,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )

            categorias.forEach { categoria ->
                ExpandableCategoryCard(categoria = categoria)
                Spacer(modifier = Modifier.height(10.dp)) // Espacio entre categorías
            }

            Spacer(modifier = Modifier.height(24.dp)) // Espacio al final de la pantalla
        }
    }
}