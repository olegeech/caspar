package com.caspar.health.common

import com.codeborne.selenide.SelenideElement


fun SelenideElement.tap(): SelenideElement {
    this.click()
    return this
}

fun select(selector: String, index: Int) {
    selector(selector).tap().options()[index-1].click()
}

fun select(selector: String, value: String) {
    //selector(selector).tap().options().findBy(){it.text() == value}.click()
    selector(selector).tap().options().find{it.text() == value}!!.click()
}