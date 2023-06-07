package cs3500.set;

import java.io.InputStreamReader;

import cs3500.set.controller.SetGameControllerImpl;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

/**
 * This class is used to run a game of set. The user can either enter 'three' to run a game of set
 * three, or general, to run a general game of set.
 */
public final class SetGame {
  /**
   * Allows the user to specify what game they want to play and allows the user to play the game.
   * @param args - user input specifying the game they want to play.
   */
  public static void main(String[] args) {
    for (String s : args) {
      if (s.equals("three")) {
        SetGameModel model = new SetThreeGameModel();
        Readable rd = new InputStreamReader(System.in);
        Appendable ap = System.out;
        SetGameView view = new SetGameTextView(model, ap);
        SetGameControllerImpl controller = new SetGameControllerImpl(model,view,rd);
        controller.playGame();
      } else if (s.equals("general")) {
        SetGameModel model = new GeneralSetGameModel();
        Readable rd = new InputStreamReader(System.in);
        Appendable ap = System.out;
        SetGameView view = new SetGameTextView(model, ap);
        SetGameControllerImpl controller = new SetGameControllerImpl(model,view,rd);
        controller.playGame();
      }

    }
  }
}
