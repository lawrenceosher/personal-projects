package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JFileChooser;

import model.IImageProcessorModel;
import model.commands.Blur;
import model.commands.Brighten;
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
import view.Features;
import view.IGUIView;

/**
 * Represents the Controller for a GUI that will allow the user to click buttons for commands
 * and load images.
 */
public class ImageGUIControllerImpl implements IImageGUIcontroller, Features, ActionListener {

  private final IImageProcessorModel m;
  private JFrame frame;
  private IGUIView v;
  private String filePath;
  private boolean loaded;

  /**
   * Constructor that initializes the model that the controller will use.
   *
   * @param m The model for the Image Processor.
   */
  public ImageGUIControllerImpl(IImageProcessorModel m) {
    if (m == null) {
      this.v.renderMessage("Can't work with a null model or view.");
    }
    this.m = m;
    this.loaded = false;
  }

  @Override
  public void setView(IGUIView v) {
    this.v = v;
    this.frame = v.getFrame();
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
      this.v.renderImage(this.m.getImage(srcFile));
    }
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Load")) {
      JFileChooser fChooser = new JFileChooser();
      fChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      int result = fChooser.showOpenDialog(frame);
      if (result == JFileChooser.APPROVE_OPTION) {
        File f = fChooser.getSelectedFile();
        this.v.renderMessage("Selected File: " + f);
        filePath = f.getAbsolutePath();
        this.acceptCommand(e.getActionCommand(), filePath);
        this.loaded = true;
      }
    } else if (loaded && e.getActionCommand().equals("Brighten")) {
      int value = 0;
      try {
        value = Integer.parseInt(v.getBrightInput());
      } catch (NumberFormatException p) {
        this.v.renderMessage("Please input a number");
      }
      this.m.execute(new Brighten(value, filePath, filePath));
      this.v.renderImage(this.m.getImage(filePath));
    } else if (loaded && e.getActionCommand().equals("Save")) {
      String savePath = this.v.getTextBoxInput();
      if (savePath.equals("New File Name")) {
        this.v.renderMessage("Need to type in a new file name");
      } else {
        this.m.execute(new Save("res/" + savePath, filePath));
        this.v.renderImage(this.m.getImage(filePath));
      }
    } else if (loaded) {
      this.acceptCommand(e.getActionCommand(), filePath);
    } else {
      this.v.renderMessage("Need to load image first");
    }
  }


}

