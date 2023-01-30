
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.ImageGUIControllerImpl;
import controller.ImageProcessorControllerImpl;
import controller.IImageProcessorController;
import model.ImageProcessorModelImpl;
import model.IImageProcessorModel;
import view.GUIView;
import view.IGUIView;


/**
 * The main class for the image processor application that puts the model and controller together
 * to have a functional application. The user either inputs text and then the name of text file
 * to be read inputs nothing and then scanner is set to system.in and receives argument.
 */
public class Main {

  /**
   * The entry point for the image processor application that allows the user to input commands.
   *
   * @param args The commands inputted by the user.
   */
  public static void main(String[] args) {
    Readable r;
    IImageProcessorModel m = new ImageProcessorModelImpl();

    if (args.length > 0) {
      String s = args[0];

      if (s.equals("-file")) {
        try {
          r = new FileReader(args[1]);
          IImageProcessorController controller = new ImageProcessorControllerImpl(m, r);
          controller.playGame();
        } catch (IOException o) {
          throw new IllegalStateException("File not read");
        }
      } else if (s.equals("-text")) {
        r = new InputStreamReader(System.in);
        IImageProcessorController controller = new ImageProcessorControllerImpl(m, r);
        controller.playGame();
      }

    } else {
      ImageGUIControllerImpl c = new ImageGUIControllerImpl(m);
      IGUIView v = new GUIView(c);
      c.setView(v);
    }

  }

}

