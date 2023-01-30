import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Represents the test class for the EuropeanSolitaireModel class.
 */
public class EuropeanSolitaireModelTest {

  private MarbleSolitaireModel board1;

  private MarbleSolitaireModel board2;

  private MarbleSolitaireModel board3;

  private MarbleSolitaireModel board4;

  private MarbleSolitaireModel board5;

  private MarbleSolitaireModel board6;

  @Before
  public void initializer() {
    this.board1 = new EuropeanSolitaireModel();
    this.board2 = new EuropeanSolitaireModel(5);
    this.board3 = new EuropeanSolitaireModel(2, 1);
    this.board4 = new EuropeanSolitaireModel(5, 1, 3);
    this.board5 = new EuropeanSolitaireModel(1);
    this.board6 = new EuropeanSolitaireModel(3, 0, 2);
  }

  //constructor that just takes in empty slot row and column
  @Test
  public void testConstructorException1() {

    try {
      new EuropeanSolitaireModel(-5, 1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-5,1).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(7, 1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (7,1).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(3, 7);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (3,7).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(5, -1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,-1).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(7, 7);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (7,7).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(-5, -2);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-5,-2).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(0, 0);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,0).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(0, 1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,1).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(0, 6);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,6).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(5, 0);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,0).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(6, 6);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6,6).", e.getMessage());
    }


  }

  //constructor that just takes in a size
  @Test
  public void testConstructorException2() {
    try {
      new EuropeanSolitaireModel(2);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(0);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(-5);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(100);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

  }

  //constructor that takes in a size, empty slot row, and empty slot column
  @Test
  public void testConstructorException3() {
    try {
      new EuropeanSolitaireModel(2, 3, 3);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(0, 3, 3);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(-10, 3, 3);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(42, 3, 3);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(42, 1, 1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(5, -2, 2);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-2,2).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(5, 2, 13);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (2,13).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(5, 13, 2);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (13,2).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(5, 13, 13);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (13,13).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(5, 7, -1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (7,-1).", e.getMessage());
    }

    try {
      new EuropeanSolitaireModel(5, -1, -1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,-1).", e.getMessage());
    }
  }

  @Test
  public void testGetSlotAt() {

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board2.getSlotAt(6,6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board1.getSlotAt(3,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board3.getSlotAt(2,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board4.getSlotAt(1,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board5.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.board1.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.board1.getSlotAt(0,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.board1.getSlotAt(1,0));
    //next 4 slots were invalid for English model, but a marble for European
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board1.getSlotAt(1,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board1.getSlotAt(5,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board1.getSlotAt(1,5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board1.getSlotAt(5,5));




    try {
      this.board5.getSlotAt(1, 1);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.board1.getSlotAt(-1, 5);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.board1.getSlotAt(7, 5);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.board1.getSlotAt(2, -1);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.board1.getSlotAt(5, 7);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.board1.getSlotAt(-1, -1);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.board1.getSlotAt(7, 7);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, this.board1.getBoardSize());
    assertEquals(13, this.board2.getBoardSize());
    assertEquals(7, this.board3.getBoardSize());
    assertEquals(13, this.board4.getBoardSize());
    assertEquals(1, this.board5.getBoardSize());
  }

  @Test
  public void testGetScore() {
    assertEquals(36, this.board1.getScore());
    assertEquals(36, this.board3.getScore());
    assertEquals(0, this.board5.getScore());
    assertEquals(128, this.board2.getScore());
    assertEquals(128, this.board4.getScore());

    this.board2.move(8, 6, 6, 6);
    assertEquals(127, this.board2.getScore());

  }

  @Test
  public void testDiagonalMove() {
    this.board1.move(1, 3, 3, 3);
    this.board1.move(2, 1, 2, 3);

    try {
      this.board1.move(4, 4, 2, 2);
      fail("Method did not throw IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("the 'from' and 'to' positions are not exactly two " +
              "positions horizontally or vertically away from each other.", e.getMessage());
    }

  }

  @Test
  public void testMoveLeft() {

    this.board2.move(6, 8, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board2.getSlotAt(6,8));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board2.getSlotAt(6,7));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board2.getSlotAt(6,6));
    assertEquals(127, this.board2.getScore());

  }

  @Test
  public void testMoveRight() {

    this.board2.move(6, 4, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board2.getSlotAt(6,4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board2.getSlotAt(6,5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board2.getSlotAt(6,6));
    assertEquals(127, this.board2.getScore());

  }

  @Test
  public void testMoveUp() {

    this.board2.move(8, 6, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board2.getSlotAt(8,6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board2.getSlotAt(7,6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board2.getSlotAt(6,6));
    assertEquals(127, this.board2.getScore());
  }

  @Test
  public void testMoveDown() {

    this.board2.move(4, 6, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board2.getSlotAt(4,6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board2.getSlotAt(5,6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board2.getSlotAt(6,6));
    assertEquals(127, this.board2.getScore());
  }

  @Test
  public void testMoveExceptionToNotEmpty() {
    try {
      this.board1.move(2, 3, 2, 1);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The position you're trying to move to is not valid since it is " +
              "not empty.", e.getMessage());
    }

    try {
      this.board1.move(2, 3, 2, 5);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The position you're trying to move to is not valid since it is " +
              "not empty.", e.getMessage());
    }
  }

  @Test
  public void testMoveExceptionNoMarbleInBetween() {

    this.board1.move(1, 3, 3, 3);
    this.board1.move(4, 3, 2, 3);

    try {
      this.board1.move(2, 3, 4, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The 'from' and 'to' positions are valid, but there is " +
              "not a marble in between them.", e.getMessage());
    }
  }

  @Test
  public void testMoveFromInvalidNoMarble() {

    this.board1.move(1, 3, 3, 3);
    this.board1.move(4, 3, 2, 3);

    try {
      this.board1.move(4, 3, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The position you're moving from is not valid since there " +
              "is no marble there.", e.getMessage());
    }
  }

  @Test
  public void testMoveNot2PosAway() {

    this.board1.move(1, 3, 3, 3);
    this.board1.move(4, 3, 2, 3);

    try {
      this.board1.move(4, 5, 1, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("the 'from' and 'to' positions are not exactly two " +
              "positions horizontally or vertically away from each other.", e.getMessage());
    }
  }

  @Test
  public void testMoveInvalidFromPos() {

    try {
      this.board1.move(-4, 3, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.board1.move(7, 3, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.board1.move(4, -1, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.board1.move(4, 7, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.board1.move(-4, -1, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.board1.move(7, 7, 8, 8);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.board1.move(-1, 7, 4, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.board1.move(1, -1, 4, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

  }

  @Test
  public void testInvalidToPos() {
    try {
      this.board1.move(4, 3, -1, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.board1.move(4, 3, 7, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.board1.move(4, 3, 4, -1);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.board1.move(4, 3, 4, 7);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.board1.move(4, 3, -1, -1);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.board1.move(4, 3, 7, 7);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.board1.move(4, 3, -1, 7);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.board1.move(4, 3, 1, -1);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }
  }

  @Test
  public void testIsGameOver() {
    assertFalse(this.board6.isGameOver());

    this.board6.move(2, 2, 0,2);
    assertEquals(35, this.board6.getScore());

    this.board6.move(4, 2, 2,2);
    assertEquals(34, this.board6.getScore());

    this.board6.move(1, 4, 1,2);
    this.board6.move(1, 1, 1,3);
    this.board6.move(3, 4, 1,4);
    this.board6.move(0, 4, 2,4);
    this.board6.move(3, 0, 3,2);
    this.board6.move(4, 0, 4,2);
    this.board6.move(3, 6, 3,4);
    this.board6.move(5, 5, 3,5);
    this.board6.move(3, 4, 3,6);
    this.board6.move(1, 5, 3,5);
    this.board6.move(3, 6, 3,4);
    this.board6.move(0, 2, 0,4);
    this.board6.move(2, 3, 0,3);
    this.board6.move(0, 4, 0,2);
    this.board6.move(4, 3, 2,3);
    this.board6.move(6, 3, 4,3);
    this.board6.move(4, 3, 4,5);
    this.board6.move(4, 6, 4,4);
    this.board6.move(5, 1, 5,3);
    this.board6.move(5, 4, 5,2);
    this.board6.move(2, 3, 2,5);
    this.board6.move(2, 6, 2,4);
    this.board6.move(3, 4, 1,4);
    this.board6.move(2, 1, 2,3);
    this.board6.move(4, 2, 2,2);
    this.board6.move(2, 2, 2,4);
    this.board6.move(2, 4, 0,4);
    this.board6.move(6, 2, 4,2);

    //game is now over since there are no more valid moves
    assertTrue(this.board6.isGameOver());
    assertEquals(6, this.board6.getScore());

    assertTrue(this.board5.isGameOver());






  }








}