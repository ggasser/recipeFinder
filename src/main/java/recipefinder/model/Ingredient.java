/**
 * 
 */
package recipefinder.model;

/**
 * @author Gabriel Gasser Noblia
 * 
 */
public class Ingredient {
  private String name;
  private int amount;
  private Unit unit;

  /**
   * 
   */
  public Ingredient() {
  }

  /**
   * 
   * @param name
   * @param amount
   * @param unit
   */
  public Ingredient(String name, int amount, Unit unit) {
    this.name = name;
    this.amount = amount;
    this.unit = unit;
  }

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
   * @return the amount
   */
  public int getAmount() {
    return amount;
  }

  /**
   * @param amount the amount to set
   */
  public void setAmount(int amount) {
    this.amount = amount;
  }

  /**
   * @return the unit
   */
  public Unit getUnit() {
    return unit;
  }

  /**
   * @param unit the unit to set
   */
  public void setUnit(Unit unit) {
    this.unit = unit;
  }
}
