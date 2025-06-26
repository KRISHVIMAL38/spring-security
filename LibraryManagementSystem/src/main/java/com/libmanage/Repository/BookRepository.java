package com.libmanage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libmanage.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
}
