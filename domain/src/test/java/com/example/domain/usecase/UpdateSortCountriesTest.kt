package com.example.domain.usecase

import com.example.domain.models.SortType
import com.example.domain.repository.CountriesRepository
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

internal class UpdateSortCountriesTest {

    private var repository = mockk<CountriesRepository>()
    private val updateSortCountries = UpdateSortCountries(repository = repository)

    @After
    fun afterTest() {
        clearMocks(repository)
    }

    @Test
    fun `should successful update sort Type`() = runBlockingTest {
        val newSotType = SortType.ConfirmedAsc

        coEvery { repository.updateSortType(newSotType) } returns true
        val actual = updateSortCountries.execute(newSotType)

        coVerify { repository.updateSortType(newSotType) }
        assert(actual)
        confirmVerified(repository)
    }
}