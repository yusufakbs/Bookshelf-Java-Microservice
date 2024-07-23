package com.bookshelf.library_service.dto

data class BookDto @JvmOverloads constructor(
    val id: BookIdDto? = null,
    val title: String,
    val bookYear: Int,
    val author: String,
    val pressName: String,
) {


}