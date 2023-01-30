package model.commands;

import model.IImageProcessorModel;
import model.IImage;

/**
 * Command that flips an image vertically and essentially "mirrors" the image.
 */
public class VerticalFlip extends ACommand implements ICommands {

  /**
   * Constructor that initializes values for the name of the source and destination files.
   *
   * @param srcFile  The name of the image that needs to be modified.
   * @param destFile The name of the modified image.
   * @throws IllegalArgumentException Thrown if the srcFile or destFile are null.
   */
  public VerticalFlip(String srcFile, String destFile) throws IllegalArgumentException {
    super(srcFile, destFile);
  }

  @Override
  public IImage runCommand(IImageProcessorModel model) {
    IImage oldImage = model.getImage(srcFile);
    for (int i = 0; i < oldImage.getHeight() / 2; i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        int firsti = i;
        int firstj = j;
        int seci = oldImage.getHeight() - 1 - i;
        int secj = j;
        oldImage.swap(firsti, firstj, seci, secj);
      }
    }
    return oldImage;
  }
}
