package com.shaw.rsc2023.api

import com.shaw.rsc2023.model.Result
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

/**{
"drivers": [
{
"id": "9",
"name": "Bruce Spruce"
},
{
"id": "19",
"name": "Andy Garcia"
},
{
"id": "14",
"name": "Jenny Lowe"
},
{
"id": "13",
"name": "Amber Shoe"
},
{
"id": "6",
"name": "Adam Stand"
},
{
"id": "15",
"name": "Ellis Roth"
},
{
"id": "2",
"name": "Chris Willis"
},
{
"id": "16",
"name": "Danika Johnson"
},
{
"id": "3",
"name": "Archie King"
},
{
"id": "25",
"name": "Monica Brown"
}
],
"routes": [
{
"id": 1,
"type": "R",
"name": "West Side Residential Route"
},
{
"id": 2,
"type": "C",
"name": "West Side Commercial Route"
},
{
"id": 3,
"type": "I",
"name": "West Side Industrial Route"
},
{
"id": 4,
"type": "R",
"name": "East Side Residential Route"
},
{
"id": 5,
"type": "C",
"name": "East Side Commercial Route"
},
{
"id": 6,
"type": "I",
"name": "East Side Industrial Route"
},
{
"id": 7,
"type": "R",
"name": "North Side Residential Route"
},
{
"id": 8,
"type": "C",
"name": "North Side Commercial Route"
},
{
"id": 9,
"type": "I",
"name": "North Side Industrial Route"
},
{
"id": 10,
"type": "R",
"name": "South Side Residential Route"
},
{
"id": 11,
"type": "C",
"name": "South Side Commercial Route"
},
{
"id": 12,
"type": "I",
"name": "South Side Industrial Route"
}
]
}*/
interface DriverRouteApi {
    @GET("/data")
    fun getDriverRouteData(): Observable<Response<Result>>

}