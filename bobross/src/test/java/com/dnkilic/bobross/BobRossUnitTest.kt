package com.dnkilic.bobross

import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class BobRossUnitTest {

    @Rule
    @JvmField
    var thrown = ExpectedException.none()

    @Test fun contentRequestThrowsIllegalArgumentException() {
        thrown.expect(IllegalArgumentException::class.java)
        BobRoss.content().request(null, null)
    }
}