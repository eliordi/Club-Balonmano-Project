package es.jcc.project.Classes

import java.util.Date

data class Mensaje(
    var id: String? = null,
    var email: String,
    var text: String,
    var date: Date? = null
)
