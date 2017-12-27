package com.yaky.betaseriefollowing.ui.activities

import android.os.Bundle
import com.yaky.betaseriefollowing.Exception.CredentialException
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.classes.User
import com.yaky.betaseriefollowing.domain.request.RequestToBetaSerie
import com.yaky.betaseriefollowing.ui.App
import kotlinx.android.synthetic.main.login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.io.IOException

class LoginActivity: BaseActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        validate.setOnClickListener {
            val password =  password.text.toString()
            val user = login.text.toString()
            if(user == "" || password == ""){
                toast(App.instance.getString(R.string.LoginInputError))
            }
            else{
                ///TODO : waiter for user info
                val user = User(user, password)
                doAsync {
                    try {
                        val response = RequestToBetaSerie().requestCredential(user)
                    }catch (e : Exception){
                        when(e){
                            is CredentialException -> {
                                uiThread {
                                    App.instance.toast(App.instance.getString(R.string.LoginWrongCredential))
                                }
                            }
                            is IOException -> {
                                uiThread {
                                    App.instance.toast(App.instance.getString(R.string.ConnectionException))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}