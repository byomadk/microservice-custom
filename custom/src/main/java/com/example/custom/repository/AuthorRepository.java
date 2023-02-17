package com.example.custom.repository;

import com.example.custom.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity, String> {
}
