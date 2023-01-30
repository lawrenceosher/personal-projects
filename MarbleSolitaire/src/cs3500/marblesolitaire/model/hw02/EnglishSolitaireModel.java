package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.A2SolitaireModel;

/**
 * Represents a game of Marble Solitaire and is an implementation
 * of the MarbleSolitaireModel interface.
 */
public class EnglishSolitaireModel extends A2SolitaireModel {


  //Set the invalid slots based on the shape and size of the board.
  //Meant to preserve the plus shape.
  @Override
  protected void setInvalid() {

    //TOP LEFT SQUARE
    for (int a = 0; a < this.armThickness - 1; a = a + 1) {
      for (int b = 0; b < this.armThickness - 1; b = b + 1) {
        this.board.get(a).set(b, SlotState.Invalid);
      }
    }


    //TOP RIGHT SQUARE
    for (int c = 0; c < this.armThickness - 1; c = c + 1) {
      for (int d = this.getBoardSize() - this.armThickness + 1;
           d < this.getBoardSize(); d = d + 1) {
        this.board.get(c).set(d, SlotState.Invalid);
      }
    }

    //BOTTOM LEFT SQUARE
    for (int e = this.getBoardSize() - this.armThickness + 1;
         e < this.getBoardSize(); e = e + 1) {
      for (int f = 0; f < this.armThickness - 1; f = f + 1) {
        this.board.get(e).set(f, SlotState.Invalid);
      }
    }

    //BOTTOM RIGHT SQUARE
    for (int g = this.getBoardSize() - this.armThickness + 1; g < this.getBoardSize(); g = g + 1) {
      for (int h = this.getBoardSize() - this.armThickness + 1;
           h < this.getBoardSize(); h = h + 1) {
        this.board.get(g).set(h, SlotState.Invalid);
      }
    }
  }

  /**
   * Constructor that takes no parameters and sets the game with its default armThickness of 3, and
   * its default empty slot of (3,3).
   */
  public EnglishSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Constructor that sets the empty slot of the game based on user input. Also sets the board
   * to have a default armThickness of 3.
   *
   * @param sRow The row of the desired empty slot.
   * @param sCol The column of the desired empty slot.
   * @throws IllegalArgumentException Exception thrown if sRow or sCol match up with an invalid
   *                                  slot of exceed the size of the board.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
  }


  /**
   * Constructor that takes in the armThickness of the board.
   * Also sets the empty slot to the center of the board.
   *
   * @param thick Represents the desired armThickness of the board.
   * @throws IllegalArgumentException Exception thrown if thick isn't a positive, odd number.
   */
  public EnglishSolitaireModel(int thick) throws IllegalArgumentException {
    this(thick, (thick + ((thick - 1) * 2)) / 2, (thick + ((thick - 1) * 2)) / 2);
  }

  /**
   * Constructor that sets the armThickness and empty slot of the board to the user input.
   *
   * @param thick Represents the armThickness of the board. Must be a positive odd number.
   * @param sRow  Represents the row of the empty slot.
   * @param sCol  Represents the column of the empty slot.
   * @throws IllegalArgumentException Exception thrown if thick isn't a positive odd
   *                                  number or if sRow or sCol aren't valid.
   */
  public EnglishSolitaireModel(int thick, int sRow, int sCol) throws IllegalArgumentException {
    super(thick, sRow, sCol);

  }

}
