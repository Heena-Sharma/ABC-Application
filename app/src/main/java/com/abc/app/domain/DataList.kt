package com.abc.app.domain

data class FundingProject(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val collectedValue: Int,
    val totalValue: Int,
    val startDate: String,
    val endDate: String,
    val mainImageURL: String,
    val list: List<Project>
)

data class Project(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val collectedValue: Int,
    val totalValue: Int,
    val startDate: String,
    val endDate: String,
    val mainImageURL: String
)