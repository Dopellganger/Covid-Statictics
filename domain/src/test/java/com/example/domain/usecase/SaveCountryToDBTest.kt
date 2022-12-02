package com.example.domain.usecase

import com.example.domain.models.CountryDetailDomain
import com.example.domain.repository.CountriesRepository
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

internal class SaveCountryToDBTest {

    private var repository = mockk<CountriesRepository>()
    private val saveCountryToDB = SaveCountryToDB(repository = repository)

    @After
    fun afterTest() {
        clearMocks(repository)
    }

    @Test
    fun `should successful save data`() = runBlockingTest {
        val saveData = CountryDetailDomain(
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

        coEvery { repository.saveCountry(saveData) } returns true
        val actual = saveCountryToDB.execute(saveData)

        coVerify { repository.saveCountry(saveData) }
        assert(actual)
        confirmVerified(repository)
    }
}