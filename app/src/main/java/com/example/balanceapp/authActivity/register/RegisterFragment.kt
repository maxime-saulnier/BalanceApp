package com.example.balanceapp.authActivity.register

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.balanceapp.R
import com.example.balanceapp.authActivity.Authentication
import com.example.balanceapp.authActivity.SDKregister
import com.example.balanceapp.databinding.FragmentRegisterBinding
import com.example.balanceapp.dto.UserRegisterDTO
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDateTime
import java.util.*
import java.util.regex.Pattern


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var limitCal = Calendar.getInstance()
        val datelimit = binding.DateBirth
        val timeNow = LocalDateTime.now();
        limitCal.set(timeNow.year-100, timeNow.monthValue-1, timeNow.dayOfMonth)
        datelimit.minDate = limitCal.timeInMillis
        limitCal.set(timeNow.year-13, timeNow.monthValue-1, timeNow.dayOfMonth)
        datelimit.maxDate = limitCal.timeInMillis

        binding.Login.setOnClickListener {
            findNavController().navigate(R.id.action_RegisterFragment_to_LoginFragment)
        }
        binding.Register.setOnClickListener {
            val firstName = binding.firstName.getText().toString()
            val lastName = binding.lastName.getText().toString()
            val password = binding.password.getText().toString()
            val date = binding.DateBirth
            limitCal = Calendar.getInstance()
            limitCal.set(date.year, date.month, date.dayOfMonth)
            val birthdate = limitCal.timeInMillis.toString()
            val taille = (if (binding.taille.getText().toString() == "") 0 else binding.taille.getText().toString().toInt())

            val poids = (Math.round((if (binding.poids.getText().toString() == "") 0.0 else binding.poids.getText().toString().toDouble()) * 10).toDouble() / 10)


            var gender = "0"
            if(binding.genre.checkedRadioButtonId.equals(binding.radioButtonFemale.id))
                gender = "1"
            val email = binding.email.getText().toString()
            val shortname =binding.surnom.getText().toString()
            Log.d("MonActivity",""+Pattern.compile("^[a-zA-Z0-9]{3}\$").matcher(shortname).find())
            Log.d("MonActivity",""+!(Pattern.compile("^[a-zA-Z0-9]{3}\$").matcher(shortname).find()))
            if(email.isEmailValid() && password.length>=8 && firstName.length>=3 && lastName.length>=3 && taille in 10..300 && poids in 10.0..600.0 && Pattern.compile("^[a-zA-Z0-9]{3}\$").matcher(shortname).find())
            {
                val userregister: UserRegisterDTO =
                    UserRegisterDTO(firstName, lastName, email, password,shortname,gender,birthdate,taille.toString(),poids.toString())
                this.context?.let { it1 -> SDKregister.register(it1, userregister) }
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
                if(firstName.length<3)
                {
                    text+=" !Nom trop petit! "
                    var textView = binding.firstName
                    textView.text.clear()
                    textView.setHintTextColor(Color.parseColor("#FF0000"))
                }
                if(lastName.length<3)
                {
                    text+=" !Prenom trop petit! "
                    var textView = binding.lastName
                    textView.text.clear()
                    textView.setHintTextColor(Color.parseColor("#FF0000"))
                }
                if(taille < 10 || taille > 300)
                {
                    text+=" !Prenom entre 10 et 300! "
                    var textView = binding.taille
                    textView.text.clear()
                    textView.setHintTextColor(Color.parseColor("#FF0000"))
                }
                if(poids < 10.0 || poids > 600.0)
                {
                    text+=" !Poids entre 10 et 600! "
                    var textView = binding.poids
                    textView.text.clear()
                    textView.setHintTextColor(Color.parseColor("#FF0000"))
                }
                if(!(Pattern.compile("^[a-zA-Z0-9]{3}\$").matcher(shortname).find()))
                {
                    text+=" !surnom pas conforme! "
                    var textView = binding.surnom
                    textView.text.clear()
                    textView.setHintTextColor(Color.parseColor("#FF0000"))
                }
                Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
            }
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
