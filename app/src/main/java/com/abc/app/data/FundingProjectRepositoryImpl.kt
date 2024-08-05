package com.abc.app.data

import com.abc.app.domain.FundingProject
import com.abc.app.domain.FundingProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FundingProjectRepositoryImpl @Inject constructor() : FundingProjectRepository {

    override fun getFundingProjects(): Flow<List<FundingProject>> = flowOf(dataSet)
}