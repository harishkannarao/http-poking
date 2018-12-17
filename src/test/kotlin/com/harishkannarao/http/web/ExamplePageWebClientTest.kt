package com.harishkannarao.http.web

import com.gargoylesoftware.htmlunit.WebClient
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class ExamplePageWebClientTest {
    @Test
    fun `return title from example domain`() {
        WebClient().use {
            val title = ExamplePageWebClient(it).getTitle()
            assertThat(title, equalTo("Example Domain"))
        }
    }

}