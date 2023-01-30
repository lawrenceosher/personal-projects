package controller;

import view.IGUIView;

/**
 * Interface that represents controllers that interacts with GUI's and runs Image Processor.
 */
public interface IImageGUIcontroller {
  /**
   * Used to set the view in the controller so controller can render messages in the view.
   * @param v IGUIView representing the GUI of our program.
   */
  public void setView(IGUIView v);

  /**
   * Tells the model to run the correct Command on the given file based on the given action command.
   * @param actionCommand the name of command received from the view.
   * @param srcFile the name of the file currently being worked on.
   */
  public void acceptCommand(String actionCommand, String srcFile);

}
