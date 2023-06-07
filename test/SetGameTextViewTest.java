import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * To represent tests for SetGameTextView. This class covers a wide range of different tests for
 * the SetGameTextView class.
 */
public class SetGameTextViewTest {
  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testInvalidSetGameTextView() {
    SetGameModel setModel = null;
    Appendable ap = null;

    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Invalid null input for the model state or appendable");
    new SetGameTextView(setModel, ap);

  }

  @Test
  public void testToString() {
    SetGameModel setModel = new SetThreeGameModel();
    Appendable ap = new StringBuilder();
    setModel.startGameWithDeck(setModel.getCompleteDeck(), 3, 3);
    SetGameView view = new SetGameTextView(setModel, ap);

    assertEquals("1EO 1EQ 1ED" + '\n' + "1SO 1SQ 1SD" + '\n' +
            "1FO 1FQ 1FD", view.toString());
  }

  @Test
  public void testToStringAfterClaimSet() {
    SetGameModel setModel = new SetThreeGameModel();
    Appendable ap = new StringBuilder();
    setModel.startGameWithDeck(setModel.getCompleteDeck(), 3, 3);
    setModel.claimSet(new Coord(0, 0), new Coord(1, 1), new Coord(2,2));
    SetGameView view = new SetGameTextView(setModel, ap);

    assertEquals("2EO 1EQ 1ED" + '\n' + "1SO 2EQ 1SD" + '\n' +
            "1FO 1FQ 2ED", view.toString());
  }

  @Test
  public void testRenderGrid() throws IOException {
    SetGameModel setModel = new SetThreeGameModel();
    Appendable ap = new StringBuilder();
    setModel.startGameWithDeck(setModel.getCompleteDeck(),3,3);
    SetGameView view = new SetGameTextView(setModel, ap);
    try {
      view.renderGrid();
    } catch (IOException e) {
      fail("Unknown IOException " + e.getMessage());
    }
    assertEquals( "1EO 1EQ 1ED" + '\n' + "1SO 1SQ 1SD" + '\n' +
            "1FO 1FQ 1FD" + "\n", ap.toString());
  }

  @Test
  public void testRenderGridAfterClaimSet() {
    SetGameModel setModel = new SetThreeGameModel();
    Appendable ap = new StringBuilder();
    setModel.startGameWithDeck(setModel.getCompleteDeck(),3,3);
    setModel.claimSet(new Coord(0, 0), new Coord(1, 1), new Coord(2,2));
    SetGameView view = new SetGameTextView(setModel, ap);
    try {
      view.renderGrid();
    } catch (IOException e) {
      fail("Unknown exception " + e.getMessage());
    }
    assertEquals("2EO 1EQ 1ED" + '\n' + "1SO 2EQ 1SD" + '\n' +
            "1FO 1FQ 2ED\n", ap.toString());

  }

  @Test
  public void testRenderMessage() {
    SetGameModel setModel = new SetThreeGameModel();
    Appendable ap = new StringBuilder();
    setModel.startGameWithDeck(setModel.getCompleteDeck(),3,3);
    SetGameView view = new SetGameTextView(setModel, ap);

    try {
      view.renderMessage("Invalid Claim.");
    } catch (IOException e) {
      fail("Unknown exception " + e.getMessage());
    }

    assertEquals("Invalid Claim.", ap.toString());

  }


}

