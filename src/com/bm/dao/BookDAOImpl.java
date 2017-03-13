package com.bm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bm.model.Book;
import com.bm.utils.DbUtil;

public class BookDAOImpl implements BookDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public boolean insertBook(Book book) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
		conn = DbUtil.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, book.getTitle());
		ps.setString(2, book.getAuthor());
		ps.setFloat(3, book.getPrice());
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Book> listAllBooks() throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Book> listBook = new ArrayList<>();
		String sql = "SELECT * FROM book";
		conn = DbUtil.getConnection();
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("book_id");
			String title = rs.getString("title");
			String author = rs.getString("author");
			float price = rs.getFloat("price");

			Book book = new Book(id, title, author, price);
			listBook.add(book);
		}

		return null;
	}

	@Override
	public boolean deleteBook(Book book) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM book where book_id = ?";
		conn = DbUtil.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, book.getId());
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateBook(Book book) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "UPDATE book SET title = ?, author = ?, price = ? WHERE book_id = ?";
		conn = DbUtil.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, book.getTitle());
		ps.setString(2, book.getAuthor());
		ps.setFloat(3, book.getPrice());
		ps.setInt(4, book.getId());

		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}

		return false;
	}

	@Override
	public Book getBook(int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Book book = null;
		String sql = "SELECT * FROM book WHERE book_id = ?";
		conn = DbUtil.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		rs = ps.executeQuery();

		if (rs.next()) {
			String title = rs.getString("title");
			String author = rs.getString("author");
			float price = rs.getFloat("price");

			book = new Book(id, title, author, price);
		}
		return book;
	}

}
