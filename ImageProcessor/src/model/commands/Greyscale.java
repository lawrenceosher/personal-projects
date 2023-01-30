package model.commands;

/**
 * Converts a Color image into a Greyscale image that is only made up of different grey shades.
 */
public class Greyscale extends AColorTransformation {

  /**
   * Constructor that initializes values for the name of the source and destination files.
   * Also initializes the matrix to be used for the color transformation.
   *
   * @param srcFile  The name of the image that needs to be modified.
   * @param destFile The name of the modified image.
   * @throws IllegalArgumentException thrown if the srcFile or destFile are null.
   */
  public Greyscale(String srcFile, String destFile) throws IllegalArgumentException {
    super(srcFile, destFile);

    this.matrix = new double[3][3];
    double[] greyTransformationRow0 = {0.2126, 0.7152, 0.0722};
    this.matrix[0] = greyTransformationRow0;
    double[] greyTransformationRow1 = {0.2126, 0.7152, 0.0722};
    this.matrix[1] = greyTransformationRow1;
    double[] greyTransformationRow2 = {0.2126, 0.7152, 0.0722};
    this.matrix[2] = greyTransformationRow2;


  }
}
