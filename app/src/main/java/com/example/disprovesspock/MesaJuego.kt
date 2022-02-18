package com.example.disprovesspock

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.disprovesspock.databinding.ActivityMesaJuegoBinding
import kotlinx.android.synthetic.main.activity_mesa_juego.*

class MesaJuego : AppCompatActivity() {

    private lateinit var binding: ActivityMesaJuegoBinding

    private val resetImagen = { dialog: DialogInterface, which: Int ->
        imgRival.setBackgroundResource(R.drawable.back)
        esElFinal()
    }



    private val ganar: Int = 3
    private val empatar: Int = 1
    private var puntaje: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMesaJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ibPiedra.setOnClickListener {
            //Toast.makeText(this,"Hola Angel, has elegido piedra",Toast.LENGTH_SHORT).show()
            val robotHand = (1..5).random()
            imgRival.setBackgroundResource(actualizarImagen(robotHand))
            val ronda: Int = hacerRonda(1, robotHand)
            mostrarAlerta(ronda)
            actualizarMarcador(ronda)
        }

        ibPapel.setOnClickListener {
            //Toast.makeText(this,"Hola Angel, has elegido papel",Toast.LENGTH_SHORT).show()
            val robotHand = (1..5).random()
            imgRival.setBackgroundResource(actualizarImagen(robotHand))
            val ronda: Int = hacerRonda(2, robotHand)
            mostrarAlerta(ronda)
            actualizarMarcador(ronda)
        }

        ibTijeras.setOnClickListener {
            //Toast.makeText(this,"Hola Angel, has elegido tijeras",Toast.LENGTH_SHORT).show()
            val robotHand = (1..5).random()
            imgRival.setBackgroundResource(actualizarImagen(robotHand))
            val ronda: Int = hacerRonda(3, robotHand)
            mostrarAlerta(ronda)
            actualizarMarcador(ronda)
        }

        ibLagarto.setOnClickListener {
            //Toast.makeText(this,"Hola Angel, has elegido lagarto",Toast.LENGTH_SHORT).show()
            val robotHand = (1..5).random()
            imgRival.setBackgroundResource(actualizarImagen(robotHand))
            val ronda: Int = hacerRonda(4, robotHand)
            mostrarAlerta(ronda)
            actualizarMarcador(ronda)
        }

        ibSpock.setOnClickListener {
            //Toast.makeText(this,"Hola Angel, has elegido spock",Toast.LENGTH_SHORT).show()
            val robotHand = (1..5).random()
            imgRival.setBackgroundResource(actualizarImagen(robotHand))
            val ronda: Int = hacerRonda(5, robotHand)
            mostrarAlerta(ronda)
            actualizarMarcador(ronda)
        }

    }

    private fun esElFinal() {
        if (tvLocal.text.toString().toInt()==3){
            //Toast.makeText(this,"HAS GANADO",Toast.LENGTH_SHORT).show()
            puntaje+=(6..10).random()
            irFinJuego(1)
        }else if (tvVisitante.text.toString().toInt()==3){
            //Toast.makeText(this,"HAS PERDIDO",Toast.LENGTH_SHORT).show()
            puntaje+=(1..4).random()
            irFinJuego(-1)
        }
    }

    private fun irFinJuego(resultado: Int) {
        val finJuego= Intent(this,FinJuego::class.java).apply {
            putExtra("resultado",resultado)
            putExtra("puntuacion",puntaje)
        }
        startActivity(finJuego)
        finish()
    }

    private fun mostrarAlerta(ronda: Int) {
        val builder = AlertDialog.Builder(this)
        when (ronda) {
            1 -> {
                builder.setTitle("HAS GANADO")
                builder.setMessage("3 puntos")
            }
            0 -> {
                builder.setTitle("EMPATE")
                builder.setMessage("1 puntos")
            }
            else -> {
                builder.setTitle("HAS PERDIDO")
                builder.setMessage("0 puntos")
            }
        }
        builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = resetImagen))
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun actualizarImagen(robotHand: Int): Int {
        var suNuevaImagen = -1
        when (robotHand) {
            1 -> {
                suNuevaImagen = R.drawable.piedra
            }
            2 -> {
                suNuevaImagen = R.drawable.papel
            }
            3 -> {
                suNuevaImagen = R.drawable.tijeras
            }
            4 -> {
                suNuevaImagen = R.drawable.lagarto
            }
            5 -> {
                suNuevaImagen = R.drawable.spock
            }
        }

        return suNuevaImagen

    }

    private fun actualizarMarcador(ronda: Int) {

        when (ronda) {
            -1 -> {
                val nuevo = tvVisitante.text.toString().toInt() + 1
                tvVisitante.text = nuevo.toString()
            }
            1 -> {
                val nuevo = tvLocal.text.toString().toInt() + 1
                tvLocal.text = nuevo.toString()
                puntaje += ganar
                puntuacion.text = puntaje.toString()
            }
            else -> {
                puntaje += empatar
                puntuacion.text = puntaje.toString()
            }
        }

    }

    private fun hacerRonda(myHand: Int, robotHand: Int): Int {
        var result = -1
        if (myHand == robotHand) {
            result = 0
        } else if (myHand == 1) {

            if (robotHand == 3 || robotHand == 4) {
                result = 1
            }

        } else if (myHand == 2) {

            if (robotHand == 1 || robotHand == 5) {
                result = 1
            }

        } else if (myHand == 3) {

            if (robotHand == 2 || robotHand == 4) {
                result = 1
            }

        } else if (myHand == 4) {

            if (robotHand == 2 || robotHand == 5) {
                result = 1
            }

        } else if (myHand == 5) {

            if (robotHand == 1 || robotHand == 3) {
                result = 1
            }

        }

        return result

    }
}