package com.example.conversormoeda

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }
    }

    fun converter(view: View) {
        val etValor = findViewById<EditText>(R.id.etValor)
        val rgDe = findViewById<RadioGroup>(R.id.rgDe)
        val rgPara = findViewById<RadioGroup>(R.id.rgPara)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        if (etValor.text.toString().isEmpty()) {
            Toast.makeText(
                this,
                "Digite um valor",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (rgDe.checkedRadioButtonId == -1 ||
            rgPara.checkedRadioButtonId == -1
        ) {
            Toast.makeText(
                this,
                "Selecione as duas moedas",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val valor = etValor.text.toString().toDouble()
        val moedaDe = getMoeda(rgDe.checkedRadioButtonId)
        val moedaPara = getMoeda(rgPara.checkedRadioButtonId)

        val resultado =
            converterMoeda(valor, moedaDe, moedaPara)

        tvResultado.text =
            "%.2f %s".format(resultado, moedaPara)
    }

    fun getMoeda(id: Int): String = when (id) {
        R.id.rbDeReal,
        R.id.rbParaReal -> "BRL"

        R.id.rbDeDolar,
        R.id.rbParaDolar -> "USD"

        R.id.rbDeEuro,
        R.id.rbParaEuro -> "EUR"

        else -> "BRL"
    }

    fun converterMoeda(
        valor: Double,
        de: String,
        para: String
    ): Double {

        val emBRL = when (de) {
            "USD" -> valor * 5.20
            "EUR" -> valor * 5.65
            else -> valor
        }

        return when (para) {
            "USD" -> emBRL / 5.20
            "EUR" -> emBRL / 5.65
            else -> emBRL
        }
    }
}