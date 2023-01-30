package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Implements the MarbleSolitaireView interface and this class represents the view of board.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {

  /**
   * The marble solitaire model that needs to be viewed.
   */
  protected final MarbleSolitaireModelState model;

  protected final Appendable app;

  /**
   * Constructor that sets the inputted model as the model that needs to be viewed. Enables
   * this.model to access the methods that it needs to print the board.
   *
   * @param model The marble solitaire model that needs to be viewed.
   * @throws IllegalArgumentException Exception thrown if the provided model is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    this(model, System.out);

  }

  /**
   * Constructor that sets the inputted model as the model that needs to be viewed. Also, sets
   * the inputted appendable to the appendable that this view will use as its destination.
   * @param model The marble solitaire model that needs to be viewed.
   * @param app Appendable used for the destination.
   * @throws IllegalArgumentException Exception thrown if the provided model or appendable are null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model,
                                 Appendable app) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Provided model is null.");
    }

    if (app == null) {
      throw new IllegalArgumentException("Provided appendable is null.");
    }

    this.model = model;
    this.app = app;

  }


  @Override
  public String toString() {

    String retVal = "";

    for (int i = 0; i < this.model.getBoardSize(); i++) {
      for (int j = 0; j < this.model.getBoardSize(); j++) {
        switch (this.model.getSlotAt(i, j)) {
          case Invalid:
            retVal = retVal + "  ";
            break;
          case Marble:
            retVal = retVal + "O";
            retVal = retVal + " ";
            break;
          case Empty:
            retVal = retVal + "_ ";
            break;
          default:
            retVal = retVal + "";
        }
      }

      retVal = retVal.stripTrailing();
      retVal = retVal + "\n";


    }


    retVal = retVal.substring(0, retVal.length() - 1);
    return retVal;
  }

  @Override
  public void renderBoard() throws IOException {

    try {
      this.app.append(this.toString());
    } catch (IOException e) {
      throw new IOException("Transmission of the board to the data destination failed.");
    }


  }

  @Override
  public void renderMessage(String message) throws IOException {

    try {
      this.app.append(message);
    } catch (IOException e) {
      throw new IOException("Transmission of the message to the data destination failed.");
    }

  }
}

