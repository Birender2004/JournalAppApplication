package com.build.journalApp.entity;


import com.build.journalApp.enums.sentiment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "test_journal")
@Setter
@Getter
@NoArgsConstructor
public class JournalEntry {

    @Id
    String id;
    String title;
    String content;
    sentiment mood;
}
