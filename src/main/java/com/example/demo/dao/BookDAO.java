package com.example.demo.dao;

import com.example.demo.book.Book;
import com.example.demo.map.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showListOfBooks() {

        return jdbcTemplate.query("SELECT * FROM book",
                new BookMapper<Book>());
    }

    public Book showBook(int book_id){
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id =?",
                new Object[]{book_id}, new BookMapper<Book>())
                        .stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book (name, author, year) VALUES (?, ?, ?)",
                        book.getName(), book.getAuthor(), book.getYear());
    }

    public void edit(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE book_id =?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }


    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?",id);
    }
}
