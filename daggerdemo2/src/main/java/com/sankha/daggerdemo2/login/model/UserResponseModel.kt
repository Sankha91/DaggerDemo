package com.sankha.daggerdemo2.login.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponseModel(
    @field: SerializedName("name") val name : String,
    @field: SerializedName("username") val username : String,
    @field: SerializedName("email") val email : String,
    @field: SerializedName("phone") val phone : String,
    @field: SerializedName("address") val address : Address
) : Serializable
data class Address(
    val street : String,
    val city : String,
    val zipcode : String,
    val suite : String
) : Serializable {
    override fun toString(): String {
        return suite+", "+street+", "+city+", "+zipcode
    }
}