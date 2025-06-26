package com.libmanage.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libmanage.DTO.BookDTO;
import com.libmanage.Entity.Book;
import com.libmanage.Repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book>getAllBooks(){
		return bookRepository.findAll();
	}

	public Book getBookById(Long id) {
		Book book=bookRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Book not found"));
		return book;
	}

	public Book addBook(BookDTO bookDTO) {
		Book book=new Book();
		book.setAuthor(bookDTO.getAuthor());
		book.setTitile(bookDTO.getTitile());
		book.setIsbn(bookDTO.getIsbn());
		book.setAvailable(bookDTO.isAvailable());
		book.setQuantity(bookDTO.getQuantity());
		
		Book savedBook = bookRepository.save(book);
		return savedBook;
	}

	public Book updateBook(Long id, BookDTO bookDTO) {
		Book oldbook=bookRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Book Not Found"));
		
		oldbook.setAuthor(bookDTO.getAuthor());
		oldbook.setTitile(bookDTO.getTitile());
		oldbook.setIsbn(bookDTO.getIsbn());
		oldbook.setAvailable(bookDTO.isAvailable());
		oldbook.setQuantity(bookDTO.getQuantity());
		
		Book savedBook = bookRepository.save(oldbook);
		return savedBook;
	}

	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);
	}
}