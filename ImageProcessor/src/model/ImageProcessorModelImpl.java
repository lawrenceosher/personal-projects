package model;

import java.util.HashMap;

import model.commands.ICommands;

/**
 * Represents the model for an image processing application and this is where the functionality
 * of the program takes shape. Retrieves images and applies commands to images.
 */
public class ImageProcessorModelImpl implements IImageProcessorModel {

  private final HashMap<String, IImage> savedImages;

  /**
   * Constructor that sets up the HashMap of strings(keys) and images(values).
   */
  public ImageProcessorModelImpl() {
    this.savedImages = new HashMap<String, IImage>();
  }

  @Override
  public void execute(ICommands c) {
    this.savedImages.put(c.getDestFile(), c.runCommand(this));
    System.out.println("command performed");
  }

  @Override
  public IImage getImage(String s) throws IllegalArgumentException {
    if (!this.savedImages.containsKey(s)) {
      throw new IllegalArgumentException("The image you're trying to get isn't stored.");
    }
    IImage oldImage = this.savedImages.get(s);
    IImage newImage = new Image(oldImage.getPixels(), s, oldImage.getWidth(), oldImage.getHeight(),
            oldImage.getMaxValue());
    return newImage;
  }


}
