package com.caspar.health.ui

import com.caspar.health.api.ApiTest
import com.caspar.health.core.Platform
import com.caspar.health.core.config
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.WebDriverRunner
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.MobileCapabilityType.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.remote.DesiredCapabilities
import java.util.concurrent.TimeUnit

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class UiTest : ApiTest() {

    @BeforeAll
    fun initDrivers() {
        fun commonMobileCapabilities(): DesiredCapabilities {
            val capabilities = DesiredCapabilities()

            capabilities.setCapability(NO_RESET, true)
            capabilities.setCapability(NEW_COMMAND_TIMEOUT, config.newCommandTimeout())
            capabilities.setCapability(DEVICE_NAME, config.deviceName())
            config.udid()?.let        { capabilities.setCapability(UDID, config.udid()) }
            config.app()?.let         { capabilities.setCapability(APP, config.app()) }
            config.language()?.let    { capabilities.setCapability("language", config.language()) }
            config.locale()?.let      { capabilities.setCapability("locale", config.locale()) }

            return capabilities
        }

        fun setupMobileDriver(capabilities: DesiredCapabilities): AppiumDriver<MobileElement> {
            var driver = AppiumDriver<MobileElement>(config.appiumConnectionUrl(), capabilities)

            when (config.platform()) {
                Platform.iOS -> driver = IOSDriver<MobileElement>(config.appiumConnectionUrl(), capabilities)
                Platform.Android -> driver = AndroidDriver<MobileElement>(config.appiumConnectionUrl(), capabilities)
            }

            driver.manage().timeouts().implicitlyWait(config.driverImplicitWaitSeconds(), TimeUnit.SECONDS)
            if (!config.removeApp() and config.resetApp()) driver.resetApp()
            if (config.removeApp()) {
                driver.removeApp(config.appPackage())
                driver.launchApp()
            }

            WebDriverRunner.setWebDriver(driver)

            return driver
        }

        when (config.platform()) {
            // see for more docs: http://appium.io/docs/en/writing-running-appium/caps/
            // todo: review and refactor (DRY) what is common and what is not...
            Platform.Android -> {
                val capabilities = commonMobileCapabilities()

                capabilities.setCapability("adbExecTimeout", config.adbExecTimeout())
                capabilities.setCapability(AUTOMATION_NAME, "UiAutomator2")
                config.appPackage()?.let  { capabilities.setCapability("appPackage", config.appPackage()) }
                config.appActivity()?.let { capabilities.setCapability("appActivity", config.appActivity()) }

                setupMobileDriver(capabilities)
            }
            Platform.iOS -> {
                val capabilities = commonMobileCapabilities()

                //ios
                capabilities.setCapability(AUTOMATION_NAME, config.automationName())
                capabilities.setCapability(PLATFORM_NAME, "iOS")
                capabilities.setCapability(PLATFORM_VERSION, config.platformVersion())

                config.bundleId()?.let       { capabilities.setCapability("bundleId", config.bundleId()) }
                config.xcodeOrgId()?.let     { capabilities.setCapability("xcodeOrgId", config.xcodeOrgId()) }
                config.xcodeSigningId()?.let { capabilities.setCapability("xcodeSigningId", config.xcodeSigningId()) }
                //config.updatedWDABundleId()?.let { capabilities.setCapability("updatedWDABundleId", config.updatedWDABundleId()) }

                setupMobileDriver(capabilities)
            }
            Platform.Web -> {
                Configuration.fastSetValue = true // todo: reflect in config
            }
        }

        Configuration.timeout = 10000 // todo: reflect in config
    }

    @AfterAll
    fun teardown() {
        if (config.quitDriver()) WebDriverRunner.getWebDriver().quit()
    }
}
