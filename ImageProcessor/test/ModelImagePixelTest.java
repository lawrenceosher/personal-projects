import org.junit.Before;
import org.junit.Test;

import model.commands.ICommands;
import model.commands.Load;
import model.ImageProcessorModelImpl;
import model.IImageProcessorModel;
import model.IImage;
import model.IPixel;
import model.Image;
import model.Pixel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * The test class that ensures that the model, image, and pixel classes and their methods are all
 * functioning properly.
 */
public class ModelImagePixelTest {


  IImageProcessorModel model;

  ICommands c;

  IImage testIM;

  IPixel testPixel;


  @Before
  public void initializer() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/LoadImage.ppm", "LoadImage.ppm");
    this.model.execute(this.c);

    Pixel[][] result = new Pixel[3][3];
    int temp = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        result[i][j] = new Pixel(temp, temp + 1, temp + 2);
        temp = temp + 3;
      }
    }

    this.testIM = new Image(result, "LoadImage.ppm", 3, 3, 255);
    this.testPixel = new Pixel(25, 50, 75);
  }


  @Test
  public void testImageConstructorException() {

    Pixel[][] result = new Pixel[3][3];
    int temp = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        result[i][j] = new Pixel(temp, temp + 1, temp + 2);
        temp = temp + 3;
      }
    }

    try {
      new Image(null, "LoadImage.ppm", 3, 3, 255);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have provided arguments be null.", e.getMessage());
    }

    try {
      new Image(result, null, 3, 3, 255);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have provided arguments be null.", e.getMessage());
    }

    try {
      new Image(result, "LoadImage.ppm", null, 3, 255);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have provided arguments be null.", e.getMessage());
    }

    try {
      new Image(result, "LoadImage.ppm", 3, null, 255);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have provided arguments be null.", e.getMessage());
    }

    try {
      new Image(result, "LoadImage.ppm", 3, 3, null);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have provided arguments be null.", e.getMessage());
    }

    try {
      new Image(null, null, null, null, null);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have provided arguments be null.", e.getMessage());
    }


  }

  @Test
  public void testGetImageException() {

    try {
      model.getImage("SampleImage.ppm");
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("The image you're trying to get isn't stored.", e.getMessage());
    }


  }

  @Test
  public void testGetImage() {
    assertEquals(this.testIM, model.getImage("LoadImage.ppm"));
  }

  @Test
  public void testGetHeight() {
    assertEquals(3, this.testIM.getHeight());
  }

  @Test
  public void testGetWidth() {
    assertEquals(3, this.testIM.getWidth());
  }

  @Test
  public void testGetPixels() {

    Pixel[][] result = new Pixel[3][3];
    int temp = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        result[i][j] = new Pixel(temp, temp + 1, temp + 2);
        temp = temp + 3;
      }
    }

    assertArrayEquals(result, this.testIM.getPixels());
  }

  @Test
  public void testGetMaxValue() {
    assertEquals(255, this.testIM.getMaxValue());
  }

  @Test
  public void testSwapException() {

    try {
      this.testIM.swap(-1, 2, 1, 2);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Pixels you're trying to swap aren't in bounds of " +
              "the image dimensions.", e.getMessage());
    }

    try {
      this.testIM.swap(0, -2, 1, 2);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Pixels you're trying to swap aren't in bounds of " +
              "the image dimensions.", e.getMessage());
    }

    try {
      this.testIM.swap(0, 2, -1, 2);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Pixels you're trying to swap aren't in bounds of " +
              "the image dimensions.", e.getMessage());
    }

    try {
      this.testIM.swap(0, 2, 1, -2);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Pixels you're trying to swap aren't in bounds of " +
              "the image dimensions.", e.getMessage());
    }

    try {
      this.testIM.swap(0, 3, 1, 2);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Pixels you're trying to swap aren't in bounds of " +
              "the image dimensions.", e.getMessage());
    }

    try {
      this.testIM.swap(3, 2, 1, 2);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Pixels you're trying to swap aren't in bounds of " +
              "the image dimensions.", e.getMessage());
    }

    try {
      this.testIM.swap(0, 2, 3, 2);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Pixels you're trying to swap aren't in bounds of " +
              "the image dimensions.", e.getMessage());
    }

    try {
      this.testIM.swap(0, 2, 1, 3);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Pixels you're trying to swap aren't in bounds of " +
              "the image dimensions.", e.getMessage());
    }

    try {
      this.testIM.swap(-1, 5, 3, 2);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Pixels you're trying to swap aren't in bounds of " +
              "the image dimensions.", e.getMessage());
    }


  }

  @Test
  public void testSwap() {
    IPixel tempPixel = this.testIM.getPixels()[0][0];
    this.testIM.swap(0, 0, 2, 2);


    assertEquals(tempPixel, this.testIM.getPixels()[2][2]);
  }

  @Test
  public void testEqualsForImage() {
    assertTrue(this.testIM.equals(this.testIM));
    assertTrue(this.model.getImage("LoadImage.ppm").equals(this.testIM));
    assertTrue(this.testIM.equals(this.model.getImage("LoadImage.ppm")));
  }

  @Test
  public void testHashCodeForImage() {
    assertEquals(this.model.getImage("LoadImage.ppm").hashCode(), this.testIM.hashCode());
    assertEquals(this.testIM.hashCode(), this.model.getImage("LoadImage.ppm").hashCode());
  }

  @Test
  public void testPixelConstructorException() {

    try {
      new Pixel(null, 5, 5);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Argument can't be null", e.getMessage());
    }

    try {
      new Pixel(5, null, 5);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Argument can't be null", e.getMessage());
    }

    try {
      new Pixel(5, 5, null);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Argument can't be null", e.getMessage());
    }

    try {
      new Pixel(null, null, null);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Argument can't be null", e.getMessage());
    }

    try {
      new Pixel(-5, 5, 5);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Argument can't be negative.", e.getMessage());
    }

    try {
      new Pixel(5, -5, 5);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Argument can't be negative.", e.getMessage());
    }

    try {
      new Pixel(5, 5, -5);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Argument can't be negative.", e.getMessage());
    }

    try {
      new Pixel(-5, -5, -5);
      fail("Constructor did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Argument can't be negative.", e.getMessage());
    }
  }


  @Test
  public void testGetRed() {
    assertEquals(25, this.testPixel.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(50, this.testPixel.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(75, this.testPixel.getBlue());
  }

  @Test
  public void testGetValue() {
    assertEquals(75, this.testPixel.getValue());
  }

  @Test
  public void testGetIntensity() {
    assertEquals(50, this.testPixel.getIntensity());
  }

  @Test
  public void testGetLuma() {
    assertEquals(46, this.testPixel.getLuma());
  }

  @Test
  public void testEqualsForPixel() {

    IPixel testPixel2 = new Pixel(25, 50, 75);
    IPixel testPixel3 = new Pixel(20, 50, 75);
    assertTrue(this.testPixel.equals(testPixel2));
    assertTrue(testPixel2.equals(testPixel2));
    assertTrue(testPixel2.equals(this.testPixel));
    assertFalse(this.testPixel.equals(testPixel3));
  }

  @Test
  public void testHashCode() {

    IPixel testPixel2 = new Pixel(25, 50, 75);
    assertEquals(this.testPixel.hashCode(), testPixel2.hashCode());
    assertEquals(testPixel2.hashCode(), this.testPixel.hashCode());
  }


}

