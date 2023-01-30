import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * Test class for a TriangleSolitaireModel.
 */
public class TriangleSolitaireModelTest {

  private MarbleSolitaireModel triBoard1;

  private MarbleSolitaireModel triBoard2;

  private MarbleSolitaireModel triBoard3;

  private MarbleSolitaireModel triBoard4;

  private MarbleSolitaireModel triBoard5;

  private MarbleSolitaireModel triBoard6;

  @Before
  public void initializer() {
    this.triBoard1 = new TriangleSolitaireModel();
    this.triBoard2 = new TriangleSolitaireModel(10);
    this.triBoard3 = new TriangleSolitaireModel(2, 1);
    this.triBoard4 = new TriangleSolitaireModel(7, 2, 2);
    this.triBoard5 = new TriangleSolitaireModel(10, 6, 3);
    this.triBoard6 = new TriangleSolitaireModel(1);

  }

  @Test
  public void testConstructorOnlyTakesInSizeException() {

    try {
      new TriangleSolitaireModel(0);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The size is invalid because it is not positive.", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(-1);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The size is invalid because it is not positive.", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(-10);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The size is invalid because it is not positive.", e.getMessage());
    }
  }

  @Test
  public void testConstructorOnlyTakesInEmptyException() {

    try {
      new TriangleSolitaireModel(4, 5);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (4,5).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(1, 4);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,4).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(0, 5);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,5).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(-4, 5);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-4,5).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(4, 7);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (4,7).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(2, -1);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (2,-1).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(7, 2);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (7,2).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(-1, -1);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,-1).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(5, 5);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,5).", e.getMessage());
    }

  }

  @Test
  public void testConstructorSizeAndEmptyException() {

    try {
      new TriangleSolitaireModel(0, 2, 2);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The size is invalid because it is not positive.", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(-1, 2, 2);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The size is invalid because it is not positive.", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(5, 4, 5);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (4,5).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(7, 1, 4);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,4).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(7, -1, 5);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,5).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(7, 7, 5);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (7,5).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(7, 7, 5);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (7,5).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(7, 2, -1);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (2,-1).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(7, 2, 7);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (2,7).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(7, -1, -1);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,-1).", e.getMessage());
    }

    try {
      new TriangleSolitaireModel(7, 7, 7);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (7,7).", e.getMessage());
    }


  }

  @Test
  public void testGetBoardSize() {
    assertEquals(5, this.triBoard1.getBoardSize());
    assertEquals(10, this.triBoard2.getBoardSize());
    assertEquals(5, this.triBoard3.getBoardSize());
    assertEquals(7, this.triBoard4.getBoardSize());
    assertEquals(10, this.triBoard5.getBoardSize());
  }

  @Test
  public void testGetScore() {
    assertEquals(14, this.triBoard1.getScore());
    assertEquals(54, this.triBoard2.getScore());
    assertEquals(27, this.triBoard4.getScore());
    assertEquals(0, new TriangleSolitaireModel(1).getScore());
  }

  @Test
  public void testMoveLeft() {

    this.triBoard5.move(6, 5, 6, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.triBoard5.getSlotAt(6,
            3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(6,
            5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(6,
            4));
    assertEquals(53, this.triBoard5.getScore());

  }

  @Test
  public void testMoveRight() {

    this.triBoard5.move(6, 1, 6, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.triBoard5.getSlotAt(6,
            3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(6,
            2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(6,
            1));
    assertEquals(53, this.triBoard5.getScore());

  }

  @Test
  public void testMoveUpLeftDiagonal() {

    this.triBoard5.move(8, 5, 6, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.triBoard5.getSlotAt(6,
            3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(7,
            4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(8,
            5));
    assertEquals(53, this.triBoard5.getScore());

  }

  @Test
  public void testMoveUpRightDiagonal() {

    this.triBoard5.move(8, 3, 6, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.triBoard5.getSlotAt(6,
            3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(7,
            3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(8,
            3));
    assertEquals(53, this.triBoard5.getScore());
  }

  @Test
  public void testMoveDownLeftDiagonal() {

    this.triBoard5.move(4, 3, 6, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.triBoard5.getSlotAt(6,
            3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(5,
            3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(4,
            3));
    assertEquals(53, this.triBoard5.getScore());

  }

  @Test
  public void testMoveDownRightDiagonal() {

    this.triBoard5.move(4, 1, 6, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.triBoard5.getSlotAt(6,
            3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(5,
            2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(4,
            1));
    assertEquals(53, this.triBoard5.getScore());
  }

  @Test
  public void testMoveDownStraightException() {

    try {
      this.triBoard5.move(2, 1, 6, 3);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("the 'from' and 'to' positions are not exactly two " +
              "positions away from each other.", e.getMessage());
    }

  }

  @Test
  public void testMoveUpStraightException() {

    MarbleSolitaireModel testBoard6 = new TriangleSolitaireModel(10, 2, 2);

    try {
      testBoard6.move(6, 3, 2, 2);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("the 'from' and 'to' positions are not exactly two " +
              "positions away from each other.", e.getMessage());
    }


  }

  @Test
  public void testMoveExceptionNoMarbleInFrom() {

    try {
      this.triBoard5.move(6, 3, 8, 5);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("The position you're moving from is not valid since " +
              "there is no marble there.", e.getMessage());
    }
  }

  @Test
  public void testMoveExceptionToPosNotEmpty() {

    try {
      this.triBoard5.move(8, 5, 6, 5);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("The position you're trying to move to is not valid " +
              "since it is not empty.", e.getMessage());
    }
  }

  @Test
  public void testExceptionNot2PosAway() {
    try {
      this.triBoard5.move(9, 6, 6, 3);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("the 'from' and 'to' positions are not exactly two " +
              "positions away from each other.", e.getMessage());
    }
  }

  @Test
  public void testExceptionNoMarbleInBetween() {
    this.triBoard5.move(8, 5, 6, 3);

    try {
      this.triBoard5.move(6, 3, 8, 5);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("The 'from' and 'to' positions are valid, but there is " +
              "not a marble in between them.", e.getMessage());
    }
  }

  @Test
  public void testExceptionInvalidFromPos() {
    try {
      this.triBoard5.move(-4, 3, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.triBoard5.move(10, 3, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.triBoard5.move(4, -1, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.triBoard5.move(4, 10, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.triBoard5.move(-4, -1, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.triBoard5.move(10, 10, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.triBoard5.move(-1, 10, 4, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.triBoard5.move(1, -1, 4, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }
  }

  @Test
  public void testExceptionInvalidToPos() {
    try {
      this.triBoard5.move(4, 3, -1, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.triBoard5.move(4, 3, 10, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.triBoard5.move(4, 3, 4, -1);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.triBoard5.move(4, 3, 4, 10);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.triBoard5.move(4, 3, -1, -1);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.triBoard5.move(4, 3, 10, 10);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.triBoard5.move(4, 3, -1, 10);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.triBoard5.move(4, 3, 1, -1);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard1.getSlotAt(0,
            0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.triBoard1.getSlotAt(2,
            2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.triBoard1.getSlotAt(2,
            4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard2.getSlotAt(0,
            0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard3.getSlotAt(2,
            1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard4.getSlotAt(2,
            2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard5.getSlotAt(6,
            3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.triBoard6.getSlotAt(0,
            0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.triBoard5.getSlotAt(9,
            9));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.triBoard5.getSlotAt(2,
            7));

    try {
      this.triBoard6.getSlotAt(1, 1);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.triBoard1.getSlotAt(-1, 5);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.triBoard1.getSlotAt(7, 5);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.triBoard1.getSlotAt(2, -1);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.triBoard1.getSlotAt(5, 7);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.triBoard1.getSlotAt(-1, -1);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.triBoard1.getSlotAt(7, 7);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

  }

  @Test
  public void testIsGameOver() {

    assertFalse(this.triBoard1.isGameOver());

    this.triBoard1.move(2, 0, 0, 0);
    this.triBoard1.move(3, 2, 1, 0);
    assertFalse(this.triBoard1.isGameOver());

    this.triBoard1.move(0, 0, 2, 0);
    this.triBoard1.move(2, 2, 0, 0);
    this.triBoard1.move(4, 4, 2, 2);
    this.triBoard1.move(3, 0, 3, 2);
    this.triBoard1.move(4, 2, 4, 4);
    this.triBoard1.move(2, 2, 4, 2);
    assertEquals(6, this.triBoard1.getScore());
    assertFalse(this.triBoard1.isGameOver());

    this.triBoard1.move(4, 1, 4, 3);
    this.triBoard1.move(4, 4, 4, 2);

    assertTrue(this.triBoard1.isGameOver());
    assertEquals(4, this.triBoard1.getScore());

    assertTrue(this.triBoard6.isGameOver());
  }


}