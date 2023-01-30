package model.commands;

import model.IImageProcessorModel;
import model.IImage;
import model.IPixel;
import model.Image;
import model.Pixel;

/**
 * Brightens or darkens an image by a certain value and this will change all the values of each
 * channel to correspond with either the brighten or darken command.
 */
public class Brighten extends ACommand {

  //the amount to brighten the image by.
  Integer value;

  /**
   * Constructor that sets the value, srcFile, and destFile for a brighten command.
   *
   * @param value    The amount to brighten or darken the image by.
   * @param srcFile  The name of the image that needs to be modified.
   * @param destFile The name of the modified image.
   * @throws IllegalArgumentException Thrown if any of the value, srcFile, or destFile are null.
   */
  public Brighten(Integer value, String srcFile, String destFile) throws IllegalArgumentException {
    super(srcFile, destFile);

    if (value == null) {
      throw new IllegalArgumentException("Provided arguments can't be null!");
    }

    this.value = value;

  }

  @Override
  public IImage runCommand(IImageProcessorModel model) {
    IImage oldImage = model.getImage(this.srcFile);
    IPixel[][] oldData = oldImage.getPixels();
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        oldData[i][j] = new Pixel(brightenBy(oldData[i][j].getRed(), oldImage.getMaxValue()),
                brightenBy(oldData[i][j].getGreen(), oldImage.getMaxValue()),
                brightenBy(oldData[i][j].getBlue(), oldImage.getMaxValue()));
      }
    }
    oldImage = new Image(oldData, this.destFile, oldImage.getWidth(), oldImage.getHeight(),
            oldImage.getMaxValue());
    return oldImage;
  }

  /**
   * Brightens the image by a certain amount, up to the maximum amount, and as little as zero.
   *
   * @param component The current value of the component for either R, G, or B.
   * @param maxValue  The maximum component value for an image.
   * @return The new value for the component that was modified.
   */
  private int brightenBy(int component, int maxValue) {

    //if they are at a component that can't be brightened anymore than the max value, return the
    //max value.
    if (Math.abs(component - maxValue) < this.value) {
      return maxValue;
    }
    if ((component + this.value) < 0) { //if they are trying to darken the image below the
      return 0;                           //minimum amount, return 0
    } else {
      return component + this.value; // increase the component value by a certain number
    }

  }

}
