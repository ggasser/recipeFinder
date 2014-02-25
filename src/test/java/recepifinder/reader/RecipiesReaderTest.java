/**
 * 
 */
package recepifinder.reader;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import recipefinder.model.Recipe;
import recipefinder.reader.RecipesReader;

/**
 * @author Gabriel Gasser Noblia
 *
 */
public class RecipiesReaderTest {

  @Test
  public void testBuildRecipiesFromString() {
    String jsonRecipes = "[" +
                            "{" +
                                "\"name\": \"grilled cheese on toast\"," +
                                "\"ingredients\": [" +
                                                  "{ \"item\":\"bread\", \"amount\":\"2\", \"unit\":\"slices\"}," +
                                                  "{ \"item\":\"cheese\", \"amount\":\"2\", \"unit\":\"slices\"}" +
                                                "]" +
                            "}," +
                            "{" +
                                "\"name\": \"salad sandwich\"," +
                                "\"ingredients\": [" +
                                                  "{ \"item\":\"bread\", \"amount\":\"2\", \"unit\":\"slices\"}," +
                                                  "{ \"item\":\"mixed salad\", \"amount\":\"100\", \"unit\":\"grams\"}" +
                                                "]" +
                            "}" +
                          "]";
    try {
      RecipesReader recipesReader = new RecipesReader();
      List<Recipe> recipies = recipesReader.buildRecipesFromString(jsonRecipes);
      
      Assert.assertEquals(2, recipies.size());    
      Assert.assertEquals("grilled cheese on toast", recipies.get(0).getName());
      Assert.assertEquals("salad sandwich", recipies.get(1).getName());
      Assert.assertEquals(2, recipies.get(0).getIngredients().size());
      Assert.assertEquals("bread", recipies.get(1).getIngredients().get(0).getName());
      
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      Assert.fail();
    }
    
    
    
  }
}
