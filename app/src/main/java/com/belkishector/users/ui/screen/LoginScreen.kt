package com.BelkisHectors.users.ui.screen

import androidx.compose.foundation.layout.*

import androidx.compose.material3.Button
import com.BelkisHectors.users.viewmodel.UserViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: UserViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Iniciar Sesión",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.validateLogin(email, password) { isValid ->
                    if (isValid) {
                        onLoginSuccess() // Navegar a la pantalla protegida si el login es exitoso
                    } else {
                        loginError = true // Mostrar un mensaje de error
                    }
                }
            }
        ) {
            Text(text = "Iniciar Sesión") // Etiqueta del botón
        }

        if (loginError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Error de inicio de sesión. Por favor, verifica tus credenciales.",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}