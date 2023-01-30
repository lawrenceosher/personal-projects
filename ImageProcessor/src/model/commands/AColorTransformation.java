package model.commands;

import model.IImage;
import model.IImageProcessorModel;
import model.IPixel;
import model.Image;
import model.Pixel;

/**
 * Converts Color images into images of different shades based on the transformation matrix
 * and the channels of each pixel.
 */
public abstract class AColorTransformation extends ACommand {

  //The 2D array to be used for the color transformation. Not in constructor because it doesn't
  //make sense to get that as an input from the Scanner that the controller is utilizing.
  //Each subclass will implement their own matrix based on what transformation is needed.
  protected double[][] matrix;

  /**
   * Constructor that initializes values for the name of the source and destination files.
   * Also initializes the matrix to be used for the color transformation.
   *
   * @param srcFile  The name of the image that needs to be modified.
   * @param destFile The name of the modified image.
   * @throws IllegalArgumentException thrown if the srcFile or destFile are null.
   */
  public AColorTransformation(String srcFile, String destFile) throws IllegalArgumentException {
    super(srcFile, destFile);

    this.matrix = new double[3][3];

  }

  @Override
  public IImage runCommand(IImageProcessorModel model) {

    IImage oldImage = model.getImage(this.srcFile);
    IPixel[][] oldPixels = oldImage.getPixels();
    IPixel[][] newPixels = new IPixel[oldImage.getHeight()][oldImage.getWidth()];
    IImage newImage;


    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {

        //array that will hold components of new pixel
        int[] newRGBPix = new int[3];
        //array that holds components of old pixel
        int[] curRGBPix = new int[3];

        curRGBPix[0] = oldPixels[i][j].getRed();
        curRGBPix[1] = oldPixels[i][j].getGreen();
        curRGBPix[2] = oldPixels[i][j].getBlue();

        for (int a = 0; a < this.matrix.length; a++) {

          double[] row = this.matrix[a];

          for (int b = 0; b < row.length; b++) {
            newRGBPix[a] = newRGBPix[a] + (int) (row[b] * curRGBPix[b]);
          }

        }

        int newRedVal = newRGBPix[0];
        int newGreenVal = newRGBPix[1];
        int newBlueVal = newRGBPix[2];

        //Clamping the pixels to be between 0 and 255

        if (newRedVal > 255) {
          newRedVal = 255;
        }

        if (newRedVal < 0) {
          newRedVal = 0;
        }

        if (newGreenVal > 255) {
          newGreenVal = 255;
        }

        if (newGreenVal < 0) {
          newGreenVal = 0;
        }

        if (newBlueVal > 255) {
          newBlueVal = 255;
        }

        if (newBlueVal < 0) {
          newBlueVal = 0;
        }



        newPixels[i][j] = new Pixel(newRedVal, newGreenVal, newBlueVal);

      }
    }

    newImage = new Image(newPixels, this.destFile, oldImage.getWidth(), oldImage.getHeight(),
            oldImage.getMaxValue());

    return newImage;
  }
}
