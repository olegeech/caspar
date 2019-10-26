package com.caspar.health.api

import com.caspar.health.core.config
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.HttpClientConfig
import io.restassured.filter.log.ResponseLoggingFilter
import org.apache.http.params.CoreConnectionPNames
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class ApiTest {

    @BeforeAll
    fun init() {
        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        // todo: reflect these timeouts in project configuration
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, config.connectionTimeout())
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, config.socketTimeout()))

        RestAssured.baseURI = config.baseApiURI()
        RestAssured.requestSpecification = RequestSpecBuilder()
                .setContentType(config.contentType())
                .build().filter(ResponseLoggingFilter())
    }

}
