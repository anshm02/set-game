package cs3500.set.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.view.SetGameView;

/**
 * Represents the controller for the SetThreeGame. Takes in input from the user as numbers
 * seperated by a space. The user can quit the game at any time by entering 'q' as input.
 */
public class SetGameControllerImpl implements SetGameController {

  private SetGameModel setModel;
  private SetGameView view;
  private Readable rd;

  /**
   * Intializes the set three game model, the view and user input.
   *
   * @param setModel - Model for the game to be played. Contains instructions for the game.
   * @param view - Allows the user to view the current state and board of the game.
   * @param rd - Takes in User Input.
   * @throws IllegalArgumentException if any parameters are null.
   */

  public SetGameControllerImpl(SetGameModel setModel, SetGameView view, Readable rd)
          throws IllegalArgumentException {

    if (setModel != null && view != null && rd != null) {
      this.setModel = setModel;
      this.view = view;
      this.rd = rd;
    } else {
      throw new IllegalArgumentException("Invalid parameters. One of the fields are null.");
    }
  }

  /**
   * Plays a game of set three by taking in necessary user input, and displaying the current
   * state of the game to the user.
   * @throws IllegalStateException - if transmission to the view fails.
   */
  @Override
  public void playGame() throws IllegalStateException {
    // determines if the game is over before it has started.
    boolean gameOverBeforeStarted = false;
    // check if the user has quit the game.
    boolean gameQuit = false;
    Scanner scan = new Scanner(this.rd);

    if (checkQuit(scan)) {
      gameOverBeforeStarted = true;
    }

    int height = 0;
    int width = 0;

    if (!gameOverBeforeStarted) {
      try {
        while (scan.hasNext()) {
          while (!scan.hasNextInt()) {
            scan.next();
            transmitMessage("Invalid height/width. Try again.");
          }


          height = scan.nextInt();

          while (!scan.hasNextInt()) {
            scan.next();
            transmitMessage("Invalid height/width. Try again.");
          }

          width = scan.nextInt();


          try {
            this.setModel.startGameWithDeck(this.setModel.getCompleteDeck(), height, width);
            break;
          } catch (IllegalArgumentException ie) {
            transmitMessage("Invalid height/width. Try again.");
          }
        }
      } catch (NoSuchElementException n) {
        transmitMessage("Invalid height/width. Try again.");
        throw new IllegalStateException("Invalid Input");
      }

      try {
        while (scan.hasNext()) {
          if (this.setModel.isGameOver()) {
            break;
          }

          renderCurrentState();
          transmitCurrentScore();
          int row1 = -1;
          int row2 = -1;
          int row3 = -1;
          int col1 = -1;
          int col2 = -1;
          int col3 = -1;

          if (checkQuit(scan)) {
            gameQuit = true;
            break; }

          while (row1 <= 0) {
            if (scan.hasNextInt()) {
              row1 = scan.nextInt();
            } else {
              scan.next();
            }
          }

          if (checkQuit(scan)) {
            gameQuit = true;
            break;
          }

          while (col1 <= 0) {
            if (scan.hasNextInt()) {
              col1 = scan.nextInt();
            } else {
              scan.next();
            }
          }

          if (checkQuit(scan)) {
            gameQuit = true;
            break;
          }

          while (row2 <= 0) {
            if (scan.hasNextInt()) {
              row2 = scan.nextInt();
            } else {
              scan.next();
            }
          }

          if (checkQuit(scan)) {
            gameQuit = true;
            break;
          }

          while (col2 <= 0) {
            if (scan.hasNextInt()) {
              col2 = scan.nextInt();
            } else {
              scan.next();
            }
          }

          if (checkQuit(scan)) {
            gameQuit = true;
            break;
          }
          while (row3 <= 0) {
            if (scan.hasNextInt()) {
              row3 = scan.nextInt();
            } else {
              scan.next();
            }
          }

          if (checkQuit(scan)) {
            gameQuit = true;
            break;
          }
          while (col3 <= 0) {
            if (scan.hasNextInt()) {
              col3 = scan.nextInt();
            } else {
              scan.next();
            }
          }

          checkClaimSet(row1 - 1,col1 - 1,row2 - 1,col2 - 1,
                  row3 - 1, col3 - 1);

        }
      } catch (NoSuchElementException e) {
        throw new IllegalStateException("Unable to read from readable object.");
      }
    }

    if (gameOverBeforeStarted) {
      gameOverBeforeStartedMessage();

    } else if (gameQuit) {
      gameQuitMessage();

    } else {
      gameOverMessage();
    }
  }


  /**
   * Determines if the user has quit, by checking for quit keywords.
   * @param scan - The user input seperated by spaces.
   * @return true if the user has quit and false if the user has not.
   */
  private boolean checkQuit(Scanner scan) {
    return scan.hasNext("Q") || scan.hasNext("q");
  }

  /**
   * Determines if the height and width of the game is 3.
   * @param s - user height is passed in a string.
   * @return
   */
  private boolean checkHeightAndWidth(String s, int boardDimension) {
    if (s.equals(Integer.toString(boardDimension))) {
      return true;
    } else {
      transmitMessage("Invalid height/width. Try again.");
      return false;
    }
  }

  /**
   * Displays the current board to the user.
   */
  private void renderCurrentState() {
    try {
      this.view.renderGrid();
    } catch (IOException e) {
      throw new IllegalStateException("transmission to view fails");
    }
  }

  /**
   * Transmits the current score.
   */
  private void transmitCurrentScore() throws IllegalStateException {
    try {
      this.view.renderMessage("Score: " + this.setModel.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("transmission to view fails");
    }
  }

  /**
   * Transmit a specificed message.
   * @param message - Given message to transmit the view.
   */
  private void transmitMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException i) {
      throw new IllegalStateException("transmission to view fails");
    }
  }

  /**
   * Determines the user has inputted a valid claim set.
   * @param row1 - User input. Represents the row of the first coordinate in the board.
   * @param col1 - User input. Represents the column of the first coordinate in the board.
   * @param row2 - User input. Represents the row of the second coordinate in the board.
   * @param col2 - User input. Represents the column of the second coordinate in the board.
   * @param row3 - User input. Represents the row of the third coordinate in the board.
   * @param col3 - User input. Represents the column of the third coordinate in the board.
   */
  private void checkClaimSet(int row1, int col1, int row2, int col2, int row3, int col3) {
    try {
      this.setModel.claimSet(new Coord(row1, col1), new Coord(row2, col2),
              new Coord(row3, col3));
    } catch (IllegalArgumentException e) {
      transmitMessage("Invalid Claim. Try Again.");
    }
  }

  /**
   * Transmits a game quit before the game has started message if the user has quit the game
   * before the game has started.
   */
  private void gameOverBeforeStartedMessage() {
    transmitMessage("Game quit!");
    transmitMessage("Score: 0");
  }

  /**
   * Transmit a game quit message if the user has quit the game.
   */
  private void gameQuitMessage() {
    transmitMessage("Game quit!");
    transmitMessage("State of game when quit:");
    renderCurrentState();
    transmitCurrentScore();
  }

  /**
   * Transmits a game over message if the game is over.
   */
  private void gameOverMessage() {
    transmitMessage("Game over!");
    transmitCurrentScore();
  }



}
