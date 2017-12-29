package com.yaky.betaseriefollowing.ui.activities

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import com.yaky.betaseriefollowing.Exception.CredentialException
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.classes.User
import com.yaky.betaseriefollowing.domain.request.RequestToBetaSerie
import com.yaky.betaseriefollowing.ui.App
import kotlinx.android.synthetic.main.login.*
import org.jetbrains.anko.*
import org.json.JSONObject
import java.io.IOException





class LoginActivity: BaseActivity(), MenuItem.OnMenuItemClickListener, AnkoLogger {
     var user: User? = null
    ///TODO: class containin all the key
    companion object {
        private val userNameKeyPrefs: String = "name"
        private val userPasswordKeyPrefs: String = "password"
        private val tokenKeyFromApi: String = "token"
        private val bundleKeyUser: String = "user"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        ///TODO: beter prefs managment ?
        val prefs = PreferenceManager.getDefaultSharedPreferences(App.instance)
        if(prefs.getString(userNameKeyPrefs, "") != "" && prefs.getString(userPasswordKeyPrefs,"") != ""){
            val user = User(prefs.getString(userNameKeyPrefs, ""))
            user.setEncryptedPassword( prefs.getString(userPasswordKeyPrefs,""))
            try{
                debug{"using prefs for login"}
                getToken(user,  {token: String ->
                    run {
                        user.token = JSONObject(token).getString(tokenKeyFromApi)
                        info { "token : " + user.token }
                        this.user = user
                   //     startActivity(Intent(this, ListSeriesActivity::class.java))
                    }
                })

            }catch (e: Exception){
                when (e) {
                    is NullPointerException -> {
                        App.instance.toast(App.instance.getString(R.string.LoginNullPointerServer))
                    }
                }
            }

        }
        else {
            validate.setOnClickListener {
                val password = password.text.toString()
                val user = login.text.toString()
                if (user == "" || password == "") {
                    toast(App.instance.getString(R.string.LoginInputError))
                } else {
                    ///TODO : waiter for user info
                    val newUser = User(user, password)
                    debug{"using input user for login "}
                    getToken(newUser, {token: String ->
                        run {
                            newUser.token = JSONObject(token).getString(tokenKeyFromApi)
                            info { "token : " + newUser.token }
                            prefs.edit().apply {
                                putString(userNameKeyPrefs, newUser.login)
                                putString(userPasswordKeyPrefs, newUser.password)
                                apply()
                            }
                            this.user = newUser
                        }
                    })
                }
            }
        }
    }

    override  fun onMenuItemClick(item: MenuItem): Boolean {
        debug { "onMenuItemClick overriden : => send user"}
        item.isChecked = true
        when (item.itemId) {
            R.id.menuListSeries -> {
                val intent = Intent(this, ListSeriesActivity::class.java)
                intent.putExtra(bundleKeyUser, this.user)
                startActivity(intent)
                return true
            }
        }
        return false
    }



    private fun getToken(user: User, callback: (m: String) -> Unit)   {
        doAsync {
            try {
                val response = RequestToBetaSerie().requestCredential(user)
                if(response != "") {
                    info{"call callback"}
                    callback(response)
                }else{
                    warn{"Response is null => throw null pointer exception"}
                    throw NullPointerException()
                }
            } catch (e: Exception) {
                when (e) {
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