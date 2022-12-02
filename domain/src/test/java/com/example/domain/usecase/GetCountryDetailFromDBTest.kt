package com.example.domain.usecase

import com.example.domain.models.CountryDetailDomain
import com.example.domain.repository.CountriesRepository
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

internal class GetCountryDetailFromDBTest {
    private var repository = mockk<CountriesRepository>()
    private val getCountryDetailFromDB = GetCountryDetailFromDB(repository = repository)

    @After
    fun afterTest() {
        clearMocks(repository)
    }

    @Test
    fun `should return correct data`() = runBlockingTest {
        val expected = CountryDetailDomain(
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
        val actualData = CountryDetailDomain(
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

        coEvery { repository.getCountryDetailFromDB(0) } returns actualData
        val actual = getCountryDetailFromDB.execute(0)

        coVerify { repository.getCountryDetailFromDB(0) }
        assert(actual == expected)
        confirmVerified(repository)
    }
}