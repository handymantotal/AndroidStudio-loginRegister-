package com.BelkisHectors.users.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.TextField
import androidx.compose.ui.text.font.FontWeight
import com.BelkisHectors.users.viewmodel.UserViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getvalue
import androidx.compose.runtime.setvalue




@Composable
fun UserScreen(onBack: () -> Unit, viewModel: UserViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val users by viewModel.users.collectAsState(initial = emptyList())
    val register by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(25.dp).paddingFromBaseline(top = 120.dp)) {
        if(register) {
            Text ("Usuario Registrado", color = MaterialTheme.colorScheme.primary)
        }
        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

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
                if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.addUser(name, email, password)
                    name = ""
                    email = ""
                    password = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Usuario")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text("Usuarios Registrados:", fontWeight = FontWeight.Bold)

        users.forEach { user ->
            Text(text = "👤 ${user.name} - 📧 ${user.email}")
        }
    }
}
