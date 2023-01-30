package cs3500.marblesolitaire.model.hw04;

/**
 * Class that represents a TriangleSolitaireModel that has the functionality of a solitaire model,
 * so it has move operations, can check the state of a slot, check the state of a game, return the
 * board size, and check the score of a game.
 */
public class TriangleSolitaireModel extends ASolitaireModel {

  //creates a shape of a right triangle, the view will make it look like an
  //equilateral triangle.
  @Override
  protected void setInvalid() {

    int counter = 1;
    while (counter <= this.getBoardSize() - 1) {
      for (int i = 0; i < this.getBoardSize(); i = i + 1) {
        for (int j = counter; j < this.getBoardSize(); j = j + 1) {
          this.board.get(i).set(j, SlotState.Invalid);
        }
        counter = counter + 1;
      }
    }

  }

  /**
   * Default constructor that makes a model of size 5 with the empty slot at (0,0).
   */
  public TriangleSolitaireModel() throws IllegalArgumentException {
    this(5, 0, 0);
  }

  /**
   * Constructor that creates a model with the inputted size and the empty slot at (0,0).
   *
   * @param size The size of the board, which means the number of slots in the bottom row and the
   *             number of rows overall.
   * @throws IllegalArgumentException Thrown if the size is not positive.
   */
  public TriangleSolitaireModel(int size) throws IllegalArgumentException {
    this(size, 0, 0);
  }

  /**
   * Constructor that creates a model with a size of 5 and the empty slot at the inputted position.
   *
   * @param eRow Row of the empty slot.
   * @param eCol Column of the empty slot.
   * @throws IllegalArgumentException Thrown if the specified position is beyond the dimensions
   *                                  of the board or if the slot is already invalid.
   */
  public TriangleSolitaireModel(int eRow, int eCol) throws IllegalArgumentException {
    this(5, eRow, eCol);
  }

