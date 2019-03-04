/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.dtos;

import co.edu.uniandes.csw.recipes.entities.IngredientEntity;
import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CesarF
 */
public class RecipeDetailDTO extends RecipeDTO {
    private List<IngredientDTO> ingredient;
    public RecipeDetailDTO(){
    
    }
    
    public RecipeDetailDTO(RecipeEntity entity){
        super(entity);
        if (entity.getIngredient()!= null) {
            ingredient = new ArrayList<>();
            for (IngredientEntity entityIngredient : entity.getIngredient()) {
                ingredient.add(new IngredientDTO(entityIngredient));
            }
        }
    }

    /**
     * @return the ingredient
     */
    public List<IngredientDTO> getIngredient() {
        return ingredient;
    }

    /**
     * @param ingredient the ingredient to set
     */
    public void setIngredient(List<IngredientDTO> ingredient) {
        this.ingredient = ingredient;
    }
    
}
