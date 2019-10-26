package com.caspar.health.model.ui.web

import com.caspar.health.model.steps.UserMenu
import com.codeborne.selenide.Selenide.element
import org.openqa.selenium.By.cssSelector

class UserMenuWeb : UserMenu {

    private val menu = element(cssSelector(".mat-menu-panel"))

    override fun clickUserLogo(): UserMenu {
        element(cssSelector("app-user-logo")).click()
        return this
    }

    override fun signOut() {
        menu.find(cssSelector("button"), 1)
    }

}
