package com.example.sanssystechnology.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sanssystechnology.R
import com.example.sanssystechnology.databinding.FragmentLoginScreenBinding
import com.example.sanssystechnology.fragment.dashbaord.DashboardScreen
import com.example.sanssystechnology.util.Session
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task

class LoginScreen : Fragment() {
    private var _binding: FragmentLoginScreenBinding? = null
    private val binding get() = _binding!!
    lateinit var session: Session


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun googleLogin() {
        checkForLastSignIn(GoogleSignIn
            .getClient(this.requireActivity(),
                GoogleSignInOptions
                    .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()))
    }

    private fun checkForLastSignIn(mGoogleSignInClient: GoogleSignInClient) {
        val account = GoogleSignIn.getLastSignedInAccount(this.requireActivity())

        if(account == null) {
            signIn(mGoogleSignInClient)
        } else {
            println(account)
            //signOut(mGoogleSignInClient)
            signOut(mGoogleSignInClient)
        }
    }
    private fun signIn(mGoogleSignInClient: GoogleSignInClient) {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            session.setLoggedIn(
                loggedIn = true,
                email = account.email,
                username = account.displayName,
                id = account.id,
                password = "12345"
            )
            toDashboard()

        } catch (e: ApiException) {
            Log.w("error", "signInResult:failed code=" + e.statusCode)
        }
    }

    private fun toDashboard() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, DashboardScreen())
        transaction.commit()
    }

    private fun signOut(mGoogleSignInClient: GoogleSignInClient) {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this.requireActivity(), OnCompleteListener<Void?> {
                println("LogOut succesfully")
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        session = Session(this.requireContext())
        binding.btnGoogleLogin.setOnClickListener {
            googleLogin()
        }
    }
}