package com.harishkannarao.http.web

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.DomElement
import com.gargoylesoftware.htmlunit.html.HtmlDivision
import com.gargoylesoftware.htmlunit.html.HtmlHeading1
import com.gargoylesoftware.htmlunit.html.HtmlPage
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
        await.alias("Wait for div element")
                .atMost(2L, TimeUnit.SECONDS)
                .pollInterval(100L, TimeUnit.MILLISECONDS)
                .until { htmlPage.querySelector<HtmlDivision>("div")?.isDisplayed }
        return htmlPage.querySelectorAll("div").first() as HtmlDivision
    }

    private fun getH1Element(domElement: DomElement): HtmlHeading1 {
        await.alias("Wait for heading element")
                .atMost(2L, TimeUnit.SECONDS)
                .pollInterval(100L, TimeUnit.MILLISECONDS)
                .until { domElement.querySelector<HtmlHeading1>("h1")?.isDisplayed }
        return domElement.querySelectorAll("h1").first() as HtmlHeading1
    }
}