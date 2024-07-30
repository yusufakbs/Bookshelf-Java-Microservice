package com.bookshelf.book_service;

import com.bookshelf.book_service.model.Book;
import com.bookshelf.book_service.repository.BookRepository;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
public class BookServiceApplication implements CommandLineRunner {

    private final BookRepository bookRepository;

    public BookServiceApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Book book1 = new Book("Harry potter ve Felsefe taşı", 2000, "Robert Jordan", "Ihtaki Yayinevi", "asdsadsad");
        Book book2 = new Book("Yüzüklerin efendisi", 1960, "J.R.R Tolkien", "Metis Yayinlari", "aaaaaaaa");
        Book book3 = new Book("Dünyanın Gözü", 2000, "Robert Jordan", "Ithaki Yayinevi", "1231asdas1");
        List<Book> bookList = bookRepository.saveAll(Arrays.asList(book1, book2, book3));
        System.out.println(bookList);
    }

    @Bean
    public GrpcServerConfigurer keepAliveServerConfigurer() {
        return serverBuilder -> {
            if (serverBuilder instanceof NettyServerBuilder) {
                ((NettyServerBuilder) serverBuilder).keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(5, TimeUnit.SECONDS).permitKeepAliveWithoutCalls(true);
            }
        };
    }

}
