package com.bookshelf.library_service.dto

data class BookIdDto @JvmOverloads constructor(
    val id: String? = "",
    val isbn: String
) {
    companion object {
        @JvmStatic
        fun convert(id: String, isbn: String): BookIdDto {
            return BookIdDto(id, isbn)
        }
    }
}