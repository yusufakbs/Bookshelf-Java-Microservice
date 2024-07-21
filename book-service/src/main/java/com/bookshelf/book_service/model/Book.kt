package com.bookshelf.book_service.model

import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator

data class Book @JvmOverloads constructor(
    @Id
    @GeneratedValue(name = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val title: String,
    val bookYear: String,
    val author: String,
    val pressName: String,
    val isbn: String

)