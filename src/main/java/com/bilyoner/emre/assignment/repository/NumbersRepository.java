package com.bilyoner.emre.assignment.repository;

import com.bilyoner.emre.assignment.model.Number;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NumbersRepository extends MongoRepository<Number, String> {
    Number findByNumber(Integer number);
}
