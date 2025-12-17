package io.tolgee.demo.exampleandroid

import android.app.Application
import io.tolgee.Tolgee
import io.tolgee.storage.TolgeeStorageProviderAndroid

class MyApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    Tolgee.init {
      contentDelivery {
        url = "https://cdn.tolg.ee/d8698fc0f02bedcee2757d4525e05146"
        storage = TolgeeStorageProviderAndroid(this@MyApplication, BuildConfig.VERSION_CODE)
      }
    }
  }
}