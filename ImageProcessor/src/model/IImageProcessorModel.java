package model;

import model.commands.ICommands;

/**
 * Interface that represents an image processor application and holds the functionality of the
 * program.
 */
public interface IImageProcessorModel {

  /**
   * Allows the given command to be executed on an image. Once the operation is performed, a new
   * place in the HashMap is opened up for the returned image to be put.
   *
   * @param c The given command that will be applied to the image.
   */
  public void execute(ICommands c);

  /**
   * Retrieves the image that corresponds with the name that is inputted.
   *
   * @param s The name of the image that needs to be accessed.
   * @return The contents of an image.
   */
  public IImage getImage(String s);

}
