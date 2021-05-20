package com.funnco.kotlinlearning1


// {
// "date":"2021-05-20T23:27:01.04217+03:00",
// "temperatureC":-12,
// "temperatureF":11,
// "summary":"Scorching"
// }
data class Weather(
    var date: String? = null,
    var temperatureC: Int? = null,
    var temperatureF: Int? = null,
    var summary: String? = null
)
