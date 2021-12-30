package com.example.movies.DataClasses

data class LinkData(val page: Int, val results: List<MovieData>, val total_pages: Int) {
}