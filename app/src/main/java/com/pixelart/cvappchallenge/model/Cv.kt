package com.pixelart.cvappchallenge.model

import com.google.gson.annotations.SerializedName

data class Cv(
    val address: Address,
    val education: List<String>,
    val email: String,
    @SerializedName("experience_summary")
    val experienceSummary: List<String>,
    val interests: List<String>,
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("technical_skills")
    val technicalSkills: List<String>,
    @SerializedName("work_history")
    val workHistory: List<WorkHistory>
)