package com.harishkannarao.http

import com.gargoylesoftware.htmlunit.WebClient
import com.harishkannarao.http.api.ExamplePageRestClient
import com.harishkannarao.http.web.ExamplePageWebClient

object HttpClientApp {
    @JvmStatic
    fun main(args: Array<String>) {
        val titleFromRest = ExamplePageRestClient().getTitle()
        println(titleFromRest)
        val webClient = WebClient()
        webClient.use {
            val titleFromWeb = ExamplePageWebClient(it).getTitle()
            println(titleFromWeb)
        }
    }
}