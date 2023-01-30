import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents the test class of the MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest {

  private MarbleSolitaireView board1;

  private MarbleSolitaireView board2;

  private MarbleSolitaireView board3;

  private MarbleSolitaireModel game1;

  private MarbleSolitaireView board4;

  private MarbleSolitaireView board1V2;

  private MarbleSolitaireView board4V2;

  private Appendable app1;

  private MarbleSolitaireView board5;

  private MarbleSolitaireView board6;

  private MarbleSolitaireView board7;

  private MarbleSolitaireView board8;


  @Before
  public void initializer() {
    this.board1 = new MarbleSolitaireTextView(new EnglishSolitaireModel());
    this.board2 = new MarbleSolitaireTextView(new EnglishSolitaireModel(5));
    this.board3 = new MarbleSolitaireTextView(new EnglishSolitaireModel(5, 5, 4));
    this.game1 = new EnglishSolitaireModel();
    this.game1.move(1, 3, 3, 3);
    this.game1.move(4, 3, 2, 3);
    this.board4 = new MarbleSolitaireTextView(this.game1);

    this.app1 = new StringBuilder();
    this.board1V2 = new MarbleSolitaireTextView(new EnglishSolitaireModel(), this.app1);
    this.board4V2 = new MarbleSolitaireTextView(this.game1, this.app1);
    this.board5 = new MarbleSolitaireTextView(new EuropeanSolitaireModel());
    this.board6 = new MarbleSolitaireTextView(new EuropeanSolitaireModel(5));
    this.board7 = new MarbleSolitaireTextView(new TriangleSolitaireModel(10));
    this.board8 = new MarbleSolitaireTextView(new TriangleSolitaireModel());


  }

  @Test
  public void testConstructorException() {

    try {
      new MarbleSolitaireTextView(null);
      fail("Constructor did not throw IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model is null.", e.getMessage());
    }

  }

  @Test
  public void testToString() {

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", this.board1.toString());

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", this.board2.toString());

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O _ O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", this.board3.toString());

    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O", this.board4.toString());

    this.game1.move(6, 3, 4, 3);
    this.board4 = new MarbleSolitaireTextView(this.game1);

    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O", this.board4.toString());

  }

  @Test
  public void testConstructor2Exception() {

    try {
      new MarbleSolitaireTextView(new EnglishSolitaireModel(), null);
      fail("Constructor did not throw IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided appendable is null.", e.getMessage());
    }

    try {
      new MarbleSolitaireTextView(null, null);
      fail("Constructor did not throw IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model is null.", e.getMessage());
    }

    try {
      new MarbleSolitaireTextView(null, new StringBuilder());
      fail("Constructor did not throw IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model is null.", e.getMessage());
    }

  }

  @Test
  public void testRenderBoardBeforeMoves() {

    try {
      this.board1V2.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed.");
    }

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", this.app1.toString());

  }

  @Test
  public void testRenderBoardAfterMoves() {

    try {
      this.board4V2.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed.");
    }

    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O", this.app1.toString());

  }

  @Test
  public void testIOException() {

    Appendable fakeAppendable = new FakeTestAppendable();
    MarbleSolitaireView fakeBoard = new MarbleSolitaireTextView(new EnglishSolitaireModel(),
            fakeAppendable);

    try {
      fakeBoard.renderBoard();
      fail("Method did not throw IOException.");
    } catch (IOException e) {
      assertEquals("Transmission of the board to the data destination failed.",
              e.getMessage());
    }

    try {
      fakeBoard.renderMessage("hello");
      fail("Method did not throw IOException.");
    } catch (IOException e) {
      assertEquals("Transmission of the message to the data destination failed.",
              e.getMessage());
    }

  }

  @Test
  public void testRenderMessageOneMessage() {

    try {
      this.board4V2.renderMessage("Hello World.");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed.");
    }

    assertEquals("Hello World.", this.app1.toString());


  }

  @Test
  public void testRenderMessageMultipleMessages() {

    try {
      this.board1V2.renderMessage("Example Message to be transmitted.");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed.");
    }

    assertEquals("Example Message to be transmitted.", this.app1.toString());

    try {
      this.board1V2.renderMessage(" Additional message.");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed.");
    }

    assertEquals("Example Message to be transmitted. Additional message.",
            this.app1.toString());


  }

  @Test
  public void testRenderMessageEmptyMessage() {

    try {
      this.board1V2.renderMessage("");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed.");
    }

    assertEquals("", this.app1.toString());

  }

  @Test
  public void testToStringEuropean() {
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.board5.toString());

    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", this.board6.toString());
  }

  @Test
  public void testToStringEuropeanAfter1Move() {
    MarbleSolitaireView board9;

    MarbleSolitaireModel game2;

    game2 = new EuropeanSolitaireModel(5);
    game2.move(8, 6, 6, 6);
    game2.move(10, 6, 8, 6);
    board9 = new MarbleSolitaireTextView(game2);

    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O _ O O O O O\n" +
            "    O O O O _ O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", board9.toString());
  }

  @Test
  public void testToStringWithTriangleDefault() {
    assertEquals("_\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O O O\n" +
            "O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O O\n" +
            "O O O O O O O O O\n" +
            "O O O O O O O O O O", this.board7.toString());

    assertEquals("_\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O O O", this.board8.toString());
  }


}



