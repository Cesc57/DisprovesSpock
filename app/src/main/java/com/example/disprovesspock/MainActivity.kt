package com.example.disprovesspock

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.disprovesspock.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
        session()
        notification()

//        btnEntrar.setOnClickListener {
//            val juegoIntent = Intent(this, MesaJuego::class.java)
//            startActivity(juegoIntent)
//        }
//
//        btnRegistrar.setOnClickListener {
//            val juegoIntent = Intent(this, OpcionesActivity::class.java)
//            startActivity(juegoIntent)
//        }

    }

    private fun setup() {
        btnRegistrar.setOnClickListener {
            if (etNombre.text.isNotEmpty() && etMail.text.isNotEmpty() && etPass.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(etMail.text.toString(),
                        etPass.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showOpciones(it.result.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }

            }
        }

        btnEntrar.setOnClickListener {

            if (etNombre.text.isNotEmpty() && etMail.text.isNotEmpty() && etPass.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(etMail.text.toString(),
                        etPass.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showOpciones(it.result.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }

            }
        }

    }

    //Notificaciones TOKEN:

    private fun notification(){

        FirebaseMessaging.getInstance().token.addOnCompleteListener{ task ->
            val token= task.result
            FBtoken.text=token.toString()
            println("\nMi Token: $token")
        }

    }

    private fun showOpciones(email: String, provider: ProviderType) {
        val optionIntent: Intent = Intent(this, OpcionesActivity::class.java).apply {
            val nombe = etNombre.text.toString()
            putExtra("nombre", nombe)
            putExtra("email", email)
            putExtra("provider", provider.name)

        }
        startActivity(optionIntent)
        finish()
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome() {
        val homeIntent = Intent(this, OpcionesActivity::class.java)
        startActivity(homeIntent)
        finish()

    }

    private fun session() {
        val prefs = getSharedPreferences(
            getString(
                R.string.prefs_file
            ),
            Context.MODE_PRIVATE
        )

        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            binding.authLayout.visibility = View.INVISIBLE
            showHome()
        }
        //finish()
    }

}