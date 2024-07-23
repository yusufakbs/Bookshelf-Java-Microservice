package com.bookshelf.library_service.service;

import com.bookshelf.library_service.client.BookServiceClient;
import com.bookshelf.library_service.dto.AddBookRequest;
import com.bookshelf.library_service.dto.LibraryDto;
import com.bookshelf.library_service.exception.LibraryNotFoundException;
import com.bookshelf.library_service.model.Library;
import com.bookshelf.library_service.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;


    public LibraryService(LibraryRepository libraryRepository, BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }

    public LibraryDto getAllBooksInLibraryById(String id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id:" + id));

        LibraryDto libraryDto = new LibraryDto(library.getId(),
                library.getUserBook()
                        .stream()
                        .map(bookServiceClient::getBookById) // feign
                        .map(ResponseEntity::getBody)
                        .collect(Collectors.toList()));

        return libraryDto;
    }

    public LibraryDto createLibrary() {
        Library newLibrary = libraryRepository.save(new Library());
        return new LibraryDto(newLibrary.getId());
    }

    public void addBookToLibrary(AddBookRequest addBookRequest) {
        String bookId = bookServiceClient.getBookByIsbn(addBookRequest.getIsbn()).getBody().getId();

        Library library = libraryRepository.findById(addBookRequest.getId())
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id:" + addBookRequest.getId()));

        library.getUserBook()
                .add(bookId);

        libraryRepository.save(library);


    }


}
