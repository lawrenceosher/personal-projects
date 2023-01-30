package cs3500.marblesolitaire.model.hw02;

import java.io.IOException;

/**
 * A mock model used for testing purposes to ensure correctness of controller.
 */
public class MockModel implements MarbleSolitaireModel {

  private final MarbleSolitaireModel model;
  private final Appendable appendable;

  /**
   * Constructor for a mock model that initializes the model and the appendable log.
   * @param model The model where the actual functionality takes place.
   * @param appendable The log that the mock model is keeping track of.
   * @throws IllegalArgumentException Thrown if either the model or appendable or null.
   */
  public MockModel(MarbleSolitaireModel model, Appendable appendable)
          throws IllegalArgumentException {

    if (model == null) {
      throw new IllegalArgumentException("Provided model is null.");
    }

    if (appendable == null) {
      throw new IllegalArgumentException("Provided appendable is null");
    }
    this.model = model;
    this.appendable = appendable;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    try {
      this.appendable.append("Move: " + fromRow + " " + fromCol + " " + toRow + " " + toCol + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Transmitting to the appendable failed.");
    }
  }

  @Override
  public boolean isGameOver() {
    return this.model.isGameOver();
  }

  @Override
  public int getBoardSize() {
    return this.model.getBoardSize();
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return this.model.getSlotAt(row, col);
  }

  @Override
  public int getScore() {
    return this.model.getScore();
  }
}