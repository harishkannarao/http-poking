package com.harishkannarao.http.api

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class ExamplePageRestClientTest {

    @Test
    fun `return title from example domain`() {
        val title = ExamplePageRestClient().getTitle()
        assertThat(title, equalTo("Example Domain"))
    }
}