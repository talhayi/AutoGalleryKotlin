package com.example.autogallerykotlin.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.autogallerykotlin.R
import com.example.autogallerykotlin.databinding.ActivityForgotPasswordBinding
import com.example.autogallerykotlin.viewmodel.ForgotPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private val viewModel: ForgotPasswordViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_login), MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (sharedPreferences.getString(getString(R.string.shared_pref_user_id), null) != null
            && sharedPreferences.getString(getString(R.string.shared_pref_user_email), null) != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.forgotPasswordEmailButton.setOnClickListener {
            val email = binding.forgotPasswordEditText.text.toString().trim()
            viewModel.forgotPasswordEmail(email)
        }
        viewModel.forgotPasswordEmail.observe(this) { forgotPasswordEmailResponse ->
            if (forgotPasswordEmailResponse.isSuccessful) {
                if (forgotPasswordEmailResponse.body()?.email != null) {
                    binding.apply {
                        forgotPasswordEditText.visibility = View.GONE
                        forgotPasswordEmailButton.visibility = View.GONE
                        forgotPasswordtextView.visibility = View.GONE
                        forgotPasswordCodeEditText.visibility = View.VISIBLE
                        forgotPasswordCodeButton.visibility = View.VISIBLE
                        forgotPasswordCodetextView.visibility = View.VISIBLE
                    }
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.correct_email_address),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.apply {
            forgotPasswordCodeButton.setOnClickListener {
                    val code = forgotPasswordCodeEditText.text.toString()
                    viewModel.resetPasswordCode(code)
            }
        }

        viewModel.resetPasswordCode.observe(this) { resetPasswordCodeResponse ->
            if (resetPasswordCodeResponse.isSuccessful) {
                if (resetPasswordCodeResponse.body()?.success == true) {
                    binding.apply {
                        forgotPasswordCodeEditText.visibility = View.GONE
                        forgotPasswordCodeButton.visibility = View.GONE
                        forgotPasswordCodetextView.visibility = View.GONE
                        newPasswordEditText.visibility = View.VISIBLE
                        newPasswordButton.visibility = View.VISIBLE
                        newPasswordtextView.visibility = View.VISIBLE
                    }
                } else {
                    Toast.makeText(
                        this,
                        resetPasswordCodeResponse.body()?.result,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.apply {
            newPasswordButton.setOnClickListener {
                val email = binding.forgotPasswordEditText.text.toString().trim()
                val password = binding.newPasswordEditText.text.toString().trim()
                viewModel.resetPassword(email, password)
            }
        }

        viewModel.resetPassword.observe(this) { resetPasswordResponse ->
            if (resetPasswordResponse.isSuccessful) {
                if (resetPasswordResponse.body()?.success == true) {
                    editor.putString(getString(R.string.shared_pref_user_id), resetPasswordResponse.body()?.id.toString()).apply()
                    editor.putString(getString(R.string.shared_pref_user_email), resetPasswordResponse.body()?.email.toString()).apply()
                    editor.clear()
                    if (sharedPreferences.getString(getString(R.string.shared_pref_user_id), null) != null
                        && sharedPreferences.getString(getString(R.string.shared_pref_user_email), null) != null) {
                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this, getString(R.string.your_password_reset), Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } else {
                    Toast.makeText(this, resetPasswordResponse?.body()?.result, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}