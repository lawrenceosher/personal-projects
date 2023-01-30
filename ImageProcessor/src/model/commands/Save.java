package model.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import model.IImageProcessorModel;
import model.IImage;
import model.IPixel;

import static java.awt.image.BufferedImage.TYPE_INT_BGR;

/**
 * Command that allows the user to save an image to a specified file path.
 */
public class Save extends ACommand implements ICommands {

  private String fileName;
  private String filePath;

  /**
   * Constructor that initializes the name of the file path and the file.
   *
   * @param filePath The name of the file path where the image will be saved to.
   * @param fileName The name of the image that needs to be saved.
   * @throws IllegalArgumentException Thrown if either the filePath or fileName are null.
   */
  public Save(String filePath, String fileName) throws IllegalArgumentException {
    super(filePath, fileName);

    this.fileName = fileName;
    this.filePath = filePath;
  }

  @Override
  public IImage runCommand(IImageProcessorModel model) {
    IImage image = model.getImage(fileName);
    String newExtension = "";
    int index = filePath.lastIndexOf('.');
    if (index > 0) {
      newExtension = filePath.substring(index + 1);
    }

    if (newExtension.equals("ppm")) {
      this.writePPM(image);
    } else {
      this.write(image, newExtension);
    }

    return image;
  }


  private void writePPM(IImage image) {
    IPixel[][] data = image.getPixels();
    try {
      OutputStream output = new FileOutputStream(filePath);
      StringBuilder sb = new StringBuilder();
      sb.append("P3\n");
      sb.append(image.getWidth() + " ");
      sb.append(image.getHeight() + "\n");
      sb.append(image.getMaxValue() + "\n");
      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          sb.append(data[i][j].getRed() + "\n");
          sb.append(data[i][j].getGreen() + "\n");
          sb.append(data[i][j].getBlue() + "\n");
        }
      }
      output.write(sb.toString().getBytes());
      output.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private void write(IImage image, String extension) {
    try {
      OutputStream output = new FileOutputStream(filePath);
      IPixel[][] data = image.getPixels();
      BufferedImage bImg = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_BGR);
      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          int r = data[i][j].getRed();
          int g = data[i][j].getGreen();
          int b = data[i][j].getBlue();
          int p = new Color(r, g, b).getRGB();//(r << 16) | (g << 8) | b;
          bImg.setRGB(j, i, p);
        }
      }
      ImageIO.write(bImg, extension, output);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
