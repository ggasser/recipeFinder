/**
 * 
 */
package recipefinder.model;

/**
 * @author Gabriel Gasser Noblia
 * 
 */
public enum Unit {
  OF("of"), GRAMS("grams"), ML("milliliters"), SLICES("slices");

  String name;

  /**
   * 
   * @param name
   */
  Unit(String name) {
    this.name = name;
  }

  /**
   * 
   * @param name
   * @return
   */
  public static Unit buildUnit(String name) {
    switch (name) {
      case "of":
        return OF;
      case "grams":
        return GRAMS;
      case "milliliters":
        return ML;
      case "slices":
        return SLICES;
      default:
        return null;
    }
  }

}
