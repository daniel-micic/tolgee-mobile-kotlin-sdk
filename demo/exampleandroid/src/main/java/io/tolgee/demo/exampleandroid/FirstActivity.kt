package io.tolgee.demo.exampleandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import io.tolgee.Tolgee
import io.tolgee.TolgeeContextWrapper
import kotlinx.coroutines.launch
import java.util.Locale

class FirstActivity : ComponentActivity() {

  val tolgee = Tolgee.instance

  override fun attachBaseContext(newBase: Context?) {
    // Wrapping base context will make sure getString calls will use tolgee
    // even for instances which cannot be replaced automatically by the compiler
    super.attachBaseContext(TolgeeContextWrapper.wrap(newBase))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

//    lifecycleScope.launch {
//      tolgee.changeFlow.collect {
//        // we want to reload activity after a language change
//        recreate()
//      }
//    }

    setContentView(R.layout.activity_first)

    // Make sure the app title stays updated
    setTitle(R.string.menu_developer_options_label)

    val buttonEn = findViewById<Button>(R.id.button_next)



    // Update texts within the app with translated ones
//    name.text = getString(R.string.menu_developer_options_label)

    buttonEn.setOnClickListener {
      startActivity(Intent(this, MainActivity::class.java))
    }

  }

  override fun onStart() {
    super.onStart()

    // Make sure the translations are loaded
    // This function will initiate translations fetching in the background and
    // will trigger changeFlow whenever updated translations are available
    tolgee.preload(this)
  }
}