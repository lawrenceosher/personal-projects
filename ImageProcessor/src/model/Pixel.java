package model;

/**
 * Represents one type of pixel and a pixel has red, green, and blue components.
 */
public class Pixel implements IPixel {
  private final Integer r;
  private final Integer g;
  private final Integer b;

  /**
   * Constructor that initializes values for a pixel's red, green, and blue components.
   *
   * @param r The red component of the pixel.
   * @param g The green component of the pixel.
   * @param b The blue component of the pixel.
   * @throws IllegalArgumentException Thrown if any of the components are null or negative.
   */
  public Pixel(Integer r, Integer g, Integer b) throws IllegalArgumentException {
    if (r == null || g == null | b == null) {
      throw new IllegalArgumentException("Argument can't be null");
    }
    if (r < 0 || g < 0 || b < 0) {
      throw new IllegalArgumentException("Argument can't be negative.");
    } else {
      this.r = r;
      this.g = g;
      this.b = b;
    }
  }

  @Override
  public int getRed() {
    return r;
  }

  @Override
  public int getGreen() {
    return g;
  }

  @Override
  public int getBlue() {
    return b;
  }

  @Override
  public int getValue() {
    return Math.max(Math.max(r, g), b);
  }

  @Override
  public int getIntensity() {
    return (r + g + b) / 3;
  }

  //casts the result of the formula as an int because a component has an int type.
  @Override
  public int getLuma() {
    return (int) ((0.2126 * r) + (0.7152 * g) + (0.0722 * b));
  }


  /**
   * Overrides the equals method of the Object class and this is done to get logical equality
   * between two pixels.
   *
   * @param o The object being compared to.
   * @return True if the pixels are the same, false if not.
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Pixel)) {
      return false;
    } else {
      Pixel other = (Pixel) o;
      return other.getRed() == this.getRed() && other.getBlue() == this.getBlue()
              && other.getGreen() == this.getGreen();
    }
  }

  /**
   * Retrieves the hash code of a pixel and two pixels should have the same hash codes if they are
   * equal.
   *
   * @return The hash code of a pixel based on its components.
   */
  @Override
  public int hashCode() {
    return this.r.hashCode() + this.g.hashCode() + this.b.hashCode();
  }
}
