package com.harishkannarao.http.web

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage

class ExamplePageWebClient(
        private val webClient: WebClient
) {
    fun getTitle(): String {
        val response = webClient.getPage<HtmlPage>("http://example.org/")
        val heading = response.getElementsByTagName("h1").firstOrNull()
        return when {
            heading != null -> heading.asText()
            else -> ""
        }
    }
}