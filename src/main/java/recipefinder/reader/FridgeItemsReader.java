/**
 * 
 */
package recipefinder.reader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import recipefinder.model.Fridge;
import recipefinder.model.FridgeIngredient;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author Gabriel Gasser Noblia
 * 
 */
public class FridgeItemsReader {

  /**
   * This method will create the content in the fridge from a CSV string.
   * 
   * @param csvFridgecontent
   * @return
   * @throws IOException
   * @throws NumberFormatException
   * @throws IllegalArgumentException
   * @throws ParseException
   */
  public Fridge buildFridge(String csvFridgecontent) throws IOException, NumberFormatException,
      IllegalArgumentException, ParseException {
    Reader reader = new StringReader(csvFridgecontent);

    CSVReader csvReader = new CSVReader(reader);
    List<String[]> fridgeContent = csvReader.readAll();
    csvReader.close();

    Map<String, FridgeIngredient> fridgeIngredientMap = new HashMap<String, FridgeIngredient>();
    for (String[] strings : fridgeContent) {
      String name = strings[0];
      String amountStr = strings[1];
      String unitStr = strings[2];
      String useByStr = strings[3];

      FridgeIngredient fridgeIngredient =
          FridgeIngredient.build(name, amountStr, unitStr, useByStr);

      fridgeIngredientMap.put(name, fridgeIngredient);

    }
    Fridge fridge = new Fridge();
    fridge.setItems(fridgeIngredientMap);

    return fridge;
  }
}
