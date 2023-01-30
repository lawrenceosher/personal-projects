package model.commands;

import model.IImageProcessorModel;
import model.IImage;

/**
 * Abstract class that represents the different methods required for each command to function.
 */
public abstract class ACommand implements ICommands {

  protected String srcFile;

  protected String destFile;

  /**
   * Common constructor that initializes values for the name of the source and destination files.
   *
   * @param srcFile  The name of the image that needs to be modified.
   * @param destFile The name of the modified image.
   * @throws IllegalArgumentException Thrown if the srcFile or destFile are null.
   */
  public ACommand(String srcFile, String destFile) throws IllegalArgumentException {
    if (srcFile == null || destFile == null) {
      throw new IllegalArgumentException("Can't have a null argument.");
    }

    this.srcFile = srcFile;
    this.destFile = destFile;
  }

  /**
   * method that performs the modification and returns modified image in given model.
   * @param model The model that the command will be performed on.
   * @return IImage representing new modified image
   */
  public abstract IImage runCommand(IImageProcessorModel model);

  /**
   * method that gets the name of file to which the command is applied to.
   * @return String name of file that command is applied to
   */
  @Override
  public String getSrcFile() {
    return this.srcFile;
  }

  /**
   * gets the name of file to which the new file will be saved under.
   * @return String name of file that modified file is saved under
   */
  @Override
  public String getDestFile() {
    return this.destFile;
  }

}
