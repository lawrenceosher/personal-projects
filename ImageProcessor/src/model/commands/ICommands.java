package model.commands;

import model.IImageProcessorModel;
import model.IImage;

/**
 * Represents the different operations that can be performed on images.
 */
public interface ICommands {

  /**
   * This method is different for each operation, depending on the functionality of that command.
   * Implements the functionality for each command.
   *
   * @param model The model that the command will be performed on.
   * @return The new image that is made after the operation is performed.
   */
  public IImage runCommand(IImageProcessorModel model);

  /**
   * Gets the name of the origin of the file that needs to be operated on.
   *
   * @return The name of the file that needs to be operated on.
   */
  public String getSrcFile();

  /**
   * Gets the name of the file that the new image needs to be assigned to.
   *
   * @return The name of the file that the new image will go to.
   */
  public String getDestFile();
}
