import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import controller.IImageGUIcontroller;
import model.IImageProcessorModel;
import model.commands.Blur;
import model.commands.Greyscale;
import model.commands.HorizontalFlip;
import model.commands.ICommands;
import model.commands.Load;
import model.commands.Save;
import model.commands.Sepia;
import model.commands.Sharpen;
import model.commands.VerticalFlip;
import model.commands.VisualizeBlue;
import model.commands.VisualizeGreen;
import model.commands.VisualizeIntensity;
import model.commands.VisualizeLuma;
import model.commands.VisualizeRed;
import model.commands.VisualizeValue;
import view.IGUIView;


/**
 * Represents a controller that is used to test whether it listens to buttons correctly
 * and changes the model.
 */
public class MockController implements IImageGUIcontroller, ActionListener {
  private final IImageProcessorModel m;
  private final String fileName;

  /**
   * The Constructor for the MockController that takes in a model and the name of the image.
   * @param m The IImageProcessorModel that is being used by this controller.
   * @param fileName Name of the file we are working with.
   */
  public MockController(IImageProcessorModel m, String fileName) {
    if (m == null) {
      throw new IllegalArgumentException();
    }
    this.m = m;
    this.fileName = fileName;
  }

  @Override
  public void setView(IGUIView v) {
    return;
  }

  @Override
  public void acceptCommand(String actionCommand, String srcFile) {
    HashMap<String, ICommands> possibleCommands = new HashMap<>();

    possibleCommands.put("Load", new Load(srcFile, srcFile));
    possibleCommands.put("Vertical-Flip", new VerticalFlip(srcFile, srcFile));
    possibleCommands.put("Horizontal-Flip", new HorizontalFlip(srcFile, srcFile));
    possibleCommands.put("Red-Component", new VisualizeRed(srcFile, srcFile));
    possibleCommands.put("Blue-Component", new VisualizeBlue(srcFile, srcFile));
    possibleCommands.put("Green-Component", new VisualizeGreen(srcFile, srcFile));
    possibleCommands.put("Value-Component", new VisualizeValue(srcFile, srcFile));
    possibleCommands.put("Intensity-Component", new VisualizeIntensity(srcFile, srcFile));
    possibleCommands.put("Luma-Component", new VisualizeLuma(srcFile, srcFile));
    possibleCommands.put("Save", new Save(srcFile, srcFile));
    possibleCommands.put("Blur", new Blur(srcFile, srcFile));
    possibleCommands.put("Sharpen", new Sharpen(srcFile, srcFile));
    possibleCommands.put("Greyscale", new Greyscale(srcFile, srcFile));
    possibleCommands.put("Sepia", new Sepia(srcFile, srcFile));

    ICommands command = possibleCommands.getOrDefault(actionCommand, null);
    if (command == null) {
      throw new IllegalArgumentException("Operation not supported!");
    } else {
      this.m.execute(command);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.acceptCommand(e.getActionCommand(), fileName);
  }
}
