package cs3500.marblesolitaire.controller;

/**
 * Represents a controller for the marble solitaire game.
 */
public interface MarbleSolitaireController {

  /**
   * Plays a new game of marble solitaire. Runs the game by rendering the current state of the game,
   * transmitting the score, getting the user input for the fromRow, fromCol, toRow, and toCol
   * positions (ignores anything other than 'q', "q", or a positive number), and passes
   * these positions to the model. These steps are completed until the game is over or quit.
   * @throws IllegalStateException Thrown if controller can't read input or transmit output. Also
   *                               throws if the game isn't over, but there is no more input to
   *                               read.
   */
  public void playGame() throws IllegalStateException;

}
