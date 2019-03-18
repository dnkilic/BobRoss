package com.example.bobross

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bobross.extensions.testOnce
import com.example.bobross.repository.PostRepository
import com.example.bobross.repository.model.*
import com.google.common.truth.Truth.assertThat
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito.mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

@RunWith(PowerMockRunner::class)
@PrepareForTest(PostRepository::class)
class MainViewModelTest {

    @JvmField @Rule val rule = InstantTaskExecutorRule()
    private lateinit var repository: PostRepository
    private lateinit var viewModel: MainViewModel

    @Before fun setup() {
        repository = mock(PostRepository::class.java)
        viewModel = MainViewModel(repository)
    }

    @Test fun shouldDisplayNetworkErrorWhenUnknownHostException() {
        // Act
        viewModel.onError(UnknownHostException())
        val actual = viewModel.posts.testOnce().value

        // Verify
        assertThat(actual).isInstanceOf(Command.Error::class.java)
        if (actual is Command.Error) {
            assertThat(actual.code).isEqualTo(ERROR_NETWORK)
        }
    }

    @Test fun shouldDisplayNetworkErrorWhenIOException() {
        // Act
        viewModel.onError(IOException())
        val actual = viewModel.posts.testOnce().value

        // Verify
        assertThat(actual).isInstanceOf(Command.Error::class.java)
        if (actual is Command.Error) {
            assertThat(actual.code).isEqualTo(ERROR_NETWORK)
        }
    }

    @Test fun shouldDisplaySomethingWentWrongWhenInternalError() {
        // Act
        viewModel.onError(HttpException(Response.error<Any>(
                500, ResponseBody.create(
                MediaType.parse("error"), "Server Error"
        ))))

        // Verify
        val actual = viewModel.posts.testOnce().value
        assertThat(actual).isInstanceOf(Command.Error::class.java)
        if (actual is Command.Error) {
            assertThat(actual.code).isEqualTo(ERROR_UNKNOWN)
        }
    }

    @Test fun shouldDisplaySomethingWentWrong() {
        // Act
        viewModel.onError(Exception())

        // Verify
        val actual = viewModel.posts.testOnce().value
        assertThat(actual).isInstanceOf(Command.Error::class.java)
        if (actual is Command.Error) {
            assertThat(actual.code).isEqualTo(ERROR_UNKNOWN)
        }
    }

    @Test fun shouldDisplayNetworkErrorOnExampleException() {
        // Act
        viewModel.onError(ExampleException(1))

        // Verify
        val actual = viewModel.posts.testOnce().value
        assertThat(actual).isInstanceOf(Command.Error::class.java)
        if (actual is Command.Error) {
            assertThat(actual.code).isEqualTo(ERROR_NETWORK)
        }
    }
}
