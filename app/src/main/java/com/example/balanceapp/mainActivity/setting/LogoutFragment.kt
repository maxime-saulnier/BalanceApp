package com.example.balanceapp.mainActivity.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.balanceapp.authActivity.AuthActivity
import com.example.balanceapp.authActivity.Token
import com.example.balanceapp.databinding.FragmentLogoutBinding


class LogoutFragment : Fragment() {

    private var _binding: FragmentLogoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        context?.let { Token.delJWT(it) }
        val intent = Intent(context, AuthActivity::class.java)
        startActivity(intent)

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}