import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.StringReader;

import cs3500.set.controller.SetGameController;
import cs3500.set.controller.SetGameControllerImpl;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

import static org.junit.Assert.assertEquals;

/**
 * Represent tests for the SetGameControllerImpl. This class provides a wide range of different
 * tests to tests the overall functionality of the game.
 */
public class SetGameControllerImplTest {

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testInvalidSetGameModelNull() {
    Readable rd = new StringReader("3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    model = null;
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid parameters. One of the fields are null.");
    new SetGameControllerImpl(model, view, rd).playGame();

  }

  @Test
  public void testInvalidViewNull() {
    Readable rd = new StringReader("3 3 2 2 2 2 2 2");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    view = null;
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid parameters. One of the fields are null.");
    new SetGameControllerImpl(model, view, rd).playGame();
  }

  @Test
  public void testInvalidReadableNull() {
    Readable rd = new StringReader("3 3 2 2 2 2 2 2");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    rd = null;
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid parameters. One of the fields are null.");
    new SetGameControllerImpl(model, view, rd).playGame();
  }

  @Test
  public void testValidHeight() {
    Readable rd = new StringReader("3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\nGame quit!\n"
                    + "State of game when quit:\n1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n" +
                    "Score: 0\n\n", out.toString());

  }

  @Test
  public void testInValidWidth() {
    Readable rd = new StringReader("2 3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Invalid height/width. Try again.\n1EO 1EQ 1ED\n1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\nScore: 0\n\nGame quit!\nState of game when quit:\n1EO 1EQ 1ED\n" +
            "1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\n", out.toString());

  }

  @Test
  public void testValidWidth() {
    Readable rd = new StringReader("3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\nGame quit!\n"
                    + "State of game when quit:\n1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n" +
                    "Score: 0\n\n", out.toString());

  }

  @Test
  public void testInvalidWidth() {
    Readable rd = new StringReader("3 2 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Invalid height/width. Try again.\n1EO 1EQ 1ED\n1SO 1SQ 1SD" +
            "\n1FO 1FQ 1FD\nScore: 0\n\nGame quit!\nState of game when quit:" +
            "\n1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\n", out.toString());

  }

  @Test
  public void testBothInvalidHeightWidth() {
    Readable rd = new StringReader("2 3 2 3  q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Invalid height/width. Try again.\nInvalid height/width. Try again.\n" +
            "1EO 1EQ 1ED\n1SO 1SQ 1SD" + "\n1FO 1FQ 1FD\nScore: 0\n\nGame quit!\n" +
            "State of game when quit:\n1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\n",
            out.toString());

  }

  @Test
  public void testBothInvalidHeightWidthSeriesOfInvalid() {
    Readable rd = new StringReader("2 2 2 2 2 3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Invalid height/width. Try again.\nInvalid height/width. Try again.\n" +
            "Invalid height/width. Try again.\nInvalid height/width. Try again.\n" +
            "Invalid height/width. Try again.\n1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD" +
                    "\nScore: 0\n\nGame quit!\nState of game when quit:" +
                    "\n1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\n",
            out.toString());

  }

  @Test
  public void testRenderBoard() {
    Readable rd = new StringReader("3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\nGame quit!\n"
                    + "State of game when quit:\n1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n" +
                    "Score: 0\n\n", out.toString());
  }


  @Test
  public void testRenderBoardException() {
    Readable rd = new StringReader("3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTestRenderBoardException(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("transmission to view fails");
    controller.playGame();

  }

  @Test
  public void testRenderMessage() {
    Readable rd = new StringReader("3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\nGame quit!\n"
                    + "State of game when quit:\n1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n" +
                    "Score: 0\n\n", out.toString());
  }

  @Test
  public void testRenderMessageException() {
    Readable rd = new StringReader("3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTestRenderMessageException(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("transmission to view fails");
    controller.playGame();

  }

  @Test
  public void testQuitBeforeStart() {
    Readable rd = new StringReader("q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Game quit!\n" + "Score: 0\n", out.toString());
  }

  @Test
  public void testQuitAfterClaimOneSetStart() {
    Readable rd = new StringReader("3 3 1 1 2 2 3 3");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\nGame over!\nScore: 1"
                    + "\n\n", out.toString());
  }

  @Test
  public void testGameQuitWhenTryingToClaimOn1() {
    Readable rd = new StringReader("3 3 q 1 2 2 3 3");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\nGame quit!\n" +
            "State of game when quit:\n" + "1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\n",
            out.toString());
  }

  @Test
  public void testGameQuitWhenTryingToClaimOn2() {
    Readable rd = new StringReader("3 3 1 q 2 2 3 3");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\nGame quit!\n" +
                    "State of game when quit:\n" + "1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n" +
                    "Score: 0\n\n", out.toString());
  }

