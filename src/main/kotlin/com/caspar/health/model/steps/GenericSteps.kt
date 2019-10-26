package com.caspar.health.model.steps

import com.caspar.health.core.User


interface GenericSteps {
    fun openApp(): LogInView
    fun logIn(): PatientsViews
}

interface LogInView {
    fun logIn(): PatientsViews
}

interface PatientsViews: UserMenu {
    fun clickAddNewPatientStatsButton(): AddNewPatientViews
    fun clickAddNewPatientMenuItem(): AddNewPatientViews
    fun checkTosVisible()
}

interface AddNewPatientViews {
    fun fillRequiredFieldsAndSave(user: User): AddNewPatientViews
    fun closeNewUserDialog(): PatientsViews
}

interface UserMenu {
    fun clickUserLogo(): UserMenu
    fun signOut()
}

interface UnknownView {

}
