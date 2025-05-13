package com.unont.lamutua

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NoteAdd
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unont.lamutua.ui.theme.BluePrimario

@Composable
fun PrincipalView(){
    val configuration = LocalConfiguration.current
    val isSmallScreen = configuration.screenHeightDp < 700 // Umbral para considerar pantalla pequeña (ajusta según necesites)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isSmallScreen) 240.dp else 280.dp)//condicional si es pequena la pantalla
                .background(BluePrimario)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally
            ) {
                Image(
                    painter =  painterResource(id = R.drawable.icono_principal_mejorado2),
                    contentDescription = "Salud",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp),
            verticalArrangement = Arrangement.SpaceBetween // Esto ahora distribuirá el espacio entre el botón superior, el Spacer y la columna inferior
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = if (isSmallScreen) 20.dp else 0.dp),
                horizontalAlignment = CenterHorizontally
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(if (isSmallScreen) 2.dp else 16.dp)
                        .height(if (isSmallScreen) 120.dp else 180.dp) // Altura más pequeña en pantallas chicas
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BluePrimario,// color que cree que es el q vamos a usar
                        contentColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.HealthAndSafety,
                            contentDescription = "flotador",
                            modifier = Modifier.size(50.dp)
                        )
                        Column(modifier = Modifier.padding(start = 20.dp)) {
                            Text(
                                text = "Solicitar",
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start,
                                color = Color.White,
                            )
                            Text(
                                text = "asistencias",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // Este Spacer ocupará todo el espacio vertical restante

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 15.dp),
                verticalArrangement = Arrangement.spacedBy(if (isSmallScreen) 1.dp else 20.dp)
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                       containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = CenterVertically
                    ) {
                        Text(
                            text = "Mis Asistencias",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = "Chevron Right",
                            tint = Color.Black
                        )
                    }
                }
                HorizontalDivider(color = Color.Black, thickness = 2.dp)
                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent // Usa containerColor
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            pressedElevation = 0.dp,
                            disabledElevation = 0.dp
                        ),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.NoteAdd,
                                contentDescription = "Note",
                                tint = Color.Black
                            )
                            Text(
                                text = "Poliza",
                                textAlign = TextAlign.Start,
                                color = Color.Black
                            )
                        }
                    }
                }
                HorizontalDivider()
                Row(verticalAlignment = CenterVertically) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                           containerColor = Color.Transparent
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp,
                            disabledElevation = 0.dp
                        ),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.ChatBubble,
                                contentDescription = "Comment",
                                tint = Color.Black
                            )
                            Text(text = "Contactar asesor",
                                color = Color.Black
                            )
                        }
                    }
                }
                HorizontalDivider()
                Row(verticalAlignment = CenterVertically) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp,
                            disabledElevation = 0.dp
                        ),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.QuestionMark,
                                contentDescription = "Question",
                                tint = Color.Black
                            )
                            Text(text = "Preguntas frecuentes",
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}