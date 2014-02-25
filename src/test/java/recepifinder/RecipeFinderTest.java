/**
 * 
 */
package recepifinder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import recipefinder.RecipeFinder;
import recipefinder.model.Fridge;
import recipefinder.reader.FridgeItemsReader;
import recipefinder.model.Recipe;
import recipefinder.reader.RecipesReader;

/**
 * @author Gabriel Gasser Noblia
 *
 */
public class RecipeFinderTest {
  private List<Recipe> recipes;
  
  
  @Before
  public void setup() {
    String jsonRecipes = "[" +
        "{" +
            "\"name\": \"grilled cheese on toast\"," +
            "\"ingredients\": [" +
                              "{ \"item\":\"bread\", \"amount\":\"4\", \"unit\":\"slices\"}," +
                              "{ \"item\":\"cheese\", \"amount\":\"2\", \"unit\":\"slices\"}" +
                            "]" +
        "}," +
        "{" +
            "\"name\": \"salad sandwich\"," +
            "\"ingredients\": [" +
                              "{ \"item\":\"bread\", \"amount\":\"2\", \"unit\":\"slices\"}," +
                              "{ \"item\":\"mixed salad\", \"amount\":\"100\", \"unit\":\"grams\"}" +
                            "]" +
        "}," +
        "{" +
            "\"name\": \"cheese sandwich with peanut butter\"," +
            "\"ingredients\": [" +
                                "{ \"item\":\"bread\", \"amount\":\"2\", \"unit\":\"slices\"}," +
                                "{ \"item\":\"peanut butter\", \"amount\":\"20\", \"unit\":\"grams\"}," +
                                "{ \"item\":\"cheese\", \"amount\":\"2\", \"unit\":\"slices\"}" +
                            "]" +
        "}" +
      "]";
    RecipesReader recipesReader = new RecipesReader();
    this.recipes = recipesReader.buildRecipesFromString(jsonRecipes);    
  }

  @Test
  public void testFindARecipeForTakeAwayResult() {
    String csvIngredients = "bread,3,slices,25/12/2014\n"
                          + "cheese,10,slices,25/12/2014\n"
                          + "mixed salad,150,grams,26/12/2013";

    FridgeItemsReader fridgeItemsReader = new FridgeItemsReader();
    Fridge fridge = null;
    try {
      fridge = fridgeItemsReader.buildFridge(csvIngredients);
    } catch (IllegalArgumentException | IOException | ParseException e) {
      e.printStackTrace();
      Assert.fail();
    }

    RecipeFinder recipeFinder = new RecipeFinder();
    recipeFinder.setFridge(fridge);
    recipeFinder.setRecipes(this.recipes);
    
    String recipeName = recipeFinder.findARecipe();
    
    Assert.assertEquals("Order Takeout" , recipeName);
  }

  @Test
  public void testFindARecipe() {
    String csvIngredients = "bread,10,slices,25/12/2014\n"
                          + "cheese,10,slices,25/12/2014\n"
                          + "mixed salad,150,grams,26/02/2014";

    FridgeItemsReader fridgeItemsReader = new FridgeItemsReader();
    Fridge fridge = null;
    try {
      fridge = fridgeItemsReader.buildFridge(csvIngredients);
    } catch (IllegalArgumentException | IOException | ParseException e) {
      e.printStackTrace();
      Assert.fail();
    }

    RecipeFinder recipeFinder = new RecipeFinder();
    recipeFinder.setFridge(fridge);
    recipeFinder.setRecipes(this.recipes);
    
    String recipeName = recipeFinder.findARecipe();
    
    Assert.assertEquals("salad sandwich" , recipeName);
  }
}
