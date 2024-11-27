package com.table.tatu.amparo.models

import com.google.gson.annotations.SerializedName
import com.table.tatu.amparo.enums.Relation
import kotlinx.serialization.Serializable

@Serializable
class SupportNetwork(
    @SerializedName("name") val name: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("relation") val relation: Relation,
) {}