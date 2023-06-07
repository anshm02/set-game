package cs3500.set.model.hw03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cs3500.set.model.hw02.Cards;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.Count;
import cs3500.set.model.hw02.Filling;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.Shape;

/**
 * To represent an abstract class for set models. This class is the model for a game of Set
 * to be played.
 */
public abstract class SetModelAbstract implements SetGameModel<Cards> {
  protected int width;
  protected int height;
  protected int score;
  protected boolean gameStarted;
  protected boolean isGameOver;
  protected Cards[][] gameBoard;
  protected List<Cards> deck;

  /**
   * Initializes the fields which represent different aspects of the game.
   */
  public SetModelAbstract() {
    this.width = 0;
    this.height = 0;
    this.score = 0;
    this.gameStarted = false;
    this.isGameOver = false;
    this.deck = new ArrayList<>();
  }

  /**
   * If the cards at the specified coordinates form a valid set, claim it,
   * and replace those cards with new cards from the deck, if possible.
   *
   * @param coord1 - the coordinates of the first card
   * @param coord2 - the coordinates of the second card
   * @param coord3 - the coordinates of the third card
   * @throws IllegalArgumentException - if any of the coordinates are invalid for the particular
   *     implementation of Set OR the cards at those coordinates do not form a set
   * @throws IllegalStateException - if the game has not started or the game has already ended
   */
  @Override
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) throws IllegalStateException,
          IllegalArgumentException {

    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not yet started");
    }

    if (checkCoordinatesOutOfBound(coord1, coord2, coord3)) {
      throw new IllegalArgumentException("Coordinates out of bounds");
    }

    if (isValidSet(coord1, coord2, coord3) && this.deck.size() >= 3) {
      this.gameBoard[coord1.row][coord1.col] =  this.deck.remove(0);
      this.gameBoard[coord2.row][coord2.col] = this.deck.remove(0);
      this.gameBoard[coord3.row][coord3.col] = this.deck.remove(0);

      this.score += 1;

    } else if (isValidSet(coord1, coord2, coord3)) {
      this.isGameOver = true;
      this.score += 1;

    } else {
      throw new IllegalArgumentException("Invalid Claim");
    }

  }

  /**
   * Starts the game with the given deck, height and width.
   * @param deck - the list of cards in the order they will be played
   * @param height - the height of the board for this game
   * @param width - the width of the board for this game
   * @throws IllegalArgumentException - If there are invalid width, height or deck paramters.
   *
   */
  @Override
  public void startGameWithDeck(List<Cards> deck, int height, int width) throws
          IllegalArgumentException {
    this.width = width;
    this.height = height;
    this.deck = deck;
    this.gameBoard = new Cards[height][width];

    for (int r = 0; r < this.height; r++) {
      for (int c = 0; c < this.width; c++) {
        this.gameBoard[r][c] = this.deck.remove(0);
      }
    }


    this.gameStarted = true;
  }

  /**
   * Return the height of the board.
   * @return the heiight of the board.
   * @throws IllegalStateException if the game has not yet started.
   */
  @Override
  public int getWidth() throws IllegalStateException {
    checkGameStarted();
    return this.width;
  }

  /**
   * Returns the width of the board.
   * @return the width of the board.
   * @throws IllegalStateException if the game has not yet started.
   */
  @Override
  public int getHeight() throws IllegalStateException {
    checkGameStarted();
    return this.height;
  }

  /**
   * The score of the game.
   * @return the score of the game.
   * @throws IllegalStateException if the game has not yet started.
   */
  @Override
  public int getScore() throws IllegalStateException {
    checkGameStarted();
    return this.score;
  }

  /**
   * Determines if there are any sets present in the board.
   * @return true if there are sets present, false if there are not sets.
   */
  @Override
  public boolean anySetsPresent() {
    checkGameStarted();
    List<Cards> listOfCardsInGrid =
            Arrays.stream(this.gameBoard).flatMap(Arrays::stream).collect(Collectors.toList());

    int listSize = listOfCardsInGrid.size();
    for (int i = 0; i < listSize; i++) {
      for (int j = 0; j < listSize; j++) {
        for (int k = 0; k < listSize; k++) {
          if (i != j && j != k && i != k) {
            if (checkSet(
                    listOfCardsInGrid.get(i).toString(), listOfCardsInGrid.get(j).toString(),
                    listOfCardsInGrid.get(k).toString())) {
              return true;
            }
          }
        }
      }
    }
    return false;

  }

  /**
   * If the 3 given coordinates make a valid set.
   * @param coord1 the coordinates of the first card
   * @param coord2 the coordinates of the second card
   * @param coord3 the coordinates of the third card
   * @return true if the given coordinates make a valid set, and false if they do not.
   * @throws IllegalArgumentException if the given coordinates are not within the game board.
   */
  @Override
  public boolean isValidSet(Coord coord1, Coord coord2, Coord coord3) throws
          IllegalArgumentException {
    checkGameStarted();

    if (checkCoordinatesOutOfBound(coord1, coord2, coord3)) {
      throw new IllegalArgumentException("Coordinates out of bounds");
    }

    Cards c1 = getCardAtCoord(coord1);
    Cards c2 = getCardAtCoord(coord2);
    Cards c3 = getCardAtCoord(coord3);

    return checkSet(c1.getCountAttribute(), c2.getCountAttribute(), c3.getCountAttribute())
            && checkSet(c1.getShapeAttribute(), c2.getShapeAttribute(), c3.getShapeAttribute())
            && checkSet(c1.getFillingAttribute(), c2.getFillingAttribute(),
            c3.getFillingAttribute());
  }

  /**
   * Gets the card at the given coordinate.
   * @param row the row of the desired card
   * @param col the column of the desired card
   * @return the card at the given coordinate
   */
  @Override
  public Cards getCardAtCoord(int row, int col) {
    checkGameStarted();
    if (row > this.height - 1 || col > this.width - 1 || row < 0 || col < 0) {
      throw new IllegalArgumentException("Coordinates out of grid");
    }

    return this.gameBoard[row][col];
  }

  /**
   * The card at the given coordinate.
   * @param coord the coordinates of the desired card
   * @return the card at the given coordiante.
   */
  @Override
  public Cards getCardAtCoord(Coord coord) {
    checkGameStarted();
    if (coord.row >= this.height || coord.col >= this.width || coord.row < 0 || coord.col < 0) {
      throw new IllegalArgumentException("Coordinates out of grid");
    }

    return getCardAtCoord(coord.row, coord.col);
  }

  /**
   * Determines if the game is over.
   *
   * @return true if the game is over, and false is the game is still playing.
   */
  @Override
  public boolean isGameOver() {
    if (anySetsPresent() && this.deck.size() < 3 && !isGameOver) {
      return false;
    }

    return isGameOver || !anySetsPresent();
  }

  /**
   * Gets all possible combinations of cards and compiles them in a deck.
   * @return the deck of all possible combinations of cards.
   */
  @Override
  public List<Cards> getCompleteDeck() {
    List<Cards> completeDeck = new ArrayList<>();

    for (int c = 0; c < Count.values().length; c++) {
      for (int f = 0; f < Filling.values().length; f++) {
        for (int s = 0; s < Shape.values().length; s++) {
          Cards cardToAdd = new Cards(Count.values()[c], Filling.values()[f], Shape.values()[s]);
          completeDeck.add(cardToAdd);
        }
      }
    }

    return completeDeck;
  }

  /**
   * Check if the inputted coordinates are valid with respect to the given the board dimensions.
   * @param coord1 - represents coordinate 1 of the board.
   * @param coord2 - represents coordinate 2 of the board.
   * @param coord3 - represents coordinate 3 of the board.
   * @return true if the coordinates are valid, and false if the coordinates are invalid.
   */
  private boolean checkCoordinatesOutOfBound(Coord coord1, Coord coord2, Coord coord3) {
    return (coord1.row < 0 || coord1.row >= this.height || coord1.col < 0
            || coord1.col >= this.width || coord2.row < 0 || coord2.row
            >= this.height || coord2.col < 0 || coord2.col >= this.width
            || coord3.row < 0 || coord3.row >= this.height || coord3.col < 0
            || coord3.col >= this.width);
  }

  /**
   * Determines the 3 given strings make a set.
   * @param c1 - represents the first card.
   * @param c2 - represents the second card.
   * @param c3 - represents the third card.
   * @return true, if the 3 given string make a set, and false if they do not.
   */
  private boolean checkSet(String c1, String c2, String c3) {
    return ((c1.equals(c2)) && c2.equals(c3) || !(c1.equals(c2) || c1.equals(c3) || c2.equals(c3)));
  }

  /**
   * Determines if the game started otherwise throws an exception.
   * @throws IllegalStateException - if the game has not started.
   */
  private void checkGameStarted() throws IllegalStateException {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not started yet");
    }

  }


}
