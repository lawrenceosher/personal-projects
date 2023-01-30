import javax.swing.JFrame;
import javax.swing.AbstractButton;

import model.IImage;
import view.IGUIView;

/**
 * Represents a GUI view that accepts a Abstract button and
 * clicks it to test whether the controller recognizes the command that matches with that button.
 */
public class MockGUI extends JFrame implements IGUIView {

  private MockController controller;
  private AbstractButton button;

  /**
   * Constructor for the MockGUI that sets up a controller and button that will be pressed.
   * @param controller The Controller that the MockGUI will be connected to.
   * @param button The button that will be pressed in the MockGUI.
   */
  public MockGUI(MockController controller, AbstractButton button) {
    super("Mock Image Processor Model");
    this.button = button;
    this.controller = controller;
    this.button.addActionListener(this.controller);
    this.button.doClick();
  }

  @Override
  public void renderMessage(String message) {
    this.controller.toString();
    return;
  }

  @Override
  public JFrame getFrame() {
    return this;
  }

  @Override
  public void renderImage(IImage img) {
    return;
  }

  @Override
  public String getTextBoxInput() throws IllegalArgumentException {
    return "";
  }

  @Override
  public String getBrightInput() throws IllegalArgumentException {
    return "";
  }

  private void clickButton() {
    this.button.doClick();
  }
}
