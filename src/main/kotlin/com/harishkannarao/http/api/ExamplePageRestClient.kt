package com.harishkannarao.http.api

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType

class ExamplePageRestClient {

    fun getTitle(): String {
        val requestSpec = RequestSpecBuilder()
                .setBaseUri("http://example.org")
                .build()

        requestSpec.basePath("/")
        requestSpec.accept(ContentType.HTML)
        val response = RestAssured.given()
                .spec(requestSpec)
                .`when`()
                .get()
                .then()
                .extract()
                .response()
        return response.body().htmlPath().getString("html.body.div.h1")
    }
}