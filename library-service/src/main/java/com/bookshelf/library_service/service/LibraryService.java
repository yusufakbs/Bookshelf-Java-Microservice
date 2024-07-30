package com.bookshelf.library_service.service;

import com.bookshelf.bookservice.BookId;
import com.bookshelf.bookservice.BookServiceGrpc;
import com.bookshelf.bookservice.Isbn;
import com.bookshelf.library_service.client.BookServiceClient;
import com.bookshelf.library_service.dto.AddBookRequest;
import com.bookshelf.library_service.dto.LibraryDto;
import com.bookshelf.library_service.exception.LibraryNotFoundException;
import com.bookshelf.library_service.model.Library;
import com.bookshelf.library_service.repository.LibraryRepository;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;

    @GrpcClient("book-service")
    private BookServiceGrpc.BookServiceBlockingStub bookServiceBlockingStub;


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
        BookId bookId = bookServiceBlockingStub.getBookIdByIsbn(Isbn.newBuilder().setIsbn(addBookRequest.getIsbn()).build());
        //String bookId = bookServiceClient.getBookByIsbn(addBookRequest.getIsbn()).getBody().getId();

        Library library = libraryRepository.findById(addBookRequest.getId())
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id:" + addBookRequest.getId()));

        library.getUserBook()
                .add(bookId.getBookId());

        libraryRepository.save(library);


    }

    public List<String> getAllLibraries() {
        return libraryRepository.findAll().stream().map(Library::getId).collect(Collectors.toList());
    }
}
