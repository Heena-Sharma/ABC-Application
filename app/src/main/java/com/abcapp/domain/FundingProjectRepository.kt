package com.abcapp.domain

import kotlinx.coroutines.flow.Flow

interface FundingProjectRepository {
    fun getFundingProjects(): Flow<List<FundingProject>>
}