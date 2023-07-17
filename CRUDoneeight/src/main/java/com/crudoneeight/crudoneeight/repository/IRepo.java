package com.crudoneeight.crudoneeight.repository;

import com.crudoneeight.crudoneeight.model.PostsApp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepo extends MongoRepository<PostsApp, String> {
}
