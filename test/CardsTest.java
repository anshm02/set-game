import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.Cards;
import cs3500.set.model.hw02.Shape;

import static org.junit.Assert.assertEquals;

/**
 * Represents the Cards Test Class.
 */

public class CardsTest {

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testNullValidCard() {
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Null Argument Inputted");
    Cards c = new Cards(null, Filling.EMPTY, Shape.OVAL);

  }

  @Test
  public void testInValidCard() {
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Null Argument Inputted");
    Cards c = new Cards(Count.TWO, null, Shape.DIAMOND);
  }

  @Test
  public void testGetCountAttribute() {
    Cards c = new Cards(Count.ONE, Filling.EMPTY, Shape.SQUIGGLE);
    assertEquals(Count.ONE, c.getCountAttribute());
  }

  @Test
  public void testGetFillingAttribute() {
    Cards c = new Cards(Count.ONE, Filling.EMPTY, Shape.SQUIGGLE);
    assertEquals(Filling.EMPTY, c.getFillingAttribute());
  }

  @Test
  public void testGetShapeAttribute() {
    Cards c = new Cards(Count.ONE, Filling.EMPTY, Shape.SQUIGGLE);
    assertEquals(Shape.SQUIGGLE, c.getShapeAttribute());
  }

  @Test
  public void testToString() {
    Cards c = new Cards(Count.ONE, Filling.EMPTY, Shape.SQUIGGLE);
    assertEquals("1ES", c.toString());
  }

}
