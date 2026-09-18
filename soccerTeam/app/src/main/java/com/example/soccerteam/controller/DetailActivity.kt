package com.example.soccerteam.controller

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.soccerteam.R
import com.example.soccerteam.model.SoccerTeam
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val team = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getSerializableExtra("TEAM", SoccerTeam::class.java)
        } else {
            intent.getSerializableExtra("TEAM") as SoccerTeam
        }

        if(team != null){
            findViewById<ImageView>(R.id.teamImageView).setImageResource(team.imageResId)
            findViewById<TextView>(R.id.nameTextView).text = "Time: ${team.name}"
            findViewById<TextView>(R.id.cityTextView).text = "Cidade: ${team.city}"
            findViewById<TextView>(R.id.stateTextView).text = "Estado: ${team.state}"

            findViewById<Button>(R.id.backButton).setOnClickListener {
                finish()
            }


        }else{
            Toast.makeText(this, "Não foi possível carregar o time", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}