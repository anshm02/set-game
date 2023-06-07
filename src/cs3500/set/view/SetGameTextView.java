package cs3500.set.view;

import java.io.IOException;

import cs3500.set.model.hw02.SetGameModelState;

/**
 * Displays the state of the gameboard. Allows the user to see the current state of the gameboard
 * and also see any helpful updates to the state of their game.
 */
public class SetGameTextView implements SetGameView {
  private SetGameModelState modelState;
  private Appendable ap;

  /**
   * Initializes the model state, if the given parameter is not null.
   * @param modelState - the state of the model.
   * @param ap - stores the destination of the output.
   *
   */
  public SetGameTextView(SetGameModelState modelState, Appendable ap) throws
          IllegalArgumentException {
    if (modelState != null && ap != null) {
      this.modelState = modelState;
      this.ap = ap;
    } else {
      throw new IllegalArgumentException("Invalid null input for the model state or appendable");
    }
  }

  /**
   * Defaults the constructor to the console.
   */
  public SetGameTextView(SetGameModelState setModel) {
    this.modelState = setModel;
    System.out.println(setModel);
  }


  /**
   * Displays the current state of the board as a string.
   * @return the current state of the board.
   */
  public String toString() {
    String res = "";
    int row = this.modelState.getWidth();
    int col = this.modelState.getHeight();

    for (int i = 0; i < col; i++) {
      for (int k = 0; k < row; k++) {
        if (i != col - 1 && k == row - 1) {
          res += this.modelState.getCardAtCoord(i, k) + "\n";
          continue;
        }
        if (i == col - 1 && k == row - 1) {
          res += this.modelState.getCardAtCoord(i, k);
          continue;
        }

        res += this.modelState.getCardAtCoord(i, k) + " ";
      }
    }

    return res;
  }

  /**
   * Renders the grid to the data output in the implementation.
   * The format of the grid is exactly that of the toString method.
   * @throws IOException if the transmission of the grid to the data output fails
   */
  @Override
  public void renderGrid() throws IOException {
    try {
      ap.append(toString() + "\n");
    } catch (IOException ex) {
      throw new IOException("transmission of grid to the data output fails.");
    }

  }

  /**
   * Renders a given message to the data output in the implementation.
   * @param message the message to be printed
   * @throws IOException if the transmission of the message to the data output fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      ap.append(message + "\n");
    } catch (IOException ex) {
      throw new IOException("transmission of the message to the data output fails.");
    }

  }

}
