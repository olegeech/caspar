package com.caspar.health.model.ui.web

import com.caspar.health.common.css
import com.caspar.health.common.input
import com.caspar.health.core.User
import com.caspar.health.model.steps.LogInView
import com.caspar.health.model.steps.PatientsViews
import com.codeborne.selenide.Selenide

class LogInWebView(val user: User) : LogInView {

    //TODO: divide by user role -> loginPatient()... if needed (returns diff pages)
    override fun logIn(): PatientsViews {
        input("login").setValue(this.user.casparId)
        input("password").setValue(this.user.password)
        css(".login").click()
        Selenide.sleep(500)
        return PatientsWebViews()
    }

}
