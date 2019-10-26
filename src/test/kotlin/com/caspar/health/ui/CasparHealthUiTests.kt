package com.caspar.health.ui

import com.caspar.health.core.User
import org.junit.jupiter.api.Test

class CasparHealthUiTests : UiTest() {

    @Test
    fun addPatient() {
        val therapist = User.defaultTherapist
        val patient = User.defaultPatient

        therapist.ui.logIn()
                .clickAddNewPatientStatsButton()
                .fillRequiredFieldsAndSave(patient)
                .closeNewUserDialog()
                .clickUserLogo()
                .signOut()

        patient.ui.logIn()
                .checkTosVisible()

    }
}