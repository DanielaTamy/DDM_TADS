package com.example.notas

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nome = intent.getStringExtra("nome")
        val nota1 = intent.getDoubleExtra("nota1", 0.0)
        val nota2 = intent.getDoubleExtra("nota2", 0.0)
        val media = intent.getDoubleExtra("media", 0.0)
        val frequencia = intent.getIntExtra("frequencia", 0)
        val situacao = intent.getStringExtra("situacao")

        val txtNome = findViewById<TextView>(R.id.txtNome)
        val txtMedia = findViewById<TextView>(R.id.txtMedia)
        val txtFrequencia = findViewById<TextView>(R.id.txtFrequencia)
        val resultado = findViewById<TextView>(R.id.resultado)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        txtNome.text = "Aluno: $nome"
        txtMedia.text = "Notas: $nota1 e $nota2\nMédia final: %.1f".format(media)
        txtFrequencia.text = "Frequência: $frequencia%"
        resultado.text = "Situação: $situacao"

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}