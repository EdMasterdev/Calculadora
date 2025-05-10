package com.example.myapplicationcompose

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.sp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.myapplicationcompose.ui.theme.MyApplicationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Greeting(name = "por favor ingresa tus datos")
            }
        }
    }
}

// Parte modificada
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        // Imagen al inicio
        androidx.compose.foundation.Image(
            painter = androidx.compose.ui.res.painterResource(id = R.drawable.login_image),
            contentDescription = "Login Image",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(bottom = 16.dp)
        )

        // Título centrado
        Text(
            text = "Login",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 24.dp)
        )



        // Campos de texto
        StyledTextField()
    }
}

// Campos con etiquetas separadas
@Composable
fun StyledTextField() {
    val contexto = LocalContext.current

    var correo by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    // Texto "Correo"
    Text(
        text = "Correo",
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(top = 12.dp, bottom = 4.dp),
        style = TextStyle(fontWeight = FontWeight.SemiBold)
    )

    OutlinedTextField(
        value = correo,
        onValueChange = { correo = it },
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth(0.8f)
    )

    Spacer(modifier = Modifier.height(8.dp))

// Texto "Contraseña"
    Text(
        text = "Contraseña",
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(top = 12.dp, bottom = 4.dp),
        style = TextStyle(fontWeight = FontWeight.SemiBold)
    )

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Ingrese su contraseña") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth(0.8f)
    )


    Spacer(modifier = Modifier.padding(16.dp))

    Button(
        onClick = {
            // Validación de credenciales específicas
            if (correo == "ed@gmail.com" && password == "Edm102_8") {
                // Si las credenciales son correctas, continúa
                val intent = Intent(contexto, MainActivity2::class.java)
                intent.putExtra("firstKeyName", correo)
                intent.putExtra("secondKeyName", password)
                contexto.startActivity(intent)
            } else {
                // Si las credenciales son incorrectas, muestra un mensaje de error
                Toast.makeText(
                    contexto,
                    "Usuario o contraseña incorrectos. Intenta nuevamente.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    ) {
        Text(text = "Enviar")
    }


}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting(name = "Android"
        )
    }
}
