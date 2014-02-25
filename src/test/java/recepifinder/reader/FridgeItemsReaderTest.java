/**
 * 
 */
package recepifinder.reader;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import recipefinder.model.Unit;

import recipefinder.model.Ingredient;
import recipefinder.model.Fridge;
import recipefinder.reader.FridgeItemsReader;

/**
 * @author Gabriel Gasser Noblia
 *
 */
public class FridgeItemsReaderTest {
  
  /**
   * 
   */
  @Test
  public void testBuildFridgeFromString() {
    String csvIngredients = "bread,10,slices,25/12/2014\n"
                          + "cheese,10,slices,25/12/2014\n"
                          + "butter,250,grams,25/12/2014\n"
                          + "peanut butter,250,grams,2/12/2014\n"
                          + "mixed salad,150,grams,26/12/2013";
    
    FridgeItemsReader fridgeItemsReader = new FridgeItemsReader();
    try {
      Fridge fridge = fridgeItemsReader.buildFridge(csvIngredients);
      
      Assert.assertEquals(5, fridge.getItems().size());
      
      Ingredient ingredient1 = new Ingredient("bread", 13, Unit.SLICES);
      Ingredient ingredient2 = new Ingredient("bread", 9, Unit.SLICES);
      Ingredient ingredient3 = new Ingredient("mixed salad", 120, Unit.GRAMS);
      
      Assert.assertFalse(fridge.containsUsuableIngredient(ingredient1));
      Assert.assertTrue(fridge.containsUsuableIngredient(ingredient2));
      Assert.assertFalse(fridge.containsUsuableIngredient(ingredient3));
      
    } catch (IllegalArgumentException | IOException | ParseException e) {
      e.printStackTrace();
      Assert.fail();
    }
  }

}
