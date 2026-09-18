package com.example.soccerteam.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.soccerteam.R
import com.example.soccerteam.model.SoccerTeam

class MainActivity : AppCompatActivity() {

    private lateinit var teamList: List<SoccerTeam>
    private lateinit var teamSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        teamSpinner = findViewById<Spinner>(R.id.teamSpinner)
        teamList = listOf(
            SoccerTeam("Cruzeiro", "Belo Horizonte", "MG", R.drawable.cruzeiro),
            SoccerTeam("Flamengo", "Rio de janeiro",  "RJ", R.drawable.flamengo),
            SoccerTeam("Internacional", "Porto Alegre", "RS", R.drawable.internacional),
            SoccerTeam("Palmeiras", "São Paulo", "SP", R.drawable.palmeiras))

        val teamNames = teamList.map {it.name}

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            teamNames)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        teamSpinner.adapter = adapter

    }

    fun getTeam(view: View){
        val selectedPosition = teamSpinner.selectedItemPosition
        val selectedTeam = teamList[selectedPosition]

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("TEAM", selectedTeam)

        startActivity(intent)
    }
}