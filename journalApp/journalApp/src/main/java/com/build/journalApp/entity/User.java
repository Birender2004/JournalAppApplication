package com.build.journalApp.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "test_users")
@Data
@Builder
@AllArgsConstructor
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    @NonNull
    private String username;
    private String email;

    private boolean sentimentAnalysis;

    @NonNull
    private String password;

    @DBRef
    List<JournalEntry> joirnalEntry= new ArrayList<>();

    List<String> roles= new ArrayList<>();


}
