import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.SwingGuiView;

/**
 * Main class that is used to display the new GUI view.
 */
public class Main1 {

  /**
   * Main method that sets up a default EnglishSolitaireModel and a GUI view.
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model1 = new EnglishSolitaireModel();
    MarbleSolitaireGuiView gui = new SwingGuiView(model1);


  }
}
