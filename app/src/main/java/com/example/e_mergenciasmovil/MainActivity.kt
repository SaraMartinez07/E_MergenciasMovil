package com.example.e_mergenciasmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.textfield.TextInputLayout
import android.text.InputFilter;
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    lateinit var textInputEditTextUser: TextInputEditText
    lateinit var textInputEditTextPass: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonContinuar = findViewById<View>(R.id.idbuttonContinuar)
        buttonContinuar.setOnClickListener {
            login2()

            textInputEditTextUser = findViewById(R.id.textInpEditUsser);
            textInputEditTextPass = findViewById(R.id.idtextinpeditPass);

            //val textInputLayTextUser = findViewById<TextInputLayout>(R.id.idtextInplayUsser)
            //val textInputLayTextPass = findViewById<TextInputLayout>(R.id.idtextInplayPass)


            textInputEditTextUser.filters = arrayOf(InputFilter.LengthFilter(20))
            textInputEditTextPass.filters = arrayOf(InputFilter.LengthFilter(2))


        }

    }
    private fun login2() {
        val intent = Intent(this, login2::class.java)
        startActivity(intent)
    }
}