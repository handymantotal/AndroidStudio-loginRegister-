package com.BelkisHectors.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
// para crear variables siempre van estas getValue y setValue para que by funcione
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

//
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.BelkisHectors.data.AppDatabase
import com.BelkisHectors.users.data.User
import com.BelkisHectors.users.ui.screen.HomeScreen
import com.BelkisHectors.users.ui.screen.LoginScreen
import com.BelkisHectors.users.ui.screen.UserScreen
import com.BelkisHectors.users.ui.theme.UsersTheme
import com.BelkisHectors.users.viewmodel.UserViewModel
import kotlinx.coroutines.launch



class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getDatabase(applicationContext)
        val userDao = db.userDao()

        // nuevo
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // FORZAR INSERCIÓN EN LA BASE DE DATOS
        lifecycleScope.launch {
            val userCount = userDao.getUserCount()
            if(userCount == 0) {  // Solo insertar si no hay usuarios
                val newUser = User(name = "Henry", email = "henry@example.com", password = "123456")
                userDao.insert(newUser)
                Log.d("Database", "Usuario insertado: ${newUser.name}")
            } else {
                Log.d("Database", "La base de datos ya tiene usuarios, no se insertó nada.")
            }
        }
        enableEdgeToEdge()
        setContent {
            UsersTheme {
                //Creamos la variable para almacenar la pantalla corriente y usamos when
                var currentScreen by remember { mutableStateOf("home") }
                when (currentScreen) {
                    "home" -> Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        HomeScreen(
                            onLoginClick = { currentScreen = "login" },
                            onRegisterClick = { currentScreen = "register" }
                        )
                    }

                    "login" -> LoginScreen(
                        viewModel = userViewModel,
                        onLoginSuccess = { currentScreen = "protected" }, // Ir a la vista protegida
                        onBack = { currentScreen = "home" }
                    )

                    "register" -> Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        UserScreen(onBack = { currentScreen = "home" },userViewModel) // Pantalla de registro
                    }

                    "protected" -> ProtectedScreen(
                        onLogout = { currentScreen = "home" } // Volver al inicio al cerrar sesión
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UsersTheme {
        Greeting("Android")
    }
}