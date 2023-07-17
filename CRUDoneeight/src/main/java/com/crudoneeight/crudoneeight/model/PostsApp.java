package com.crudoneeight.crudoneeight.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "postsApp")
public class PostsApp {
    @Id
    private String _id;

    private String name;

    private String user;

    private String logo;

    private String description;

    private LocalDate expiry_date;
}
