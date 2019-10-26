package com.caspar.health.common

import com.codeborne.selenide.*
import com.codeborne.selenide.Selectors.*
import io.appium.java_client.MobileBy


fun ids(value: String): ElementsCollection {
    return Selenide.elements(byId(value))
}

fun name(value: String): SelenideElement {
    return Selenide.element(byName(value))
}

fun button(uisref: String): SelenideElement {
    return Selenide.element(byCssSelector("button[uisref='$uisref']"))
}

fun submitButton(): SelenideElement {
    return Selenide.element(byCssSelector("button[type='submit']"))
}

fun input(name: String): SelenideElement {
    return Selenide.element(byCssSelector("input[formcontrolname='$name']"))
}

fun selector(selector: String) =
        Selenide.element(byCssSelector("mat-select[formcontrolname='$selector']"))

fun SelenideElement.options(): ElementsCollection {
    Selenide.sleep(500)
    return Selenide.elements(byCssSelector("mat-option"))
}

fun css(selector: String): SelenideElement {
    return Selenide.element(selector)
}

fun csss(selector: String): ElementsCollection {
    return Selenide.elements(selector)
}

fun xpath(selector: String): SelenideElement {
    return Selenide.element(byXpath(selector))
}

fun xpaths(selector: String): ElementsCollection {
    return Selenide.elements(byXpath(selector))
}

fun SelenideElement.xpath(selector: String): SelenideElement {
    return this.find(byXpath(selector))
}

fun SelenideElement.xpaths(selector: String): ElementsCollection {
    return this.findAll(byXpath(selector))
}

fun SelenideElement.child(tagOrClassName: String = "*"): SelenideElement {
    return this.xpath("./*/*/$tagOrClassName")
}

fun SelenideElement.childs(tagOrClassName: String = "*"): ElementsCollection {
    return this.xpaths("./*/*/$tagOrClassName")
}

fun SelenideElement.descendant(tagOrClassName: String = "*"): SelenideElement {
    return this.xpath(".//$tagOrClassName")
}

fun SelenideElement.descendants(tagOrClassName: String = "*"): ElementsCollection {
    return this.xpaths(".//$tagOrClassName")
}

fun SelenideElement.className(value: String): SelenideElement {
    return this.find(MobileBy.className(value))
}

fun SelenideElement.classNames(value: String): ElementsCollection {
    return this.findAll(MobileBy.className(value))
}

fun SelenideElement.androidView(value: String): SelenideElement {
    return this.find(MobileBy.className("android.view.$value"))
}

fun SelenideElement.androidViews(value: String): ElementsCollection {
    return this.findAll(MobileBy.className("android.view.$value"))
}

fun ElementsCollection.findBy(predicate: (SelenideElement) -> Boolean): SelenideElement {
    return this.findBy(matching(predicate))
}

fun ElementsCollection.filterBy(predicate: (SelenideElement) -> Boolean): ElementsCollection {
    return this.filterBy(matching(predicate))
}