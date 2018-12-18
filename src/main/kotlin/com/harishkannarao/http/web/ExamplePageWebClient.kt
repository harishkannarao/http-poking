package com.harishkannarao.http.web

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.*
import org.awaitility.kotlin.await
import java.util.concurrent.TimeUnit

class ExamplePageWebClient(
        private val webClient: WebClient
) {
    fun getTitle(): String {
        val htmlPage = webClient.getPage<HtmlPage>("http://example.org/")
        val div = getDivElement(htmlPage)
        val heading = getH1Element(div)
        return heading.asText()
    }

    private fun getDivElement(htmlPage: HtmlPage): HtmlDivision {
        val divs = { htmlPage.querySelectorAll("div") }
        await.alias("Wait for div element")
                .atMost(2L, TimeUnit.SECONDS)
                .pollInterval(100L, TimeUnit.MILLISECONDS)
                .until { divs().firstOrNull()?.isDisplayed }
        return divs().first() as HtmlDivision
    }

    private fun getH1Element(domElement: DomElement): HtmlHeading1 {
        val headings = { domElement.querySelectorAll("h1") }
        await.alias("Wait for heading element")
                .atMost(2L, TimeUnit.SECONDS)
                .pollInterval(100L, TimeUnit.MILLISECONDS)
                .until { headings().firstOrNull()?.isDisplayed }
        return headings().first() as HtmlHeading1
    }
}