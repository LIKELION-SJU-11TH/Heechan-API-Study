package com.example.demo.repository;

import com.example.demo.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity,Long> {
}
