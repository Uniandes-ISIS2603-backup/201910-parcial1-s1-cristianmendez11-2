/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.test.persistence;

import co.edu.uniandes.csw.recipes.ejb.RecipeLogic;
import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author c.mendez11
 */
@RunWith(Arquillian.class)
public class RecipeLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private RecipeLogic recipeLogic;

    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecipeEntity.class.getPackage())
                .addPackage(RecipeLogic.class.getPackage())
                .addPackage(RecipePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createRecipeTest() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        RecipeEntity result = recipeLogic.createRecipe(newEntity);
        Assert.assertNotNull(result);
        RecipeEntity entity = em.find(RecipeEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
    }
}
