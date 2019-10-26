package com.caspar.health.model.steps

import com.caspar.health.core.User
import com.caspar.health.core.config
import com.caspar.health.model.ui.web.LogInWebView
import com.codeborne.selenide.Selenide

class StepsWeb(user: User) : UserGenericSteps(user), GenericSteps {

    override fun openApp(): LogInView {
        Selenide.open(config.app())
        return LogInWebView(this.user)
    }

    override fun logIn(): PatientsViews {
        return this.openApp().logIn()
    }

}