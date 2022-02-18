package com.example.disprovesspock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.disprovesspock.databinding.ActivityFinJuegoBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_fin_juego.*

class FinJuego : AppCompatActivity() {

    private lateinit var binding: ActivityFinJuegoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFinJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this) {}

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        //SET INFO
        val bundle: Bundle? = intent.extras
        val resultado:Int?=bundle?.getInt("resultado")
        val puntuacion:Int?=bundle?.getInt("puntuacion")

        tvPuntaje.text= puntuacion.toString()
        if (resultado==1){
            tvResult.text="HAS GANADO"
        }else if(resultado==-1){
            tvResult.text="HAS PERDIDO"
        }

        btnOtra.setOnClickListener {
            val intent= Intent(this,MesaJuego::class.java)
            startActivity(intent)
            finish()
        }

        btnPrincipal.setOnClickListener {
            val intent= Intent(this,OpcionesActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}