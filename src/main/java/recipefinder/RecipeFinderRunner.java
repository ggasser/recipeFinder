/**
 * 
 */
package recipefinder;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import recipefinder.model.Fridge;
import recipefinder.model.Recipe;
import recipefinder.reader.FridgeItemsReader;
import recipefinder.reader.RecipesReader;

/**
 * @author Gabriel Gasser Noblia
 * 
 */
public class RecipeFinderRunner {

  /**
   * @param args
   */
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("");
      System.out.println("Illegal Arguments");
      System.out.println("");
      System.out.println("Usage:");
      System.out.println("      recipeFidnerRunner <fridge.csv> <recipes.json>");
      System.exit(1);
    }

    String fridgeFilePath = args[0];
    String recipeFilePath = args[1];

    String fridgeContent = null;
    try {
      fridgeContent = readFileContent(fridgeFilePath);
    } catch (IOException e) {
      System.out.println("Error reading file: " + fridgeFilePath);
      System.exit(1);
    }

    String recipesAvailable = null;
    try {
      recipesAvailable = readFileContent(recipeFilePath);
    } catch (IOException e) {
      System.out.println("Error reading file: " + recipeFilePath);
      System.exit(1);
    }    
    
    FridgeItemsReader fridgeItemsReader = new FridgeItemsReader();
    Fridge fridge = null;
    try {
      fridge = fridgeItemsReader.buildFridge(fridgeContent);
    } catch (IllegalArgumentException | IOException | ParseException e) {
      System.out.println("Error building fridge content.");      
      System.exit(1);
    }
    
    RecipesReader recipesReader = new RecipesReader();   
    List<Recipe> recipes = null;
    try {
      recipes = recipesReader.buildRecipesFromString(recipesAvailable);
    } catch (Exception e) {
      System.out.println("Error building recipes.");      
      System.exit(1);      
    }   
    
    RecipeFinder recipeFinder = new RecipeFinder();
    recipeFinder.setFridge(fridge);
    recipeFinder.setRecipes(recipes);
    
    String recipe = recipeFinder.findARecipe();
    
    System.out.println("");
    System.out.println(recipe);
  }

  /**
   * @param filePath
   */
  private static String readFileContent(String filePath) throws IOException {
    FileInputStream fridgeInputStream = null;
    try {
      fridgeInputStream = new FileInputStream(filePath);
      String fridgeContent = IOUtils.toString(fridgeInputStream);
      return fridgeContent;
    } finally {
      if (fridgeInputStream != null) {
        try {
          fridgeInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
