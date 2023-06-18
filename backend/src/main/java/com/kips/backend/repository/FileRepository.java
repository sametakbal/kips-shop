package com.kips.backend.repository;

import com.kips.backend.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer>{
    Optional<FileEntity> findByName(String name);
}
