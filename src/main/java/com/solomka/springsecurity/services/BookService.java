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

    public Book getById(final String bookId) throws BadIdException {
        if (bookId == null || bookId.isBlank()) {
            throw new BadIdException();
        }

        Book book = bookDao.getById(bookId);
        if (book == null) {
            throw new BadIdException();
        }
        return book;
    }

    public Book createBook(final CreateBookDto createBookDto) throws BookNameIsNullException, BookNameIsTooLongException {
        final Book newBook = new Book(UUID.randomUUID().toString());
        newBook.setName(getValidatedBookName(createBookDto.getName()));
        newBook.setDescription(createBookDto.getDescription());
        newBook.setAuthors(createBookDto.getAuthors());
        newBook.setNumberOfWords(createBookDto.getNumberOfWords());
        newBook.setRating(createBookDto.getRating());
        newBook.setYearOfPublication(createBookDto.getYearOfPublication());
        return bookDao.addBook(newBook);
    }

    public String getValidatedBookName(final String name) throws BookNameIsNullException, BookNameIsTooLongException {
        if (name == null) {
            throw new BookNameIsNullException();
        }
        if (name.length() > 10) {
            throw new BookNameIsTooLongException();
        }
        return name.trim();
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    public Book deleteBook(final String bookId) throws BadIdException {
        if (bookId == null || bookId.isBlank()) {
            throw new BadIdException();
        }
        Book book = bookDao.deleteById(bookId);
        if (book == null) {
            throw new BadIdException();
        }
        return book;
    }
}
