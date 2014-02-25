/**
 * 
 */
package recipefinder.model;

import java.util.Date;
import java.util.Map;

/**
 * @author Gabriel Gasser Noblia
 * 
 */
public class Fridge {
  private Map<String, FridgeIngredient> items;

  /**
   * @return the items
   */
  public Map<String, FridgeIngredient> getItems() {
    return items;
  }

  /**
   * @param items the items to set
   */
  public void setItems(Map<String, FridgeIngredient> items) {
    this.items = items;
  }

  /**
   * This method will verify if the given ingredient is available in the fridge and if it is not
   * expired.
   * 
   * @param ingredient
   * @return
   */
  public boolean containsUsuableIngredient(Ingredient ingredient) {
    FridgeIngredient item = this.items.get(ingredient.getName());
    boolean checksPassed = true;
    if (item != null) {
      checksPassed &= item.getAmount() >= ingredient.getAmount();
      checksPassed &= new Date().compareTo(item.getUseBy()) <= 0;

      return checksPassed;
    }

    return false;
  }

  /**
   * 
   * @param ingredient
   * @return
   */
  public Date getIngredientUseBy(Ingredient ingredient) {
    FridgeIngredient item = this.items.get(ingredient.getName());
    if (item != null) {
      return item.getUseBy();
    }
    return null;
  }
}
