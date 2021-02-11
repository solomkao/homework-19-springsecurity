package com.solomka.springsecurity.services;

import com.solomka.springsecurity.daos.BookDao;
import com.solomka.springsecurity.exceptions.BadIdException;
import com.solomka.springsecurity.exceptions.BookNameIsNullException;
import com.solomka.springsecurity.exceptions.BookNameIsTooLongException;
import com.solomka.springsecurity.models.Book;
import com.solomka.springsecurity.models.CreateBookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    public final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Book getById(final String bookId) {
        if (bookId == null || bookId.isBlank()) {
            throw new BadIdException();
        }
        return bookDao.getById(bookId);
    }

    public Book createBook(final CreateBookDto createBookDto) {
        final Book newBook = new Book(UUID.randomUUID().toString());
        newBook.setName(getValidatedBookName(createBookDto.getName()));
        newBook.setDescription(createBookDto.getDescription());
        newBook.setAuthors(createBookDto.getAuthors());
        newBook.setNumberOfWords(createBookDto.getNumberOfWords());
        newBook.setRating(createBookDto.getRating());
        newBook.setYearOfPublication(createBookDto.getYearOfPublication());

        Book book = bookDao.addBook(newBook);
        System.out.println("book = " + book);
        return book;
    }

    public String getValidatedBookName(final String name) {
        if (name == null) {
            throw new BookNameIsNullException();
        }
        if (name.length() > 1000) {
            throw new BookNameIsTooLongException();
        }
        return name.trim();
    }
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    public Book deleteBook(final String bookId) {
        if (bookId == null || bookId.isBlank()) {
            throw new BadIdException();
        }
        return bookDao.deleteById(bookId);
    }
    public Book deleteBookById(final String bookId) {
        return this.bookDao.deleteById(bookId);
    }
}
