package com.edgar.recipes.service;

import org.springframework.stereotype.Component;

import com.edgar.recipes.models.Recipe;
import com.edgar.recipes.repositories.RecipeRepository;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Integer saveRecipe(Recipe recipe) {
        Recipe response = recipeRepository.save(recipe);
        return response.getId();
    }

    public Optional<Recipe> getRecipe(Integer id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> findByCategory(String category) {
        return recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> findByName(String name) {
        return recipeRepository.findAllByNameContainsIgnoreCaseOrderByDateDesc(name);
    }

    public void deleteRecipe(Integer id) {
        recipeRepository.deleteById(id);
    }

    public void deleteAll() {
        recipeRepository.deleteAll();
    }
}
