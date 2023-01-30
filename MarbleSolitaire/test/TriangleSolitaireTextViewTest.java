import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for testing the view of a TriangleSolitaireBoard.
 */
public class TriangleSolitaireTextViewTest {

  private MarbleSolitaireView triBoard1;

  private MarbleSolitaireView triBoard2;

  private MarbleSolitaireView triBoard3;

  private MarbleSolitaireView triBoard4;

  private MarbleSolitaireView triBoard5;

  private Appendable app1;

  private MarbleSolitaireView triBoard5V2;


  @Before
  public void initializer() {
    this.triBoard1 = new TriangleSolitaireTextView(new TriangleSolitaireModel(7, 2,
            2));
    this.triBoard2 = new TriangleSolitaireTextView(new TriangleSolitaireModel(10));

    this.triBoard3 = new TriangleSolitaireTextView(new TriangleSolitaireModel(3));

    this.triBoard4 = new TriangleSolitaireTextView(new TriangleSolitaireModel(2));

    this.triBoard5 = new TriangleSolitaireTextView(new TriangleSolitaireModel());

    this.app1 = new StringBuilder();
    this.triBoard5V2 = new TriangleSolitaireTextView(new TriangleSolitaireModel(), this.app1);
  }

  @Test
  public void testConstructorException() {

    try {
      new TriangleSolitaireTextView(null);
      fail("Constructor did not throw IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model is null.", e.getMessage());
    }

  }

  @Test
  public void testConstructor2Exception() {

    try {
      new TriangleSolitaireTextView(new EnglishSolitaireModel(), null);
      fail("Constructor did not throw IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided appendable is null.", e.getMessage());
    }

    try {
      new TriangleSolitaireTextView(null, null);
      fail("Constructor did not throw IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model is null.", e.getMessage());
    }

    try {
      new TriangleSolitaireTextView(null, new StringBuilder());
      fail("Constructor did not throw IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Provided model is null.", e.getMessage());
    }

  }

  @Test
  public void testToStringBeforeMoves() {
    assertEquals("      O\n" +
            "     O O\n" +
            "    O O _\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", this.triBoard1.toString());

    assertEquals("         _\n" +
            "        O O\n" +
            "       O O O\n" +
            "      O O O O\n" +
            "     O O O O O\n" +
            "    O O O O O O\n" +
            "   O O O O O O O\n" +
            "  O O O O O O O O\n" +
            " O O O O O O O O O\n" +
            "O O O O O O O O O O", this.triBoard2.toString());

    assertEquals("  _\n" +
            " O O\n" +
            "O O O", this.triBoard3.toString());

    assertEquals(" _\n" +
            "O O", this.triBoard4.toString());

    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", this.triBoard5.toString());


  }

  @Test
  public void testToStringAfterMoves() {
    TriangleSolitaireModel testGame1 = new TriangleSolitaireModel(10, 6, 3);
    TriangleSolitaireTextView view1 = new TriangleSolitaireTextView(testGame1);

    assertEquals("         O\n" +
            "        O O\n" +
            "       O O O\n" +
            "      O O O O\n" +
            "     O O O O O\n" +
            "    O O O O O O\n" +
            "   O O O _ O O O\n" +
            "  O O O O O O O O\n" +
            " O O O O O O O O O\n" +
            "O O O O O O O O O O", view1.toString());

    testGame1.move(8, 5, 6, 3);
    testGame1.move(8, 3, 8, 5);

    view1 = new TriangleSolitaireTextView(testGame1);

    assertEquals("         O\n" +
            "        O O\n" +
            "       O O O\n" +
            "      O O O O\n" +
            "     O O O O O\n" +
            "    O O O O O O\n" +
            "   O O O O O O O\n" +
            "  O O O O _ O O O\n" +
            " O O O _ _ O O O O\n" +
            "O O O O O O O O O O", view1.toString());

  }

  @Test
  public void testRenderBoardBeforeMoves() {

    try {
      this.triBoard5V2.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed.");
    }

    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", this.app1.toString());

  }

  @Test
  public void testRenderBoardAfterMoves() {

    TriangleSolitaireModel testGame1 = new TriangleSolitaireModel();
    testGame1.move(2, 0, 0, 0);
    this.triBoard5V2 = new TriangleSolitaireTextView(testGame1, this.app1);

    try {
      this.triBoard5V2.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed.");
    }

    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O", this.app1.toString());

  }

  @Test
  public void testIOException() {

    Appendable fakeAppendable = new FakeTestAppendable();
    MarbleSolitaireView fakeBoard = new TriangleSolitaireTextView(new TriangleSolitaireModel(),
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
      this.triBoard5V2.renderMessage("Hello World.");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed.");
    }

    assertEquals("Hello World.", this.app1.toString());


  }

  @Test
  public void testRenderMessageMultipleMessages() {

    try {
      this.triBoard5V2.renderMessage("Example Message to be transmitted.");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed.");
    }

    assertEquals("Example Message to be transmitted.", this.app1.toString());

    try {
      this.triBoard5V2.renderMessage(" Additional message.");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed.");
    }

    assertEquals("Example Message to be transmitted. Additional message.",
            this.app1.toString());


  }

  @Test
  public void testRenderMessageEmptyMessage() {

    try {
      this.triBoard5V2.renderMessage("");
    } catch (IOException e) {
      throw new IllegalStateException("Transmission to view failed.");
    }

    assertEquals("", this.app1.toString());

  }

}