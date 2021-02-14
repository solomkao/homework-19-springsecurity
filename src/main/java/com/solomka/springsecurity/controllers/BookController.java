package com.solomka.springsecurity.controllers;

import com.solomka.springsecurity.exceptions.BadIdException;
import com.solomka.springsecurity.models.Book;
import com.solomka.springsecurity.models.CreateBookDto;
import com.solomka.springsecurity.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('user:read')")
    public ResponseEntity<List<Book>> getAll(
            @RequestParam(value = "sortBy", defaultValue = "byName", required = false) String sortBy,
            @RequestParam(value = "limit", defaultValue = "10", required = false) String limit,
            @RequestParam(value = "offset", defaultValue = "0", required = false) String offset,
            @RequestParam(value = "name", defaultValue = "", required = false) String name,
            @RequestParam(value = "description", defaultValue = "", required = false) String description,
            @RequestParam(value = "ratingMoreThan", defaultValue = "0", required = false) String ratingMoreThan
    ) {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @PostMapping(
            value = "/books",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyAuthority('user:write')")
    public ResponseEntity<Book> createBook(@RequestBody CreateBookDto createBookDto) {
        Book newBook = bookService.createBook(createBookDto);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping(value = "/books/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('user:read')")
    public ResponseEntity<Book> getById(
            @PathVariable(value = "bookId") String bookId
    ) throws BadIdException {
        Book book = bookService.getById(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping(value = "/books/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('user:write')")
    public ResponseEntity<Book> deleteById(
            @PathVariable(value = "bookId") String bookId
    ) throws BadIdException {
        Book book = bookService.deleteBook(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}