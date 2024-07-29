package com.bookshelf.library_service.controller;

import com.bookshelf.library_service.dto.AddBookRequest;
import com.bookshelf.library_service.dto.LibraryDto;
import com.bookshelf.library_service.service.LibraryService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/v1/library")
public class LibraryController {

    @Value("${library.service.count}")
    private Integer count;

    Logger logger = LoggerFactory.getLogger(LibraryController.class);

    private final LibraryService libraryService;
    private final Environment environment;

    public LibraryController(LibraryService libraryService, Environment environment) {
        this.libraryService = libraryService;
        this.environment = environment;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(libraryService.getAllBooksInLibraryById(id));
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary() {
        logger.info("Library created on port number " + environment.getProperty("local.server.port"));
        return ResponseEntity.ok(libraryService.createLibrary());
    }

    @PutMapping
    public ResponseEntity<Void> addBookToLibrary(@RequestBody AddBookRequest addBookRequest) {
        libraryService.addBookToLibrary(addBookRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllLibraries(){
        return ResponseEntity.ok(libraryService.getAllLibraries());
    }

    @GetMapping("/count")
    public ResponseEntity<String> getCount(){
        return ResponseEntity.ok("Library service count : " + count);
    }




}
