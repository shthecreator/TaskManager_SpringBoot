package org.example.springeducationproj.repository;

import org.example.springeducationproj.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
        extends JpaRepository<Category, Long> {

}
