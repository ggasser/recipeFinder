package recipefinder;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import recipefinder.model.Fridge;
import recipefinder.model.Ingredient;
import recipefinder.model.Recipe;

/**
 * 
 * @author Gabriel Gasser Noblia
 *
 */
public class RecipeFinder {
  private Fridge fridge;
  private List<Recipe> recipes;

  /**
   * This method will analyze the ingredients in the fridge and will return what recipe can be done
   * with those ingredients. If more than one recipe is found, the one with the closest use-by
   * ingredient will be returned.
   * 
   * @return
   */
  public String findARecipe() {
    // This map will contain the recipe name as key and the lower user-by date
    // for the ingredients on that recipe.
    Map<String, Date> foundRecipes = new HashMap<String, Date>();

    for (Recipe aRecipe : recipes) {
      checkIngredientsAndToFoundRecipes(foundRecipes, aRecipe);
    }
    List<Map.Entry<String, Date>> foundRecipesEntries =
        new LinkedList<Map.Entry<String, Date>>(foundRecipes.entrySet());

    Collections.sort(foundRecipesEntries, new Comparator<Map.Entry<String, Date>>() {

      @Override
      public int compare(Entry<String, Date> o1, Entry<String, Date> o2) {
        return o1.getValue().compareTo(o2.getValue());
      }
    });

    if (foundRecipesEntries.isEmpty()) {
      return "Order Takeout";
    }

    return foundRecipesEntries.get(0).getKey();
  }

  /**
   * 
   * @param fridge
   */
  public void setFridge(Fridge fridge) {
    this.fridge = fridge;
  }

  /**
   * @param recipies the recipes to set
   */
  public void setRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }

  /**
   * This method will check every single ingredient in the recipe, if all is good, it will add the
   * recipe to the list of found ones.
   * 
   * @param foundRecipes
   * @param aRecipe
   */
  private void checkIngredientsAndToFoundRecipes(Map<String, Date> foundRecipes, Recipe aRecipe) {
    boolean haveAllIngredients = true;
    Date lowerDate = null;
    Iterator<Ingredient> ingredientIterator = aRecipe.getIngredients().iterator();
    while (ingredientIterator.hasNext() && haveAllIngredients) {
      Ingredient anIngredient = ingredientIterator.next();

      boolean isUsable = fridge.containsUsuableIngredient(anIngredient);
      haveAllIngredients &= isUsable;
      if (isUsable) {
        Date useBy = fridge.getIngredientUseBy(anIngredient);
        if (lowerDate == null || lowerDate.compareTo(useBy) > 0) {
          lowerDate = useBy;
        }
      }
    }
    if (haveAllIngredients) {
      foundRecipes.put(aRecipe.getName(), lowerDate);
    }
  }
}
