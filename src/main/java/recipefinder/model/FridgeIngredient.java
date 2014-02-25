/**
 * 
 */
package recipefinder.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Gabriel Gasser Noblia
 * 
 */
public class FridgeIngredient extends Ingredient {
  private Date useBy;

  /**
   * 
   */
  public FridgeIngredient() {
    super();
  }

  /**
   * 
   * @param name
   * @param amount
   * @param unit
   * @param useBy
   */
  public FridgeIngredient(String name, int amount, Unit unit, Date useBy) {
    super(name, amount, unit);
    this.useBy = useBy;
  }

  /**
   * @return the useBy
   */
  public Date getUseBy() {
    return useBy;
  }

  /**
   * @param useBy the useBy to set
   */
  public void setUseBy(Date useBy) {
    this.useBy = useBy;
  }

  /**
   * 
   * @param name
   * @param amountStr
   * @param unitStr
   * @param useByStr
   * @return
   */
  public static FridgeIngredient build(String name, String amountStr, String unitStr,
      String useByStr) throws NumberFormatException, IllegalArgumentException, ParseException {
    Unit unit = Unit.buildUnit(unitStr);
    if (unit == null) {
      throw new IllegalArgumentException("Invalid unit value");
    }

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    FridgeIngredient fridgeIngredient = new FridgeIngredient();
    fridgeIngredient.setName(name);
    fridgeIngredient.setAmount(Integer.parseInt(amountStr));
    fridgeIngredient.setUnit(unit);
    fridgeIngredient.setUseBy(dateFormat.parse(useByStr));

    return fridgeIngredient;
  }
}
