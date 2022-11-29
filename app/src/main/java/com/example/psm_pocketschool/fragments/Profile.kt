package com.example.psm_pocketschool.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import com.example.psm_pocketschool.Controller.UpdateUser.UpdateUserConntroller
import com.example.psm_pocketschool.MainActivity
import com.example.psm_pocketschool.Model.User.User
import com.example.psm_pocketschool.R
import com.example.psm_pocketschool.Session.Prefs
import com.example.psm_pocketschool.Session.UserApplication.Companion.prefs
import com.example.psm_pocketschool.View.IUpdateUserView
import com.example.psm_pocketschool.databinding.FragmentProfileBinding
import com.squareup.picasso.Picasso

//TODO -> editar imagen
class Profile : Fragment(), View.OnClickListener, IUpdateUserView {
    private var _binding:FragmentProfileBinding?=null
    private val binding get()=_binding!!

    private lateinit var user:User

    private var updateUserConntroller:UpdateUserConntroller?=null

    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user=prefs.getCredentials()
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_profile, container, false)
        _binding=FragmentProfileBinding.inflate(inflater,container,false)
        binding.txtCarrer.text=user.carrer
        binding.editTextUsName.setText(user.username)
        binding.editTextPersonName.setText(user.name)
        Picasso.get().load(user.imgUser).into(binding.imgProfilePic)
        updateUserConntroller= UpdateUserConntroller(this)

        binding.btnSaveChange.setOnClickListener(this)
        binding.changeImg.setOnClickListener(this)
        binding.btnCerrarSesion.setOnClickListener(this)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onClick(v: View?) {
        val itemId= v?.id
        when(itemId){
            R.id.btnSaveChange->{
                val userAux=User(binding.editTextPersonName.text.toString(),
                    binding.editTextUsName.text.toString(),
                    binding.editTextPassword.text.toString())
                if (validaciones(userAux)){
                    updateUserConntroller!!.onUpdate(userAux)
                }
                else{
                    Toast.makeText(activity, "No puedes capturar campos vacios", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.changeImg->{
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)
            }
            R.id.btnCerrarSesion->{
                prefs.wipe()
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun OnUpdateSuccess(message: String?) {
        requireActivity().runOnUiThread {
            Toast.makeText(activity,message, Toast.LENGTH_LONG).show()
        }
        val fragmentB = Home()
        activity?.getSupportFragmentManager()!!.beginTransaction()
            .replace(R.id.frame_layout, fragmentB, "fragmnetId")
            .commit();
    }

    override fun OnUpdateError(message: String?) {
        requireActivity().runOnUiThread {
            Toast.makeText(activity,message, Toast.LENGTH_LONG).show()
        }
    }

    fun validaciones(user:User):Boolean{
        var resultado=true

        if (user.name.isEmpty() || user.username.isEmpty()){
            resultado=false
        }
        return resultado
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            binding.imgProfilePic.setImageURI(imageUri)
        }
    }


}