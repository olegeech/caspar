package com.caspar.health.core

import com.caspar.health.model.api.DeviceStepsApi
import com.caspar.health.model.steps.GenericSteps

data class User(
        var casparId: String = "",
        var password: String = "",
        var firstName: String = "",
        var lastName: String = "",
        val birthday: Birthday = Birthday(),
        var city: String = "",
        var country: String = ""
) {

    private val platform: Platform = config.platform()
    private val steps: GenericSteps = Farm.device(platform, this)
    val api: DeviceStepsApi = DeviceStepsApi(this)
    val ui = steps

    data class Birthday(var day: Int = 0, var month: Int = 0, var year: String = "")

    companion object {
        val defaultTherapist = User("IXE0865", "78350619")
        val defaultPatient = User("", "", "firstName", "lastName",
                Birthday(18, 1, "1983"), "Kyiv", "Ukraine")
    }
}
