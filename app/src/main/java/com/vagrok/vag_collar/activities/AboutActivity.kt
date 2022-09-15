package com.vagrok.vag_collar.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.simplemobiletools.commons.helpers.isOnMainThread
import com.vagrok.vag_collar.R
import com.vagrok.vag_collar.helpers.URL_FB
import com.vagrok.vag_collar.helpers.URL_MAIL
import com.vagrok.vag_collar.helpers.URL_REDDIT
import com.vagrok.vag_collar.helpers.URL_WEBSITE

class AboutActivity : AppCompatActivity() {

    lateinit var about_email_holder : RelativeLayout
    lateinit var about_faq_holder : RelativeLayout
    lateinit var about_facebook_holder : RelativeLayout
    lateinit var about_website_holder : RelativeLayout
    lateinit var about_reddit_holder : RelativeLayout
    lateinit var about_privacy_policy_holder : RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        //region initiation
        about_email_holder = findViewById(R.id.about_email_holder)
        about_faq_holder = findViewById(R.id.about_faq_holder)
        about_facebook_holder = findViewById(R.id.about_facebook_holder)
        about_website_holder = findViewById(R.id.about_website_holder)
        about_reddit_holder = findViewById(R.id.about_reddit_holder)
        about_privacy_policy_holder = findViewById(R.id.about_privacy_policy_holder)
        //endregion

        //region OnClick listeners
        //fb
        about_facebook_holder.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(URL_FB)
            startActivity(intent)
        })

        //reddit
        about_reddit_holder.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(URL_REDDIT)
            startActivity(intent)
        })
        //website
        about_website_holder.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(URL_WEBSITE)
            startActivity(intent)
        })
        //privacy Policy
        about_privacy_policy_holder.setOnClickListener(View.OnClickListener {
            val intent = Intent(this , PrivacyActivity::class.java)
            startActivity(intent)
        })
        //endregion
    }



    fun getUrlFromIntent(view: View) {

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(URL_MAIL)
        startActivity(intent)
    }

    fun Context.toast(id: Int, length: Int = Toast.LENGTH_SHORT) {
        toast(getString(id), length)
    }

    fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
        try {
            if (isOnMainThread()) {
                doToast(this, msg, length)
            } else {
                Handler(Looper.getMainLooper()).post {
                    doToast(this, msg, length)
                }
            }
        } catch (e: Exception) {
        }
    }

    private fun doToast(context: Context, message: String, length: Int) {
        if (context is Activity) {
            if (!context.isFinishing && !context.isDestroyed) {
                Toast.makeText(context, message, length).show()
            }
        } else {
            Toast.makeText(context, message, length).show()
        }
    }

    fun Context.showErrorToast(msg: String, length: Int = Toast.LENGTH_LONG) {
        toast(String.format(getString(R.string.an_error_occurred), msg), length)
    }

    fun Context.showErrorToast(exception: Exception, length: Int = Toast.LENGTH_LONG) {
        showErrorToast(exception.toString(), length)
    }


}