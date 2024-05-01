package com.lebilotta.pubsubmessaging.repository;

import com.lebilotta.pubsubmessaging.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
