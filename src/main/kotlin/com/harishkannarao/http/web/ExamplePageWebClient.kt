package com.harishkannarao.http.web

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.*
import org.awaitility.kotlin.await
import java.lang.IllegalStateException
import java.util.concurrent.TimeUnit

class ExamplePageWebClient(
        private val webClient: WebClient
) {
    fun getTitle(): String {
        val htmlPage = webClient.getPage<HtmlPage>("http://example.org/")
        val div = getDivElement(htmlPage)
        val heading = getH1Element(div)
        return heading.textContent
    }

    private fun getDivElement(node: DomNode): HtmlDivision {
        val cssSelector = "div"
        awaitElementDisplay(node, cssSelector)
        return getMandatoryElement(node, cssSelector)
    }

    private fun getH1Element(node: DomNode): HtmlHeading1 {
        val cssSelector = "h1"
        awaitElementDisplay(node, cssSelector)
        return getMandatoryElement(node, cssSelector)
    }

    private fun awaitElementDisplay(node: DomNode, cssSelector: String) {
        val elementQuery: (DomNode) -> List<DomNode> = {
            getNodesByCssSelector(it, cssSelector)
        }
        await.alias("Wait for '$cssSelector' element to be displayed on page")
                .atMost(2L, TimeUnit.SECONDS)
                .pollInterval(100L, TimeUnit.MILLISECONDS)
                .until { elementQuery(node).firstOrNull()?.isDisplayed }
    }

    private fun getNodesByCssSelector(node: DomNode, cssSelector: String): List<DomNode> {
        return node.querySelectorAll(cssSelector).toList()
    }

    private fun <T : DomNode> getElement(node: DomNode, cssSelector: String): T? {
        return node.querySelector(cssSelector) as T?
    }

    private fun <T : DomNode> getMandatoryElement(node: DomNode, cssSelector: String): T {
        return getElement(node, cssSelector)
                ?: throw IllegalStateException("'$cssSelector' element not found on page")
    }

}