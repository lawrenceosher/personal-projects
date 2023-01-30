package model;

/**
 * Represents the type of image that will be operated on. All images will be able to utilize
 * these commands.
 */
public interface IImage {

  /**
   * Retrieves the height of the image.
   *
   * @return The height of the image.
   */
  public int getHeight();

  /**
   * Retrieves the width of the image.
   *
   * @return The width of the image.
   */
  public int getWidth();

  /**
   * Retrieves the 2D array of pixels that an image is composed of.
   *
   * @return 2D array of pixels.
   */
  public IPixel[][] getPixels();

  /**
   * Retrieves the maximum value of the channels in the image.
   *
   * @return The maximum value of the channels in the image.
   */
  public int getMaxValue();

  /**
   * Allows the contents of an image to be swapped based on the inputed from and to positions
   * of the pixels.
   *
   * @param i1 The from row of the pixel being swapped from.
   * @param j1 The  from column of the pixel being swapped from.
   * @param i2 The to row of the pixel being swapped to.
   * @param j2 The to column of the pixel being swapped to.
   * @throws IllegalArgumentException Thrown if any of the rows or columns of the pixels are
   *                                  not within the bounds of the image dimensions.
   */
  public void swap(int i1, int j1, int i2, int j2) throws IllegalArgumentException;

}
