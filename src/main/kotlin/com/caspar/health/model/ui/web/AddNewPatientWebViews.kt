package com.caspar.health.model.ui.web

import com.caspar.health.common.submitButton
import com.caspar.health.common.input
import com.caspar.health.common.select
import com.caspar.health.core.User
import com.caspar.health.model.steps.AddNewPatientViews
import com.caspar.health.model.steps.PatientsViews
import com.caspar.health.model.steps.UserMenu
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import org.openqa.selenium.By.cssSelector

class AddNewPatientWebViews() : AddNewPatientViews {

    private val newUserDialog = element(cssSelector("app-new-user-dialog"))

    override fun fillRequiredFieldsAndSave(user: User): AddNewPatientViews {
        input("first_name").setValue(user.firstName)
        input("last_name").setValue(user.lastName)
        select("day", user.birthday.day)
        select("month", user.birthday.month)
        select("year", user.birthday.year)
        input("city").setValue(user.city)
        select("country_id", user.country)
        submitButton().click()
        Selenide.sleep(500)
        updatePatientIdPassword(user)
        return this
    }

    override fun closeNewUserDialog(): PatientsViews {
        newUserDialog.find(cssSelector("button.mat-button")).click()
        return PatientsWebViews()
    }

    private fun updatePatientIdPassword(user: User) {
        user.casparId = newUserDialog.find(cssSelector(".section-title + *"), 2).text()
        user.password = newUserDialog.find(cssSelector(".section-title + *"), 3).text()

    }

}
