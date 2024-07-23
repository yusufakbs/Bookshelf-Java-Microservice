package com.bookshelf.book_service.dto

data class BookIdDto(
    val id: String? = "",
    val isbn: String
) {
    companion object {
        @JvmStatic
        fun convert(id: String, isbn: String): BookIdDto {
            return BookIdDto(id, isbn);
        }
    }
}