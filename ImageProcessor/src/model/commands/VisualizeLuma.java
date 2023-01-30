package model.commands;

import model.IPixel;

/**
 * Command that creates a greyscaled image and sets all the channels of each pixel equal to the
 * weighted sum of the three components for each pixel.
 */
public class VisualizeLuma extends AVisualize {

  /**
   * Constructor that initializes values for the name of the source and destination files.
   *
   * @param srcFile  The name of the image that needs to be modified.
   * @param destFile The name of the modified image.
   * @throws IllegalArgumentException Thrown if the srcFile or destFile are null.
   */
  public VisualizeLuma(String srcFile, String destFile) throws IllegalArgumentException {
    super(srcFile, destFile);

  }

  @Override
  protected int getValueForGreyscale(IPixel givenPix) {
    return givenPix.getLuma();
  }

}
