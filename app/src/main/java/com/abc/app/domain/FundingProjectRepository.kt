package com.abc.app.domain

import com.abc.app.domain.FundingProject
import kotlinx.coroutines.flow.Flow

interface FundingProjectRepository {
    fun getFundingProjects(): Flow<List<FundingProject>>
}