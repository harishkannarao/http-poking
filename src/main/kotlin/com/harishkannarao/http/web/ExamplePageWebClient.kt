package com.harishkannarao.http.web

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlDivision
import com.gargoylesoftware.htmlunit.html.HtmlHeading1
import com.gargoylesoftware.htmlunit.html.HtmlPage

class ExamplePageWebClient(
        private val webClient: WebClient
) {
    fun getTitle(): String {
        val response = webClient.getPage<HtmlPage>("http://example.org/")
        val div = response.querySelectorAll("div").firstOrNull() as HtmlDivision?
        val heading = div?.querySelectorAll("h1")?.firstOrNull() as HtmlHeading1?
        return heading?.asText() ?: ""
    }
}