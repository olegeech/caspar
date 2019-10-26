package com.caspar.health.core

import com.caspar.health.model.steps.GenericSteps
import com.caspar.health.model.steps.StepsAndroid
import com.caspar.health.model.steps.StepsIOS
import com.caspar.health.model.steps.StepsWeb

object Farm {
    fun device(platform: Platform, user: User): GenericSteps {
        return when (platform) {
            Platform.Android -> StepsAndroid(user)
            Platform.iOS -> StepsIOS(user)
            Platform.Web -> StepsWeb(user)
        }
    }
}
