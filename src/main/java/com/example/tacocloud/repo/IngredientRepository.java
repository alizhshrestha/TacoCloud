package com.example.tacocloud.repo;

import com.example.tacocloud.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface IngredientRepository
        extends JpaRepository<Ingredient, String> {

}
