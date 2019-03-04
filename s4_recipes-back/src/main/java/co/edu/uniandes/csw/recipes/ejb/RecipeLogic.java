/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.ejb;

import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author CesarF
 */
@Stateless
public class RecipeLogic {
    @Inject
    private RecipePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    public RecipeEntity getRecipe(Long id) {
        return persistence.find(id);
    }

    //TODO crear el método createRecipe
    public RecipeEntity createRecipe(RecipeEntity recipeEntity)throws BusinessLogicException 
    {
        String nombre=recipeEntity.getName();
        String descripcion=recipeEntity.getDescription();
        if(nombre.equals("")||(nombre==null)||(nombre.length()>30))
        {
            throw new BusinessLogicException("El nombre es invalido");
        }       
       if(persistence.find(recipeEntity.getId()).getName().equals(nombre))
       {
           throw new BusinessLogicException("Ya existe una receta con ese nombre");
       }
       
        if(descripcion.equals("")||(descripcion==null)||(descripcion.length()>150))
        {
            throw new BusinessLogicException("La descripcion es invalida");
        }
        persistence.createRecipe(recipeEntity);
        return recipeEntity;
    }

}
