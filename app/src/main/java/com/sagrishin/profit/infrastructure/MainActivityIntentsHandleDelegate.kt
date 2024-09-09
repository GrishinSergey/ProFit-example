package com.sagrishin.profit.infrastructure

import android.content.Intent
import com.sagrishin.profit.infrastructure.MainActivityStartArgs.StartFromPushNotification
import javax.inject.Inject

class MainActivityIntentsHandleDelegate @Inject constructor() {

    fun handleOnPushClick(intent: Intent): StartFromPushNotification? {
        val name = StartFromPushNotification::class.simpleName
        val pushStartArgs = intent.getParcelableExtra<StartFromPushNotification>(name)
        return pushStartArgs
    }

}
