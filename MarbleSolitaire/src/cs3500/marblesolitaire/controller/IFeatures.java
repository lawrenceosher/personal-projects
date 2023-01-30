package cs3500.marblesolitaire.controller;

/**
 * Interface that represents the different features that the view will require.
 */
public interface IFeatures {

  /**
   * The input that the controller will utilize and this will be based on a mouse click.
   * @param row The row of the cell.
   * @param col The column of the cell.
   */
  public void clickedInput(int row, int col);
}
