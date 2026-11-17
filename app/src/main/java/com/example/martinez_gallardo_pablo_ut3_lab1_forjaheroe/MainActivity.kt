package com.example.martinez_gallardo_pablo_ut3_lab1_forjaheroe

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val TAG = "ForjaHeroe"
    private val LogCat: (String) -> Unit = {mensaje -> Log.d(TAG, mensaje)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // se cogen todas las variables del activity_main.xml
        val idNombre = findViewById<EditText>(R.id.idNombre)
        val idRaza = findViewById<Spinner>(R.id.idRaza)
        val idFaccion = findViewById<RadioGroup>(R.id.idFaccion)
        val cbSigilo = findViewById<CheckBox>(R.id.cbSigilo)
        val cbEspada = findViewById<CheckBox>(R.id.cbEspada)
        val btnRegistrar = findViewById<ImageButton>(R.id.btnRegistrar)


        idNombre.requestFocus()

        btnRegistrar.setOnClickListener {
            val nombre = idNombre.text.toString().trim()

            // manda un mensaje de error si el campo del nombre está vacío
            if (nombre.isEmpty()) {
                idNombre.error = getString(R.string.error_nombre)
                idNombre.requestFocus()
                return@setOnClickListener
            }


            // manda un mensaje de error si no hay nada marcado
            val faccion = idFaccion.checkedRadioButtonId
            if (faccion == -1) {
                Toast.makeText(this, R.string.error_faccion, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // se coge el nombre de la raza del desplegable de razas
            val raza = idRaza.selectedItem.toString()
            val fac = findViewById<RadioButton>(faccion).text.toString()

            // si no hay ninguna habilidad marcada aparece como que no hay ninguna habilidad
            val habilidades = listOf(cbSigilo, cbEspada).filter { it.isChecked }
                .joinToString(", ") { it.text.toString() }.ifEmpty { getString(R.string.ninguna) }


            // se crea el mensaje de lo que se ha seleccionado y se lanza el mensaje
            val resumen = getString(R.string.resumen, nombre, raza, fac, habilidades)
            Toast.makeText(this, resumen, Toast.LENGTH_LONG).show()

            LogCat("Personaje --> Nombre: $nombre | Raza: $raza | Facción: $fac | Habilidades: $habilidades")
        }
    }
}