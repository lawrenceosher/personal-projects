package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import model.IImage;
import model.IPixel;

/**
 * Class that represents the part of the GUI where the Histogram is displayed. The histogram
 * reflects components of the images.
 */
public class HistogramPanel extends JLabel {

  private Graphics2D g;
  private final IPixel[][] pixels;
  private HashMap<Integer, Integer> redHistogram;
  //   Key: component-value, Value: Frequency
  private HashMap<Integer, Integer> greenHistogram;
  private HashMap<Integer, Integer> blueHistogram;
  private HashMap<Integer, Integer> intensityHistogram;
  private int maxValue;

  /**
   * Constructor for the histogram that initializes an image to be used for the histogram.
   *
   * @param img Image to be used for the histogram.
   */
  public HistogramPanel(IImage img) {
    if (img == null) {
      throw new IllegalArgumentException("Histogram received a null image");
    }
    this.pixels = img.getPixels();
    this.redHistogram = new HashMap<>();
    this.greenHistogram = new HashMap<>();
    this.blueHistogram = new HashMap<>();
    this.intensityHistogram = new HashMap<>();
    this.maxValue = 0;
    for (int i = 0; i < 256; i++) {
      this.redHistogram.put(i, 0);
      this.greenHistogram.put(i, 0);
      this.blueHistogram.put(i, 0);
      this.intensityHistogram.put(i, 0);
    }
    this.setUpHist();
  }

  private void setUpHist() {
    int prevMax = 0;
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[0].length; j++) {
        int redComp = this.pixels[i][j].getRed();
        int greenComp = this.pixels[i][j].getGreen();
        int blueComp = this.pixels[i][j].getBlue();
        int intensityComp = this.pixels[i][j].getIntensity();

        for (Map.Entry<Integer, Integer> e : this.redHistogram.entrySet()) {
          if (e.getKey() == redComp) {
            this.redHistogram.replace(e.getKey(), e.getValue(), e.getValue() + 1);
            prevMax = Math.max(e.getValue() + 1, prevMax);
          }
        }

        for (Map.Entry<Integer, Integer> e : this.greenHistogram.entrySet()) {
          if (e.getKey() == greenComp) {
            this.greenHistogram.replace(e.getKey(), e.getValue(), e.getValue() + 1);
            prevMax = Math.max(e.getValue() + 1, prevMax);
          }
        }

        for (Map.Entry<Integer, Integer> e : this.blueHistogram.entrySet()) {
          if (e.getKey() == blueComp) {
            this.blueHistogram.replace(e.getKey(), e.getValue(), e.getValue() + 1);
            prevMax = Math.max(e.getValue() + 1, prevMax);
          }
        }

        for (Map.Entry<Integer, Integer> e : this.intensityHistogram.entrySet()) {
          if (e.getKey() == intensityComp) {
            this.intensityHistogram.replace(e.getKey(), e.getValue(), e.getValue() + 1);
            prevMax = Math.max(e.getValue() + 1, prevMax);
          }
        }
      }
    }

    this.maxValue = prevMax;
  }

  /**
   * Renders the Histogram so it can be drawn into the window of the GUI.
   *
   * @return A BufferedImage of the Histogram.
   */
  public BufferedImage renderHistogram() {

    BufferedImage finalImg;

    this.setBackground(Color.WHITE);
    this.setPreferredSize(new Dimension(500, 500));
    finalImg = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);

    int scale = maxValue / 500;
    Graphics2D g = finalImg.createGraphics();

    Integer prevKey = 0;
    Integer prevVal = 0;
    for (Map.Entry<Integer, Integer> e : redHistogram.entrySet()) {
      Integer curKey = e.getKey();
      Integer curVal = e.getValue();

      g.setColor(Color.RED);

      curVal = curVal / scale;
      curKey = curKey * 2;
      g.drawLine(prevKey, 500 - prevVal, curKey, 500 - curVal);


      prevKey = curKey;
      prevVal = curVal;
    }

    prevKey = 0;
    prevVal = 0;
    for (Map.Entry<Integer, Integer> e : blueHistogram.entrySet()) {
      Integer curKey = e.getKey();
      Integer curVal = e.getValue();

      curVal = curVal / scale;
      curKey = curKey * 2;
      g.setColor(Color.BLUE);
      g.drawLine(prevKey, 500 - prevVal, curKey, 500 - curVal);

      prevKey = curKey;
      prevVal = curVal;
    }


    prevKey = 0;
    prevVal = 0;
    for (Map.Entry<Integer, Integer> e : greenHistogram.entrySet()) {
      Integer curKey = e.getKey();
      Integer curVal = e.getValue();

      curVal = curVal / scale;
      curKey = curKey * 2;
      g.setColor(Color.GREEN);
      g.drawLine(prevKey, 500 - prevVal, curKey, 500 - curVal);

      prevKey = curKey;
      prevVal = curVal;
    }


    prevKey = 0;
    prevVal = 0;
    for (Map.Entry<Integer, Integer> e : intensityHistogram.entrySet()) {
      Integer curKey = e.getKey();
      Integer curVal = e.getValue();

      curVal = curVal / scale;
      curKey = curKey * 2;
      g.setColor(Color.WHITE);
      g.drawLine(prevKey, 500 - prevVal, curKey, 500 - curVal);

      prevKey = curKey;
      prevVal = curVal;
    }

    return finalImg;
  }


}
