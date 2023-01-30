import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Represents the test class for the EnglishSolitaireModel class.
 */
public class EnglishSolitaireModelTest {

  private MarbleSolitaireModel game1;
  private MarbleSolitaireModel game2;
  private MarbleSolitaireModel game3;
  private MarbleSolitaireModel game4;
  private MarbleSolitaireModel game5;
  private MarbleSolitaireModel game6;

  @Before
  public void initializer() {
    this.game1 = new EnglishSolitaireModel();
    this.game2 = new EnglishSolitaireModel(5);
    this.game3 = new EnglishSolitaireModel(7, 9, 9);
    this.game4 = new EnglishSolitaireModel(5, 5, 5);
    this.game5 = new EnglishSolitaireModel(1);
    this.game6 = new EnglishSolitaireModel(3, 2);
  }


  @Test
  public void testConstructorException1() {

    try {
      new EnglishSolitaireModel(-5, 1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-5,1).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(7, 1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (7,1).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(3, 7);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (3,7).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(5, -1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,-1).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(7, 7);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (7,7).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(-5, -2);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-5,-2).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(1, 1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,1).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(0, 0);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,0).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(0, 1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,1).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(0, 6);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,6).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(5, 0);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,0).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(5, 5);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (5,5).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(6, 6);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6,6).", e.getMessage());
    }


  }

  @Test
  public void testConstructorException2() {
    try {
      new EnglishSolitaireModel(2);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(0);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(-5);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(100);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

  }

  @Test
  public void testConstructorException3() {
    try {
      new EnglishSolitaireModel(2, 3, 3);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(0, 3, 3);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(-10, 3, 3);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(42, 3, 3);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(42, 1, 1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid argument. Must be a positive, odd number.", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(3, 1, 1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (1,1).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(5, 2, 2);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (2,2).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(5, -2, 2);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-2,2).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(5, 2, 13);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (2,13).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(5, 13, 2);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (13,2).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(5, 13, 13);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (13,13).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(5, 7, -1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (7,-1).", e.getMessage());
    }

    try {
      new EnglishSolitaireModel(5, -1, -1);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-1,-1).", e.getMessage());
    }
  }

  @Test
  public void testGetSlotAt() {

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.game1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game1.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game1.getSlotAt(0, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game1.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game1.getSlotAt(5, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game2.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game2.getSlotAt(8, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.game2.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game5.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game4.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.game4.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game4.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game3.getSlotAt(9, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.game3.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game3.getSlotAt(7, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game6.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game2.getSlotAt(6, 12));

    try {
      this.game5.getSlotAt(1, 1);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.game1.getSlotAt(-1, 5);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.game1.getSlotAt(7, 5);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.game1.getSlotAt(2, -1);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.game1.getSlotAt(5, 7);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.game1.getSlotAt(-1, -1);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

    try {
      this.game1.getSlotAt(7, 7);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input. Row or column are beyond the dimensions of the board.",
              e.getMessage());
    }

  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, this.game1.getBoardSize());
    assertEquals(13, this.game2.getBoardSize());
    assertEquals(19, this.game3.getBoardSize());
    assertEquals(13, this.game4.getBoardSize());
    assertEquals(1, this.game5.getBoardSize());
  }

  @Test
  public void testGetScore() {
    assertEquals(32, this.game1.getScore());
    assertEquals(104, this.game2.getScore());
    assertEquals(216, this.game3.getScore());
    assertEquals(104, this.game4.getScore());
    assertEquals(0, this.game5.getScore());

    this.game1.move(1, 3, 3, 3);
    assertEquals(31, this.game1.getScore());

  }

  @Test
  public void testMove() {
    this.game1.move(1, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game1.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game1.getSlotAt(3, 3));
    assertEquals(31, this.game1.getScore());

    this.game1.move(4, 3, 2, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game1.getSlotAt(2, 3));
    assertEquals(30, this.game1.getScore());

    //test Exceptions for move()
    try {
      this.game1.move(2, 3, 2, 1);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The position you're trying to move to is not valid since it is " +
              "not empty.", e.getMessage());
    }

    try {
      this.game1.move(2, 3, 2, 5);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The position you're trying to move to is not valid since it is " +
              "not empty.", e.getMessage());
    }

    try {
      this.game1.move(2, 3, 4, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The 'from' and 'to' positions are valid, but there is " +
              "not a marble in between them.", e.getMessage());
    }

    try {
      this.game1.move(4, 5, 1, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("the 'from' and 'to' positions are not exactly two " +
              "positions horizontally or vertically away from each other.", e.getMessage());
    }

    try {
      this.game1.move(2, 3, 0, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The position you're trying to move to is not valid since it is " +
              "not empty.", e.getMessage());
    }

    try {
      this.game1.move(4, 3, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("The position you're moving from is not valid since there " +
              "is no marble there.", e.getMessage());
    }

    try {
      this.game1.move(-4, 3, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.game1.move(7, 3, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.game1.move(4, -1, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.game1.move(4, 7, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.game1.move(-4, -1, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.game1.move(7, 7, 2, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.game1.move(-1, 7, 4, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.game1.move(1, -1, 4, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'from' position", e.getMessage());
    }

    try {
      this.game1.move(4, 3, -1, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.game1.move(4, 3, 7, 3);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.game1.move(4, 3, 4, -1);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.game1.move(4, 3, 4, 7);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.game1.move(4, 3, -1, -1);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.game1.move(4, 3, 7, 7);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.game1.move(4, 3, -1, 7);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    try {
      this.game1.move(4, 3, 1, -1);
      fail("Method did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Not a valid 'to' position", e.getMessage());
    }

    //Back to regular testing
    this.game1.move(4, 1, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game1.getSlotAt(4, 3));
    assertEquals(29, this.game1.getScore());

    this.game1.move(4, 4, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game1.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.game1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.game1.getSlotAt(4, 2));
    assertEquals(28, this.game1.getScore());


  }

  @Test
  public void testIsGameOver() {
    assertFalse(this.game1.isGameOver());

    this.game1.move(1, 3, 3, 3);
    assertFalse(this.game1.isGameOver());

    this.game1.move(4, 3, 2, 3);
    assertFalse(this.game1.isGameOver());

    this.game1.move(4, 1, 4, 3);
    this.game1.move(4, 4, 4, 2);
    this.game1.move(3, 5, 3, 3);
    this.game1.move(3, 3, 1, 3);
    this.game1.move(0, 3, 2, 3);
    this.game1.move(1, 4, 3, 4);
    this.game1.move(6, 3, 4, 3);
    this.game1.move(6, 4, 4, 4);
    this.game1.move(3, 4, 5, 4);
    this.game1.move(2, 6, 2, 4);
    this.game1.move(2, 3, 2, 5);
    assertEquals(19, this.game1.getScore());
    this.game1.move(4, 6, 2, 6);
    this.game1.move(2, 6, 2, 4);
    this.game1.move(2, 1, 2, 3);
    this.game1.move(2, 3, 2, 5);
    this.game1.move(3, 1, 3, 3);
    this.game1.move(0, 2, 2, 2);
    this.game1.move(3, 3, 5, 3);
    this.game1.move(5, 2, 3, 2);
    this.game1.move(3, 2, 1, 2);
    this.game1.move(5, 4, 5, 2);
    this.game1.move(6, 2, 4, 2);

    //game is now over since there are no more valid moves
    assertTrue(this.game1.isGameOver());
    assertEquals(8, this.game1.getScore());

    assertTrue(this.game5.isGameOver());


  }

  @Test
  public void testDiagonalMove() {
    this.game1.move(1, 3, 3, 3);
    this.game1.move(2, 1, 2, 3);

    try {
      this.game1.move(4, 4, 2, 2);
      fail("Method did not throw IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("the 'from' and 'to' positions are not exactly two " +
              "positions horizontally or vertically away from each other.", e.getMessage());
    }

  }


}