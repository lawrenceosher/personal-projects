import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import model.IPixel;
import model.commands.ICommands;
import model.IImageProcessorModel;
import model.IImage;
import model.Image;
import model.Pixel;

/**
 * Represents a model that is used to output the given input from the controller and ensure that
 * it is correct.
 */
public class MockModel implements IImageProcessorModel {

  final Appendable outputLog;
  private HashMap<String, IImage> savedImages;

  /**
   * Constructor that initializes the value of the output log.
   *
   * @param input The input from the controller.
   * @throws IllegalArgumentException Thrown if the input is null.
   */
  public MockModel(Appendable input) throws IllegalArgumentException {
    this.outputLog = Objects.requireNonNull(input);
    this.savedImages = new HashMap<String, IImage>();
  }

  /**
   * Allows the given command to be executed on an image. Once the operation is performed, a new
   * place in the HashMap is opened up for the returned image to be put.
   * @param c The given command that will be applied to the image.
   * @throws IllegalStateException Thrown if appeding to the appendable fails.
   */
  @Override
  public void execute(ICommands c) throws IllegalStateException {
    try {
      outputLog.append(c.toString());
    } catch (IOException o) {
      throw new IllegalStateException("Cannot append to the appendable.");
    }
    this.savedImages.put(c.getDestFile(), c.runCommand(this));
  }

  @Override
  public IImage getImage(String s) {

    IImage oldImage = this.savedImages.get(s);

    IPixel[][] newData = new Pixel[oldImage.getHeight()][oldImage.getWidth()];
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        newData[i][j] = oldImage.getPixels()[i][j];
      }
    }
    IImage newImage = new Image(newData, s, oldImage.getWidth(), oldImage.getHeight(),
            oldImage.getMaxValue());
    return newImage;
  }


}
