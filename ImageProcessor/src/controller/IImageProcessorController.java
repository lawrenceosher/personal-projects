package controller;

/**
 * Interface that represents the controller that can be used to run the image processor application.
 */
public interface IImageProcessorController {

  /**
   * Method that gives model inputs and tells model what modifications to do. Stores the possible
   * commands that can be run for the image processor.
   */
  public void playGame();
}
