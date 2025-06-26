package com.libmanage.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libmanage.Entity.Book;
import com.libmanage.Entity.IssueRecord;
@Repository
public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long>{
	Optional<IssueRecord>findById(Long issueRecordId);

	
}
