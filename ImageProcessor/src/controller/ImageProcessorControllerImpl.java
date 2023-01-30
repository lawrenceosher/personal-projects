package controller;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

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
import model.IImageProcessorModel;

/**
 * Controller that feeds input to the model and allows for the image processor to actually be run.
 */
public class ImageProcessorControllerImpl implements IImageProcessorController {

  //the model
  private final IImageProcessorModel m;

  //the input
  private final Readable s;

  /**
   * Constructor that takes in a model and input and allows the user to use the application.
   * @param m The inputted model.
   * @param s The inputted input.
   */
  public ImageProcessorControllerImpl(IImageProcessorModel m, Readable s) {
    if (m == null || s == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    } else {
      this.s = s;
      this.m = m;
    }
  }

  @Override
  public void playGame() throws IllegalArgumentException {
    Scanner scanner = new Scanner(this.s);
    HashMap<String, Function<Scanner, ICommands>> possibleCommands = new HashMap<>();
    possibleCommands.put("load", (Scanner s) -> new Load(s.next(), s.next()));
    possibleCommands.put("brighten", (Scanner s) -> {
      return new Brighten(s.nextInt(), s.next(), s.next());
    });
    possibleCommands.put("vertical-flip", (Scanner s) -> {
      return new VerticalFlip(s.next(), s.next());
    });
    possibleCommands.put("horizontal-flip", (Scanner s) -> {
      return new HorizontalFlip(s.next(), s.next());
    });
    possibleCommands.put("red-component", (Scanner s) -> {
      return new VisualizeRed(s.next(), s.next());
    });
    possibleCommands.put("blue-component", (Scanner s) -> {
      return new VisualizeBlue(s.next(), s.next());
    });
    possibleCommands.put("green-component", (Scanner s) -> {
      return new VisualizeGreen(s.next(), s.next());
    });
    possibleCommands.put("value-component", (Scanner s) -> {
      return new VisualizeValue(s.next(), s.next());
    });
    possibleCommands.put("intensity-component", (Scanner s) -> {
      return new VisualizeIntensity(s.next(), s.next()); });
    possibleCommands.put("luma-component", (Scanner s) -> {
      return new VisualizeLuma(s.next(), s.next());
    });
    possibleCommands.put("save", (Scanner s) -> {
      return new Save(s.next(), s.next()); });
    possibleCommands.put("blur", (Scanner s) -> {
      return new Blur(s.next(), s.next()); });
    possibleCommands.put("sharpen", (Scanner s) -> {
      return new Sharpen(s.next(), s.next()); });
    possibleCommands.put("greyscale", (Scanner s) -> {
      return new Greyscale(s.next(), s.next()); });
    possibleCommands.put("sepia", (Scanner s) -> {
      return new Sepia(s.next(), s.next()); });

    while (scanner.hasNext()) {

      String next = scanner.next();
      if (next.equalsIgnoreCase("q") || next.equalsIgnoreCase("quit")) {
        return;
      }

      Function<Scanner, ICommands> command = possibleCommands.getOrDefault(next.stripLeading(),
              null);
      if (command == null) {
        throw new IllegalArgumentException("Operation not supported!");
      } else {
        ICommands commandEx = command.apply(scanner);
        this.m.execute(commandEx);
        //view.renderImage(this.m.getImage)
      }

    }


  }
}
