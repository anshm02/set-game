package cs3500.set.model.hw02;

/**
 * To represent the different fillings a card can have.
 */
public enum Filling {
  EMPTY("E"), STRIPED("S"), FULL("F");

  private final String value;
  Filling(String value) {
    this.value = value;
  }

  /**
   * Gets the value of the inputted attribute.
   * @return the value of the attribute
   */
  String getValue() {
    return this.value;
  }

}
