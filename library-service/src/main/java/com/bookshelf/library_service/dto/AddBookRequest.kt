package com.bookshelf.library_service.dto

data class AddBookRequest(
    val id: String,
    val isbn: String,
) {
}