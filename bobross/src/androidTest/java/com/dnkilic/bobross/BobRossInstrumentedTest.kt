package com.dnkilic.bobross

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BobRossInstrumentedTest {

    @Rule
    @JvmField
    var thrown: ExpectedException = ExpectedException.none()
    private var appContext: Context? = null

    @Before fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test fun nullImageUrlThrowsIllegalArgumentException() {
        thrown.expect(IllegalArgumentException::class.java)
        BobRoss.with(appContext).load(null)
    }

    @Test fun nullErrorPlaceHolderThrowsIllegalArgumentException() {
        thrown.expect(IllegalArgumentException::class.java)
        BobRoss.with(appContext).load("").error(null)
    }

    @Test fun nullImageViewThrowsIllegalArgumentException() {
        thrown.expect(IllegalArgumentException::class.java)
        BobRoss.with(appContext).load("").error(1).asImageInto(null)
    }

    @Test fun nullContextThrowsIllegalArgumentException() {
        thrown.expect(IllegalArgumentException::class.java)
        BobRoss.with(null).load("").error(1).asImageInto(null)
    }
}

