package com.bm.dao;

import java.sql.SQLException;
import java.util.List;

import com.bm.model.Book;

public interface BookDAO {
	 public boolean insertBook(Book book) throws SQLException,ClassNotFoundException;
	 public List<Book> listAllBooks() throws SQLException,ClassNotFoundException;
	 public boolean deleteBook(Book book) throws SQLException,ClassNotFoundException;
	 public boolean updateBook(Book book) throws SQLException,ClassNotFoundException;
	 public Book getBook(int id) throws SQLException,ClassNotFoundException;
}
