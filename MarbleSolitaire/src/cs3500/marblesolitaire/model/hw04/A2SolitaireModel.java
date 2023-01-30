package cs3500.marblesolitaire.model.hw04;

/**
 * Abstract class that is meant to be the super class for both European and English Solitaire.
 * The abstract classes (A2SolitaireModel and ASolitaireModel) are split up so that a triangle
 * solitaire model won't have its constructors calling super() to this abstract class.
 * The constructors work differently and calling super() wouldn't make sense.
 */
public abstract class A2SolitaireModel extends ASolitaireModel {

  /**
   * Constructor that sets the armThickness and empty slot of the board to the user input.
   *
   * @param thick Represents the armThickness of the board. Must be a positive odd number.
   * @param sRow  Represents the row of the empty slot.
   * @param sCol  Represents the column of the empty slot.
   * @throws IllegalArgumentException Exception thrown if thick isn't a positive odd
   *                                  number or if sRow or sCol aren't valid.
   */
  protected A2SolitaireModel(int thick, int sRow, int sCol) throws IllegalArgumentException {

    final int emptySlotRow;
    final int emptySlotCol;

    if (thick % 2 == 0 || thick < 1) {
      throw new IllegalArgumentException("Invalid argument. Must be a positive, odd number.");
    }

    this.armThickness = thick;

    if (sRow < 0 || sRow >= this.getBoardSize() || sCol < 0 || sCol >= this.getBoardSize()) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," +
              sCol + ").");
    }

    this.boardInitializer();
    this.setInvalid();

    if (this.board.get(sRow).get(sCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," +
              sCol + ").");
    }

    emptySlotRow = sRow;
    emptySlotCol = sCol;
    this.board.get(emptySlotRow).set(emptySlotCol, SlotState.Empty);

  }

  /**
   * Constructor that takes in the armThickness of the board.
   * Also sets the empty slot to the center of the board.
   *
   * @param thick Represents the desired armThickness of the board.
   * @throws IllegalArgumentException Exception thrown if thick isn't a positive, odd number.
   */
  protected A2SolitaireModel(int thick) throws IllegalArgumentException {
    this(thick, (thick + ((thick - 1) * 2)) / 2, (thick + ((thick - 1) * 2)) / 2);
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
  protected A2SolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
  }

  /**
   * Constructor that takes no parameters and sets the game with its default armThickness of 3, and
   * its default empty slot of (3,3).
   */
  protected A2SolitaireModel() {
    this(3, 3, 3);
  }

}
