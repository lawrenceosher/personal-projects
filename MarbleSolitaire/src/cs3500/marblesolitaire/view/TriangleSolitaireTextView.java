package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Class that represents the output for a TriangleSolitaireBoard, and it's meant to render
 * different outputs for boards of different sizes with different empty slot positions.
 */
public class TriangleSolitaireTextView extends MarbleSolitaireTextView {

  /**
   * Constructor that sets the inputted model as the model that needs to be viewed. Enables
   * this.model to access the methods that it needs to print the board.
   *
   * @param model The marble solitaire model that needs to be viewed.
   * @throws IllegalArgumentException Thrown if the provided model is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    this(model, System.out);
  }

  /**
   * Constructor that sets the inputted model as the model that needs to be viewed. Also, sets
   * the inputted appendable to the appendable that this view will use as its destination.
   *
   * @param model The marble solitaire model that needs to be viewed.
   * @param app   Appendable used for the destination.
   * @throws IllegalArgumentException Exception thrown if the provided model or appendable are null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model,
                                   Appendable app) throws IllegalArgumentException {
    super(model, app);
  }


  @Override
  public String toString() {

    String retVal = "";

    //properly moves over the marbles to achieve the triangle look
    int spacesCounter = this.model.getBoardSize() - 1;

    while (spacesCounter > 0) {
      for (int i = 0; i < this.model.getBoardSize(); i++) {
        for (int k = 0; k < spacesCounter; k = k + 1) {
          retVal = retVal + " ";
        }

        for (int j = 0; j < this.model.getBoardSize(); j++) {

          switch (this.model.getSlotAt(i, j)) {
            case Invalid:
              retVal = retVal + " ";
              break;
            case Marble:
              retVal = retVal + "O ";
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
        spacesCounter = spacesCounter - 1;

      }
    }

    retVal = retVal.substring(0, retVal.length() - 1);
    return retVal;
  }


}
