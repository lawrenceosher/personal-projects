import org.junit.Before;
import org.junit.Test;

import model.commands.Blur;
import model.commands.Brighten;
import model.commands.Greyscale;
import model.commands.HorizontalFlip;
import model.commands.ICommands;
import model.commands.Load;
import model.commands.Save;
import model.commands.Sepia;
import model.commands.Sharpen;
import model.commands.VerticalFlip;
import model.commands.VisualizeBlue;
import model.commands.VisualizeGreen;
import model.commands.VisualizeIntensity;
import model.commands.VisualizeLuma;
import model.commands.VisualizeRed;
import model.commands.VisualizeValue;
import model.ImageProcessorModelImpl;
import model.IImageProcessorModel;
import model.IImage;
import model.Image;
import model.IPixel;
import model.Pixel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The test class for all the commands of the image processor application.
 */
public class CommandsTest {

  IImageProcessorModel model;

  ICommands c;

  IPixel[][] resultCopy;

  IImage img;


  @Before
  public void initializer() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/LoadImage.ppm", "LoadImage.ppm");
    this.model.execute(this.c);

    this.resultCopy = new IPixel[3][3];
    int temp = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        this.resultCopy[i][j] = new Pixel(temp, temp + 1, temp + 2);
        temp = temp + 3;
      }
    }
    img = new Image(resultCopy, "LoadImage", 3, 3, 255);
  }

  @Test
  public void testLoad() {

    assertEquals(3, this.model.getImage("LoadImage.ppm").getWidth());
    assertEquals(3, this.model.getImage("LoadImage.ppm").getHeight());
    assertEquals(255, this.model.getImage("LoadImage.ppm").getMaxValue());
    assertEquals(3, this.model.getImage("LoadImage.ppm").getPixels().length);
    assertEquals(3, this.model.getImage("LoadImage.ppm").getPixels()[0].length);

    IPixel[][] result = new IPixel[3][3];
    int temp = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        result[i][j] = new Pixel(temp, temp + 1, temp + 2);
        temp = temp + 3;
      }
    }

    IImage img = new Image(result, "LoadImage.ppm", 3, 3, 255);
    assertEquals(img, this.model.getImage("LoadImage.ppm"));

  }

  @Test
  public void testLoadJPEG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.jpeg", "h");
    this.model.execute(this.c);

    assertEquals(3, this.model.getImage("h").getWidth());
    assertEquals(3, this.model.getImage("h").getHeight());
    assertEquals(255, this.model.getImage("h").getMaxValue());
    assertEquals(3, this.model.getImage("h").getPixels().length);
    assertEquals(3, this.model.getImage("h").getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];

    result[0][0] = new Pixel(106,107,47);
    result[0][1] = new Pixel(221,222,162);
    result[0][2] = new Pixel(135,147,255);
    result[1][0] = new Pixel(151,152,92);
    result[1][1] = new Pixel(197,198,138);
    result[1][2] = new Pixel(94,106,240);
    result[2][0] = new Pixel(79,96,0);
    result[2][1] = new Pixel(151,168,38);
    result[2][2] = new Pixel(1,0,251);

    assertArrayEquals(this.model.getImage("h").getPixels(), result);

  }

  @Test
  public void testLoadPNG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.png", "h");
    this.model.execute(this.c);

    assertEquals(3, this.model.getImage("h").getWidth());
    assertEquals(3, this.model.getImage("h").getHeight());
    assertEquals(238, this.model.getImage("h").getMaxValue());
    assertEquals(3, this.model.getImage("h").getPixels().length);
    assertEquals(3, this.model.getImage("h").getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];

    result[0][0] = new Pixel(106,107,47);
    result[0][1] = new Pixel(213,218,206);
    result[0][2] = new Pixel(142,152,238);
    result[1][0] = new Pixel(150,156,77);
    result[1][1] = new Pixel(188,195,177);
    result[1][2] = new Pixel(100,109,212);
    result[2][0] = new Pixel(80,93,0);
    result[2][1] = new Pixel(145,156,126);
    result[2][2] = new Pixel(11,16,154);

    assertArrayEquals(result,this.model.getImage("h").getPixels());

  }

  @Test
  public void testBrighten() {
    ICommands c2;
    c2 = new Brighten(10, "LoadImage.ppm", "LoadImageBrighter");
    this.model.execute(c2);

    IImage img = this.model.getImage("LoadImageBrighter");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3, img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    Pixel[][] result = new Pixel[3][3];
    int temp = 10;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        result[i][j] = new Pixel(temp, temp + 1, temp + 2);
        temp = temp + 3;
      }
    }

    assertEquals(result, img.getPixels());

  }

  @Test
  public void testBrightenJPEG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.jpeg", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new Brighten(10, "h", "hbright");
    this.model.execute(c2);

    IImage img = this.model.getImage("hbright");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3, img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];

    result[0][0] = new Pixel(116,117,57);
    result[0][1] = new Pixel(231,232,172);
    result[0][2] = new Pixel(145,157,255);
    result[1][0] = new Pixel(161,162,102);
    result[1][1] = new Pixel(207,208,148);
    result[1][2] = new Pixel(104,116,250);
    result[2][0] = new Pixel(89,106,10);
    result[2][1] = new Pixel(161,178,48);
    result[2][2] = new Pixel(11,10,255);

    assertArrayEquals(result, img.getPixels());

  }

  @Test
  public void testBrightenOnMaxValue() {
    IImageProcessorModel model2 = new ImageProcessorModelImpl();
    ICommands c2 = new Load("res/color.ppm", "color");
    model2.execute(c2);
    IImage imgOld = model2.getImage("color");

    assertEquals(255, imgOld.getPixels()[0][0].getRed());
    assertEquals(0, imgOld.getPixels()[0][0].getGreen());
    assertEquals(0, imgOld.getPixels()[0][0].getBlue());

    ICommands c3 = new Brighten(10, "color", "colorBrighten");
    model2.execute(c3);

    IImage imgBrightened = model2.getImage("colorBrighten");

    //checks that you cant brighten over the max value
    assertEquals(255, imgBrightened.getPixels()[0][0].getRed());
    assertEquals(10, imgBrightened.getPixels()[0][0].getBlue());
    assertEquals(10, imgBrightened.getPixels()[0][0].getGreen());
  }

  @Test
  public void testDarkenOnMinValues() {
    IImageProcessorModel model2 = new ImageProcessorModelImpl();
    ICommands c2 = new Load("res/color.ppm", "color");
    model2.execute(c2);
    IImage imgOld = model2.getImage("color");

    assertEquals(255, imgOld.getPixels()[0][0].getRed());
    assertEquals(0, imgOld.getPixels()[0][0].getGreen());
    assertEquals(0, imgOld.getPixels()[0][0].getBlue());

    ICommands c3 = new Brighten(-10, "color", "colorDarken");
    model2.execute(c3);

    IImage imgBrightened = model2.getImage("colorDarken");

    //checks that you cant darken under min value
    assertEquals(245, imgBrightened.getPixels()[0][0].getRed());
    assertEquals(0, imgBrightened.getPixels()[0][0].getBlue());
    assertEquals(0, imgBrightened.getPixels()[0][0].getGreen());
  }


  @Test
  public void testHorizontalFlip() {
    ICommands c2;
    c2 = new HorizontalFlip("LoadImage.ppm", "LoadImageHorizontal");
    this.model.execute(c2);

    assertEquals(3, this.model.getImage("LoadImageHorizontal").getWidth());
    assertEquals(3, this.model.getImage("LoadImageHorizontal").getHeight());
    assertEquals(255, this.model.getImage("LoadImageHorizontal").getMaxValue());
    assertEquals(3, this.model.getImage("LoadImageHorizontal").getPixels().length);
    assertEquals(3, this.model.getImage("LoadImageHorizontal").getPixels()[0].length);

    Pixel[][] result = new Pixel[3][3];
    int temp = 6;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        result[i][j] = new Pixel(temp, temp + 1, temp + 2);
        temp = temp - 3;
      }
      temp = temp + 18;
    }

    IImage img = new Image(result, "LoadImageHorizontal", 3, 3, 255);
    assertEquals(img, this.model.getImage("LoadImageHorizontal"));
  }


  @Test
  public void testHorizontalFlipPNG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.png", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new HorizontalFlip("h", "hFlipped");
    this.model.execute(c2);

    IImage img = this.model.getImage("hFlipped");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(238, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];

    result[0][0] = new Pixel(142,152,238);
    result[0][1] = new Pixel(213,218,206);
    result[0][2] = new Pixel(106,107,47);

    result[1][0] = new Pixel(100,109,212);
    result[1][1] = new Pixel(188,195,177);
    result[1][2] = new Pixel(150,156,77);


    result[2][0] = new Pixel(11,16,154);
    result[2][1] = new Pixel(145,156,126);
    result[2][2] = new Pixel(80,93,0);
    assertArrayEquals(result,this.model.getImage("hFlipped").getPixels());
  }


  @Test
  public void testVerticalFlip() {
    ICommands c2;
    c2 = new VerticalFlip("LoadImage.ppm", "LoadImageVertical");
    this.model.execute(c2);

    assertEquals(3, this.model.getImage("LoadImageVertical").getWidth());
    assertEquals(3, this.model.getImage("LoadImageVertical").getHeight());
    assertEquals(255, this.model.getImage("LoadImageVertical").getMaxValue());
    assertEquals(3, this.model.getImage("LoadImageVertical").getPixels().length);
    assertEquals(3, this.model.getImage("LoadImageVertical").getPixels()[0].length);

    Pixel[][] result = new Pixel[3][3];
    int temp = 0;
    for (int i = 2; i >= 0; i--) {
      for (int j = 0; j < 3; j++) {
        result[i][j] = new Pixel(temp, temp + 1, temp + 2);
        temp = temp + 3;
      }
    }
    assertArrayEquals(result, this.model.getImage("LoadImageVertical").getPixels());
  }

  @Test
  public void testVerticalFlipJPEG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.jpeg", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new VerticalFlip("h", "hbright");
    this.model.execute(c2);

    IImage img = this.model.getImage("hbright");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3, img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];

    result[0][0] = new Pixel(79,96,0);
    result[0][1] = new Pixel(151,168,38);
    result[0][2] = new Pixel(1,0,251);

    result[1][0] = new Pixel(151,152,92);
    result[1][1] = new Pixel(197,198,138);
    result[1][2] = new Pixel(94,106,240);
    result[2][0] = new Pixel(106,107,47);
    result[2][1] = new Pixel(221,222,162);
    result[2][2] = new Pixel(135,147,255);

    assertArrayEquals(result, img.getPixels());

  }

  @Test
  public void testVisualizeBlue() {
    ICommands c2;
    c2 = new VisualizeBlue("LoadImage.ppm", "LoadImageBlue");
    this.model.execute(c2);

    assertEquals(3, this.model.getImage("LoadImageBlue").getWidth());
    assertEquals(3, this.model.getImage("LoadImageBlue").getHeight());
    assertEquals(255, this.model.getImage("LoadImageBlue").getMaxValue());
    assertEquals(3, this.model.getImage("LoadImageBlue").getPixels().length);
    assertEquals(3, this.model.getImage("LoadImageBlue").getPixels()[0].length);

    Pixel[][] result = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int blue = resultCopy[i][j].getBlue();
        result[i][j] = new Pixel(blue, blue, blue);
      }
    }
    assertEquals(result, this.model.getImage("LoadImageBlue").getPixels());
  }

  @Test
  public void testVisualizeBlueBMP() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.bmp", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new VisualizeBlue("h", "hBlue");
    this.model.execute(c2);

    IImage img = this.model.getImage("hBlue");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(238, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];

    result[0][0] = new Pixel(47,47,47);
    result[0][1] = new Pixel(206,206,206);
    result[0][2] = new Pixel(238,238,238);
    result[1][0] = new Pixel(77,77,77);
    result[1][1] = new Pixel(177,177,177);
    result[1][2] = new Pixel(212,212,212);
    result[2][0] = new Pixel(0,0,0);
    result[2][1] = new Pixel(126,126,126);
    result[2][2] = new Pixel(154,154,154);

    assertEquals(result,img.getPixels());

  }

  @Test
  public void testVisualizeGreenPPM() {
    ICommands c2;
    c2 = new VisualizeGreen("LoadImage.ppm", "LoadImageGreen");
    this.model.execute(c2);

    assertEquals(3, this.model.getImage("LoadImageGreen").getWidth());
    assertEquals(3, this.model.getImage("LoadImageGreen").getHeight());
    assertEquals(255, this.model.getImage("LoadImageGreen").getMaxValue());
    assertEquals(3, this.model.getImage("LoadImageGreen").getPixels().length);
    assertEquals(3, this.model.getImage("LoadImageGreen").getPixels()[0].length);

    Pixel[][] result = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 2; j >= 0; j--) {
        int green = resultCopy[i][j].getGreen();
        result[i][j] = new Pixel(green, green, green);
      }
    }
    assertEquals(result, this.model.getImage("LoadImageGreen").getPixels());
  }

  @Test
  public void testVisualizeGreenJPEG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.jpeg", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new VisualizeGreen("h", "hGreen");
    this.model.execute(c2);


    IImage img = this.model.getImage("hGreen");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];

    result[0][0] = new Pixel(107,107,107);
    result[0][1] = new Pixel(222,222,222);
    result[0][2] = new Pixel(147,147,147);
    result[1][0] = new Pixel(152,152,152);
    result[1][1] = new Pixel(198,198,198);
    result[1][2] = new Pixel(106,106,106);
    result[2][0] = new Pixel(96,96,96);
    result[2][1] = new Pixel(168,168,168);
    result[2][2] = new Pixel(0,0,0);

    assertArrayEquals(result, this.model.getImage("hGreen").getPixels());

  }

  @Test
  public void testVisualizeRed() {
    ICommands c2;
    c2 = new VisualizeRed("LoadImage.ppm", "LoadImageRed");
    this.model.execute(c2);

    assertEquals(3, this.model.getImage("LoadImageRed").getWidth());
    assertEquals(3, this.model.getImage("LoadImageRed").getHeight());
    assertEquals(255, this.model.getImage("LoadImageRed").getMaxValue());
    assertEquals(3, this.model.getImage("LoadImageRed").getPixels().length);
    assertEquals(3, this.model.getImage("LoadImageRed").getPixels()[0].length);

    Pixel[][] result = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 2; j >= 0; j--) {
        int red = resultCopy[i][j].getRed();
        result[i][j] = new Pixel(red, red, red);
      }
    }
    assertEquals(result, this.model.getImage("LoadImageRed").getPixels());
  }

  @Test
  public void testVisualizeRedJPEG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.jpeg", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new VisualizeRed("h", "hRed");
    this.model.execute(c2);


    IImage img = this.model.getImage("hRed");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];

    result[0][0] = new Pixel(106,106,106);
    result[0][1] = new Pixel(221,221,221);
    result[0][2] = new Pixel(135,135,135);
    result[1][0] = new Pixel(151,151,151);
    result[1][1] = new Pixel(197,197,197);
    result[1][2] = new Pixel(94,94,94);
    result[2][0] = new Pixel(79,79,79);
    result[2][1] = new Pixel(151,151,151);
    result[2][2] = new Pixel(1,1,1);

    assertArrayEquals(result, this.model.getImage("hRed").getPixels());

  }

  @Test
  public void testVisualizeIntensity() {
    ICommands c2;
    c2 = new VisualizeIntensity("LoadImage.ppm", "LoadImageIntensity");
    this.model.execute(c2);

    assertEquals(3, this.model.getImage("LoadImageIntensity").getWidth());
    assertEquals(3, this.model.getImage("LoadImageIntensity").getHeight());
    assertEquals(255, this.model.getImage("LoadImageIntensity").getMaxValue());
    assertEquals(3, this.model.getImage("LoadImageIntensity").getPixels().length);
    assertEquals(3, this.model.getImage("LoadImageIntensity").getPixels()[0].length);

    Pixel[][] result = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 2; j >= 0; j--) {
        int intensity = resultCopy[i][j].getIntensity();
        result[i][j] = new Pixel(intensity, intensity, intensity);
      }
    }
    assertEquals(result, this.model.getImage("LoadImageIntensity").getPixels());
  }

  @Test
  public void testVisualizeIntensityJPEG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.jpeg", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new VisualizeIntensity("h", "hInt");
    this.model.execute(c2);


    IImage img = this.model.getImage("hInt");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];

    result[0][0] = new Pixel(86,86,86);
    result[0][1] = new Pixel(201,201,201);
    result[0][2] = new Pixel(179,179,179);
    result[1][0] = new Pixel(131,131,131);
    result[1][1] = new Pixel(177,177,177);
    result[1][2] = new Pixel(146,146,146);
    result[2][0] = new Pixel(58,58,58);
    result[2][1] = new Pixel(119,119,119);
    result[2][2] = new Pixel(84,84,84);

    assertArrayEquals(result, this.model.getImage("hInt").getPixels());

  }

  @Test
  public void testVisualizeLuma() {
    ICommands c2;
    c2 = new VisualizeLuma("LoadImage.ppm", "LoadImageLuma");
    this.model.execute(c2);

    assertEquals(3, this.model.getImage("LoadImageLuma").getWidth());
    assertEquals(3, this.model.getImage("LoadImageLuma").getHeight());
    assertEquals(255, this.model.getImage("LoadImageLuma").getMaxValue());
    assertEquals(3, this.model.getImage("LoadImageLuma").getPixels().length);
    assertEquals(3, this.model.getImage("LoadImageLuma").getPixels()[0].length);

    Pixel[][] result = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 2; j >= 0; j--) {
        int luma = resultCopy[i][j].getLuma();
        result[i][j] = new Pixel(luma, luma, luma);
      }
    }
    assertEquals(result, this.model.getImage("LoadImageLuma").getPixels());
  }

  @Test
  public void testVisualizeLumaJPEG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.jpeg", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new VisualizeLuma("h", "hInt");
    this.model.execute(c2);


    IImage img = this.model.getImage("hInt");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];

    result[0][0] = new Pixel(102,102,102);
    result[0][1] = new Pixel(217,217,217);
    result[0][2] = new Pixel(152,152,152);
    result[1][0] = new Pixel(147,147,147);
    result[1][1] = new Pixel(193,193,193);
    result[1][2] = new Pixel(113,113,113);
    result[2][0] = new Pixel(85,85,85);
    result[2][1] = new Pixel(154,154,154);
    result[2][2] = new Pixel(18,18,18);

    assertArrayEquals(result, this.model.getImage("hInt").getPixels());

  }

  @Test
  public void testSave() {

    ICommands c2;
    c2 = new Save("res/SaveTest.ppm", "LoadImage.ppm");
    this.model.execute(c2);

    ICommands c3 = new Load("res/SaveTest.ppm", "SaveTest");
    this.model.execute(c3);

    assertEquals(3, this.model.getImage("SaveTest").getWidth());
    assertEquals(3, this.model.getImage("SaveTest").getHeight());
    assertEquals(255, this.model.getImage("SaveTest").getMaxValue());
    assertEquals(3, this.model.getImage("SaveTest").getPixels().length);
    assertEquals(3, this.model.getImage("SaveTest").getPixels()[0].length);

    assertEquals(this.model.getImage("LoadImage.ppm").getPixels(),
            this.model.getImage("SaveTest").getPixels());
  }

  @Test
  public void testSavePNG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.png", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new Save("res/savedPNG.png", "h");
    this.model.execute(c2);

    ICommands c3 = new Load("res/savedPNG.png", "savedPNG");
    this.model.execute(c3);

    IImage img = this.model.getImage("savedPNG");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(238, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);


    assertArrayEquals(this.model.getImage("h").getPixels(),
            this.model.getImage("savedPNG").getPixels());
  }

  @Test
  public void testLoadPPMSavePNG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.ppm", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new Save("res/savedPNG.png", "h");
    this.model.execute(c2);

    ICommands c3 = new Load("res/savedPNG.png", "savedPNG");
    this.model.execute(c3);

    IImage img = this.model.getImage("savedPNG");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);


    assertArrayEquals(this.model.getImage("h").getPixels(),
            this.model.getImage("savedPNG").getPixels());
  }


  @Test
  public void testLoadPNGSaveBMP() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.png", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new Save("res/savedBMP.bmp", "h");
    this.model.execute(c2);

    ICommands c3 = new Load("res/savedBMP.bmp", "savedBMP");
    this.model.execute(c3);

    IImage img = this.model.getImage("savedBMP");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(238, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);


    assertArrayEquals(this.model.getImage("h").getPixels(),
            this.model.getImage("savedBMP").getPixels());
  }


  @Test
  public void testLoadJPEGSavePPM() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.jpeg", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new Save("res/savedPPM.ppm", "h");
    this.model.execute(c2);

    ICommands c3 = new Load("res/savedPPM.ppm", "savedPPM");
    this.model.execute(c3);

    IImage img = this.model.getImage("savedPPM");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);


    assertArrayEquals(this.model.getImage("h").getPixels(),
            this.model.getImage("savedPPM").getPixels());
  }

  /*
  @Test
  public void testLoadBMPSaveJPEG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.bmp", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new Save("res/savedPPM.jpeg", "h");
    this.model.execute(c2);

    ICommands c3 = new Load("res/savedPPM.jpeg", "savedJPEG");
    this.model.execute(c3);

    IImage img = this.model.getImage("savedJPEG");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(249, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);


    assertArrayEquals(this.model.getImage("h").getPixels(),
            this.model.getImage("savedJPEG").getPixels());
  }*/


  @Test
  public void testVisualizeValue() {
    ICommands c2;
    c2 = new VisualizeValue("LoadImage.ppm", "LoadImageValue");
    this.model.execute(c2);

    assertEquals(3, this.model.getImage("LoadImageValue").getWidth());
    assertEquals(3, this.model.getImage("LoadImageValue").getHeight());
    assertEquals(255, this.model.getImage("LoadImageValue").getMaxValue());
    assertEquals(3, this.model.getImage("LoadImageValue").getPixels().length);
    assertEquals(3, this.model.getImage("LoadImageValue").getPixels()[0].length);

    Pixel[][] result = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 2; j >= 0; j--) {
        int val = resultCopy[i][j].getValue();
        result[i][j] = new Pixel(val, val, val);
      }
    }
    assertEquals(result, this.model.getImage("LoadImageValue").getPixels());
  }

  @Test
  public void testVisualizeValueJPEG() {
    this.model = new ImageProcessorModelImpl();
    this.c = new Load("res/horizontal.jpeg", "h");
    this.model.execute(this.c);
    ICommands c2;
    c2 = new VisualizeValue("h", "hVal");
    this.model.execute(c2);


    IImage img = this.model.getImage("hVal");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];

    result[0][0] = new Pixel(107,107,107);
    result[0][1] = new Pixel(222,222,222);
    result[0][2] = new Pixel(255,255,255);
    result[1][0] = new Pixel(152,152,152);
    result[1][1] = new Pixel(198,198,198);
    result[1][2] = new Pixel(240,240,240);
    result[2][0] = new Pixel(96,96,96);
    result[2][1] = new Pixel(168,168,168);
    result[2][2] = new Pixel(251,251,251);

    assertArrayEquals(result, this.model.getImage("hVal").getPixels());

  }

  // tests whether readPPM throws exception when loading a file not present
  @Test
  public void testLoadImageException() throws IllegalStateException {
    try {
      IImageProcessorModel model2 = new ImageProcessorModelImpl();
      ICommands c2 = new Load("res/Sample.ppm", "color");
      model2.execute(c2);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalStateException e) {
      assertEquals("File not found", e.getMessage());
    }
  }

  @Test
  public void testBrightenImageNotFoundException() throws IllegalArgumentException {
    try {
      IImageProcessorModel model2 = new ImageProcessorModelImpl();
      ICommands c2 = new Brighten(10, "Sample.ppm", "color");
      model2.execute(c2);
      fail("Method did not throw an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("The image you're trying to get isn't stored.", e.getMessage());
    }
  }

  @Test
  public void testGreyscale() {

    this.c = new Load("res/horizontal.ppm", "horiz");
    this.model.execute(this.c);

    ICommands c2;
    c2 = new Greyscale("horiz", "horizGrey");
    this.model.execute(c2);

    assertEquals(3, this.model.getImage("horizGrey").getWidth());
    assertEquals(3, this.model.getImage("horizGrey").getHeight());
    assertEquals(255, this.model.getImage("horizGrey").getMaxValue());
    assertEquals(3, this.model.getImage("horizGrey").getPixels().length);
    assertEquals(3, this.model.getImage("horizGrey").getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];
    IPixel[] row0 = new Pixel[3];
    row0[0] = new Pixel(99, 99, 99);
    row0[1] = new Pixel(223, 223, 223);
    row0[2] = new Pixel(156, 156, 156);
    result[0] = row0;

    IPixel[] row1 = new Pixel[3];
    row1[0] = new Pixel(132, 132, 132);
    row1[1] = new Pixel(210, 210, 210);
    row1[2] = new Pixel(110, 110, 110);
    result[1] = row1;

    IPixel[] row2 = new Pixel[3];
    row2[0] = new Pixel(54, 54, 54);
    row2[1] = new Pixel(182, 182, 182);
    row2[2] = new Pixel(18, 18, 18);
    result[2] = row2;

    assertArrayEquals(result, this.model.getImage("horizGrey").getPixels());

  }

  @Test
  public void testGreyscaleJPEG() {

    this.c = new Load("res/horizontal.jpeg", "horiz");
    this.model.execute(this.c);

    ICommands c2;
    c2 = new Greyscale("horiz", "horizGrey");
    this.model.execute(c2);

    IImage img = this.model.getImage("horizGrey");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];
    IPixel[] row0 = new Pixel[3];
    row0[0] = new Pixel(101, 101, 101);
    row0[1] = new Pixel(215, 215, 215);
    row0[2] = new Pixel(151, 151, 151);
    result[0] = row0;

    IPixel[] row1 = new Pixel[3];
    row1[0] = new Pixel(146, 146, 146);
    row1[1] = new Pixel(191, 191, 191);
    row1[2] = new Pixel(111, 111, 111);
    result[1] = row1;

    IPixel[] row2 = new Pixel[3];
    row2[0] = new Pixel(84, 84, 84);
    row2[1] = new Pixel(154,154, 154);
    row2[2] = new Pixel(18, 18, 18);
    result[2] = row2;

    assertArrayEquals(result, this.model.getImage("horizGrey").getPixels());

  }

  @Test
  public void testSepia() {

    this.c = new Load("res/horizontal.ppm", "horiz");
    this.model.execute(this.c);

    ICommands c2;
    c2 = new Sepia("horiz", "horizSepia");
    this.model.execute(c2);

    assertEquals(3, this.model.getImage("horizSepia").getWidth());
    assertEquals(3, this.model.getImage("horizSepia").getHeight());
    assertEquals(255, this.model.getImage("horizSepia").getMaxValue());
    assertEquals(3, this.model.getImage("horizSepia").getPixels().length);
    assertEquals(3, this.model.getImage("horizSepia").getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];
    IPixel[] row0 = new Pixel[3];
    row0[0] = new Pixel(133, 118, 93);
    row0[1] = new Pixel(255, 251, 195);
    row0[2] = new Pixel(221, 196, 153);
    result[0] = row0;

    IPixel[] row1 = new Pixel[3];
    row1[0] = new Pixel(194, 172, 135);
    row1[1] = new Pixel(253, 224, 176);
    row1[2] = new Pixel(163, 144, 113);
    result[1] = row1;

    IPixel[] row2 = new Pixel[3];
    row2[0] = new Pixel(100, 88, 69);
    row2[1] = new Pixel(196, 174, 136);
    row2[2] = new Pixel(48, 42, 33);
    result[2] = row2;

    assertArrayEquals(result, this.model.getImage("horizSepia").getPixels());

  }

  @Test
  public void testSepiaJPEG() {

    this.c = new Load("res/horizontal.jpeg", "horiz");
    this.model.execute(this.c);

    ICommands c2;
    c2 = new Sepia("horiz", "horizSepia");
    this.model.execute(c2);

    IImage img = this.model.getImage("horizSepia");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];
    IPixel[] row0 = new Pixel[3];
    row0[0] = new Pixel(131, 116, 91);
    row0[1] = new Pixel(255, 255, 199);
    row0[2] = new Pixel(214, 189, 147);
    result[0] = row0;

    IPixel[] row1 = new Pixel[3];
    row1[0] = new Pixel(192, 171, 134);
    row1[1] = new Pixel(255, 226, 176);
    row1[2] = new Pixel(162, 144, 112);
    result[1] = row1;

    IPixel[] row2 = new Pixel[3];
    row2[0] = new Pixel(104, 92, 72);
    row2[1] = new Pixel(195, 173, 134);
    row2[2] = new Pixel(47, 42, 32);
    result[2] = row2;

    assertArrayEquals(result, this.model.getImage("horizSepia").getPixels());

  }

  @Test
  public void testGreyscaleConstructorException() {

    try {
      new Greyscale(null, "horiz");
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have a null argument.", e.getMessage());
    }

    try {
      new Greyscale("horiz", null);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have a null argument.", e.getMessage());
    }

  }

  @Test
  public void testSepiaConstructorException() {

    try {
      new Sepia(null, "horiz");
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have a null argument.", e.getMessage());
    }

    try {
      new Sepia("horiz", null);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have a null argument.", e.getMessage());
    }

  }

  @Test
  public void testBlur() {

    this.c = new Load("res/horizontal.ppm", "horiz");
    this.model.execute(this.c);

    ICommands c2;
    c2 = new Blur("horiz", "horizBlur");
    this.model.execute(c2);

    IImage img = this.model.getImage("horizBlur");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];
    IPixel[] row0 = new Pixel[3];
    row0[0] = new Pixel(81, 85, 62);
    row0[1] = new Pixel(103, 139, 116);
    row0[2] = new Pixel(75, 97, 120);
    result[0] = row0;

    IPixel[] row1 = new Pixel[3];
    row1[0] = new Pixel(130, 101, 59);
    row1[1] = new Pixel(119, 168, 126);
    row1[2] = new Pixel(65, 107, 149);
    result[1] = row1;

    IPixel[] row2 = new Pixel[3];
    row2[0] = new Pixel(101, 60, 18);
    row2[1] = new Pixel(66, 108, 66);
    row2[2] = new Pixel(18, 60, 101);
    result[2] = row2;

    assertArrayEquals(result, this.model.getImage("horizBlur").getPixels());

  }

  @Test
  public void testBlurJPEG() {

    this.c = new Load("res/horizontal.jpeg", "horiz");
    this.model.execute(this.c);

    ICommands c2;
    c2 = new Blur("horiz", "horizBlur");
    this.model.execute(c2);

    IImage img = this.model.getImage("horizBlur");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];
    IPixel[] row0 = new Pixel[3];
    row0[0] = new Pixel(85, 85, 52);
    row0[1] = new Pixel(125, 128, 116);
    row0[2] = new Pixel(85, 90, 122);
    result[0] = row0;

    IPixel[] row1 = new Pixel[3];
    row1[0] = new Pixel(108, 112, 58);
    row1[1] = new Pixel(146, 152, 135);
    row1[2] = new Pixel(88, 94, 153);
    result[1] = row1;

    IPixel[] row2 = new Pixel[3];
    row2[0] = new Pixel(69, 76, 24);
    row2[1] = new Pixel(87, 94, 78);
    row2[2] = new Pixel(43, 46, 106);
    result[2] = row2;

    assertArrayEquals(result, this.model.getImage("horizBlur").getPixels());

  }

  @Test
  public void testSharpen() {

    this.c = new Load("res/horizontal.ppm", "horiz");
    this.model.execute(this.c);

    ICommands c2;
    c2 = new Sharpen("horiz", "horizSharpen");
    this.model.execute(c2);

    assertEquals(3, this.model.getImage("horizSharpen").getWidth());
    assertEquals(3, this.model.getImage("horizSharpen").getHeight());
    assertEquals(255, this.model.getImage("horizSharpen").getMaxValue());
    assertEquals(3, this.model.getImage("horizSharpen").getPixels().length);
    assertEquals(3, this.model.getImage("horizSharpen").getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];
    IPixel[] row0 = new Pixel[3];
    row0[0] = new Pixel(163, 189, 91);
    row0[1] = new Pixel(255, 255, 255);
    row0[2] = new Pixel(161, 245, 255);
    result[0] = row0;

    IPixel[] row1 = new Pixel[3];
    row1[0] = new Pixel(255, 255, 91);
    row1[1] = new Pixel(255, 255, 255);
    row1[2] = new Pixel(123, 255, 255);
    result[1] = row1;

    IPixel[] row2 = new Pixel[3];
    row2[0] = new Pixel(255, 76, 0);
    row2[1] = new Pixel(127, 255, 114);
    row2[2] = new Pixel(0, 76, 255);
    result[2] = row2;

    assertArrayEquals(result, this.model.getImage("horizSharpen").getPixels());

  }

  @Test
  public void testSharpenJPEG() {

    this.c = new Load("res/horizontal.jpeg", "horiz");
    this.model.execute(this.c);

    ICommands c2;
    c2 = new Sharpen("horiz", "horizSharpen");
    this.model.execute(c2);

    IImage img = this.model.getImage("horizSharpen");
    assertEquals(3, img.getWidth());
    assertEquals(3, img.getHeight());
    assertEquals(255, img.getMaxValue());
    assertEquals(3,img.getPixels().length);
    assertEquals(3, img.getPixels()[0].length);

    IPixel[][] result = new Pixel[3][3];
    IPixel[] row0 = new Pixel[3];
    row0[0] = new Pixel(190, 185, 47);
    row0[1] = new Pixel(255, 255, 255);
    row0[2] = new Pixel(202, 213, 255);
    result[0] = row0;

    IPixel[] row1 = new Pixel[3];
    row1[0] = new Pixel(255, 255, 95);
    row1[1] = new Pixel(255, 255, 255);
    row1[2] = new Pixel(228, 245, 255);
    result[1] = row1;

    IPixel[] row2 = new Pixel[3];
    row2[0] = new Pixel(134, 152, 0);
    row2[1] = new Pixel(223, 246, 160);
    row2[2] = new Pixel(25, 27, 255);
    result[2] = row2;

    assertArrayEquals(result, this.model.getImage("horizSharpen").getPixels());

  }

  @Test
  public void testBlurConstructorException() {

    try {
      new Blur(null, "horiz");
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have a null argument.", e.getMessage());
    }

    try {
      new Blur("horiz", null);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have a null argument.", e.getMessage());
    }

  }

  @Test
  public void testSharpenConstructorException() {

    try {
      new Sharpen(null, "horiz");
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have a null argument.", e.getMessage());
    }

    try {
      new Sharpen("horiz", null);
      fail("Constructor did not throw an IllegalArgumentException.");
    } catch (IllegalArgumentException e) {
      assertEquals("Can't have a null argument.", e.getMessage());
    }

  }


}
