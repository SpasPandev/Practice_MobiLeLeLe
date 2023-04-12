package com.example.practice_mobilelele.repository;

import com.example.practice_mobilelele.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findByName(String name);

    @Query("SELECT b FROM Brand AS b JOIN FETCH b.models")
    List<Brand> findAllBrandsWithModels();
}
