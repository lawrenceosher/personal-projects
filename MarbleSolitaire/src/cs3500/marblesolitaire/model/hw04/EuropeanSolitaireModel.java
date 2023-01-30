package cs3500.marblesolitaire.model.hw04;

/**
 * A EuropeanSolitaireModel is similar to an English one, except its corners are filled in to
 * create an octagon shape, instead of the plus shape of the English board.
 */
public class EuropeanSolitaireModel extends A2SolitaireModel {


  //creates octagon shape by setting certain slots in the corners to be invalid.
  @Override
  protected void setInvalid() {

    int counterTopLeft = this.armThickness - 1;
    int counterTopRight = this.getBoardSize() - this.armThickness + 1;
    int counterBottomLeft = 1;
    int counterBottomRight = this.getBoardSize() - 1;

    //TOP LEFT SECTION
    while (counterTopLeft > 0) {
      for (int i = 0; i < this.armThickness - 1; i = i + 1) {
        for (int j = 0; j < counterTopLeft; j = j + 1) {
          this.board.get(i).set(j, SlotState.Invalid);
        }
        counterTopLeft = counterTopLeft - 1;
      }
    }

    //TOP RIGHT SECTION
    while (counterTopRight < this.getBoardSize()) {
      for (int a = 0; a < this.armThickness - 1; a = a + 1) {
        for (int b = counterTopRight; b < this.getBoardSize(); b = b + 1) {
          this.board.get(a).set(b, SlotState.Invalid);
        }
        counterTopRight = counterTopRight + 1;
      }
    }

    //BOTTOM LEFT SECTION
    while (counterBottomLeft < armThickness - 1) {
      for (int c = this.getBoardSize() - this.armThickness + 1;
           c < this.getBoardSize(); c = c + 1) {
        for (int d = 0; d < counterBottomLeft; d = d + 1) {
          this.board.get(c).set(d, SlotState.Invalid);
        }
        counterBottomLeft = counterBottomLeft + 1;
      }
    }

    //BOTTOM RIGHT SECTION
    while (counterBottomRight > this.getBoardSize() - this.armThickness) {
      for (int e = this.getBoardSize() - this.armThickness + 1; e < this.getBoardSize();
           e = e + 1) {
        for (int f = counterBottomRight; f < this.getBoardSize(); f = f + 1) {
          this.board.get(e).set(f, SlotState.Invalid);
        }
        counterBottomRight = counterBottomRight - 1;
      }
    }

  }

  /**
   * Constructor that takes no parameters and sets the model with its default side length of 3, and
   * its default empty slot of (3,3).
   */
  public EuropeanSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Constructor that creates the model with the inputted side length and the empty slot at the
   * center of the board.
   *
   * @param sideLength The desired side length of the board.
   * @throws IllegalArgumentException Thrown if the side length isn't a positive, odd number.
   */
  public EuropeanSolitaireModel(int sideLength) throws IllegalArgumentException {
    this(sideLength, (sideLength + ((sideLength - 1) * 2)) / 2,
            (sideLength + ((sideLength - 1) * 2)) / 2);
  }

  /**
   * Constructor that creates the model with a default side length of 3 and sets the empty position
   * to be in the inputted row and column.
   *
   * @param slotEmptyRow The row of the empty slot.
   * @param slotEmptyCol The column of the empty slot.
   * @throws IllegalArgumentException Thrown if the row or column are beyond the dimensions of the
   *                                  board or if the slot at that position is already invalid.
   */
  public EuropeanSolitaireModel(int slotEmptyRow, int slotEmptyCol)
          throws IllegalArgumentException {
    this(3, slotEmptyRow, slotEmptyCol);
  }

  /**
   * Constructor that creates the model with the inputted side length and empty position.
   *
   * @param sideLength   The desired side length of the board.
   * @param slotEmptyRow The row of the empty slot.
   * @param slotEmptyCol The column of the empty slot.
   * @throws IllegalArgumentException Thrown if the side length isn't a positive, odd number, or if
   *                                  the row or column are beyond the dimensions of the
   *                                  board or if the slot at that position is already invalid.
   */
  public EuropeanSolitaireModel(int sideLength, int slotEmptyRow, int slotEmptyCol)
          throws IllegalArgumentException {
    super(sideLength, slotEmptyRow, slotEmptyCol);
  }


}
