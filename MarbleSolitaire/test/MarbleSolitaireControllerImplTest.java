import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MockModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the MarbleSolitaireControllerImpl class.
 */
public class MarbleSolitaireControllerImplTest {

  private MarbleSolitaireModel model;

  private MarbleSolitaireView view;

  private Readable input;

  private Appendable output;

  private MarbleSolitaireController controller;

  @Before
  public void initializer() {

    this.model = new EnglishSolitaireModel();
    this.output = new StringBuilder();
    this.view = new MarbleSolitaireTextView(this.model, this.output);
  }

  @Test
  public void testConstructorException() {
    try {
      new MarbleSolitaireControllerImpl(null, this.view, new StringReader(""));
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model is null.", e.getMessage());
    }

    try {
      new MarbleSolitaireControllerImpl(this.model, null, new StringReader(""));
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided view is null.", e.getMessage());
    }

    try {
      new MarbleSolitaireControllerImpl(this.model, this.view, null);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided input is null.", e.getMessage());
    }

    try {
      new MarbleSolitaireControllerImpl(null, null, null);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model is null.", e.getMessage());
    }

    try {
      new MarbleSolitaireControllerImpl(this.model, null, null);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided view is null.", e.getMessage());
    }

    try {
      new MarbleSolitaireControllerImpl(null, this.view, null);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model is null.", e.getMessage());
    }
  }

  @Test
  public void test1Move() {
    this.input = new StringReader("2 4 4 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", this.output.toString());

  }


