import java.io.IOException;

import cs3500.set.model.hw02.SetGameModelState;
import cs3500.set.view.SetGameView;

/**
 * Represents a mock class for the SetGameTextView. The purpose of this class is to the test whether
 * the controller can successfully handle an IOException thrown by renderMessage.
 */
public class MockViewTestRenderMessageException implements SetGameView {

  private Appendable ap;
  private SetGameModelState setModel;

  /**
   * Initializes the output object and the set three game model.
   * @param ap - Stores strings which hold information about the state of the game.
   * @param setModel - the model of the Set Three Game.
   */
  public MockViewTestRenderMessageException(Appendable ap, SetGameModelState setModel) {

    if (ap != null || setModel != null) {
      this.ap = ap;
      this.setModel = setModel;
    }
  }

  @Override
  public void renderGrid() throws IOException {
    ap.append("Grid");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    throw new IOException();
  }
}
