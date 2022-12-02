package com.example.domain.usecase

import com.example.domain.models.CountryDomain
import com.example.domain.models.ResultWrapper
import com.example.domain.models.SortType
import com.example.domain.repository.CountriesRepository
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

internal class GetCountriesTest {

    private var repository = mockk<CountriesRepository>()
    private val getCountries = GetCountries(repository = repository)

    @After
    fun afterTest() {
        clearMocks(repository)
    }

    @Test
    fun `should return correct Success data`() = runBlockingTest {
        val expected = ResultWrapper.Success(
            listOf(
                CountryDomain("test name", 0, 108555, 1)
            )
        )
        val actualData = ResultWrapper.Success(
            listOf(
                CountryDomain("test name", 0, 108555, 1)
            )
        )

        coEvery { repository.getCountriesFromInternet(SortType.Alphabet) } returns actualData
        val actual = getCountries.execute(SortType.Alphabet)

        coVerify { repository.getCountriesFromInternet(SortType.Alphabet) }
        assert(actual == expected)
        confirmVerified(repository)
    }

    @Test
    fun `should return correct NetworkError data`() = runBlockingTest {
        val expected = ResultWrapper.NetworkError
        val actualData = ResultWrapper.NetworkError

        coEvery { repository.getCountriesFromInternet(SortType.Alphabet) } returns actualData
        val actual = getCountries.execute(SortType.Alphabet)

        coVerify { repository.getCountriesFromInternet(SortType.Alphabet) }
        assert(actual == expected)
        confirmVerified(repository)
    }

    @Test
    fun `should return correct GenericError data`() = runBlockingTest {
        val expected = ResultWrapper.GenericError(404, "Not Found")
        val actualData = ResultWrapper.GenericError(404, "Not Found")

        coEvery { repository.getCountriesFromInternet(SortType.Alphabet) } returns actualData
        val actual = getCountries.execute(SortType.Alphabet)

        coVerify { repository.getCountriesFromInternet(SortType.Alphabet) }
        assert(actual == expected)
        confirmVerified(repository)
    }
}