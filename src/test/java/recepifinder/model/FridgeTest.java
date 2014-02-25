/**
 * 
 */
package recepifinder.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

import recipefinder.model.Fridge;
import recipefinder.model.Ingredient;
import recipefinder.model.Unit;
import recipefinder.reader.FridgeItemsReader;

/**
 * @author Gabriel Gasser Noblia
 *
 */
public class FridgeTest {
  private Fridge fridge;
  
  
  @Before
  public void populateFridge() {
    String csvIngredients = "bread,10,slices,25/12/2013\n"
        + "cheese,10,slices,25/12/2014\n"
        + "butter,250,grams,25/12/2014\n"
        + "peanut butter,250,grams,2/12/2014\n"
        + "mixed salad,150,grams,26/12/2014";

    FridgeItemsReader fridgeItemsReader = new FridgeItemsReader();
    try {
      this.fridge = fridgeItemsReader.buildFridge(csvIngredients);
    } catch (IllegalArgumentException | IOException | ParseException e) {
      e.printStackTrace();
    }    
  }
  
  @Test
  public void testContainsUsuableIngredientWithADueIngredient() {
    Ingredient anIngredient = new Ingredient("bread", 4, Unit.SLICES);
    
    Assert.assertFalse(this.fridge.containsUsuableIngredient(anIngredient));
  }

  @Test
  public void testContainsUsuableIngredientWithAGoodIngredient() {
    Ingredient anIngredient = new Ingredient("cheese", 4, Unit.SLICES);
    
    Assert.assertTrue(this.fridge.containsUsuableIngredient(anIngredient));
  }

  @Test
  public void testContainsUsuableIngredientWithNotEnoughAmount() {
    Ingredient anIngredient = new Ingredient("cheese", 12, Unit.SLICES);
    
    Assert.assertFalse(this.fridge.containsUsuableIngredient(anIngredient));
  }
  
  @Test
  public void testGetIngredientsUseBy() {
    Ingredient anIngredient = new Ingredient("cheese", 12, Unit.SLICES);
    
    Date expectedDate = new GregorianCalendar(2014, 11, 25).getTime();
    Date ingredientUseBy = this.fridge.getIngredientUseBy(anIngredient);
    
    Assert.assertEquals(expectedDate, ingredientUseBy);
  }
  
}
