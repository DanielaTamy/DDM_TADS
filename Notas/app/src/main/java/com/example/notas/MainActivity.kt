package com.example.notas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var nome: EditText
    private lateinit var nota1: EditText
    private lateinit var nota2: EditText
    private lateinit var frequencia: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nome = findViewById(R.id.nome)
        nota1 = findViewById(R.id.nota1)
        nota2 = findViewById(R.id.nota2)
        frequencia = findViewById(R.id.frequencia)
    }

    fun calcular(view: View) {
        val nomeAluno = nome.text.toString()
        val valorNota1 = nota1.text.toString().toDoubleOrNull()
        val valorNota2 = nota2.text.toString().toDoubleOrNull()
        val valorFrequencia = frequencia.text.toString().toIntOrNull()

        if (nomeAluno.isEmpty() || valorNota1 == null || valorNota2 == null || valorFrequencia == null) {
            Toast.makeText(this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show()
            return
        }

        val media = (valorNota1 + valorNota2) / 2

        val situacao = if (valorFrequencia < 75) {
            "Reprovado por falta"
        } else if (media >= 7) {
            "Aprovado"
        } else if (media >= 4) {
            "Final"
        } else {
            "Reprovado por nota"
        }

        val intent = Intent(this, ResultadoActivity::class.java)
        intent.putExtra("nome", nomeAluno)
        intent.putExtra("nota1", valorNota1)
        intent.putExtra("nota2", valorNota2)
        intent.putExtra("media", media)
        intent.putExtra("frequencia", valorFrequencia)
        intent.putExtra("situacao", situacao)

        startActivity(intent)
    }
}