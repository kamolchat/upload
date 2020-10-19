package com.example.voting.repository;

import com.example.voting.entity.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface FileStorageRepository extends JpaRepository<FileStorage, Long>{
  Optional<FileStorage> findById(Long id);
}