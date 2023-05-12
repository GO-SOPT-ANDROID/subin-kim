package org.android.go.sopt.data

import org.android.go.sopt.data.remote.model.ResponseReqresDto

data class MultiData(
    val type: Int,
    val title: String?,
    val dataInfo: List<ResponseReqresDto.UserInfo>?
)