  /**
   * Constructor that creates a model based on the given user input for the size of the board
   * and the position of the empty slot.
   *
   * @param size The size of the board, which means the number of slots in the bottom row and the
   *             number of rows overall.
   * @param eRow Row of the empty slot.
   * @param eCol Column of the empty slot.
   * @throws IllegalArgumentException Thrown if the size isn't positive, or if the specified
   *                                  empty position is beyond the dimensions of the board
   *                                  or if the slot is already invalid.
   */
  public TriangleSolitaireModel(int size, int eRow, int eCol) throws IllegalArgumentException {
    final int emptySlotRow;
    final int emptySlotCol;

    if (size <= 0) {
      throw new IllegalArgumentException("The size is invalid because it is not positive.");
    }

    this.armThickness = size;

    if (eRow < 0 || eRow >= this.getBoardSize() || eCol < 0 || eCol >= this.getBoardSize()) {
      throw new IllegalArgumentException("Invalid empty cell position (" + eRow + "," +
              eCol + ").");
    }

    this.boardInitializer();
    this.setInvalid();

    if (this.board.get(eRow).get(eCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty cell position (" + eRow + "," +
              eCol + ").");
    }

    emptySlotRow = eRow;
    emptySlotCol = eCol;
    this.board.get(emptySlotRow).set(emptySlotCol, SlotState.Empty);

  }

  /**
   * Move a single marble from a given position to another given position.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException A move is only valid if there is a valid row and column for
   *                                  the "from" position, a valid row and column for the "to"
   *                                  position, the "from" position has a marble at its slot,
   *                                  the "to" position is empty at its slot, the "from" and "to"
   *                                  positions are two positions away from each other,
   *                                  and if there is a marble in between the valid "from" and "to"
   *                                  positions.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    if (fromRow < 0 || fromRow > this.getBoardSize() - 1 || fromCol < 0 ||
            fromCol > this.getBoardSize() - 1) {
      throw new IllegalArgumentException("Not a valid 'from' position");
    }

    if (toRow < 0 || toRow > this.getBoardSize() - 1 || toCol < 0 ||
            toCol > this.getBoardSize() - 1) {
      throw new IllegalArgumentException("Not a valid 'to' position");
    }

    if (this.board.get(fromRow).get(fromCol) != SlotState.Marble) {
      throw new IllegalArgumentException("The position you're moving from is not valid " +
              "since there is no marble there.");
    }

    if (this.board.get(toRow).get(toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("The position you're trying to move to is not valid " +
              "since it is not empty.");
    }

    if (!this.validMove2PosAway(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("the 'from' and 'to' positions are not exactly two " +
              "positions away from each other.");
    }

    if (!this.marbleInBetween(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("The 'from' and 'to' positions are valid, but there is " +
              "not a marble in between them.");
    }

    this.board.get(fromRow).set(fromCol, SlotState.Empty);
    this.board.get(toRow).set(toCol, SlotState.Marble);

    if (this.validMoveRight(fromRow, fromCol, toRow, toCol)) {
      this.board.get(toRow).set(toCol - 1, SlotState.Empty);
    }

    if (this.validMoveLeft(fromRow, fromCol, toRow, toCol)) {
      this.board.get(toRow).set(toCol + 1, SlotState.Empty);
    }

    if (this.validMoveUp(fromRow, fromCol, toRow, toCol)) {
      this.board.get(toRow + 1).set(toCol, SlotState.Empty);
    }

    if (this.validMoveDown(fromRow, fromCol, toRow, toCol)) {
      this.board.get(toRow - 1).set(toCol, SlotState.Empty);
    }

    if (this.validMoveDiagonalUpLeft(fromRow, fromCol, toRow, toCol)) {
      this.board.get(toRow + 1).set(toCol + 1, SlotState.Empty);
    }

    if (this.validMoveDiagonalDownRight(fromRow, fromCol, toRow, toCol)) {
      this.board.get(toRow - 1).set(toCol - 1, SlotState.Empty);
    }
  }

  //checks if it is a valid move (positionally) in the direction of the upper left diagonal.
  protected boolean validMoveDiagonalUpLeft(int fromRow, int fromCol, int toRow, int toCol) {
    return fromRow - toRow == 2 && fromCol - toCol == 2;
  }

  //valid move to the upward right diagonal is the same expression as a valid move up, so no
  //override is necessary.

  //valid move to the downward left diagonal is the same expression as a valid move down, so no
  //override is necessary.

  //checks if it is a valid move (positionally) in the direction of the lower right diagonal.
  protected boolean validMoveDiagonalDownRight(int fromRow, int fromCol, int toRow, int toCol) {
    return toRow - fromRow == 2 && toCol - fromCol == 2;
  }

  //valid move left and right are the same, so no change

  /**
   * Checks if there is a valid move 2 moves away based on the inputted from and to positions
   * and checks if there is a marble in between.
   *
   * @param fromRow The row number of the position to be moved from (starts at 0).
   * @param fromCol The column number of the position to be moved from (starts at 0).
   * @param toRow   The row number of the position to be moved to (starts at 0).
   * @param toCol   The column number of the position to be moved to (starts at 0).
   * @return true if the move is valid, false if not.
   */
  @Override
  protected boolean marbleInBetween(int fromRow, int fromCol, int toRow, int toCol) {

    boolean retVal = false;

    //valid move right
    if (this.validMoveRight(fromRow, fromCol, toRow, toCol) &&
            this.getSlotAt(toRow, toCol - 1) == SlotState.Marble) {
      retVal = true;
    }

    //valid move left
    if (this.validMoveLeft(fromRow, fromCol, toRow, toCol) &&
            this.getSlotAt(toRow, toCol + 1) == SlotState.Marble) {
      retVal = true;
    }

    //valid move up left diagonal
    if (this.validMoveDiagonalUpLeft(fromRow, fromCol, toRow, toCol) &&
            this.getSlotAt(fromRow - 1, fromCol - 1) == SlotState.Marble) {
      retVal = true;
    }

    //valid move up right diagonal
    if (this.validMoveUp(fromRow, fromCol, toRow, toCol) &&
            this.getSlotAt(toRow + 1, toCol) == SlotState.Marble) {
      retVal = true;
    }

    //valid move down left diagonal
    if (this.validMoveDown(fromRow, fromCol, toRow, toCol) &&
            this.getSlotAt(toRow - 1, toCol) == SlotState.Marble) {
      retVal = true;
    }

    //valid move down right diagonal
    if (this.validMoveDiagonalDownRight(fromRow, fromCol, toRow, toCol) &&
            this.getSlotAt(toRow - 1, toCol - 1) == SlotState.Marble) {
      retVal = true;
    }

    return retVal;
  }

  /**
   * Helper method that checks if there is a valid move 2 positions away.
   * Doesn't check if there is a marble in between slots.
   *
   * @param fromRow The row number of the position to be moved from (starts at 0).
   * @param fromCol The column number of the position to be moved from (starts at 0).
   * @param toRow   The row number of the position to be moved to (starts at 0).
   * @param toCol   The column number of the position to be moved to (starts at 0).
   * @return true if valid, false if not.
   */
  @Override
  protected boolean validMove2PosAway(int fromRow, int fromCol, int toRow, int toCol) {

    boolean retVal = false;

    if (this.validMoveRight(fromRow, fromCol, toRow, toCol)) {
      retVal = true;
    }

    if (this.validMoveLeft(fromRow, fromCol, toRow, toCol)) {
      retVal = true;
    }

    if (this.validMoveUp(fromRow, fromCol, toRow, toCol)) {
      retVal = true;
    }

    if (this.validMoveDown(fromRow, fromCol, toRow, toCol)) {
      retVal = true;
    }

    if (this.validMoveDiagonalUpLeft(fromRow, fromCol, toRow, toCol)) {
      retVal = true;
    }

    if (this.validMoveDiagonalDownRight(fromRow, fromCol, toRow, toCol)) {
      retVal = true;
    }


    return retVal;

  }

  /**
   * Checks if there is a valid move available (to the left or right or 4 diagonal directions)
   * from the inputted slot on the board.
   *
   * @param row The row of the slot you want to check.
   * @param col The column of the slot you want to check.
   * @return true if there is a valid move, false otherwise.
   */
  @Override
  protected boolean validMoveAvailable(int row, int col) {

    boolean moveUpLeft = false;
    boolean moveUpRight = false;
    boolean moveDownLeft = false;
    boolean moveDownRight = false;
    boolean moveLeft = false;
    boolean moveRight = false;

    if (this.checkValidFromTo(row, col, row - 2, col)) {
      moveUpRight = true;
    }

    if (this.checkValidFromTo(row, col, row + 2, col)) {
      moveDownLeft = true;
    }

    if (this.checkValidFromTo(row, col, row - 2, col - 2)) {
      moveUpLeft = true;
    }

    if (this.checkValidFromTo(row, col, row + 2, col + 2)) {
      moveDownRight = true;
    }

    if (this.checkValidFromTo(row, col, row, col - 2)) {
      moveLeft = true;
    }

    if (this.checkValidFromTo(row, col, row, col + 2)) {
      moveRight = true;
    }


    return moveUpRight || moveDownLeft || moveUpLeft || moveDownRight || moveLeft || moveRight;
  }


  @Override
  public int getBoardSize() {
    return this.armThickness;
  }


}
