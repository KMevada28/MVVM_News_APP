package com.newsapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.newsapp.lrf.LRFActivity
import com.newsapp.utils.Constants.Companion.navigateToActivity
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.let {
            return inflater.inflate(R.layout.fragment_login, container, false)
        }
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            activity?.let {
                linkSignUp.setOnClickListener {

                    /*val name = enterName.text.toString()
                    if (name.isEmpty()) {
                        enterName.error = "Please enter a name"
                    } else {*/
                   /* val action = LoginFragmentDirections.toSignUpFragment()
                    *//*action.setNameToShow(name)*//*
                    findNavController().navigate(action)*/
                    val navController = Navigation.findNavController(activity , R.id.nav_graph)
                    navController.navigate(R.id.to_signUpFragment)

                    /*}*/
                }

                forgotPassword.setOnClickListener {
                    findNavController().navigate(LoginFragmentDirections.ActionLoginFragmentToForgotPasswordFragment())
                }

                btnLogin.setOnClickListener {
                    navigateToActivity(this.context, (MainActivity::class.java))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            print(e.printStackTrace())
        }


    }
}
