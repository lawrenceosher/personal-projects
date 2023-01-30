package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

/**
 * Controller that utilizes the features that the view will need.
 */
public class ControllerFeatures implements IFeatures {

  private MarbleSolitaireModel model;

  private MarbleSolitaireGuiView view;

  /**
   * Constructor that initializes the model and the view for the MarbleSolitaire game.
   * @param model The model for Marble Solitaire.
   * @param view The GUI view for Marble Solitaire.
   * @throws IllegalArgumentException Thrown if the model or view are null.
   */
  public ControllerFeatures(MarbleSolitaireModel model, MarbleSolitaireGuiView view)
          throws IllegalArgumentException {

    if (model == null || view == null) {
      throw new IllegalArgumentException("Can't have provided arguments as null.");
    }

    this.model = model;
    this.view = view;
  }


  @Override
  public void clickedInput(int row, int col) {

    this.model.move(row, row, col, col);




    //if game is over:
    this.view.renderMessage("Game over!");

  }
}
