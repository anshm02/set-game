package cs3500.set.model.hw03;

import java.util.List;

import cs3500.set.model.hw02.Cards;
import cs3500.set.model.hw02.Coord;

/**
 * This class represents a general game of set. It differs from a SeT3 game by allowing the
 * board to take multiple dimension sizes for the board height and width.
 */
public class GeneralSetGameModel extends SetModelAbstract {
  /**
   * Attempts to claim a set given 3 coordinates of cards on the gameboard. If the board does not
   * have any sets, a new row of cards will be added, if there are enough cards in the deck.
   * @param coord1 the coordinates of the first card
   * @param coord2 the coordinates of the second card
   * @param coord3 the coordinates of the third card
   */
  @Override
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) {
    super.claimSet(coord1, coord2, coord3);

    while (!anySetsPresent()) {
      if (this.deck.size() < this.width) {
        this.isGameOver = true;
        break;
      }
      addGameBoardRow();

    }

  }

  /**
   * Starts the game of with the given deck, height and width. If there are no sets on the board
   * the deck height is increased by 1 and a new row is added to the gameboard.
   * @param deck - the list of cards in the order they will be played
   * @param height - the height of the board for this game
   * @param width - the width of the board for this game
   * @throws IllegalArgumentException - if the height and board does not allow
   *        - for 3 cards to be entered, if the width or height is negative,
   *        - the given deck is null or the deck size is less than the number of cards on the board.
   */
  @Override
  public void startGameWithDeck(List<Cards> deck, int height, int width) throws
          IllegalArgumentException {
    if (height * width < 3 || height < 0 || width < 0 || deck == null
        || deck.size() < width * height) {
      throw new IllegalArgumentException("Invalid Arguments");
    }

    super.startGameWithDeck(deck, height, width);

    while (!super.anySetsPresent()) {
      if (this.deck.size() < this.width) {
        this.isGameOver = true;
        break;
      }
      addGameBoardRow();
    }
  }



  /**
   * Determines if the game is over.
   * @return true, if the game is over and false if the game is still playing.
   */
  @Override
  public boolean isGameOver() {
    if (super.isGameOver) {
      return true;
    }

    return this.deck.size() < this.width && !anySetsPresent();

  }

  /**
   * Creates a row of cards to be added to the game board.
   * @return a list of cards to be added to the game board.
   */
  private Cards[] addRow() {
    Cards[] addToGameBoard = new Cards[this.width];
    for (int i = 0; i < this.width; i++) {
      addToGameBoard[i] = this.deck.remove(0);

    }

    return addToGameBoard;
  }

  /**
   * Adds row of cards to the game board. Increases the height of the game board.
   */
  private void addGameBoardRow() {
    Cards[] addRowToGameBoard = addRow();
    this.height += 1;
    Cards[][] oldGameBoard = new Cards[this.gameBoard.length][];
    for (int i = 0; i < this.gameBoard.length; i++) {
      oldGameBoard[i] = this.gameBoard[i];
    }
    this.height += 1;
    this.gameBoard = new Cards[this.height][this.width];

    for (int i = 0; i < this.gameBoard.length - 1; i++) {
      this.gameBoard[i] = oldGameBoard[i];
    }

    this.gameBoard[this.gameBoard.length - 1] = addRowToGameBoard;


  }
}
