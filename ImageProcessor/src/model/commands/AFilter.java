package model.commands;

import java.util.ArrayList;

import model.IImage;
import model.IImageProcessorModel;
import model.IPixel;
import model.Image;
import model.Pixel;

/**
 * Abstract class that represents a filter that will be applied to an image to change its pixels
 * depending on the pixels around it.
 */
public class AFilter extends ACommand {

  protected double[][] matrix;

  /**
   * Constructor that initializes values for the name of the source and destination files.
   *
   * @param srcFile  The name of the image that needs to be modified.
   * @param destFile The name of the modified image.
   * @throws IllegalArgumentException Thrown if the srcFile or destFile are null.
   */
  public AFilter(String srcFile, String destFile) throws IllegalArgumentException {
    super(srcFile, destFile);


  }

  @Override
  public IImage runCommand(IImageProcessorModel model) {

    IImage oldImage = model.getImage(this.srcFile);
    IPixel[][] oldPixels = oldImage.getPixels();
    IPixel[][] newPixels = new IPixel[oldImage.getHeight()][oldImage.getWidth()];
    IImage newImage;

    ArrayList<double[][]> components = new ArrayList<>();

    for (int i = 0; i < oldImage.getHeight(); i = i + 1) {
      for (int j = 0; j < oldImage.getWidth(); j = j + 1) {
        components = retComponentArrays(oldPixels, i, j);

        int newPixelRedVal = (int) arrayMultiplication(components.get(0));
        int newPixelGreenVal = (int) arrayMultiplication(components.get(1));
        int newPixelBlueVal = (int) arrayMultiplication(components.get(2));

        if (newPixelRedVal > 255) {
          newPixelRedVal = 255;
        }
        if (newPixelRedVal < 0) {
          newPixelRedVal = 0;
        }
        if (newPixelGreenVal > 255) {
          newPixelGreenVal = 255;
        }
        if (newPixelGreenVal < 0) {
          newPixelGreenVal = 0;
        }
        if (newPixelBlueVal > 255) {
          newPixelBlueVal = 255;
        }
        if (newPixelBlueVal < 0) {
          newPixelBlueVal = 0;
        }

        newPixels[i][j] = new Pixel(newPixelRedVal, newPixelGreenVal, newPixelBlueVal);
      }
    }


    newImage = new Image(newPixels, this.destFile, oldImage.getWidth(), oldImage.getHeight(),
            oldImage.getMaxValue());

    return newImage;

  }

  /**
   * given the current pixel and the pixels in the image, it returns an ArrayList of arrays,
   * the first one being an array of the red component, array of green componenet values and
   * array of blue component values.
   *
   * @param data    IPixel[][] representing the array pixels in the Image.
   * @param centerI the i value for the current pixel we are looking at
   * @param centerJ the j value for the current pixel we are looking at
   * @return ArrayList of double[][] representing an array of each component in the 3x3 channel.
   */
  protected ArrayList<double[][]> retComponentArrays(IPixel[][] data, int centerI, int centerJ) {

    ArrayList<double[][]> r = new ArrayList<double[][]>();
    double[][] newRed = new double[matrix.length][matrix[0].length];
    double[][] newGreen = new double[matrix.length][matrix[0].length];
    double[][] newBlue = new double[matrix.length][matrix[0].length];

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {

        try {
          int tempi = centerI - this.matrix.length / 2 + i;
          int tempj = centerJ - this.matrix[0].length / 2 + j;
          newRed[i][j] = data[tempi][tempj].getRed();
          newGreen[i][j] = data[tempi][tempj].getGreen();
          newBlue[i][j] = data[tempi][tempj].getBlue();
        } catch (IndexOutOfBoundsException o) {
          newRed[i][j] = 0;
          newGreen[i][j] = 0;
          newBlue[i][j] = 0;
        }
      }
    }

    r.add(newRed);
    r.add(newGreen);
    r.add(newBlue);

    return r;
  }

  /**
   * multiplies given channel by the matrix and returns the sum of those values in the array.
   *
   * @param channel the 3x3 of values of a certain componenet (red,green,blue)
   * @return double reprenting sum of all values multiplied by the matrix.
   */
  protected double arrayMultiplication(double[][] channel) {
    double sum = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {

        sum = sum + this.matrix[i][j] * channel[i][j];
      }
    }
    return sum;
  }
}

