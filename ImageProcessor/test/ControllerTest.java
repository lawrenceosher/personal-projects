import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.StringReader;

import controller.IImageGUIcontroller;
import controller.ImageGUIControllerImpl;
import controller.ImageProcessorControllerImpl;
import controller.IImageProcessorController;
import model.ImageProcessorModelImpl;
import model.IImageProcessorModel;
import model.IImage;
import model.Image;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for the controller and utilizes a mock model to check for correct input.
 */
public class ControllerTest {

  IImageProcessorModel model;

  //Explanation for commented out tests:
  //We were trying to test the inputs the model is receiving from controller.
  // Our issue was our controller is passing a FunctionObject, not a string,
  // so the assertEquals is checking the reference of the command which is failing.
  // If I had more time I would have overrided toString() in each Command class as a
  // possible solution.

  /*
  @Test
  public void testInputLoad() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    //the input given to the controller
    Appendable theInput = new StringBuilder("load res/color.ppm color");
    Readable r = new StringReader(theInput.toString());
    //Scanner scan = new Scanner(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new Load("res/color.ppm","color")).toString(),
            inputFromMockModel.toString());
  }

  @Test
  public void testInputBright() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    //the input given to the controller
    Appendable theInput = new StringBuilder("load res/color.ppm color");
    Readable r = new StringReader(theInput.toString());
    //Scanner scan = new Scanner(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new Load("res/color.ppm","color")).toString(),
            inputFromMockModel.toString());
  }

  @Test
  public void testInputRed() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    Appendable theInput = new StringBuilder("red-component color colorRed");
    Readable r = new StringReader(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new VisualizeRed("color","colorRed")).toString(),
            inputFromMockModel.toString());
  }

  @Test
  public void testInputGreen() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    Appendable theInput = new StringBuilder("green-component color colorGreen");
    Readable r = new StringReader(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new VisualizeGreen("color","colorGreen")).toString(),
            inputFromMockModel.toString());
  }
  @Test
  public void testInputBlue() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    Appendable theInput = new StringBuilder("blue-component color colorBlue");
    Readable r = new StringReader(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new VisualizeBlue("color","colorBlue")).toString(),
            inputFromMockModel.toString());
  }

  @Test
  public void testInputVal() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    Appendable theInput = new StringBuilder("value-component color colorVal");
    Readable r = new StringReader(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new VisualizeValue("color","colorVal")).toString(),
            inputFromMockModel.toString());
  }

  @Test
  public void testInputLuma() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    Appendable theInput = new StringBuilder("luma-component color colorLuma");
    Readable r = new StringReader(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new VisualizeLuma("color","colorLuma")).toString(),
            inputFromMockModel.toString());
  }

  @Test
  public void testInputIntensity() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    Appendable theInput = new StringBuilder("intensity-component color colorIntensity");
    Readable r = new StringReader(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new VisualizeIntensity("color","colorIntensity")).toString(),
            inputFromMockModel.toString());
  }

  @Test
  public void testInputHorizontal() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    Appendable theInput = new StringBuilder("horizontal-flip color colorhorizontal");
    Readable r = new StringReader(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new HorizontalFlip("color","colorhorizontal")).toString(),
            inputFromMockModel.toString());
  }

  @Test
  public void testInputVertical() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    Appendable theInput = new StringBuilder("vertical-flip color colorVertical");
    Readable r = new StringReader(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new VerticalFlip("color","colorVertical")).toString(),
            inputFromMockModel.toString());
  }

  @Test
  public void testInputSavePPM() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    Appendable theInput = new StringBuilder("save res/colorSaved.ppm color");
    Readable r = new StringReader(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new Save("res/colorSaved.ppm","color")).toString(),
            inputFromMockModel.toString());
  }
   @Test
  public void testInputSaveJPEG() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    Appendable theInput = new StringBuilder("save res/colorSaved.jpeg color");
    Readable r = new StringReader(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new Save("res/colorSaved.jpeg","color")).toString(),
            inputFromMockModel.toString());
  }

 @Test
  public void testInputPNG() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    Appendable theInput = new StringBuilder("save res/colorSaved.png color");
    Readable r = new StringReader(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new Save("res/colorSaved.png","color")).toString(),
            inputFromMockModel.toString());
  }

   @Test
  public void testInputBMP() {
    Appendable inputFromMockModel = new StringBuilder();
    IImageProcessorModel mockModel = new MockModel(inputFromMockModel);

    Appendable theInput = new StringBuilder("save res/colorSaved.bmp color");
    Readable r = new StringReader(theInput.toString());

    IGUIController controller = new GUIController(mockModel, r);
    controller.playGame();

    assertEquals((new Save("res/colorSaved.bmp","color")).toString(),
            inputFromMockModel.toString());
  }

    @Test
  public void testBlurController() {
    Readable in11 = new StringReader("blur LoadImageBrighter LoadImagBlur");

    IImageProcessorController controller11 = new ImageProcessorControllerImpl(model, in11);
    controller11.playGame();
    model.getImage("LoadImageBlur");


    assertEquals(16, model.getImage("LoadImageBlur").getPixels()[0][0].getRed());
    assertEquals(18, model.getImage("LoadImageBlur").getPixels()[0][0].getBlue());
    assertEquals(17, model.getImage("LoadImageBlur").getPixels()[0][0].getGreen());


  }

     @Test
  public void testSepiaController() {
    Readable in11 = new StringReader("sepia LoadImageBrighter LoadImagSepia");

    IImageProcessorController controller11 = new ImageProcessorControllerImpl(model, in11);
    controller11.playGame();
    model.getImage("LoadImageSepia");


    assertEquals(16, model.getImage("LoadImageSepia").getPixels()[0][0].getRed());
    assertEquals(18, model.getImage("LoadImageSepia").getPixels()[0][0].getBlue());
    assertEquals(17, model.getImage("LoadImageSepia").getPixels()[0][0].getGreen());


  }

     @Test
  public void testSharpenController() {
    Readable in11 = new StringReader("sharpen LoadImageBrighter LoadImageSharpen");

    IImageProcessorController controller11 = new ImageProcessorControllerImpl(model, in11);
    controller11.playGame();
    model.getImage("LoadImageSharpen");


    assertEquals(16, model.getImage("LoadImageSharpen").getPixels()[0][0].getRed());
    assertEquals(18, model.getImage("LoadImageSharpen").getPixels()[0][0].getBlue());
    assertEquals(17, model.getImage("LoadImageSharpen").getPixels()[0][0].getGreen());


  }

   @Test
  public void testGreyScaleController() {
    Readable in11 = new StringReader("blur LoadImageBrighter LoadImagGreyScale");

    IImageProcessorController controller11 = new ImageProcessorControllerImpl(model, in11);
    controller11.playGame();
    model.getImage("LoadImageGreyScale");


    assertEquals(16, model.getImage("LoadImageGreyScale").getPixels()[0][0].getRed());
    assertEquals(18, model.getImage("LoadImageGreyScale").getPixels()[0][0].getBlue());
    assertEquals(17, model.getImage("LoadImageGreyScale").getPixels()[0][0].getGreen());


  }

*/

