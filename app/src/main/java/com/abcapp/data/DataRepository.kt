package com.abcapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class DataRepository @Inject constructor() {
    fun getData(): Flow<List<FundingProject>> = flowOf(dataSet)
}
