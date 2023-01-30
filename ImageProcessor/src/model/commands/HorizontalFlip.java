package model.commands;

import model.IImageProcessorModel;
import model.IImage;

/**
 * Command that flips an image horizontally and essentially "mirrors" the image.
 */
public class HorizontalFlip extends ACommand {

  /**
   * Constructor that initializes values for the name of the source and destination files.
   *
   * @param srcFile  The name of the image that needs to be modified.
   * @param destFile The name of the modified image.
   * @throws IllegalArgumentException Thrown if the srcFile or destFile are null.
   */
  public HorizontalFlip(String srcFile, String destFile) throws IllegalArgumentException {
    super(srcFile, destFile);
  }

  @Override
  public IImage runCommand(IImageProcessorModel model) {
    IImage oldImage = model.getImage(srcFile);
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth() / 2; j++) {
        int firsti = i;
        int firstj = j;
        int seci = i;
        int secj = oldImage.getWidth() - 1 - j;
        oldImage.swap(firsti, firstj, seci, secj);
      }
    }

    return oldImage;
  }


}
