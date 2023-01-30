package model.commands;

/**
 * Converts a color image into a sepia-toned image that has a reddish brown tone.
 */
public class Sepia extends AColorTransformation {

  /**
   * Constructor that initializes values for the name of the source and destination files.
   * Also initializes the matrix to be used for the color transformation.
   *
   * @param srcFile  The name of the image that needs to be modified.
   * @param destFile The name of the modified image.
   * @throws IllegalArgumentException thrown if the srcFile or destFile are null.
   */
  public Sepia(String srcFile, String destFile) throws IllegalArgumentException {
    super(srcFile, destFile);

    this.matrix = new double[3][3];
    double[] sepiaTransformationRow0 = {0.393, 0.769, 0.1896};
    this.matrix[0] = sepiaTransformationRow0;
    double[] sepiaTransformationRow1 = {0.349, 0.686, 0.168};
    this.matrix[1] = sepiaTransformationRow1;
    double[] sepiaTransformationRow2 = {0.272, 0.534, 0.131};
    this.matrix[2] = sepiaTransformationRow2;

  }
}
