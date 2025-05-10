package com.example.myapplicationcompose

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.OutlinedTextField
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicationcompose.ui.theme.MyApplicationComposeTheme


class MainActivity2 : ComponentActivity() {

    var firstKeyName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            // getIntent() is a method from the started activity
            val myIntent = intent // gets the previously created intent
            firstKeyName = myIntent.getStringExtra("firstKeyName") // will return "FirstKeyValue"
            val userName = intent.getStringExtra("firstKeyName") ?: ""

            Toast.makeText(this, userName, Toast.LENGTH_LONG).show()

            MyApplicationComposeTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxHeight()
                ) { innerPadding ->
                    Greeting2(
                        name = userName,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }


    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    val activity = (LocalContext.current as? Activity)

    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Calculadora")
        Text(text = "Usuario: $name")

        Spacer(modifier = Modifier.padding(top = 8.dp))

        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("#1") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("#2") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.padding(top = 12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                resultado = ((num1.toDoubleOrNull() ?: 0.0) + (num2.toDoubleOrNull() ?: 0.0)).toString()
            }) {
                Text("SUMAR")
            }
            Button(onClick = {
                resultado = ((num1.toDoubleOrNull() ?: 0.0) - (num2.toDoubleOrNull() ?: 0.0)).toString()
            }) {
                Text("RESTAR")
            }
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                resultado = ((num1.toDoubleOrNull() ?: 0.0) * (num2.toDoubleOrNull() ?: 0.0)).toString()
            }) {
                Text("MULTIPLICAR")
            }
            Button(onClick = {
                val divisor = num2.toDoubleOrNull()
                resultado = if (divisor == null || divisor == 0.0) {
                    "División inválida"
                } else {
                    ((num1.toDoubleOrNull() ?: 0.0) / divisor).toString()
                }
            }) {
                Text("DIVIDIR")
            }
        }

        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text("Resultado")

        OutlinedTextField(
            value = resultado,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        Button(onClick = { activity?.finish() }) {
            Text("Salir")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyApplicationComposeTheme {
        Greeting2("username")
    }
}
