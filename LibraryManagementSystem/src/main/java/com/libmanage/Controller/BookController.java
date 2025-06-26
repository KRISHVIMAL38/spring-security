package com.libmanage.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libmanage.DTO.BookDTO;
import com.libmanage.Entity.Book;
import com.libmanage.Service.BookService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("")
	public ResponseEntity<List<Book>>getAllBooks(){
		return ResponseEntity.ok(bookService.getAllBooks());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book>getBookById(@PathVariable Long id){
		return ResponseEntity.ok(bookService.getBookById(id));
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Book>addBook(@RequestBody BookDTO bookDTO){
		return ResponseEntity.ok(bookService.addBook(bookDTO));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
		return ResponseEntity.ok(bookService.updateBook(id,bookDTO));	
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void>deleteBook(@PathVariable Long id){
		 bookService.deleteBookById(id);
		return ResponseEntity.ok().build();
	}
}
