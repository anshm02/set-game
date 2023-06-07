package cs3500.set.model.hw02;

/**
 * To represent a card.
 */
public class Cards {

  Count c;
  Filling f;
  Shape s;

  /**
   * Initializes the attributes of the card.
   *
   * @param c - number of shapes in a card.
   * @param f - filling type of the shape.
   * @param s - shape type.
   */
  public Cards(Count c, Filling f, Shape s) {
    if (c == null || f == null || s == null) {
      throw new IllegalArgumentException("Null Argument Inputted");
    }

    this.c = c;
    this.f = f;
    this.s = s;
  }

  /**
   * Gets the count attribute of the card.
   * @return count attribute
   */
  public String getCountAttribute() {
    return this.c.toString();
  }

  /**
   * Gets the filling attribute of the card.
   * @return filling attribute
   */
  public String getFillingAttribute() {
    return this.f.toString();
  }

  /**
   * Gets the shape attribute of the card.
   * @return shape attribute
   */
  public String getShapeAttribute() {
    return this.s.toString();
  }

  /**
   * Gets the string representation of the attributes.
   * @return a string using the starting initials of the attributes
   */
  public String toString() {
    return this.c.getValue() + this.f.getValue() + this.s.getValue();
  }

}
