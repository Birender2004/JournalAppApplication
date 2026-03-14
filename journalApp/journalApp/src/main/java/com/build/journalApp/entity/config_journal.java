package com.build.journalApp.entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "confi_journal")
@Data
public class config_journal {

    String key;
    String value;
}
