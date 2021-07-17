package com.karis.networkboundresource.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "response")
@Parcelize
data class Response(

	@PrimaryKey
	val id : Int,

	@field:SerializedName("results")
	val results: List<ListItem>

) : Parcelable

@Parcelize
data class Origin(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String
) : Parcelable

@Parcelize
data class Location(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String
) : Parcelable

@Parcelize
data class ListItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("species")
	val species: String,

	@field:SerializedName("created")
	val created: String,

	@field:SerializedName("origin")
	val origin: Origin,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("location")
	val location: Location,

	@field:SerializedName("episode")
	val episode: List<String>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("status")
	val status: String
) : Parcelable
