package com.example.autogallerykotlin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.autogallerykotlin.R
import com.example.autogallerykotlin.databinding.FragmentAddAdvertiseBinding
import com.example.autogallerykotlin.databinding.FragmentUploadImagesBinding

class UploadImagesFragment : Fragment() {
    private var _binding: FragmentUploadImagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUploadImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.uploadImageBackButton.setOnClickListener {
            findNavController().navigate(UploadImagesFragmentDirections.actionUploadImagesFragmentToAdvertsFragment())
        }


    }

}