package view;

import javax.swing.JFrame;

import model.IImage;

/**
 * Interface that represents the view for the GUI for the Image Processing Application.
 */
public interface IGUIView {

  /**
   * Display a message in a suitable area of the GUI.
   * @param message the message to be displayed
   */
  void renderMessage(String message);

  /**
   * Retrieves the state of the Frame of the GUI.
   * @return JFrame of the GUI.
   */
  JFrame getFrame();

  /**
   * Renders the given image to the GUI application and makes it show up in a scrollable pane.
   * @param img The image that needs to be rendered.
   */
  void renderImage(IImage img);

  /**
   * Retrieves the input that the user enters into the text box will be used to save the file.
   * @return User inputted string.
   * @throws IllegalArgumentException Thrown if the file path is invalid.
   */
  String getTextBoxInput() throws IllegalArgumentException;

  /**
   * Retrieves the input for the amount to brighten the image by and parses it as an Integer.
   * @return User inputted integer.
   * @throws IllegalArgumentException Thrown if the user doesn't enter an Integer.
   */
  String getBrightInput() throws IllegalArgumentException;




}
