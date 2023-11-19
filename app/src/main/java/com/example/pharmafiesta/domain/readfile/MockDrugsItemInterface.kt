package com.example.pharmafiesta.domain.readfile


interface MockDrugsItemInterface {
    suspend fun generateMockList(): List<DrugsUiModel>
}