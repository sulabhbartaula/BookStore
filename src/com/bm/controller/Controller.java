package com.bm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.dao.BookDAO;
import com.bm.model.Book;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		System.out.println("action");
		
		try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertBook(request, response);
                break;
            case "/delete":
                deleteBook(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateBook(request, response);
                break;
            default:
                listBook(request, response);
                break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException, ClassNotFoundException {
        List<Book> listBook = bookDAO.listAllBooks();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.getBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
 
    }
 
    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));
 
        Book newBook = new Book(title, author, price);
        bookDAO.insertBook(newBook);
        response.sendRedirect("list");
    }
 
    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));
 
        Book book = new Book(id, title, author, price);
        bookDAO.updateBook(book);
        response.sendRedirect("list");
    }
 
    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Book book = new Book(id);
        bookDAO.deleteBook(book);
        response.sendRedirect("list");
 
    }
}
