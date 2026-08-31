package com.example.jogodascapitais

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var currentQuestion = 0
    private var score = 0

    private val totalQuestions = 5

    // Perguntas disponíveis
    private val questions = arrayOf(
        "Qual é a capital do Paraná?",
        "Qual é a capital de Santa Catarina?",
        "Qual é a capital do Rio Grande do Sul?",
        "Qual é a capital de São Paulo?",
        "Qual é a capital do Rio de Janeiro?",
        "Qual é a capital de Minas Gerais?",
        "Qual é a capital do Espírito Santo?",
        "Qual é a capital da Bahia?",
        "Qual é a capital de Pernambuco?",
        "Qual é a capital do Ceará?",
        "Qual é a capital do Maranhão?",
        "Qual é a capital do Pará?",
        "Qual é a capital do Amazonas?",
        "Qual é a capital de Goiás?",
        "Qual é a capital do Mato Grosso?",
        "Qual é a capital do Mato Grosso do Sul?",
        "Qual é a capital do Acre?",
        "Qual é a capital de Rondônia?",
        "Qual é a capital de Roraima?",
        "Qual é a capital do Amapá?"
    )

    // Cada resposta corresponde à pergunta
    // localizada no mesmo índice
    private val correctAnswers = arrayOf(
        "Curitiba",
        "Florianópolis",
        "Porto Alegre",
        "São Paulo",
        "Rio de Janeiro",
        "Belo Horizonte",
        "Vitória",
        "Salvador",
        "Recife",
        "Fortaleza",
        "São Luís",
        "Belém",
        "Manaus",
        "Goiânia",
        "Cuiabá",
        "Campo Grande",
        "Rio Branco",
        "Porto Velho",
        "Boa Vista",
        "Macapá"
    )

    // Índices das 5 perguntas sorteadas
    private val gameQuestions = IntArray(5)

    // Alternativas da pergunta atual
    private val currentOptions = arrayOf("", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sortQuestions()
        showQuestion()
    }

    // Sorteia 5 perguntas sem repetição
    private fun sortQuestions() {
        var position = 0
        while (position < totalQuestions) {
            val number = Random.nextInt(questions.size)
            var repeated = false
            for (i in 0 until position) {
                if (gameQuestions[i] == number) {
                    repeated = true
                }
            }
            if (!repeated) {
                gameQuestions[position] = number
                position++
            }
        }
    }

    // Mostra a pergunta atual
    private fun showQuestion() {
        val txtQuestionNumber =
            findViewById<TextView>(R.id.txtQuestionNumber)
        val txtQuestion =
            findViewById<TextView>(R.id.txtQuestion)
        val radioOption1 =
            findViewById<RadioButton>(R.id.radioOption1)
        val radioOption2 =
            findViewById<RadioButton>(R.id.radioOption2)
        val radioOption3 =
            findViewById<RadioButton>(R.id.radioOption3)
        val radioOption4 =
            findViewById<RadioButton>(R.id.radioOption4)
        val radioGroup =
            findViewById<RadioGroup>(R.id.radioGroupOptions)

        val questionIndex = gameQuestions[currentQuestion]
        txtQuestionNumber.text =
            "Pergunta ${currentQuestion + 1} de $totalQuestions"

        txtQuestion.text =
            questions[questionIndex]

        createOptions(questionIndex)

        radioOption1.text = currentOptions[0]
        radioOption2.text = currentOptions[1]
        radioOption3.text = currentOptions[2]
        radioOption4.text = currentOptions[3]

        radioGroup.clearCheck()
    }

    // Cria quatro alternativas
    private fun createOptions(questionIndex: Int) {
        val correctAnswer =
            correctAnswers[questionIndex]
        // A primeira alternativa começa contendo a resposta correta
        currentOptions[0] = correctAnswer

        var position = 1

        // Sorteia mais três capitais
        while (position < 4) {
            val randomIndex =
                Random.nextInt(correctAnswers.size)

            val answer =
                correctAnswers[randomIndex]
            var repeated = false
            // Verifica se a capital já foi utilizada
            for (i in 0 until position) {
                if (currentOptions[i] == answer) {
                    repeated = true
                }
            }
            if (!repeated) {
                currentOptions[position] = answer
                position++
            }
        }
        shuffleOptions()
    }

    // Embaralha as quatro alternativas
    private fun shuffleOptions() {

        for (i in currentOptions.indices) {
            val randomPosition =
                Random.nextInt(currentOptions.size)
            val temp = currentOptions[i]
            currentOptions[i] =
                currentOptions[randomPosition]
            currentOptions[randomPosition] = temp
        }
    }

    fun answerQuestion(view: View) {
        val radioGroup =
            findViewById<RadioGroup>(R.id.radioGroupOptions)

        val selectedId =
            radioGroup.checkedRadioButtonId

        // Verifica se alguma alternativa foi selecionada
        if (selectedId == -1) {
            Toast.makeText(
                this,
                "Selecione uma resposta.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Descobre qual alternativa foi selecionada
        val selectedAnswer = when (selectedId) {
            R.id.radioOption1 -> currentOptions[0]
            R.id.radioOption2 -> currentOptions[1]
            R.id.radioOption3 -> currentOptions[2]
            R.id.radioOption4 -> currentOptions[3]
            else -> ""
        }

        // Índice da pergunta atual
        val questionIndex =
            gameQuestions[currentQuestion]

        // Verifica a resposta
        if (selectedAnswer == correctAnswers[questionIndex]) {
            score += 2
            Toast.makeText(
                this,
                "Correto!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this,
                "Incorreto!",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Avança para a próxima pergunta
        currentQuestion++

        // Verifica se o jogo terminou
        if (currentQuestion < totalQuestions) {
            showQuestion()
        } else {
            showFinalResult()
        }
    }

    // Apresenta a pontuação final
    private fun showFinalResult() {
        val txtQuestionNumber =
            findViewById<TextView>(R.id.txtQuestionNumber)
        val txtQuestion =
            findViewById<TextView>(R.id.txtQuestion)
        val txtResult =
            findViewById<TextView>(R.id.txtResult)
        val radioGroup =
            findViewById<RadioGroup>(R.id.radioGroupOptions)
        val btnAnswer =
            findViewById<Button>(R.id.btnAnswer)


        txtQuestionNumber.text = "Fim do jogo"
        txtQuestion.text = "Pontuação final"
        txtResult.text =
            "$score de ${totalQuestions * 2} pontos"

        radioGroup.visibility = View.GONE
        btnAnswer.visibility = View.GONE
    }
}