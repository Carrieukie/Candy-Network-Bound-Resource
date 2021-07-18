package com.karis.networkboundresource.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "candy")
@Parcelize
data class CandyItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable
