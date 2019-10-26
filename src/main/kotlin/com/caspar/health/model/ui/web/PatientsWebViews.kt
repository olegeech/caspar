package com.caspar.health.model.ui.web

import com.caspar.health.common.button
import com.caspar.health.model.steps.AddNewPatientViews
import com.caspar.health.model.steps.PatientsViews
import com.caspar.health.model.steps.UserMenu
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import org.openqa.selenium.By.cssSelector

class PatientsWebViews : PatientsViews {

    override fun clickAddNewPatientStatsButton(): AddNewPatientViews {
        button("core.new-patient").click()
        Selenide.sleep(500)
        return AddNewPatientWebViews()
    }

    override fun checkTosVisible() {
        element(cssSelector(".tos-large")).shouldBe(visible)
    }

    override fun clickAddNewPatientMenuItem(): AddNewPatientViews {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clickUserLogo(): UserMenu {
        return UserMenuWeb().clickUserLogo()
    }

    override fun signOut() {
        return UserMenuWeb().signOut()
    }

}
