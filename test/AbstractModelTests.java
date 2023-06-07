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
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw02.Shape;
import cs3500.set.model.hw03.GeneralSetGameModel;

import static org.junit.Assert.assertEquals;

/**
 * Represents an abstract class for testing set models. This class contains class for both
 * the set three model and the general set model.
 */
public abstract class AbstractModelTests {

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

  List<Cards> createLessSampleBoard() {
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

    return exampleGameBoard;
  }

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testGetWidthToStartGame() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 5, 5);
    assertEquals(5, model.getWidth());

  }

  @Test
  public void testInvalidWidthToStartGame() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Arguments");
    model.startGameWithDeck(model.getCompleteDeck(), 3, -4);
  }

  @Test
  public void testInvalidWidthWhenGameNotStarted() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Game has not started yet");
    assertEquals(model.getWidth(), 3);
  }


  @Test
  public void testValidHeightToStartGame() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Arguments");
    model.startGameWithDeck(model.getCompleteDeck(), -3, 5);

  }


  @Test
  public void testGetHeightToStartGame() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 5, 5);
    assertEquals(5, model.getWidth());


  }

  @Test
  public void testInvalidGetScore() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Game has not started yet");
    model.getScore();

  }


  @Test
  public void testGetScoreAfterClaimSet() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 3, 3);
    model.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2,2));
    assertEquals(1, model.getScore());

  }

  @Test
  public void testGetScoreAfter2ClaimSet() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 3, 3);
    model.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2,2));
    model.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2,2));
    assertEquals(2, model.getScore());

  }

  @Test
  public void testGetScoreAfter3ClaimSet() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 3, 3);
    model.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2,2));
    model.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2,2));
    model.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2,2));
    assertEquals(3, model.getScore());
  }


  @Test
  public void testInvalidAnySetsPresentGameNotStarted() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Game has not started yet");
    model.anySetsPresent();

  }

  @Test
  public void testValidAnySetsPresent() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 3, 3);
    assertEquals( true, model.anySetsPresent());

  }

  @Test
  public void testInValidAnySetsPresent() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 3, 1);
    assertEquals( false, model.anySetsPresent());

  }

  @Test
  public void testInValid2AnySetsPresent() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 3, 2);
    assertEquals( false, model.anySetsPresent());

  }

  @Test
  public void testValid2AnySetsPresent() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 5, 5);
    assertEquals( true, model.anySetsPresent());

  }

  @Test
  public void testInValidSet() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Game has not started yet");
    model.isValidSet(new Coord(0,0), new Coord(1,1),
            new Coord(2, 2));

  }

  @Test
  public void testInValidSetCoordinates() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Coordinates out of bounds");
    model.startGameWithDeck(model.getCompleteDeck(), 3,3);
    model.isValidSet(new Coord(5,0), new Coord(5,1),
            new Coord(9, 2));

  }

  @Test
  public void testInValidSetCoordinates2() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Coordinates out of bounds");
    model.startGameWithDeck(model.getCompleteDeck(), 3,3);
    model.isValidSet(new Coord(5,0), new Coord(2,4),
            new Coord(9, 2));

  }

  @Test
  public void testInValidSetCoordinates4() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Coordinates out of bounds");
    model.startGameWithDeck(model.getCompleteDeck(), 4,3);
    model.isValidSet(new Coord(3,0), new Coord(2,2),
            new Coord(2, 5));

  }

  @Test
  public void testInValidSetCoordinates6() {
    SetGameModel model = new GeneralSetGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Coordinates out of bounds");
    model.startGameWithDeck(model.getCompleteDeck(), 4,5);
    model.isValidSet(new Coord(3,0), new Coord(2,2),
            new Coord(4, 2));

  }


  @Test
  public void testValidSet() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 3, 3);
    assertEquals( true, model.isValidSet(new Coord(0,0), new Coord(1,1),
            new Coord(2, 2)));

  }

  @Test
  public void testisValidSet() {
    SetGameModel setModel = new SetThreeGameModel();
    List<Cards> exampleBoard = createSampleBoard();
    setModel.startGameWithDeck(exampleBoard, 3, 3);

    assertEquals(true, setModel.isValidSet(new Coord(0,0),
            new Coord(0, 1), new Coord(0, 2)));

    assertEquals(false, setModel.isValidSet(new Coord(1,0),
            new Coord(0, 1), new Coord(0, 2)));

  }

  @Test
  public void testInvalidValidSet() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    setModel.startGameWithDeck(exampleBoard, 3, 3);
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Coordinates out of bounds");
    setModel.isValidSet(new Coord(0, -3), new Coord(0, 2),
            new Coord(0, 1));

  }


  @Test
  public void testGetCardAtCoord() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    Cards c = exampleBoard.get(0);
    setModel.startGameWithDeck(exampleBoard, 3, 3);

    assertEquals(c,
            setModel.getCardAtCoord(0,0));

  }

  @Test
  public void testGetCard2AtCoord() {
    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(model.getCompleteDeck(), 3, 3);
    assertEquals( "1FQ", model.getCardAtCoord(2,1));

  }

  @Test
  public void testGetCardAtCoordRowCol() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    Cards c = exampleBoard.get(0);
    setModel.startGameWithDeck(exampleBoard, 3, 3);
    assertEquals(c, setModel.getCardAtCoord(0,0));

  }

  @Test
  public void testInvalidValidSetNoGame() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Game has not started");
    setModel.isValidSet(new Coord(0, 0), new Coord(0, 2),
            new Coord(0, 1));

  }

  @Test
  public void testInvalidGetCardAtCoordRowCol() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Game has not started");
    setModel.getCardAtCoord(0, 0);

  }

  @Test
  public void testGetCardAtCoordType() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    Cards c = exampleBoard.get(0);
    setModel.startGameWithDeck(exampleBoard, 3, 3);

    assertEquals(c, setModel.getCardAtCoord(new Coord(0,0)));

  }

  @Test
  public void testInvalidGetCardAtCoordType() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Game has not started");
    setModel.getCardAtCoord(new Coord(0,0));

  }

  @Test
  public void testCoordInvalidGetCardAtCoordType() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    setModel.startGameWithDeck(exampleBoard, 3, 3);
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Coordinates out of grid");
    setModel.getCardAtCoord(new Coord(0,-3));

  }


  @Test
  public void testAnySetsPresentInvalid() {
    SetGameModel setModel = new SetThreeGameModel();
    List<Cards> exampleBoard = createSampleBoard();
    setModel.startGameWithDeck(exampleBoard, 3, 3);
    setModel.claimSet(new Coord(0, 0), new Coord(0,1), new Coord(0,2));
    assertEquals(false, setModel.anySetsPresent());

  }

  @Test
  public void testInvalidAnySetsPresentInvalid() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Game has not started");
    setModel.anySetsPresent();

  }

  @Test
  public void testGetCompleteDeck() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    List<Cards> list = setModel.getCompleteDeck();
    assertEquals(27, list.size());

  }

  @Test
  public void testStartGameWithNullDeck() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Argument");
    setModel.startGameWithDeck(null, 3, 3);


  }

  @Test
  public void testStartGameWithLessCards() {
    List<Cards> exampleBoard = createLessSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Argument");
    setModel.startGameWithDeck(exampleBoard, 3, 3);


  }



  @Test
  public void testClaimSet() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Coordinates out of bounds");
    setModel.startGameWithDeck(exampleBoard, 3, 3);

    setModel.claimSet(new Coord(-3, 0), new Coord(0, 2),
            new Coord(0, 1));

  }

  @Test
  public void testInvalidClaimSet() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Game has not yet started");

    setModel.claimSet(new Coord(1, 0), new Coord(0, 2),
            new Coord(0, 1));

  }

  @Test
  public void testInvalidClaim() {
    List<Cards> exampleBoard = createSampleBoard();
    SetGameModel setModel = new SetThreeGameModel();
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid Claim");
    setModel.startGameWithDeck(exampleBoard, 3, 3);

    setModel.claimSet(new Coord(1, 0), new Coord(0, 2),
            new Coord(0, 1));

  }

  @Test
  public void testisGameOver() {
    SetGameModel setModel = new SetThreeGameModel();
    List<Cards> exampleBoard = createSampleBoard();
    setModel.startGameWithDeck(exampleBoard, 3, 3);

    assertEquals(false, setModel.isGameOver());

  }

  @Test
  public void testisGameOver2() {
    SetGameModel setModel = new SetThreeGameModel();
    List<Cards> exampleBoard = createSampleBoard();
    setModel.startGameWithDeck(exampleBoard, 3, 3);

    assertEquals(false, setModel.isGameOver());

  }

  @Test
  public void testGame2isOver() {
    SetGameModel setModel = new SetThreeGameModel();
    List<Cards> exampleBoard = setModel.getCompleteDeck();
    setModel.startGameWithDeck(exampleBoard, 3, 3);
    setModel.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));

    assertEquals(false, setModel.isGameOver());
  }



  @Test
  public void testAnySetsPresent() {
    SetGameModel setModel = new SetThreeGameModel();
    List<Cards> exampleBoard = createSampleBoard();
    setModel.startGameWithDeck(exampleBoard, 3, 3);

    assertEquals(true, setModel.anySetsPresent());

  }

  @Test
  public void testTheGameOver() {
    SetGameModel setModel = new SetThreeGameModel();
    setModel.startGameWithDeck(setModel.getCompleteDeck(), 3, 3);
    setModel.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2, 2));
    setModel.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2, 2));
    setModel.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2, 2));
    setModel.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2, 2));
    setModel.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2, 2));
    setModel.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2, 2));
    setModel.claimSet(new Coord(0,0), new Coord(1,1), new Coord(2, 2));

    assertEquals(true, setModel.isGameOver());

  }


}
