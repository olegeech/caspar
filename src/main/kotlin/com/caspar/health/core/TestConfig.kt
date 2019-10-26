package com.caspar.health.core

import org.aeonbits.owner.Config
import org.aeonbits.owner.Config.*
import org.aeonbits.owner.ConfigCache
import java.net.URL

@LoadPolicy(LoadType.MERGE)
@Sources(// higher/first prop source override the prop from lower/last source
        "system:properties", // allows to run like > ./gradlew test -Denv=dev
        "system:env",        // allows to run like > env=dev ./gradlew test
        "file:~/.messenger-specs.config",   // handy for local environment
        "file:/etc/messenger-specs.config", // handy for server specific env
        "file:./src/test/resources/config.properties", // handy for server specific env
        "classpath:config.properties")      // default predefined environments (TODO: doesn't work with classpath, please see above)
interface TestConfig : Config {
    @Key("env")
    fun env(): String

    @Key("\${env}.platform")
    @DefaultValue("Web")
    fun platform(): Platform

    @Key("\${env}.appiumConnectionUrl")
    @DefaultValue("http://127.0.0.1:4723/wd/hub")
    fun appiumConnectionUrl(): URL

    @Key("\${env}.driverImplicitWaitSeconds")
    @DefaultValue("2") // todo: do we yet need it, while we are using selenide with its own implicit waits?
    fun driverImplicitWaitSeconds(): Long

    @Key("\${env}.resetApp")
    @DefaultValue("false")
    fun resetApp(): Boolean

    @Key("\${env}.removeApp")
    @DefaultValue("false")
    fun removeApp(): Boolean

    @Key("\${env}.quitDriver")
    @DefaultValue("false")
    fun quitDriver(): Boolean

    @Key("\${env}.verifyCodeTimeoutMs")
    @DefaultValue("1000")
    fun verifyCodeTimeoutMs(): Long

    /* Appium caps */

    @Key("\${env}.newCommandTimeout")
    @DefaultValue("60")
    fun newCommandTimeout(): Int

    @Key("\${env}.adbExecTimeout")
    @DefaultValue("20000")
    fun adbExecTimeout(): Int

    @Key("\${env}.app")  // used also as Web app url
    fun app(): String?

    @Key("\${env}.udid")
    fun udid(): String?

    @Key("\${env}.appPackage")
    fun appPackage(): String?

    @Key("\${env}.appActivity")
    fun appActivity(): String?

    @Key("\${env}.language")
    fun language(): String?

    @Key("\${env}.locale")
    fun locale(): String?

    @Key("\${env}.deviceName")
    @DefaultValue("Device from \${env}")
    fun deviceName(): String

    /* -- ios */

    @Key("\${env}.automationName")
    @DefaultValue("XCUITest")
    fun automationName(): String

    @Key("\${env}.platformName")
    @DefaultValue("iOS")
    fun platformName(): String

    @Key("\${env}.platformVersion")
    @DefaultValue("12.2")
    fun platformVersion(): String

    @Key("\${env}.bundleId")
    fun bundleId(): String?

    @Key("\${env}.xcodeOrgId")
    fun xcodeOrgId(): String?

    @Key("\${env}.xcodeSigningId")
    fun xcodeSigningId(): String?

    /* API */

    @Key("\${env}.api.baseURI")
    @DefaultValue("https://beta.caspar-health.com/api/v1/")
    fun baseApiURI(): String

    @Key("\${env}.api.contentType")
    @DefaultValue("application/json")
    fun contentType(): String

    @Key("\${env}.api.deviceID")
    @DefaultValue("0d69931d-1026-44dd-9e2f-0a4712539e04")
    fun deviceID(): String

    @Key("\${env}.api.connectionTimeout")
    @DefaultValue("2000")
    fun connectionTimeout(): Int

    @Key("\${env}.api.socketTimeout")
    @DefaultValue("6000")
    fun socketTimeout(): Int

}

val config = ConfigCache.getOrCreate(TestConfig::class.java)