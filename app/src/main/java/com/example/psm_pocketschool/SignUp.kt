package com.example.psm_pocketschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.psm_pocketschool.Controller.GetCarrers.GetCarrersController
import com.example.psm_pocketschool.Controller.Register.RegisterController
import com.example.psm_pocketschool.Model.Carrer.Carrer
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.View.IGetCarrersView
import com.example.psm_pocketschool.View.IRegisterView
import com.example.psm_pocketschool.databinding.ActivitySignUpBinding
import java.util.*
import kotlin.collections.ArrayList

class SignUp : AppCompatActivity(), View.OnClickListener,
    IRegisterView, AdapterView.OnItemSelectedListener, IGetCarrersView {
    private lateinit var binding:ActivitySignUpBinding

    var registerController:RegisterController?=null
    var getCarrersController:GetCarrersController?=null

    private lateinit var typeUserSelecc: String
    private lateinit var typeUsers: ArrayAdapter<String>

    private lateinit var carrers: ArrayAdapter<String>
    private lateinit var carrerSelecc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerController= RegisterController(this)
        getCarrersController=GetCarrersController(this)
        getCarrersController!!.getCarrers()

        initSpinner()
        //initSpinnerCarrer()

        binding.textViewLogin.setOnClickListener(this)
        binding.btnSignUp.setOnClickListener(this)
    }

    //TODO: Progress bar,

    override fun onClick(v: View?) {
        val itemId= v?.id
        when(itemId){
            R.id.textViewLogin->{
                Log.d("Click", "login")
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.btnSignUp->{
                //binding.progressBar.isVisible=true
                val userAux=User(
                    binding.editTextPersonName.text.toString(),
                    binding.editTextUserName.text.toString(),
                    binding.editTextPassword.text.toString(),
                    binding.editTextEmailAddress.text.toString(),
                    typeUserSelecc,
                    carrerSelecc
                )
                if (validaciones(userAux)){
                    registerController?.onRegister(userAux)
                    //binding.progressBar.isVisible=false
                }
            }
        }
    }

    fun initSpinner(){
        typeUsers=ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        typeUsers.addAll(Arrays.asList("Student", "Teacher"))

        binding.spinnerTipoUsuario.onItemSelectedListener=this
        binding.spinnerTipoUsuario.adapter=typeUsers
    }
    fun initSpinnerCarrer(listCarrers: ArrayList<Carrer>){
        carrers=ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        var listNameCarrers= arrayListOf<String>()
        listCarrers.forEach {
            listNameCarrers!!.add(it.name)
        }
        carrers.addAll(listNameCarrers!!)
        binding.spinnerCarrera.onItemSelectedListener=this
        binding.spinnerCarrera.adapter=carrers

    }
    fun validaciones(user: User):Boolean{
        var resultado=true

        if (user.name.isEmpty() || user.email.isEmpty() || user.username.isEmpty() || user.password.isEmpty()){
            resultado=false
            Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show()
        }

        if (user.password.length<8){
            resultado=false
            Toast.makeText(this, "La contrasea debe de tener minimo 8 caracteres", Toast.LENGTH_SHORT).show()
        }
        if(user.password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull()==null){
            resultado=false
            Toast.makeText(this, "La contrasea debe de tener minimo 1 mayuscula", Toast.LENGTH_SHORT).show()
        }
        if(user.password.filter{ it.isDigit() }.firstOrNull() == null){
            resultado=false
            Toast.makeText(this, "La contrasea debe de tener minimo 1 numero", Toast.LENGTH_SHORT).show()
        }
        if(user.password.filter{ it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null){
            resultado=false
            Toast.makeText(this, "La contrasea debe de tener minimo 1 minuscula", Toast.LENGTH_SHORT).show()
        }
        return resultado
    }

    override fun OnRegisterSuccess(message: String?) {
        runOnUiThread {
            Toast.makeText(this,message, Toast.LENGTH_LONG).show()
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun OnRegisterError(message: String?) {
        runOnUiThread {
            Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val spinner=parent
        if (spinner!!.id===R.id.spinnerCarrera){
            carrerSelecc=carrers.getItem(position).toString()
        }
        else if(spinner!!.id===R.id.spinnerTipoUsuario){
            typeUserSelecc= typeUsers.getItem(position).toString()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onSuccessCarrers(carrers: ArrayList<Carrer>) {
        initSpinnerCarrer(carrers)
    }

}