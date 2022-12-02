package com.example.domain.usecase

import com.example.domain.models.CountryDetailDomain
import com.example.domain.models.ResultWrapper
import com.example.domain.repository.CountriesRepository
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

internal class GetCountryDetailTest {

    private var repository = mockk<CountriesRepository>()
    private val getCountriesDetail = GetCountryDetail(repository = repository)

    @After
    fun afterTest() {
        clearMocks(repository)
    }

    @Test
    fun `should return correct Success data`() = runBlockingTest {
        val expected = ResultWrapper.Success(
            CountryDetailDomain(
                "test name",
                0,
                108555,
                1,
                0.0,
                0.0,
                0,
                null,
                null,
                null
            )
        )
        val actualData = ResultWrapper.Success(
            CountryDetailDomain(
                "test name",
                0,
                108555,
                1,
                0.0,
                0.0,
                0,
                null,
                null,
                null
            )
        )

        coEvery { repository.getCountryDetail(0) } returns actualData
        val actual = getCountriesDetail.execute(0)

        coVerify { repository.getCountryDetail(0) }
        assert(actual == expected)
        confirmVerified(repository)
    }

    @Test
    fun `should return correct NetworkError data`() = runBlockingTest {
        val expected = ResultWrapper.NetworkError
        val actualData = ResultWrapper.NetworkError

        coEvery { repository.getCountryDetail(0) } returns actualData
        val actual = getCountriesDetail.execute(0)

        coVerify { repository.getCountryDetail(0) }
        assert(actual == expected)
        confirmVerified(repository)
    }

    @Test
    fun `should return correct GenericError data`() = runBlockingTest {
        val expected = ResultWrapper.GenericError(404, "Not Found")
        val actualData = ResultWrapper.GenericError(404, "Not Found")

        coEvery { repository.getCountryDetail(0) } returns actualData
        val actual = getCountriesDetail.execute(0)

        coVerify { repository.getCountryDetail(0) }
        assert(actual == expected)
        confirmVerified(repository)
    }
}