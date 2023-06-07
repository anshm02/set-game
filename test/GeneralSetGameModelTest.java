import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Cards;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.Shape;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;

import static org.junit.Assert.assertEquals;

/**
 * Represents test for the GeneralSetGameModel. Adds additional tests in addiiton to the
 * AbstractModelTests to test additional features of in this model.
 */
public class GeneralSetGameModelTest extends AbstractModelTests {

  List<Cards> createSampleBoard2() {
    Cards c1 = new Cards(Count.ONE, Filling.STRIPED, Shape.OVAL);
    Cards c2 = new Cards(Count.TWO, Filling.EMPTY, Shape.SQUIGGLE);
    Cards c3 = new Cards(Count.THREE, Filling.FULL, Shape.DIAMOND);
    Cards c4 = new Cards(Count.ONE, Filling.STRIPED, Shape.DIAMOND);
    Cards c5 = new Cards(Count.THREE, Filling.STRIPED, Shape.DIAMOND);


    List<Cards> exampleGameBoard = new ArrayList<>();
    exampleGameBoard.add(c1);
    exampleGameBoard.add(c2);
    exampleGameBoard.add(c3);
    exampleGameBoard.add(c4);
    exampleGameBoard.add(c5);

    return exampleGameBoard;

  }

  List<Cards> createSampleBoard3() {
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

    return exampleGameBoard;

  }


  List<Cards> createSampleBoard() {
    Cards c1 = new Cards(Count.ONE, Filling.STRIPED, Shape.OVAL);
    Cards c2 = new Cards(Count.TWO, Filling.EMPTY, Shape.SQUIGGLE);
    Cards c3 = new Cards(Count.THREE, Filling.FULL, Shape.DIAMOND);
    Cards c4 = new Cards(Count.ONE, Filling.STRIPED, Shape.DIAMOND);
    Cards c5 = new Cards(Count.THREE, Filling.STRIPED, Shape.DIAMOND);


    List<Cards> exampleGameBoard = new ArrayList<>();
    exampleGameBoard.add(c1);
    exampleGameBoard.add(c2);
    exampleGameBoard.add(c3);
    exampleGameBoard.add(c4);
    exampleGameBoard.add(c5);

    return exampleGameBoard;

  }

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testCheckHeightAfterNoSets() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 3, 1);
    assertEquals(9, model.getHeight());

  }

  @Test
  public void testCheckHeigth2AfterNoSets() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 3, 2);
    assertEquals(4, model.getHeight());


  }

  @Test
  public void testBoardAfterNoSets() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 3, 2);
    SetGameTextView view = new SetGameTextView(model);
    assertEquals("1EO 1EQ\n" +
            "1ED 1SO\n" +
            "1SQ 1SD\n" +
            "1FO 1FQ\n" ,view.toString());

  }

  @Test
  public void testBoardAfterSet() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 3, 4);
    SetGameTextView view = new SetGameTextView(model);
    assertEquals("1EO 1EQ 1ED 1SO\n" +
            "1SQ 1SD 1FO 1FQ\n" +
            "1FD 2EO 2EQ 2ED" ,view.toString());

  }

  @Test
  public void testBoardHeightAfterSet() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 4, 4);
    SetGameTextView view = new SetGameTextView(model);
    assertEquals(4 ,model.getHeight());

  }

  @Test
  public void testInvalidStartGameWithSet() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Arguments");
    model.startGameWithDeck(model.getCompleteDeck(), 1, 2);


  }

  @Test
  public void testInvalidStartGameHeightNotPosWithSet() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Arguments");
    model.startGameWithDeck(model.getCompleteDeck(), -1, 2);


  }

  @Test
  public void testInvalidStartGameWidthNotPosWithSet() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Arguments");
    model.startGameWithDeck(model.getCompleteDeck(), -1, 2);


  }

  @Test
  public void testInvalidStartGameDeckNullPosWithSet() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Arguments");
    model.startGameWithDeck(null, -1, 2);


  }

  @Test
  public void testInvalidStartGameDeckTooBigPosWithSet() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Arguments");
    model.startGameWithDeck(null, 6, 6);


  }


  @Test
  public void testGameIsOverIfNotEnoughDeckInCardToAddNewRow() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(createSampleBoard2(), 1, 3);
    assertEquals(true, model.isGameOver());


  }

  @Test
  public void testGameIsOverIfNotEnoughDeckInCardToReplaceClaimRow() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(createSampleBoard3(), 3, 3);
    model.claimSet(new Coord(0,0), new Coord(0,1), new Coord(0,2));

    assertEquals(true, model.isGameOver());


  }






}
