package com.example.nzheng2.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils

import kotlinx.android.synthetic.main.activity_new_person.*

class NewPersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_person)
        button_save.setOnClickListener() {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_person.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = edit_person.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()

        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.nzheng2.myapplication.REPLY"
    }

}
