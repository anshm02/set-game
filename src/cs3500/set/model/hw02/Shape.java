package cs3500.set.model.hw02;

/**
 * To represent the different shapes a card can have.
 */
public enum Shape {
  OVAL("O"), SQUIGGLE("Q"), DIAMOND("D");

  private final String value;
  Shape(String value) {
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
