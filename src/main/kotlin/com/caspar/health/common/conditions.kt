package com.caspar.health.common

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Driver
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.WebElement

fun matching(predicate: (SelenideElement) -> Boolean) = object: Condition("matching custom predicate") { // todo: improve error messages
    override fun apply(driver: Driver?, element: WebElement?): Boolean {
        return predicate(Selenide.element(element))
    }
}