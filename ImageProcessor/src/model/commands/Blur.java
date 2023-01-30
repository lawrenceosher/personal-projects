package model.commands;


/**
 * Blurs the image by applying a Gaussian Blur matrix as a kernel to each pixel in the image.
 */
public class Blur extends AFilter {


  /**
   * Constructor that initializes values for the name of the source and destination files.
   * Also initializes the matrix that will be used as a kernel for the filter on images.
   * @param srcFile  The name of the image that needs to be modified.
   * @param destFile The name of the modified image.
   * @throws IllegalArgumentException Thrown if the srcFile or destFile are null.
   */
  public Blur(String srcFile, String destFile) throws IllegalArgumentException {
    super(srcFile, destFile);

    this.matrix = new double[3][3];
    double[] gBlurRow0 = {0.0625, 0.125, 0.0625};
    this.matrix[0] = gBlurRow0;
    double[] gBlurRow1 = {0.125, 0.25, 0.125};
    this.matrix[1] = gBlurRow1;
    double[] gBlurRow2 = {0.0625, 0.125, 0.0625};
    this.matrix[2] = gBlurRow2;

  }




}
