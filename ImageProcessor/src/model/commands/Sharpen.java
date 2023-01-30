package model.commands;


/**
 * Sharpens the image by applying a filter kernel that will make the edges more apparent.
 */
public class Sharpen extends AFilter {

  /**
   * Constructor that initializes values for the name of the source and destination files.
   * Also initializes the matrix that will be used as a kernel for the filter on images.
   *
   * @param srcFile  The name of the image that needs to be modified.
   * @param destFile The name of the modified image.
   * @throws IllegalArgumentException Thrown if the srcFile or destFile are null.
   */
  public Sharpen(String srcFile, String destFile) throws IllegalArgumentException {
    super(srcFile, destFile);

    this.matrix = new double[5][5];

    double[] sharpenRow0 = {-0.125, -0.125, -0.125, -0.125, -0.125};
    this.matrix[0] = sharpenRow0;

    double[] sharpenRow1 = {-0.125, 0.25, 0.25, 0.25, -0.125};
    this.matrix[1] = sharpenRow1;

    double[] sharpenRow2 = {-0.125, 0.25, 1, 0.25, -0.125};
    this.matrix[2] = sharpenRow2;

    double[] sharpenRow3 = {-0.125, 0.25, 0.25, 0.25, -0.125};
    this.matrix[3] = sharpenRow3;


    double[] sharpenRow4 = {-0.125, -0.125, -0.125, -0.125, -0.125};
    this.matrix[4] = sharpenRow4;


  }

}
