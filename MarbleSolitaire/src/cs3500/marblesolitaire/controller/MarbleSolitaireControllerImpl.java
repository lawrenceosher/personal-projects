package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Implementation of a Marble Solitaire controller that controls the flow of inputs from a Readable
 * and transmitting the state of the game to the output (Appendable).
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {


  //the model that the controller is going to direct to.
  private MarbleSolitaireModel model;

  //the view that the controller is going to direct to.
  private MarbleSolitaireView view;

  //the input that the controller will use.
  private Readable input;

  /**
   * Constructor for MarbleSolitaireControllerImpl that sets values for the model, view, and input.
   *
   * @param model The model that the controller is going to direct to.
   * @param view  The view that the controller is going to direct to.
   * @param input The input that the controller will use.
   * @throws IllegalArgumentException Thrown if either the model, view, or input are null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) throws IllegalArgumentException {

    if (model == null) {
      throw new IllegalArgumentException("Provided model is null.");
    }

    if (view == null) {
      throw new IllegalArgumentException("Provided view is null.");
    }

    if (input == null) {
      throw new IllegalArgumentException("Provided input is null.");
    }

    this.model = model;
    this.view = view;
    this.input = input;

  }


  @Override
  public void playGame() throws IllegalStateException {

    Scanner scanner = new Scanner(this.input);
    Integer[] posVals = new Integer[4];
    posVals[0] = null;
    posVals[1] = null;
    posVals[2] = null;
    posVals[3] = null;


    //when the game is not over:
    while (!this.model.isGameOver()) {

      this.transmitBoard();

      if (!this.model.isGameOver() && !scanner.hasNext()) {
        throw new IllegalStateException("Game isn't over, but there is no more input to read.");
      }

      //obtaining next user input
      while (scanner.hasNext()) {

        for (int i = 0; i < 4; i++) {
          while (posVals[i] == null && scanner.hasNext()) {
            if (scanner.hasNext("q") || scanner.hasNext("Q")) {
              this.quit();
              return;
            } else {
              try {
                posVals[i] = Integer.parseInt(scanner.next()) - 1;
                if (posVals[i] < 0) {
                  posVals[i] = null;
                  throw new NumberFormatException();
                }
              } catch (NumberFormatException e) {
                //do nothing, acts as a skip
              }
            }
          }
        }

        if (posVals[0] == null || posVals[1] == null || posVals[2] == null || posVals[3] == null) {
          throw new IllegalStateException("Ran out of inputs.");
        }

        try {
          this.model.move(posVals[0], posVals[1], posVals[2], posVals[3]);
        } catch (IllegalArgumentException e) {
          try {
            this.view.renderMessage("Invalid move. Play again. " + e.getMessage() + "\n");
          } catch (IOException f) {
            throw new IllegalStateException("Transmission to the view failed");
          }
        }

        //to reset the while loops and ensure there can be multiple moves
        posVals[0] = null;
        posVals[1] = null;
        posVals[2] = null;
        posVals[3] = null;

        //When the game is over:
        if (this.model.isGameOver()) {

          try {
            this.view.renderMessage("Game over!\n");
          } catch (IOException e) {
            throw new IllegalStateException("Transmission to the view failed.");
          }

          this.transmitBoard();

          return;

        }

      }


    }
  }


  //quits the game
  private void quit() throws IllegalStateException {

    try {
      this.view.renderMessage("Game quit!\nState of game when quit:\n");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to the view failed.");
    }

    this.transmitBoard();

  }

  //transmits the state of the board and the score to the view
  private void transmitBoard() throws IllegalStateException {

    //render the current state of the board
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to the view failed.");
    }

    //render the score
    try {
      this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to the view failed.");
    }
  }

}



