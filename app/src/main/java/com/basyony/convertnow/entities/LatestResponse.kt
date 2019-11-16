package com.basyony.convertnow.entities

import com.google.gson.annotations.SerializedName

data class LatestResponse(

	@SerializedName("date")
	val date: String,

	@SerializedName("rates")
	val rates: Rates,

	@SerializedName("base")
	val base: String
)