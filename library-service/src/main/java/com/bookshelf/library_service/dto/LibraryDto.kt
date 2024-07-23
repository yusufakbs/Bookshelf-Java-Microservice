package com.bookshelf.library_service.dto

data class LibraryDto @JvmOverloads constructor(
    val id: String,
    val userBookList: List<BookDto> = ArrayList()
) {
}