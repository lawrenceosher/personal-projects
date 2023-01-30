package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Abstract class that supports moving marbles for the different models, getting the size,
 * tracking the score, and tracking if the game is over or not, and tracking the state of the
 * different slots.
 */
public abstract class ASolitaireModel implements MarbleSolitaireModel {

  /**
   * Determines the size of the top row of the board, must be an odd number.
   */
  protected int armThickness;

  /**
   * Represents a 2D arraylist of the board that is made up of SlotStates.
   */
  protected ArrayList<ArrayList<SlotState>> board = new ArrayList<ArrayList<SlotState>>();


  /**
   * Sets the board up and fills it with marbles initially.
   */
  protected void boardInitializer() {
    for (int i = 0; i < this.getBoardSize(); i = i + 1) {
      ArrayList<SlotState> row = new ArrayList<SlotState>();

      for (int j = 0; j < this.getBoardSize(); j = j + 1) {
        row.add(SlotState.Marble);
      }

      this.board.add(row);
    }
  }

  /**
   * Sets the invalid slots on the respective types of marble solitaire boards.
   */
  protected abstract void setInvalid();


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
   *                                  positions are orthogonally two positions away from each other,
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
              "positions horizontally or vertically away from each other.");
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
  }

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

    //valid move up
    if (this.validMoveUp(fromRow, fromCol, toRow, toCol) &&
            this.getSlotAt(toRow + 1, toCol) == SlotState.Marble) {
      retVal = true;
    }

    //valid move down
    if (this.validMoveDown(fromRow, fromCol, toRow, toCol) &&
            this.getSlotAt(toRow - 1, toCol) == SlotState.Marble) {
      retVal = true;
    }

    return retVal;
  }

  /**
   * Helper method that checks if there is a valid move 2 positions away. Purely checks for
   * if there is a valid orthogonal move. Doesn't check if there is a marble in between slots.
   *
   * @param fromRow The row number of the position to be moved from (starts at 0).
   * @param fromCol The column number of the position to be moved from (starts at 0).
   * @param toRow   The row number of the position to be moved to (starts at 0).
   * @param toCol   The column number of the position to be moved to (starts at 0).
   * @return true if valid, false if not.
   */
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

    return retVal;

  }

  /**
   * Helper method that checks if it a valid move to the left.
   *
   * @param fromRow The row number of the position to be moved from (starts at 0).
   * @param fromCol The column number of the position to be moved from (starts at 0).
   * @param toRow   The row number of the position to be moved to (starts at 0).
   * @param toCol   The column number of the position to be moved to (starts at 0).
   * @return true if valid, false if not.
   */
  protected boolean validMoveLeft(int fromRow, int fromCol, int toRow, int toCol) {
    return fromCol - toCol == 2 && fromRow == toRow;
  }

  /**
   * Helper method that checks if it a valid move to the right.
   *
   * @param fromRow The row number of the position to be moved from (starts at 0).
   * @param fromCol The column number of the position to be moved from (starts at 0).
   * @param toRow   The row number of the position to be moved to (starts at 0).
   * @param toCol   The column number of the position to be moved to (starts at 0).
   * @return true if valid, false if not.
   */
  protected boolean validMoveRight(int fromRow, int fromCol, int toRow, int toCol) {
    return toCol - fromCol == 2 && fromRow == toRow;
  }

  /**
   * Helper method that checks if it a valid move upwards.
   *
   * @param fromRow The row number of the position to be moved from (starts at 0).
   * @param fromCol The column number of the position to be moved from (starts at 0).
   * @param toRow   The row number of the position to be moved to (starts at 0).
   * @param toCol   The column number of the position to be moved to (starts at 0).
   * @return true if valid, false if not.
   */
  protected boolean validMoveUp(int fromRow, int fromCol, int toRow, int toCol) {
    return fromRow - toRow == 2 && fromCol == toCol;
  }

  /**
   * Helper method that checks if it a valid move downwards.
   *
   * @param fromRow The row number of the position to be moved from (starts at 0).
   * @param fromCol The column number of the position to be moved from (starts at 0).
   * @param toRow   The row number of the position to be moved to (starts at 0).
   * @param toCol   The column number of the position to be moved to (starts at 0).
   * @return true if valid, false if not.
   */
  protected boolean validMoveDown(int fromRow, int fromCol, int toRow, int toCol) {
    return toRow - fromRow == 2 && fromCol == toCol;
  }

  @Override
  public boolean isGameOver() {

    boolean retVal = false;

    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        retVal = retVal || this.validMoveAvailable(i, j);
      }
    }

    return !retVal;
  }

  /**
   * Checks if there is a valid move available orthogonally from the inputted slot on the board.
   *
   * @param row The row of the slot you want to check.
   * @param col The column of the slot you want to check.
   * @return true if there is a valid move, false otherwise.
   */
  protected boolean validMoveAvailable(int row, int col) {

    boolean moveUp = false;
    boolean moveDown = false;
    boolean moveLeft = false;
    boolean moveRight = false;

    if (this.checkValidFromTo(row, col, row - 2, col)) {
      moveUp = true;
    }

    if (this.checkValidFromTo(row, col, row + 2, col)) {
      moveDown = true;
    }

    if (this.checkValidFromTo(row, col, row, col - 2)) {
      moveLeft = true;
    }

    if (this.checkValidFromTo(row, col, row, col + 2)) {
      moveRight = true;
    }


    return moveUp || moveDown || moveLeft || moveRight;
  }


  /**
   * Helper method that ensures that the "from" and "to" positions are valid, there is a marble at
   * the specified "from" position, the "to" position is empty, the "to" and "from" positions are
   * exactly two positions away (horizontally or vertically), and that there is a marble in the
   * slot between the "to" and "from" positions.
   *
   * @param fromRow The row number of the position to be moved from (starts at 0).
   * @param fromCol The column number of the position to be moved from (starts at 0).
   * @param toRow   The row number of the position to be moved to (starts at 0).
   * @param toCol   The column number of the position to be moved to (starts at 0).
   * @return true if valid, false if not.
   */
  protected boolean checkValidFromTo(int fromRow, int fromCol, int toRow, int toCol) {

    boolean retVal = true;

    if (fromRow < 0 || fromRow > this.getBoardSize() - 1 || fromCol < 0 ||
            fromCol > this.getBoardSize() - 1) {
      return false;
    }

    if (toRow < 0 || toRow > this.getBoardSize() - 1 || toCol < 0 ||
            toCol > this.getBoardSize() - 1) {
      return false;
    }

    if (this.board.get(fromRow).get(fromCol) != SlotState.Marble) {
      return false;
    }

    if (this.board.get(toRow).get(toCol) != SlotState.Empty) {
      return false;
    }

    if (!this.validMove2PosAway(fromRow, fromCol, toRow, toCol)) {
      return false;
    }

    if (!this.marbleInBetween(fromRow, fromCol, toRow, toCol)) {
      return false;
    }

    return retVal;
  }

  @Override
  public int getBoardSize() {
    return this.armThickness + ((this.armThickness - 1) * 2);
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= this.getBoardSize() || col < 0 || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("Invalid input. Row or column are beyond the " +
              "dimensions of the board.");
    }

    return this.board.get(row).get(col);
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < this.getBoardSize(); i = i + 1) {
      for (int j = 0; j < this.getBoardSize(); j = j + 1) {
        if (this.board.get(i).get(j) == SlotState.Marble) {
          score = score + 1;
        }
      }
    }

    return score;
  }

}
