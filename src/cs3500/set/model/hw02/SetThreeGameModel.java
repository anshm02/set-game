package cs3500.set.model.hw02;

import java.util.List;

import cs3500.set.model.hw03.SetModelAbstract;

/**
 * Represents the model for the game.
 */
public class SetThreeGameModel extends SetModelAbstract {

  /**
   * Initializes data to start the game.
   */
  public SetThreeGameModel() {
    super();
  }


  /**
   * Starts the game, by drawing out the board. If the deck if null or the height and width
   * parameters 3, throws an exception.
   *
   * @param deck   the list of cards in the order they will be played.
   * @param height the height of the board for this game.
   * @param width  the width of the board for this game.
   * @throws IllegalArgumentException if there are invalid height, width or deck arguments.
   */
  @Override
  public void startGameWithDeck(List<Cards> deck, int height, int width)
          throws IllegalArgumentException {
    if (width != 3 || height != 3 || deck == null || deck.size() < 9) {
      throw new IllegalArgumentException("Invalid Argument");
    }

    super.startGameWithDeck(deck, height, width);
  }






}
