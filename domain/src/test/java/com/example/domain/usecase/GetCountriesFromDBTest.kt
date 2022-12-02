package com.example.domain.usecase

import com.example.domain.models.CountryDomain
import com.example.domain.models.SortType
import com.example.domain.repository.CountriesRepository
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

internal class GetCountriesFromDBTest {
    private var repository = mockk<CountriesRepository>()
    private val getCountriesFromDB = GetCountriesFromDB(repository = repository)

    @After
    fun afterTest() {
        clearMocks(repository)
    }

    @Test
    fun `should return correct data`() = runBlockingTest {
        val expected = listOf(
            CountryDomain("test name", 0, 108555, 1)
        )
        val actualData = listOf(
            CountryDomain("test name", 0, 108555, 1)
        )

        coEvery { repository.getCountriesFromStorage(SortType.Alphabet) } returns actualData
        val actual = getCountriesFromDB.execute(SortType.Alphabet)

        coVerify { repository.getCountriesFromStorage(SortType.Alphabet) }
        assert(actual == expected)
        confirmVerified(repository)
    }
}