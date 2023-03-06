package com.fin.sampletest.data

import com.google.gson.annotations.SerializedName

class Owner (

    @SerializedName("login"               ) var login             : String?  = null,
    @SerializedName("id"                  ) var id                : Int?     = null,
    @SerializedName("avatar_url"          ) var avatar_url         : String?  = null,
    @SerializedName("gravatar_id"         ) var gravatarId        : String?  = null,


)