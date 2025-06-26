package com.libmanage.Service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.libmanage.Entity.Book;
import com.libmanage.Entity.IssueRecord;
import com.libmanage.Entity.User;
import com.libmanage.Repository.BookRepository;
import com.libmanage.Repository.IssueRecordRepository;
import com.libmanage.Repository.UserRepository;

@Service
public class IssueRecordService {
	@Autowired
	private IssueRecordRepository issueRecordRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	 
	public IssueRecordService(IssueRecordRepository issueRecordRepository, BookRepository bookRepository,
			UserRepository userRepository) {
		super();
		this.issueRecordRepository = issueRecordRepository;
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
	}

	public IssueRecord issueTheBook(Long bookId) {
		Book book=bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book Not Found"));
		
		if(book.getQuantity()<=0 || book.isAvailable()==false) {
			throw new RuntimeException("Book is not available");
		}
		
		String userName=SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
		
		User user= userRepository.findByUsername(userName)
				.orElseThrow(() ->new RuntimeException("User Not Found"));
		
		IssueRecord issueRecord=new IssueRecord();
		issueRecord.setIssueDate(LocalDate.now());
		issueRecord.setDueDate(LocalDate.now().plusDays(14));
		issueRecord.setIsReturned(false);
		issueRecord.setUser(user);
		issueRecord.setBook(book);
		
		book.setQuantity(book.getQuantity()-1);
		if(book.getQuantity()==0) {
			book.setAvailable(false);
		}
		
		
		bookRepository.save(book);
		return issueRecordRepository.save(issueRecord);

	}
	
	public IssueRecord returnTheBook(Long issueRecordId) {
		 IssueRecord issueRecord = issueRecordRepository.findById(issueRecordId)
				 .orElseThrow(()-> new RuntimeException("IssueRecord Not Found"));
		 
		 if(issueRecord.getIsReturned()) {
			 throw new RuntimeException("Book is already returned");
		 }
		 
		 Book book=issueRecord.getBook();
		 User user=issueRecord.getUser();
		 
		 book.setQuantity(book.getQuantity()+1);
		 book.setAvailable(true);
		 bookRepository.save(book);
		 
		 issueRecord.setIsReturned(true);
		 issueRecord.setReturnDate(LocalDate.now());
		 return issueRecordRepository.save(issueRecord);

	}
}
