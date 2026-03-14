package com.build.journalApp.repository;

import com.build.journalApp.entity.config_journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface confi_database extends MongoRepository<config_journal, ObjectId> {
}
