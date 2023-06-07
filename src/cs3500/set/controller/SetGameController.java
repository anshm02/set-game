package cs3500.set.controller;


/**
 * This interface allows for the user to make moves on the game board and accordingly
 * transmit updates that allow the user to see the user to see the current board, the score
 * and when the game is over.
 */
public interface SetGameController {

  /**
   * Plays a new game of Set.
   * @throws IllegalStateException - if the controller is unable to read or transmit a message.
   */
  void playGame() throws IllegalStateException;
}
