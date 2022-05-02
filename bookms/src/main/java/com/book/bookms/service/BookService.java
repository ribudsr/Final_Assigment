package com.book.bookms.service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.bookms.data.Book;
import com.book.bookms.data.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	public Book getBook(Long isbn) {
		Optional<Book> bookoptinoal=bookRepository.findById(isbn);
		if(bookoptinoal.isEmpty()) {
			return null;
		}
		
		if(bookoptinoal.isPresent()) {
			return bookoptinoal.get();
		}
		
		return null;
	}
	
	public List<Book> getBooks() {
		List<Book> bookList=new ArrayList<>();
		bookRepository.findAll().forEach(Book->bookList.add(Book));
		return bookList; 
	}
	
	public Book saveBook(Book book) {
	  long d = System.currentTimeMillis();
	  Date date = new Date(d);
	   book.setPublishedDate(date);
	   return bookRepository.save(book);
	}
	
	public Book editBook(Book book) {
		return bookRepository.save(book);
	}
	
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
	
}
