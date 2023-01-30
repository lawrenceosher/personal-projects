package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Runnable version of the program that will take in various command-line arguments.
 */
public final class MarbleSolitaire {
  /**
   * Entry point to the program where users can input command-line arguments for type of model,
   * size of model, and the empty slot position.
   *
   * @param args Different commands for type, size, and empty position.
   */
  public static void main(String[] args) {

    MarbleSolitaireModel model;

    MarbleSolitaireView view;

    Readable input;

    String modelCommand;

    String type = null;

    Integer sizeNum = Integer.parseInt(null);

    Integer eRow = Integer.parseInt(null);

    Integer eCol = Integer.parseInt(null);

    input = new InputStreamReader(System.in);

    for (int i = 0; i < args.length; i = i + 1) {
      modelCommand = args[i];
      switch (modelCommand) {
        case "english":
          type = "english";
          break;
        case "european":
          type = "european";
          break;
        case "triangular":
          type = "triangular";
          break;
        case "-size":
          sizeNum = Integer.parseInt(args[i + 1]);
          break;
        case "-hole":
          eRow = Integer.parseInt(args[i + 1]);
          eCol = Integer.parseInt(args[i + 2]);
          break;
        default:
          sizeNum = 3;
          eRow = 3;
          eCol = 3;
      }
    }

    switch (type) {
      case "english":
        if (sizeNum == null && eRow == null) {
          model = new EnglishSolitaireModel();
        } else {
          model = new EnglishSolitaireModel(sizeNum, eRow, eCol);
        }
        view = new MarbleSolitaireTextView(model);
        break;
      case "european":
        if (sizeNum == null && eRow == null) {
          model = new EuropeanSolitaireModel();
        } else {
          model = new EuropeanSolitaireModel(sizeNum, eRow, eCol);
        }
        view = new MarbleSolitaireTextView(model);
        break;
      case "triangular":
        if (sizeNum == null && eRow == null) {
          model = new TriangleSolitaireModel();
        } else {
          model = new TriangleSolitaireModel(sizeNum, eRow, eCol);
        }
        view = new TriangleSolitaireTextView(model);
        break;
      default:
        throw new IllegalArgumentException("Invalid model type. Must be either english, " +
                "european, or triangular.");

    }

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();

  }
}