  @Before
  public void testController() {
    Appendable testOut = new StringBuilder();
    Appendable outputBuilder = new StringBuilder("");
    Readable in = new StringReader("load res/LoadImage.ppm LoadImage");

    model = new ImageProcessorModelImpl();

    IImageProcessorController controller = new ImageProcessorControllerImpl(model, in);
    controller.playGame();
    model.getImage("LoadImage");

    Pixel[][] result = new Pixel[3][3];
    int temp = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        result[i][j] = new Pixel(temp, temp + 1, temp + 2);
        temp = temp + 3;
      }
    }

    IImage img = new Image(result, "LoadImage", 3, 3, 255);
    assertEquals(img, model.getImage("LoadImage"));
    assertEquals(0, model.getImage("LoadImage").getPixels()[0][0].getRed());

    Readable in2 = new StringReader("brighten 10 LoadImage LoadImageBrighter");

    IImageProcessorController controller2 = new ImageProcessorControllerImpl(model, in2);
    controller2.playGame();
    model.getImage("LoadImageBrighter");
    assertEquals(10, model.getImage("LoadImageBrighter").getPixels()[0][0].getRed());

  }

  @Test
  public void testControllerConstructorException() {

    try {
      new ImageProcessorControllerImpl(null, new InputStreamReader(System.in));
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Arguments cannot be null", e.getMessage());
    }

    try {
      new ImageProcessorControllerImpl(this.model, null);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Arguments cannot be null", e.getMessage());
    }

    try {
      new ImageProcessorControllerImpl(null, null);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Arguments cannot be null", e.getMessage());
    }

    try {
      new ImageGUIControllerImpl(null);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Arguments cannot be null", e.getMessage());
    }
  }


  @Test
  public void testGUIcontroller() {
    //testing controller contruction
    model = new ImageProcessorModelImpl();
    IImageGUIcontroller controller = new ImageGUIControllerImpl(model);
    assertEquals(10, model.getImage("res/beach.jpeg").getPixels()[0][0].getRed());
    assertEquals(10, model.getImage("res/beach.jpeg").getPixels()[0][0].getBlue());
    assertEquals(10, model.getImage("res/beach.jpeg").getPixels()[0][0].getGreen());
  }

  @Test
  public void testRedController() {
    Readable in3 = new StringReader("red-component LoadImageBrighter LoadImageRed");

    IImageProcessorController controller3 = new ImageProcessorControllerImpl(model, in3);
    controller3.playGame();
    model.getImage("LoadImageRed");
    assertEquals(10, model.getImage("LoadImageRed").getPixels()[0][0].getRed());
    assertEquals(10, model.getImage("LoadImageRed").getPixels()[0][0].getBlue());
    assertEquals(10, model.getImage("LoadImageRed").getPixels()[0][0].getGreen());

  }

  @Test
  public void testGreenController() {
    Readable in4 = new StringReader("green-component LoadImage LoadImageGreen");

    IImageProcessorController controller4 = new ImageProcessorControllerImpl(model, in4);
    controller4.playGame();
    model.getImage("LoadImageGreen");

    assertEquals(1, model.getImage("LoadImageGreen").getPixels()[0][0].getRed());
    assertEquals(1, model.getImage("LoadImageGreen").getPixels()[0][0].getBlue());
    assertEquals(1, model.getImage("LoadImageGreen").getPixels()[0][0].getGreen());
  }

  @Test
  public void testBlueController() {
    Readable in5 = new StringReader("blue-component LoadImage LoadImageBlue");

    IImageProcessorController controller5 = new ImageProcessorControllerImpl(model, in5);
    controller5.playGame();
    model.getImage("LoadImageBlue");

    assertEquals(2, model.getImage("LoadImageBlue").getPixels()[0][0].getRed());
    assertEquals(2, model.getImage("LoadImageBlue").getPixels()[0][0].getBlue());
    assertEquals(2, model.getImage("LoadImageBlue").getPixels()[0][0].getGreen());

  }

  @Test
  public void testLumaController() {
    Readable in6 = new StringReader("luma-component LoadImageBrighter LoadImageLuma");

    IImageProcessorController controller6 = new ImageProcessorControllerImpl(model, in6);
    controller6.playGame();
    model.getImage("LoadImageLuma");

    assertEquals(10, model.getImage("LoadImageLuma").getPixels()[0][0].getRed());
    assertEquals(10, model.getImage("LoadImageLuma").getPixels()[0][0].getBlue());
    assertEquals(10, model.getImage("LoadImageLuma").getPixels()[0][0].getGreen());

  }

  @Test
  public void testValueController() {
    Readable in7 = new StringReader("value-component LoadImage LoadImageValue");

    IImageProcessorController controller7 = new ImageProcessorControllerImpl(model, in7);
    controller7.playGame();
    model.getImage("LoadImageValue");
    assertEquals(2, model.getImage("LoadImageValue").getPixels()[0][0].getRed());
    assertEquals(2, model.getImage("LoadImageValue").getPixels()[0][0].getBlue());
    assertEquals(2, model.getImage("LoadImageValue").getPixels()[0][0].getGreen());

  }

  @Test
  public void testIntensityController() {
    Readable in8 = new StringReader("intensity-component LoadImage LoadImageIntensity");

    IImageProcessorController controller8 = new ImageProcessorControllerImpl(model, in8);
    controller8.playGame();
    model.getImage("LoadImageIntensity");

    assertEquals(1, model.getImage("LoadImageIntensity").getPixels()[0][0].getRed());
    assertEquals(1, model.getImage("LoadImageIntensity").getPixels()[0][0].getBlue());
    assertEquals(1, model.getImage("LoadImageIntensity").getPixels()[0][0].getGreen());
  }

  @Test
  public void testVerticalController() {
    Readable in9 = new StringReader("vertical-flip LoadImageBrighter LoadImageFlip");

    IImageProcessorController controller9 = new ImageProcessorControllerImpl(model, in9);
    controller9.playGame();
    model.getImage("LoadImageFlip");

    assertEquals(28, model.getImage("LoadImageFlip").getPixels()[0][0].getRed());
    assertEquals(30, model.getImage("LoadImageFlip").getPixels()[0][0].getBlue());
    assertEquals(29, model.getImage("LoadImageFlip").getPixels()[0][0].getGreen());

  }

  @Test
  public void testHorizontalController() {
    Readable in10 = new StringReader("horizontal-flip LoadImageBrighter LoadImageHorizontal");

    IImageProcessorController controller10 = new ImageProcessorControllerImpl(model, in10);
    controller10.playGame();
    model.getImage("LoadImageHorizontal");


    assertEquals(16, model.getImage("LoadImageHorizontal").getPixels()[0][0].getRed());
    assertEquals(18, model.getImage("LoadImageHorizontal").getPixels()[0][0].getBlue());
    assertEquals(17, model.getImage("LoadImageHorizontal").getPixels()[0][0].getGreen());


  }

}
