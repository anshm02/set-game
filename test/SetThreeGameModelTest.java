import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.ArrayList;
import java.util.List;
import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.Cards;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw02.Shape;

/**
 * Represents test for the SetThreeGameModel. Adds additional tests in addiiton to the
 * AbstractModelTests to test additional features of in this model.
 */
public class SetThreeGameModelTest extends AbstractModelTests {

  List<Cards> createSampleBoard() {
    Cards c1 = new Cards(Count.ONE, Filling.STRIPED, Shape.OVAL);
    Cards c2 = new Cards(Count.TWO, Filling.EMPTY, Shape.SQUIGGLE);
    Cards c3 = new Cards(Count.THREE, Filling.FULL, Shape.DIAMOND);
    Cards c4 = new Cards(Count.ONE, Filling.STRIPED, Shape.DIAMOND);
    Cards c5 = new Cards(Count.THREE, Filling.STRIPED, Shape.DIAMOND);
    Cards c6 = new Cards(Count.ONE, Filling.EMPTY, Shape.SQUIGGLE);
    Cards c7 = new Cards(Count.TWO, Filling.FULL, Shape.DIAMOND);
    Cards c8 = new Cards(Count.THREE, Filling.EMPTY, Shape.OVAL);
    Cards c9 = new Cards(Count.ONE, Filling.EMPTY, Shape.SQUIGGLE);
    Cards c10 = new Cards(Count.TWO, Filling.FULL, Shape.OVAL);
    Cards c11 = new Cards(Count.ONE, Filling.FULL, Shape.SQUIGGLE);
    Cards c12 = new Cards(Count.THREE, Filling.STRIPED, Shape.OVAL);

    List<Cards> exampleGameBoard = new ArrayList<>();
    exampleGameBoard.add(c1);
    exampleGameBoard.add(c2);
    exampleGameBoard.add(c3);
    exampleGameBoard.add(c4);
    exampleGameBoard.add(c5);
    exampleGameBoard.add(c6);
    exampleGameBoard.add(c7);
    exampleGameBoard.add(c8);
    exampleGameBoard.add(c9);
    exampleGameBoard.add(c10);
    exampleGameBoard.add(c11);
    exampleGameBoard.add(c12);

    return exampleGameBoard;

  }

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();


  @Test
  public void testStartGameWithInvalidHeightDeck() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Argument");
    setModel.startGameWithDeck(exampleBoard, 4, 3);

  }

  @Test
  public void testStartGameWithInvalidWidthtDeck() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Argument");
    setModel.startGameWithDeck(exampleBoard, 3, 2);

  }

  @Test
  public void testStartGameWithInvalidHeightWidthDeck() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Argument");
    setModel.startGameWithDeck(exampleBoard, 2, 2);

  }


}
