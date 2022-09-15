package com.vagrok.vag_collar.activities



import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.royrodriguez.transitionbutton.TransitionButton
import com.vagrok.vag_collar.R
import com.vagrok.vag_collar.activities.PreferenceHelper.customPreference
import com.vagrok.vag_collar.activities.PreferenceHelper.password
import com.vagrok.vag_collar.activities.PreferenceHelper.userId
import com.vagrok.vag_collar.helpers.IS_FIRST_TIME
import com.google.android.material.textfield.TextInputEditText;
import com.vagrok.vag_collar.activities.PreferenceHelper.clearValues
import com.vagrok.vag_collar.activities.PreferenceHelper.user_logged
import com.vagrok.vag_collar.helpers.USER_ID
import com.vagrok.vag_collar.helpers.USER_PASSWORD
import com.vagrok.vag_collar.helpers.User_LOGGED

class MainActivity : AppCompatActivity()  {

    lateinit var username_input : TextInputEditText
    lateinit var password_input : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region declaration params
        var passwordLayout : TextInputLayout
        var transitionButton: TransitionButton
        passwordLayout = findViewById(R.id.passwordField)
        username_input = findViewById(R.id.username_input)
        password_input = findViewById(R.id.password_input)
        val prefs = customPreference(this, IS_FIRST_TIME)
        //endregion



        if (prefs.user_logged){

           // val intent = Intent(this , MainActivity2::class.java)
           // startActivity(intent)
            val intent = Intent(this , HomeActivity::class.java)
            startActivity(intent)
        }

        // Set error text
        passwordLayout.error = getString(R.string.app_name)


        //initiating

        //onclick listener

    }

    fun LoginCheck(view: View) {

        if (!password_input.text.toString().equals("") && !username_input.text.toString().equals("")){
            //region User Preferences
            val prefs = customPreference(this, IS_FIRST_TIME)
            prefs.password = password_input.text.toString()
            prefs.userId = username_input.text.toString().toInt()
            prefs.user_logged = true

            if(prefs.user_logged)
            {
                print("user ID : "+prefs.userId + "user password : "+ prefs.password + "user is logged :"+prefs.user_logged)
                val intent = Intent(this , MainActivity2::class.java)
                startActivity(intent)
            }
            //endregion
        }

        /*  val sharedPreference =  getSharedPreferences(IS_FIRST_TIME, Context.MODE_PRIVATE)
          var editor = sharedPreference.edit()
          editor.putBoolean("is_loged",true)
          editor.commit()
          sharedPreference.getBoolean("is_loged")
              val intent = Intent(this , MainActivity2::class.java)
              startActivity(intent)

              val intent2 = Intent(this , SettingsActivity::class.java)
              startActivity(intent)
  */
    }

    fun redirectToAboutUs(view: View) {
        val intent = Intent(this , AboutActivity::class.java)
        startActivity(intent)
    }


}

object PreferenceHelper {


    fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.userId
        get() = getInt(USER_ID, 0)
        set(value) {
            editMe {
                it.putInt(USER_ID, value)
            }
        }

    var SharedPreferences.password
        get() = getString(USER_PASSWORD, "")
        set(value) {
            editMe {
                it.putString(USER_PASSWORD, value)
            }
        }
    var SharedPreferences.user_logged
        get() = getBoolean(User_LOGGED , false)
    set(value) {
        editMe {
            it.putBoolean(User_LOGGED, value)
        }
    }

    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}

