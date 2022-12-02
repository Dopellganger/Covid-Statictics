package com.example.domain.usecase

import com.example.domain.models.SortType
import com.example.domain.repository.CountriesRepository
import io.mockk.*
import org.junit.After
import org.junit.Test

internal class GetCurrentSortTypeTest {

    private var repository = mockk<CountriesRepository>()
    private val getCurrentSortType = GetCurrentSortType(repository = repository)

    @After
    fun afterTest() {
        clearMocks(repository)
    }

    @Test
    fun `should return correct sort type`() {
        val expected = SortType.Alphabet
        val actualData = SortType.Alphabet

        every { repository.getCurrentSortType() } returns actualData
        val actual = getCurrentSortType.execute()

        verify { repository.getCurrentSortType() }
        assert(expected == actual)
        confirmVerified(repository)
    }

}