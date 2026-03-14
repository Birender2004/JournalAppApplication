package com.build.journalApp.repository;

import com.build.journalApp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

// Allows to access the database and the easy use of mongodb CRUD operations without manual implementation.
public interface journalRepository extends MongoRepository<JournalEntry, String> {


}
