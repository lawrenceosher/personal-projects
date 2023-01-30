package model;

/**
 * Represents a type of pixel that will make up the contents of an image.
 */
public interface IPixel {

  /**
   * Retrieves the value of the red component of the pixel.
   *
   * @return The value of the red component of the pixel.
   */
  public int getRed();

  /**
   * Retrieves the value of the green component of the pixel.
   *
   * @return The value of the green component of the pixel.
   */
  public int getGreen();

  /**
   * Retrieves the value of the blue component of the pixel.
   *
   * @return The value of the blue component of the pixel.
   */
  public int getBlue();

  /**
   * Retrieves the maximum value of the three components that make up a pixel.
   *
   * @return The maximum value of the three components.
   */
  public int getValue();

  /**
   * Retrieves the average value of the three components of the pixel.
   *
   * @return The average value of the three components of the pixel.
   */
  public int getIntensity();

  /**
   * Retrieves the weighted sum of the three components of the pixel according to the formula:
   * 0.2126r + 0.7152g + 0.0722b.
   *
   * @return The weighted sum of the three components of the pixel.
   */
  public int getLuma();
}