  @Test
  public void testInvalidNotEmptyPosition1() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 3 4 3 2 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. The position you're trying to move to is not valid since " +
            "it " + "is not empty.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testInvalidNotEmptyPosition2() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 3 4 3 6 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. The position you're trying to move to is not valid since " +
            "it " + "is not empty.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testInvalidNoMarbleBetween() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 3 4 5 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. The 'from' and 'to' positions are valid, but there is " +
            "not a marble in between them.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testInvalidNot2PosAway() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 5 6 2 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. the 'from' and 'to' positions are not exactly " +
            "two positions horizontally or vertically away from each other.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testInvalidToPosNotEmpty() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 3 4 1 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. The position you're trying to move to is not valid " +
            "since it is not empty.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testInvalidNoMarbleInFrom() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 5 4 3 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. The position you're moving from is not valid since " +
            "there is no marble there.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testInvalidFrom1() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 8 4 3 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. Not a valid 'from' position\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testInvalidFrom2() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 5 8 3 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. Not a valid 'from' position\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testInvalidFrom3() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 8 8 3 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. Not a valid 'from' position\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testInvalidTo1() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 5 4 8 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. Not a valid 'to' position\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testInvalidTo2() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 5 4 5 8 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. Not a valid 'to' position\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testInvalidTo3() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 5 4 8 8 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. Not a valid 'to' position\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testValid4Moves() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 5 2 5 4 5 5 5 3 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O _ O _ _ O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n", this.output.toString());

  }

  @Test
  public void testDiagonal() {
    this.input = new StringReader("2 4 4 4 3 2 3 4 5 5 3 3 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. the 'from' and 'to' positions are not exactly " +
            "two positions horizontally or vertically away from each other.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }

  @Test
  public void testValid2Moves() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", this.output.toString());

  }



  @Test
  public void test1InvalidThenValid() {
    this.input = new StringReader("3 4 3 2 2 4 4 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. The position you're trying to move to is not valid " +
            "since it " + "is not empty.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", this.output.toString());

  }


  @Test
  public void test1MoveWithInvalidInputs() {
    this.input = new StringReader("2 4 hello 4 world 4 q");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", this.output.toString());

  }

  @Test
  public void test1MoveWithInvalidInputs2() {
    this.input = new StringReader("2 4 hello hello hello 4 world 4 q");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", this.output.toString());

  }

  @Test
  public void test1MoveWithInvalidInputs3() {
    this.input = new StringReader("a 2 bad 4 hello hello hello 4 world 4 q");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", this.output.toString());

  }

  @Test
  public void testSingularBadInput() {
    this.input = new StringReader("bad");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    try {
      this.controller.playGame();
      fail("Method didn't throw IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Ran out of inputs.", e.getMessage());
    }

  }

  @Test
  public void testNotEnoughInputs1() {
    this.input = new StringReader("2 4 4");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    try {
      this.controller.playGame();
      fail("Method didn't throw IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Ran out of inputs.", e.getMessage());
    }

  }

  @Test
  public void testNotEnoughInputs2() {
    this.input = new StringReader("2");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    try {
      this.controller.playGame();
      fail("Method didn't throw IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Ran out of inputs.", e.getMessage());
    }

  }

  @Test
  public void testOnlyOneMove() {
    this.input = new StringReader("2 4 4 4");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    try {
      this.controller.playGame();
      fail("Method didn't throw IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Game isn't over, but there is no more input to read.", e.getMessage());
    }

  }

  @Test
  public void testNoInput() {
    this.input = new StringReader("");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    try {
      this.controller.playGame();
      fail("Method didn't throw IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Game isn't over, but there is no more input to read.", e.getMessage());
    }

  }

  @Test
  public void testOnlyOneMoveNotTwo() {
    this.input = new StringReader("2 4 4 4 4 7");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    try {
      this.controller.playGame();
      fail("Method didn't throw IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals("Ran out of inputs.", e.getMessage());
    }

  }

  @Test
  public void testIgnoringNegativeAndZero() {
    this.input = new StringReader("-1 2 -5 -2 4 0 4 -7 -8 4 q");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", this.output.toString());

  }


  //testing quit in different spots
  @Test
  public void testQuitBefore() {
    this.input = new StringReader("q 2 4 4 4");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", this.output.toString());

  }

  @Test
  public void testQuit2ndSpot() {
    this.input = new StringReader("2 Q 4 4 4");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", this.output.toString());

  }

  @Test
  public void testQuit3rdSpot() {
    this.input = new StringReader("2 4 q 4 4");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", this.output.toString());

  }

  @Test
  public void testQuit4thSpot() {
    this.input = new StringReader("2 4 4 Q 4");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", this.output.toString());

  }

  @Test
  public void testMultipleQuits() {
    this.input = new StringReader("2 Q 4 4 q 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", this.output.toString());

  }

  @Test
  public void testMultipleQuitsAfterFirstMove() {
    this.input = new StringReader("2 4 4 4 5 q 4 q 3 q 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", this.output.toString());

  }

  @Test
  public void testJustQuit() {
    this.input = new StringReader("q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", this.output.toString());

  }


  @Test
  public void testGameOver() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 5 2 5 4 5 5 5 3 4 6 4 4 4 4 2 4 1 "
            + "4 3 4 2 5 4 5 7 4 5 4 7 5 5 5 4 5 6 5 3 7 3 5 3 4 3 6 5 7 3 7 3 7 3 5 3 2 "
            + "3 4 3 4 3 6 4 2 4 4 1 3 3 3 4 4 6 4 6 3 4 3 4 3 2 3 6 5 6 3 7 3 5 3");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game over!\n" +
            "    _ _ O\n" +
            "    O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ O _ _ O _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 8\n", this.output.toString());

  }

  @Test
  public void testGameOverWithBadInputs() {
    this.input = new StringReader("bad -2 2 4 4 4 5 4 3 4 5 0 2 5 4 5 5 5 3 4 6 4 4 4 4 2 4 1 "
            + "4 3 4 2 5 4 5 7 4 5 4 7 5 5 5 4 5 6 5 bad 3 7 3 5 3 4 3 6 not good 5 7 3 7 3 7 "
            + "3 5 3 2 3 4 3 4 3 6 4 2 bad 4 4 1 3 3 3 4 4 6 4 6 random 3 4 3 4 3 2 3 6 5 6 3 "
            + "7 3 5 3 bad 2 5 1 4 5 7 2 4");

    //can't make more moves after game is over

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game over!\n" +
            "    _ _ O\n" +
            "    O _ _\n" +
            "O _ _ _ _ O _\n" +
            "O _ _ _ _ _ _\n" +
            "O _ O _ _ O _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 8\n", this.output.toString());

  }

  @Test
  public void testGameOverForTriangleBoard() {
    this.model = new TriangleSolitaireModel();
    this.view = new TriangleSolitaireTextView(this.model, this.output);
    this.input = new StringReader("bad -2 3 1 1 1 random 4 3 2 1 1 -5 1 3 1 3 -10 3 1 1 5 5 " +
            "invalid 3 3 4 1 4 3 5 3 5 5 3 bad 3 5 3 5 not good 2 5 4 5 5 5 3 bad 2 1 5 4 5 3 2 2");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    _\n" +
                          "   O O\n" +
                          "  O O O\n" +
                          " O O O O\n" +
                          "O O O O O\n" +
                          "Score: 14\n" +
                          "Game over!\n" +
                          "    O\n" +
                          "   _ _\n" +
                          "  O _ _\n" +
                          " _ _ _ _\n" +
                          "O _ O _ _\n" +
                          "Score: 4\n", this.output.toString());
  }

  @Test
  public void testGameOverForEuropeanBoard() {

    this.model =  new EuropeanSolitaireModel(3, 0, 2);
    this.view = new MarbleSolitaireTextView(this.model, this.output);
    this.input = new StringReader("bad 3 no 3 1 3 5 3 good 3 3 2 5 2 3 2 ra 2 2 4 4 5 2 5 1 5 " +
            "3 5 4 1 4 3 -5 5 1 5 3 4 7 4 5 6 6 4 6 4 5 input 4 7 2 6 4 6 4 7 4 5 1 3 1 5 3 " +
            "4 1 4 1 -5 5 1 3 5 4 -10 3 4 7 4 5 4 5 4 5 6 5 7 5 5 6 2 6 4 bad 6 5 6 3 3 4 3 6 3 " +
            "7 3 5 4 5 design 2 5 3 2 3 4 5 3 3 or 3 3 3 3 5 3 5 1 5 7 3 5 3 bad 2 4 5 6 5 1 2 3");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    _ O O\n" +
                          "  O O O O O\n" +
                          "O O O O O O O\n" +
                          "O O O O O O O\n" +
                          "O O O O O O O\n" +
                          "  O O O O O\n" +
                          "    O O O\n" +
                          "Score: 36\n" +
                          "Game over!\n" +
                          "    O _ O\n" +
                          "  _ _ _ _ _\n" +
                          "O _ _ _ _ _ _\n" +
                          "_ _ _ _ _ _ _\n" +
                          "_ _ O _ O _ _\n" +
                          "  _ _ _ _ _\n" +
                          "    _ _ O\n" +
                          "Score: 6\n", this.output.toString());
  }

  @Test
  public void testMultipleMoves() {
    this.input = new StringReader("2 4 4 4 5 4 3 4 5 2 5 4 5 5 5 3 q");

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    this.controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O _ O _ _ O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n", this.output.toString());

  }

  @Test
  public void testIOException() {
    this.output = new FakeTestAppendable();
    this.input = new StringReader("2 4");
    this.view = new MarbleSolitaireTextView(this.model, this.output);

    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);

    try {
      this.controller.playGame();
      fail("IOException was not thrown by method.");
    } catch (IllegalStateException e) {
      assertEquals("Transmission to the view failed.", e.getMessage());
    }

  }

  //the mock
  @Test
  public void testValidResultWithMock() {
    Appendable inputThatTheModelReceived = new StringBuilder();
    MarbleSolitaireModel model = new MockModel(new EnglishSolitaireModel(),
            inputThatTheModelReceived);

    Appendable input = new StringBuilder("2 4 4 4 q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model,
            view, userInput);

    controller.playGame();

    String properOutput = "Move: 1 3 3 3\n";
    assertEquals(properOutput, inputThatTheModelReceived.toString());
  }

  @Test
  public void testValid4MovesWithMock() {
    Appendable inputThatTheModelReceived = new StringBuilder();
    MarbleSolitaireModel model = new MockModel(new EnglishSolitaireModel(),
            inputThatTheModelReceived);

    Appendable input = new StringBuilder("2 4 4 4 5 4 3 4 5 2 5 4 5 5 5 3 q");
    Readable userInput = new StringReader(input.toString());
    Appendable controllerOutput = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, controllerOutput);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model,
            view, userInput);

    controller.playGame();

    String properOutput = "Move: 1 3 3 3\n" +
                          "Move: 4 3 2 3\n" +
                          "Move: 4 1 4 3\n" +
                          "Move: 4 4 4 2\n";
    assertEquals(properOutput, inputThatTheModelReceived.toString());
  }

}
