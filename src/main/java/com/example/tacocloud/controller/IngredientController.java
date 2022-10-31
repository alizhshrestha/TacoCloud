package com.example.tacocloud.controller;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.repo.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class IngredientController {

    private IngredientRepository repo;

    public IngredientController(IngredientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients(){
        return repo.findAll();
    }

    @PostMapping
//    @PreAuthorize("#{hasRole('ADMIN')}")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient){
        return repo.save(ingredient);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("#{hasRole('ADMIN')}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String ingredientId){
        repo.deleteById(ingredientId);
    }
}
