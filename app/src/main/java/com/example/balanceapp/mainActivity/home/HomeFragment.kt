package com.example.balanceapp.mainActivity.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.balanceapp.databinding.FragmentHomeBinding
import com.example.balanceapp.dto.UserBodyDTO

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val body: UserBodyDTO = UserBodyDTO(25.0,10.0,50.0,10.0,30.0,120.0)
        binding.setWeight.text= body.getweight().toString()+" kg";
        binding.setIMC.text= body.getIMC().toString();
        binding.setFatMass.text= body.getfatMass().toString()+" %";
        binding.setMuscleMass.text= body.getmuscleMass().toString()+" %";
        binding.setWaterMass.text= body.getwaterMass().toString()+" %";
        binding.setBPM.text= body.getBPM().toString();

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}