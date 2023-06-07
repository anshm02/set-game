package cs3500.set.model.hw02;

/**
 * To represent the different counts of elements a card can have.
 */
public enum Count {
  ONE("1"), TWO("2"), THREE("3");

  private final String value;
  Count(String value) {
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
