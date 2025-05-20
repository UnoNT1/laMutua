package com.unont.lamutua.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ListaSeleccionable(
    listaDeElementos: List<String>,
    iconos: List<ImageVector>,
    onOpcionSeleccionada: (String) -> Unit // Callback para notificar la selección
) {
    var opcionSeleccionada by remember { mutableStateOf<String?>(null) } // Estado para la opción seleccionada

    Column {
        listaDeElementos.forEachIndexed { index, item ->
            // Determina si el item actual está seleccionado
            val isSelected = opcionSeleccionada == item

            Row(
                modifier = Modifier
                    .size(width = 300.dp, height = 50.dp)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .background(
                        // Aplica el color de fondo condicionalmente
                        color = if (isSelected) Color.Gray.copy(alpha = 0.3f) else Color.Transparent // Color de resaltado
                    )
                    .clickable {
                        opcionSeleccionada = item // Actualiza el estado al hacer clic
                        onOpcionSeleccionada(item) // Llama al callback con la opción seleccionada
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Espacio fijo para el icono
                Box(modifier = Modifier.width(32.dp), contentAlignment = Alignment.CenterStart) {
                    if (index < iconos.size) {
                        Icon(
                            imageVector = iconos[index],
                            contentDescription = item,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.MoreHoriz,
                            contentDescription = item,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp)) // Espacio entre el icono y el texto
                Text(
                    text = item,
                    modifier = Modifier.fillMaxWidth(), // El texto ocupa el resto del ancho
                )
            }
        }
    }
}