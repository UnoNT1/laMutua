package com.unont.lamutua.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unont.lamutua.ui.theme.BluePrimario
import com.unont.lamutua.ui.theme.poppinsFontFamily

@Composable
fun HeaderComponent(navController: NavController, titulo: String){
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth().background(BluePrimario).height(110.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.navigateUp() }, modifier = Modifier.size(100.dp, 50.dp)
                    .padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                )//para sacarle la sombra del boton
            ) { // O navController.popBackStack()
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Volver")
            }
            Text(
                text = titulo,
                style = TextStyle(fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f) // Ocupa el espacio restante
                    .padding(start = 20.dp, top = 10.dp)
                    .fillMaxWidth(),
                color = Color.White
            )
        }
    }
}