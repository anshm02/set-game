import java.io.IOException;

import cs3500.set.model.hw02.SetGameModelState;
import cs3500.set.view.SetGameView;

/**
 * Represent a mock class for the view test. The purpose of this class is to make it easier
 * to write tests. It becomes quite difficult to write out the state of the board for tests.
 * This class simplifies the process by retruning "rendered board" if the renderGrid() method
 * has been called.
 */
public class MockViewTest implements SetGameView {

  private Appendable ap;
  private SetGameModelState setModel;

  /**
   * Initializes the output object and the set three game model.
   * @param ap - Stores strings which hold information about the state of the game.
   * @param setModel - the model of the Set Three Game.
   */
  public MockViewTest(Appendable ap, SetGameModelState setModel) {
    if (ap != null || setModel != null) {
      this.ap = ap;
      this.setModel = setModel;
    }
  }

  @Override
  public void renderGrid() throws IOException {
    ap.append("Rendered Board\n");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    ap.append(message + "\n");
  }
}
