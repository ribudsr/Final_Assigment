package com.book.bookms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.book.bookms.data.Book;
import com.book.bookms.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
//Book(bookms): fetch/add/edit/delete books
	@GetMapping("/getAllbooks")
	public ResponseEntity getAllBooks() {
		if(!bookService.getBooks().isEmpty()) {
			return new ResponseEntity(bookService.getBooks(),HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/getBook/{isbn}")
	public ResponseEntity getBooks(@PathVariable("isbn") Long isbn) {
		Book book=bookService.getBook(isbn);
		if(book!=null){
			return new ResponseEntity(book,HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity("Book with the id not available",HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/addBook")
	public ResponseEntity addBooks(@RequestBody Book book) {
		Book mybook=bookService.saveBook(book);
		if(mybook!=null) {
			return new ResponseEntity(book,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Book not saved",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/editBook")
	public ResponseEntity editBooks(@RequestBody Book book) {
		Book mybook=bookService.editBook(book);
		if(mybook!=null) {
			return new ResponseEntity(mybook,HttpStatus.OK);
		}
		else {
			return new ResponseEntity("Book not update",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteBook?{id}")
	public ResponseEntity deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return new ResponseEntity("Book deleted",HttpStatus.ACCEPTED);
	}
}
