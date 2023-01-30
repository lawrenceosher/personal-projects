package view;

/**
 * Interface that represents the high-level requests from the user when they are using a GUI.
 */
public interface Features {

  /**
   * Accepts whichever command the user wants to process the image with and this method
   * is made to avoid tightly coupling our design.
   * @param actionCommand The name of the command that the user wants.
   * @param srcFile The file of the image that the command will be applied to.
   */
  public void acceptCommand(String actionCommand, String srcFile);



}

