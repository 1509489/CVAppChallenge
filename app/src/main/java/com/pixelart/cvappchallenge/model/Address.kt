package com.pixelart.cvappchallenge.model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("post_code")
    val postCode: String,
    val street: String,
    val town: String
)