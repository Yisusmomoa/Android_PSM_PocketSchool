package com.example.psm_pocketschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.psm_pocketschool.Controller.Login.LoginController
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.View.ILoginView
import com.example.psm_pocketschool.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener, ILoginView {
    private lateinit var binding: ActivityMainBinding
    private var loginController: LoginController? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val textViewSignUp=findViewById<TextView>(R.id.textViewSignUp)
        binding.textViewSignUp.setOnClickListener(this)
        //val btnLogin=findViewById<Button>(R.id.btnLogin)
        binding.btnLogin.setOnClickListener(this)
        loginController= LoginController(this)
    }

    override fun onClick(v: View?) {
        val itemId= v?.id
        when(itemId){
            R.id.textViewSignUp->{
                Log.d("Click", "sign up")
                val intent=Intent(this, SignUp::class.java)
                startActivity(intent)
            }
            R.id.btnLogin->{
                Log.d("Click", "Loginbtn")
                val userAux=User(binding.editTextPasswordLogin.text.toString(),binding.editTextEmailAddressLogin.text.toString())
                loginController?.onLogin(userAux)

            }
        }
    }

    override fun OnLoginSuccess(message: String?) {
        runOnUiThread {
            Toast.makeText(this,message, Toast.LENGTH_LONG).show()
        }
        val intent=Intent(this, Home::class.java)
        startActivity(intent)
    }

    override fun OnLoginError(message: String?) {
        runOnUiThread {
            Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        }
    }

}