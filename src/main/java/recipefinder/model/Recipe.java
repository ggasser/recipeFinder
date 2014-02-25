/**
 * 
 */
package recipefinder.model;

import java.util.List;

/**
 * @author Gabriel Gasser Noblia
 * 
 */
public class Recipe {
  private String name;
  private List<Ingredient> ingredients;

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the ingredients
   */
  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  /**
   * @param ingredients the ingredients to set
   */
  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
}
