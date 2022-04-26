package com.example.balanceapp.authActivity.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.balanceapp.authActivity.Authentication
import com.example.balanceapp.R
import com.example.balanceapp.databinding.FragmentLoginBinding
import com.example.balanceapp.dto.UserLoginDTO
import com.example.balanceapp.mainActivity.MainActivity
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Register.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
        }
        binding.login.setOnClickListener {
            val email = binding.email.getText().toString()
            val password = binding.password.getText().toString()
            Log.d("MonActivity", "email: " + email + " password: " + password)
            if(email.isEmailValid() && password.length>=8)
            {
                val userlogin: UserLoginDTO = UserLoginDTO(email, password)
                this.context?.let { it1 -> Authentication.login(it1, view, userlogin) }
            }
            else
            {
                var text= ""
                if(!email.isEmailValid())
                {
                    text+=" !adresse email non conforme! "
                    var textView = binding.email
                    textView.text.clear()
                    textView.setHintTextColor(Color.parseColor("#FF0000"))
                }
                if(password.length<8)
                {
                    text+=" !mot de passe trop petit! "
                    var textView = binding.password
                    textView.text.clear()
                    textView.setHintTextColor(Color.parseColor("#FF0000"))
                }
                Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
            }
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }



    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}