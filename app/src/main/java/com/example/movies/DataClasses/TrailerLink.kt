package com.example.movies.DataClasses

data class TrailerLink(
    val id: Int,
    val results: List<GetTrailerData>
) {
}