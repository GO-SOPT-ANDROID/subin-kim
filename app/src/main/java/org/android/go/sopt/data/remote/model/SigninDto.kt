package org.android.go.sopt.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSigninDto(
    @SerialName("id")
    val id : String,
    @SerialName("password")
    val password: String
)

@Serializable
data class ResponseSigninDto(
    @SerialName("status")
    val status : Int,
    @SerialName("message")
    val message : String,
    @SerialName("data")
    val data : UserInfo,
){
    @Serializable
    data class UserInfo(
        @SerialName("id")
        val id : String,
        @SerialName("name")
        val name : String,
        @SerialName("skill")
        val skill : String
    )
}

