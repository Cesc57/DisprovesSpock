package com.example.disprovesspock

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.disprovesspock.databinding.ActivityOpcionesBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_opciones.*

enum class ProviderType {
    BASIC
}

class OpcionesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpcionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpcionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //SETUP
        val bundle: Bundle? = intent.extras
        val nombre: String? = bundle?.getString("nombre")
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")
        //setup()   //email?:"",provider?:""

        //Guardar datos
        val prefs = getSharedPreferences(getString(
            R.string.prefs_file),
            Context.MODE_PRIVATE).edit()
        prefs.putString("nombre", nombre)
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

        btnCerrar.setOnClickListener {
            val prefs = getSharedPreferences(getString(
                R.string.prefs_file),
                Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnJuego.setOnClickListener {
            val intent = Intent(this, MesaJuego::class.java)
            startActivity(intent)
            finish()
        }

        //tvUsuario.setText("Bienvenid@ $nombre")

    }

}