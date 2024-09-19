package com.example.propuestolab1

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.propuestolab1.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Se realiza la configuración del spinner (selector de grupo sanguineo)
        val spinnerGrupoSanguineo = binding.grupoSanguineo
        val adapter = ArrayAdapter.createFromResource(this, R.array.grupos_sanguineos, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGrupoSanguineo.adapter = adapter
        val miArchivo = "miArchivo.txt"
        val btnRegistro = binding.registro
        btnRegistro.setOnClickListener {
            val nombres = binding.nombres.text.toString()
            val apellidos = binding.apellidos.text.toString()
            val correo = binding.correo.text.toString()
            val telefono = binding.telefono.text.toString()
            val dni = binding.dni.text.toString()
            val grupoSanguineo = spinnerGrupoSanguineo.selectedItem.toString()
            val textoBuilder = StringBuilder()
            textoBuilder.append("===Usuario===\n")
            textoBuilder.append("Nombres: ").append(nombres).append("\n")
            textoBuilder.append("Apellidos: ").append(apellidos).append("\n")
            textoBuilder.append("Correo Electronico: ").append(correo).append("\n")
            textoBuilder.append("Telefono: ").append(telefono).append("\n")
            textoBuilder.append("DNI: ").append(dni).append("\n")
            textoBuilder.append("Grupo sanguineo: ").append(grupoSanguineo).append("\n")
            textoBuilder.append("=============\n")

            escribirArchivo(miArchivo, textoBuilder.toString())
            // Se reinicia el formulario
            binding.nombres.text.clear()
            binding.apellidos.text.clear()
            binding.correo.text.clear()
            binding.telefono.text.clear()
            binding.dni.text.clear()
        }
        val btnLeerDatos = binding.leerDatos
        btnLeerDatos.setOnClickListener {
            val archivos = fileList() // Obtener lista de archivos en el almacenamiento interno
            if (archivoExiste(archivos, miArchivo)) {
                leerArchivo(miArchivo)
            } else {
                Toast.makeText(getApplicationContext(), "No hay información para mostrar", Toast.LENGTH_SHORT).show()
                Log.e("Not found", "El archivo aun no existe")
            }
        }

    }

    private fun escribirArchivo(archivo: String, texto: String) {
        // Se usa el MODE_APPEND para sobreescribir el archivo y no empezarlo desde 0
        OutputStreamWriter(openFileOutput(archivo, Activity.MODE_APPEND)).use { writer ->
            writer.write("$texto\n")
            Toast.makeText(getApplicationContext(), "Información guardada", Toast.LENGTH_SHORT).show()
            writer.flush()
            writer.close()
        }
    }
    private fun leerArchivo(archivo: String) {
        try {
            val inputStream = openFileInput(archivo)
            val reader = BufferedReader(InputStreamReader(inputStream))
            var linea = reader.readLine()
            val stringBuilder = StringBuilder()
            while (linea != null) {
                stringBuilder.append(linea).append("\n");
                linea = reader.readLine()
            }
            Toast.makeText(getApplicationContext(), "Reviso el Logcat", Toast.LENGTH_SHORT).show()
            Log.d("Datos guadados", stringBuilder.toString())
            reader.close()

        } catch (e: Exception) {
            Log.e("Error", "No se pudo leer el archivo: ${e.message}")
        }
    }
    fun archivoExiste(archivos: Array<String>, archivo: String): Boolean {
        return archivos.contains(archivo)
    }

}