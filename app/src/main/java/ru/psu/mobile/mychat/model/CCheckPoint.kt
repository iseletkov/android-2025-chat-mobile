package ru.psu.mobile.mychat.model

import java.util.UUID

data class CCheckPoint (
    var id : UUID,
    var name : String,
    var lat : String,
    var lon : String
)
