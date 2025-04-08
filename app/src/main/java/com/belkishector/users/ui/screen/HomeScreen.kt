package com.BelkisHectors.users.ui.screen



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(modifier: Modifier = Modifier,onLoginClick:()-> Unit, onRegisterClick:() -> Unit) {
    // Proporcionar un valor por defecto para modifier
    // Agregamos una columna centrada vertical y horizontalmente
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp),  // Usar el modifier entrante
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Agregamos un texto que diga Bienvenido
        Text("Bienvenido", style = MaterialTheme.typography.headlineMedium)

        // Agregamos espacio
        Spacer(modifier = Modifier.height(16.dp))  // Cambiar a Modifier

        // Agregamos un botón de iniciar sesión
        Button(
            onClick = onLoginClick, // Es obligatorio, así que lo dejamos con una función vacía de momento
            modifier = Modifier.fillMaxWidth() // Expande el botón al ancho completo de la pantalla
        ) {
            Text("Iniciar sesión")
        }

        // Agregamos espacio
        Spacer(modifier = Modifier.height(16.dp)) // Cambiar a Modifier

        // Agregamos un botón de registrarse
        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }
    }
}

