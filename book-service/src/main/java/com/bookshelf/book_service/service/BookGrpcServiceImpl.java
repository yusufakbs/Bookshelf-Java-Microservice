package com.bookshelf.book_service.service;

import com.bookshelf.book_service.dto.BookIdDto;
import com.bookshelf.book_service.exception.BookNotFoundException;
import com.bookshelf.book_service.repository.BookRepository;
import com.bookshelf.bookservice.BookId;
import com.bookshelf.bookservice.BookServiceGrpc;
import com.bookshelf.bookservice.Isbn;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BookGrpcServiceImpl extends BookServiceGrpc.BookServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(BookGrpcServiceImpl.class);

    private final BookRepository bookRepository;

    public BookGrpcServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void getBookIdByIsbn(Isbn request, StreamObserver<BookId> responseObserver) {
        logger.info("Grpc call received:" + request.getIsbn());
        BookIdDto bookIdDto = bookRepository.findBookByIsbn(request.getIsbn())
                .map(book -> new BookIdDto(book.getId(), book.getIsbn()))
                .orElseThrow(() -> new BookNotFoundException("Book could not found by isbn:" + request.getIsbn()));
        responseObserver.onNext(
                BookId.newBuilder()
                        .setBookId(bookIdDto.getId())
                        .setIsbn(bookIdDto.getIsbn())
                        .build()
        );
        responseObserver.onCompleted();
    }
}