  @Test
  public void testGameQuitWhenTryingToClaimOn3() {
    Readable rd = new StringReader("3 3 1 1 q 2 3 3");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\nGame quit!\n" +
                    "State of game when quit:\n" + "1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n" +
                    "Score: 0\n\n", out.toString());
  }

  @Test
  public void testGameQuitWhenTryingToClaimOn4() {
    Readable rd = new StringReader("3 3 1 1 2 q 3 3");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\nGame quit!\n" +
                    "State of game when quit:\n" + "1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n" +
                    "Score: 0\n\n", out.toString());
  }

  @Test
  public void testGameQuitWhenTryingToClaimOn5() {
    Readable rd = new StringReader("3 3 1 1 2 2 q 3");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\nGame quit!\n" +
                    "State of game when quit:\n" + "1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n" +
                    "Score: 0\n\n", out.toString());
  }

  @Test
  public void testGameQuitWhenTryingToClaimOn6() {
    Readable rd = new StringReader("3 3 1 1 2 2 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new SetGameTextView(model, out);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\nScore: 0\n\nGame quit!\n" +
                    "State of game when quit:\n" + "1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n" +
                    "Score: 0\n\n", out.toString());
  }

  @Test
  public void testRow1InvalidAndReplace() {
    Readable rd = new StringReader("3 3 1 -2 1 2 2 3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Rendered Board\nScore: 0\n\nRendered Board\nScore: 1\n\nGame quit!\n" +
                    "State of game when quit:\nRendered Board\nScore: 1\n\n",out.toString());
  }

  @Test
  public void testCol1InvalidAndReplace() {
    Readable rd = new StringReader("3 3 1 1 -2 2 2 3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Rendered Board\nScore: 0\n\nRendered Board\nScore: 1\n\nGame quit!\n" +
            "State of game when quit:\nRendered Board\nScore: 1\n\n",out.toString());
  }

  @Test
  public void testRow2InvalidAndReplace() {
    Readable rd = new StringReader("3 3 1 1 2 -2 2 3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Rendered Board\nScore: 0\n\nRendered Board\nScore: 1\n\nGame quit!\n" +
            "State of game when quit:\nRendered Board\nScore: 1\n\n",out.toString());
  }

  @Test
  public void testCol2InvalidAndReplace() {
    Readable rd = new StringReader("3 3 1 1 2 2 -2 3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Rendered Board\nScore: 0\n\nRendered Board\nScore: 1\n\nGame quit!\n" +
            "State of game when quit:\nRendered Board\nScore: 1\n\n",out.toString());
  }

  @Test
  public void testRow3InvalidAndReplace() {
    Readable rd = new StringReader("3 3 1 1 2 2 -2 3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Rendered Board\nScore: 0\n\nRendered Board\nScore: 1\n\nGame quit!\n" +
            "State of game when quit:\nRendered Board\nScore: 1\n\n",out.toString());
  }

  @Test
  public void testCol3InvalidAndReplace() {
    Readable rd = new StringReader("3 3 1 1 2 2 -2 3 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Rendered Board\nScore: 0\n\nRendered Board\nScore: 1\n\nGame quit!\n" +
            "State of game when quit:\nRendered Board\nScore: 1\n\n",out.toString());
  }

  @Test
  public void testOutOfInputExceptionWhenClaiming() {
    Readable rd = new StringReader("3 3 1");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Unable to read from readable object.");
    controller.playGame();
  }

  @Test
  public void testOutOfInputExceptionWhenStartingBoard() {
    Readable rd = new StringReader("3");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Invalid Input.");
    controller.playGame();
  }

  @Test
  public void testOutOfInputExceptionWhenStartingBoardAfterInvalid() {
    Readable rd = new StringReader("-1 3");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Invalid Input.");
    controller.playGame();
  }

  @Test
  public void testOutOfInputExceptionWhenStartingBoardAfterInvalid2() {
    Readable rd = new StringReader("-1 3 -1");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Invalid Input.");
    controller.playGame();
  }


  @Test
  public void testOutOfInputExceptionAfterClaimingBoard() {
    Readable rd = new StringReader("3 3 1 1 2 2 3 3");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Unable to read from readable object.");
    controller.playGame();
  }

  @Test
  public void testOutOfInputExceptionAfterClaimingBoard2() {
    Readable rd = new StringReader("3 3 1 1 2 2 3 3 1");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Unable to read from readable object.");
    controller.playGame();
  }

  @Test
  public void testOutOfInputExceptionAfterClaimingBoardAfterInvalid() {
    Readable rd = new StringReader("3 3 1 1 2 2 3 3 1 2 -1");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("Unable to read from readable object.");
    controller.playGame();
  }

  @Test
  public void testCheckIfClaimSet() {
    Readable rd = new StringReader("3 3 1 1 2 2 3 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Rendered Board\n" +
            "Score: 0\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "Rendered Board\n" +
            "Score: 0\n\n", out.toString());
  }

  @Test
  public void testPlayGameWithInvalidSets() {
    Readable rd = new StringReader("3 3 1 2 3 4 3 4 2 20 q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Rendered Board\nScore: 0\n\nInvalid Claim. Try Again.\n" +
            "Rendered Board\nScore: 0\n\nGame quit!\nState of game when quit:\nRendered Board\n" +
            "Score: 0\n\n", out.toString());

  }

  @Test
  public void testQuitBeforeGameStart() {
    Readable rd = new StringReader("q");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Game quit!\n" +
            "Score: 0\n", out.toString());
  }

  @Test
  public void testplayFullGameTestGameOver() {
    Readable rd = new StringReader("3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3" +
            "1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 " +
            "1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Rendered Board\n" +
            "Score: 0\n" +
            "\n" +
            "Rendered Board\n" +
            "Score: 1\n" +
            "\n" +
            "Rendered Board\n" +
            "Score: 2\n" +
            "\n" +
            "Rendered Board\n" +
            "Score: 3\n" +
            "\n" +
            "Invalid Claim. Try Again.\n" +
            "Rendered Board\n" +
            "Score: 3\n" +
            "\n" +
            "Rendered Board\n" +
            "Score: 4\n" +
            "\n" +
            "Rendered Board\n" +
            "Score: 5\n" +
            "\n" +
            "Rendered Board\n" +
            "Score: 6\n" +
            "\n" +
            "Game over!\n" +
            "Score: 7\n\n", out.toString());
  }

  @Test
  public void testPlayFullGameTestGameOverAfterInvalidSets() {
    Readable rd = new StringReader("3 3 5 3 2 1 2 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3" +
            "1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 " +
            "1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3 1 1 2 2 3 3");
    Appendable out = new StringBuilder();
    SetGameModel model = new SetThreeGameModel();
    SetGameView view = new MockViewTest(out, model);
    SetGameController controller = new SetGameControllerImpl(model, view, rd);
    controller.playGame();
    assertEquals("Rendered Board\n" +
            "Score: 0\n" +
            "\n" +
            "Invalid Claim. Try Again.\n" +
            "Rendered Board\n" +
            "Score: 0\n" +
            "\n" +
            "Rendered Board\n" +
            "Score: 1\n" +
            "\n" +
            "Rendered Board\n" +
            "Score: 2\n" +
            "\n" +
            "Invalid Claim. Try Again.\n" +
            "Rendered Board\n" +
            "Score: 2\n" +
            "\n" +
            "Rendered Board\n" +
            "Score: 3\n" +
            "\n" +
            "Rendered Board\n" +
            "Score: 4\n" +
            "\n" +
            "Rendered Board\n" +
            "Score: 5\n" +
            "\n" +
            "Rendered Board\n" +
            "Score: 6\n" +
            "\n" +
            "Game over!\n" +
            "Score: 7\n\n", out.toString());
  }





}





