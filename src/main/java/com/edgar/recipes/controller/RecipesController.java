package com.edgar.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.edgar.recipes.models.*;
import com.edgar.recipes.service.RecipeService;
import java.util.Map;

@RestController
public class RecipesController {
    RecipeService recipeService;

    @Autowired
    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<?> addRecipe(@Valid @RequestBody Recipe recipe) {
        int id = recipeService.saveRecipe(recipe);
        return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable Integer id) {
        var recipe = recipeService.getRecipe(id);
        if (recipe.isEmpty()) {
            return new ResponseEntity<>("Recipe not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @GetMapping(value = "/api/recipe/search", params = "category")
    public ResponseEntity<?> getRecipesByCategory(@RequestParam("category") String category) {
        var result = recipeService.findByCategory(category);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/api/recipe/search", params = "name")
    public ResponseEntity<?> getRecipesByName(@RequestParam("name") String name) {
        var result = recipeService.findByName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<?> changeRecipe(@PathVariable Integer id, @Valid @RequestBody Recipe recipe) {
        if (recipeService.getRecipe(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipe.setId(id);
        recipeService.saveRecipe(recipe);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Integer id) {
        var recipe = recipeService.getRecipe(id);
        if (recipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/recipe/all")
    public ResponseEntity<?> deleteAllRecipes() {
        recipeService.deleteAll();


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
