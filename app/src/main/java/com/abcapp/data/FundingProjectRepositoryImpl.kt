package com.abcapp.data

import com.abcapp.domain.FundingProject
import com.abcapp.domain.FundingProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FundingProjectRepositoryImpl @Inject constructor() : FundingProjectRepository {

    override fun getFundingProjects(): Flow<List<FundingProject>> = flowOf(dataSet)
}