package com.example.domain.usecase

import com.example.domain.models.CountryDetailDomain
import com.example.domain.repository.CountriesRepository
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

internal class DeleteCountryFromDBTest {

    private var repository = mockk<CountriesRepository>()
    private val deleteCountryFromDB = DeleteCountryFromDB(repository = repository)

    @After
    fun afterTest() {
        clearMocks(repository)
    }

    @Test
    fun `should successful delete data`() = runBlockingTest {
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

        coEvery { repository.deleteCountry(saveData) } returns true
        val actual = deleteCountryFromDB.execute(saveData)

        coVerify { repository.deleteCountry(saveData) }
        assert(actual)
        confirmVerified(repository)
    }
}