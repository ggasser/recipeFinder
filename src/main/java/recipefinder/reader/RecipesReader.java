/**
 * 
 */
package recipefinder.reader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import recipefinder.model.Ingredient;
import recipefinder.model.Recipe;
import recipefinder.model.Unit;

/**
 * @author Gabriel Gasser Noblia
 * 
 */
public class RecipesReader {

  public List<Recipe> buildRecipesFromString(String jsonRecipes) throws IllegalArgumentException {
    JsonReader jsonReader = Json.createReader(new StringReader(jsonRecipes));
    JsonArray recipesArray = jsonReader.readArray();

    return buildRecipes(recipesArray);
  }

  /**
   * This method will return a list of recipes.
   * 
   * @param recipesArray
   * @return
   */
  private List<Recipe> buildRecipes(JsonArray recipesArray) {
    List<Recipe> recipesList = new ArrayList<Recipe>();
    for (int x = 0; x < recipesArray.size(); x++) {
      JsonObject recipeJsonObject = recipesArray.getJsonObject(x);
      String recipeName = recipeJsonObject.get("name").toString();
      JsonArray ingredientsJsonArray = (JsonArray) recipeJsonObject.get("ingredients");

      List<Ingredient> ingredientsList = buildIngredientList(ingredientsJsonArray);

      // Creates the recipe
      Recipe recipe = new Recipe();
      recipe.setName(recipeName);
      recipe.setIngredients(ingredientsList);

      // Add the recipe to the list of recipies
      recipesList.add(recipe);

    }

    return recipesList;
  }

  /**
   * This method returns a list of ingredients.
   * 
   * @param ingredientsJsonArray
   * @return
   */
  private List<Ingredient> buildIngredientList(JsonArray ingredientsJsonArray) {
    List<Ingredient> ingredientsList = new ArrayList<Ingredient>();
    for (int y = 0; y < ingredientsJsonArray.size(); y++) {
      JsonObject ingredientJsonObject = ingredientsJsonArray.getJsonObject(y);

      String name = ingredientJsonObject.getString("item");
      int amount = Integer.parseInt(ingredientJsonObject.getString("amount"));
      Unit unit = Unit.buildUnit(ingredientJsonObject.getString("unit"));

      if (unit == null) {
        throw new IllegalArgumentException("Invalid unit value.");
      }

      Ingredient ingredient = new Ingredient(name, amount, unit);
      ingredientsList.add(ingredient);
    }
    return ingredientsList;
  }

}
