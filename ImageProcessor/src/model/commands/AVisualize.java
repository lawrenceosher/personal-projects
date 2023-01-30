package model.commands;

import model.IImageProcessorModel;
import model.IImage;
import model.IPixel;
import model.Image;
import model.Pixel;

/**
 * Abstract class for all the visualize commands that allows a certain component to be retrieved
 * and set for all the pixels in the new image.
 */
public abstract class AVisualize extends ACommand {

  /**
   * Constructor that initializes values for the name of the source and destination files.
   *
   * @param srcFile  The name of the image that needs to be modified.
   * @param destFile The name of the modified image.
   * @throws IllegalArgumentException Thrown if the srcFile or destFile are null.
   */
  public AVisualize(String srcFile, String destFile) throws IllegalArgumentException {
    super(srcFile, destFile);
  }

  @Override
  public IImage runCommand(IImageProcessorModel model) {
    IImage oldImage = model.getImage(this.srcFile);
    IPixel[][] oldData = model.getImage(this.srcFile).getPixels();
    IPixel[][] newData = new Pixel[oldImage.getHeight()][oldImage.getWidth()];
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        int replaceVal = this.getValueForGreyscale(oldData[i][j]);
        newData[i][j] = new Pixel(replaceVal, replaceVal, replaceVal);
      }
    }
    IImage newImage = new Image(newData, this.destFile, oldImage.getWidth(), oldImage.getHeight(),
            oldImage.getMaxValue());
    return newImage;
  }

  //gets the value that will be applied to each component in each pixel of the new image.
  protected abstract int getValueForGreyscale(IPixel givenPix);
}
