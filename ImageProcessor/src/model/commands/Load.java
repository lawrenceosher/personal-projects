package model.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.IImageProcessorModel;
import model.IImage;
import model.Image;
import model.Pixel;

/**
 * Command that loads in an image from a file path and allows the program to use that image.
 * It's the starting point for the image processor application.
 */
public class Load extends ACommand implements ICommands {

  private String filePath;

  BufferedImage img;


  /**
   * Loads an image into the application and allows it to be named and used throughout
   * the application.
   *
   * @param imagePath The path where the image is being loaded in from.
   * @param destFile  The name of the image that will be used throughout the program.
   * @throws IllegalArgumentException Thrown if the imagePath or destFile are null.
   */
  public Load(String imagePath, String destFile) throws IllegalArgumentException {
    super(imagePath, destFile);
    this.filePath = imagePath;
  }

  @Override
  public IImage runCommand(IImageProcessorModel model) {
    String extension = "";
    int index = filePath.lastIndexOf('.');
    if (index > 0) {
      extension = filePath.substring(index + 1);
    } else {
      throw new IllegalStateException("no extension");
    }
    IImage image;
    if (extension.equals("ppm")) {
      image = this.readPPM(filePath);
    } else {
      image = this.read(filePath);
    }

    return image;


  }


  //reads PPM files
  private IImage readPPM(String fileName) {
    Scanner sc;

    try {

      InputStream n = new FileInputStream(fileName);

      sc = new Scanner(n);
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File not found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());
    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    Pixel[][] data = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        data[i][j] = new Pixel(r, g, b);
      }
    }
    IImage image = new Image(data, this.destFile, width, height, maxValue);

    return image;
  }

  //reads JPEG, png, and bmp files
  private IImage read(String fileName) {
    try {

      InputStream n = new FileInputStream(fileName);
      try {
        this.img = ImageIO.read(n);
      } catch (IOException o) {
        throw new IllegalStateException("NO");
      }
    } catch (IOException o) {
      throw new IllegalStateException("Unable to read");
    }

    int width = img.getWidth();
    int height = img.getHeight();
    Pixel[][] data = new Pixel[height][width];

    int max = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color c = new Color(img.getRGB(j, i));
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        data[i][j] = new Pixel(r, g, b);
        int current = Math.max(Math.max(r, g), b);
        max = Math.max(current, max);
      }
    }
    IImage image = new Image(data, this.destFile, width, height, max);

    return image;
  }

}
