package model;

/**
 * Represents one type of image that the image processor will be able to use.
 */
public class Image implements IImage {
  private final IPixel[][] pixels;
  private final String imageName;
  private final int width;
  private final int height;
  private final int maxValue;

  /**
   * Constructor that initializes the values for the image's pixels, name, width, height, and
   * maximum value of the channels of the pixels.
   *
   * @param data      The 2D array of the pixels.
   * @param imageName The name of the image.
   * @param width     The width of the image.
   * @param height    The height of the image.
   * @param maxValue  The maximum value of the channels of the pixels.
   * @throws IllegalArgumentException Thrown if any of the arguments are null.
   */
  public Image(IPixel[][] data, String imageName, Integer width, Integer height, Integer maxValue)
          throws IllegalArgumentException {
    if (data == null || imageName == null || width == null || height == null || maxValue == null) {
      throw new IllegalArgumentException("Can't have provided arguments be null.");
    }

    this.pixels = data;
    this.imageName = imageName;
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  @Override
  public IPixel[][] getPixels() {
    IPixel[][] newData = new Pixel[this.height][this.width];
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        newData[i][j] = pixels[i][j];
      }
    }
    return newData;
  }

  @Override
  public void swap(int i1, int j1, int i2, int j2) throws IllegalArgumentException {

    if (i1 < 0 || i1 >= this.height || i2 < 0 || i2 >= this.height || j1 < 0  || j1 >= this.width
            || j2 < 0 || j2 >= this.width) {
      throw new IllegalArgumentException("Pixels you're trying to swap aren't in bounds of " +
              "the image dimensions.");
    }
    IPixel temp = this.pixels[i1][j1];
    this.pixels[i1][j1] = this.pixels[i2][j2];
    this.pixels[i2][j2] = temp;
  }

  /**
   * Overrides the equals method in the Object class. Makes it so two images can be checked for
   * logical equality.
   *
   * @param o The other object that is being compared to.
   * @return True if the images are the same, false if not.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Image)) {
      return false;
    }

    Image other = (Image) o;
    boolean allPixelsEqual = true;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        allPixelsEqual = allPixelsEqual && this.pixels[i][j].equals(other.pixels[i][j]);
      }
    }

    return allPixelsEqual && this.imageName.equalsIgnoreCase(other.imageName)
            && this.width == other.width && this.height == other.height
            && this.maxValue == other.maxValue;

  }

  /**
   * Computes the value of the hash code for an image. Two images should have the same hash code
   * if they are equal.
   *
   * @return The value of the hash code as an integer.
   */
  @Override
  public int hashCode() {

    int hashForEachPixel = 0;

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        hashForEachPixel = hashForEachPixel + this.pixels[i][j].hashCode();
      }
    }

    return hashForEachPixel + this.imageName.hashCode() + this.width + this.height
            + this.maxValue;
  }

}



