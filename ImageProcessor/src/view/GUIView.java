package view;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Container;

import controller.ImageGUIControllerImpl;
import model.IImage;
import model.IPixel;

import static java.awt.image.BufferedImage.TYPE_INT_BGR;

/**
 * View that represents a Graphical User Interface that the user will interact with when they
 * are using the Image Processor Application.
 */
public class GUIView extends JFrame implements IGUIView {

  private JTextField textBox;

  private JTextField brightInput;

  private JLabel histoLabel;

  private JLabel messageLabel;
  private JLabel imageLabel;


  /**
   * Constructor that sets up the overall frame with the different panels for images, buttons,
   * and histograms. Connects the view with the given controller.
   *
   * @param controller The controller that the view will interact with.
   * @throws IllegalArgumentException Thrown if the given controller is null.
   */
  public GUIView(ImageGUIControllerImpl controller) throws IllegalArgumentException {
    super("Image Processor Model");

    if (controller == null) {
      throw new IllegalArgumentException("Can't have a null argument.");
    }
    Container c;
    JPanel newHisto;

    JPanel buttonPanel;

    this.setLayout(new FlowLayout());
    c = getContentPane();
    this.setPreferredSize(new Dimension(1500, 1000));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    buttonPanel = new JPanel();
    JButton blurButton = new JButton("Blur");
    buttonPanel.add(blurButton);
    blurButton.setActionCommand("Blur");
    blurButton.addActionListener(controller);


    JButton loadButton = new JButton("Load");
    buttonPanel.add(loadButton);
    loadButton.setActionCommand("Load");
    loadButton.addActionListener(controller);

    JButton sharpenButton = new JButton("Sharpen");
    buttonPanel.add(sharpenButton);
    sharpenButton.setActionCommand("Sharpen");
    sharpenButton.addActionListener(controller);

    JButton vfButton = new JButton("Vertical-Flip");
    buttonPanel.add(vfButton);
    vfButton.setActionCommand("Vertical-Flip");
    vfButton.addActionListener(controller);

    JButton hfButton = new JButton("Horizontal-Flip");
    buttonPanel.add(hfButton);
    hfButton.setActionCommand("Horizontal-Flip");
    hfButton.addActionListener(controller);

    JButton brightButton = new JButton("Brighten");
    buttonPanel.add(brightButton);

    brightButton.setActionCommand("Brighten");
    brightButton.addActionListener(controller);
    this.brightInput = new JTextField("Brighten by: ");
    this.brightInput.addActionListener(controller);
    buttonPanel.add(this.brightInput);

    JButton redButton = new JButton("Red-Component");
    buttonPanel.add(redButton);
    redButton.setActionCommand("Red-Component");
    redButton.addActionListener(controller);

    JButton blueButton = new JButton("Blue-Component");
    buttonPanel.add(blueButton);
    blueButton.setActionCommand("Blue-Component");
    blueButton.addActionListener(controller);

    JButton greenButton = new JButton("Green-Component");
    buttonPanel.add(greenButton);
    greenButton.setActionCommand("Green-Component");
    greenButton.addActionListener(controller);

    JButton valueButton = new JButton("Value-Component");
    buttonPanel.add(valueButton);
    valueButton.setActionCommand("Value-Component");
    valueButton.addActionListener(controller);

    JButton intensityButton = new JButton("Intensity-Component");
    buttonPanel.add(intensityButton);
    intensityButton.setActionCommand("Intensity-Component");
    intensityButton.addActionListener(controller);

    JButton lumaButton = new JButton("Luma-Component");
    buttonPanel.add(lumaButton);
    lumaButton.setActionCommand("Luma-Component");
    lumaButton.addActionListener(controller);

    JButton greyscaleButton = new JButton("Greyscale");
    buttonPanel.add(greyscaleButton);
    greyscaleButton.setActionCommand("Greyscale");
    greyscaleButton.addActionListener(controller);

    JButton sepiaButton = new JButton("Sepia");
    buttonPanel.add(sepiaButton);
    sepiaButton.setActionCommand("Sepia");
    sepiaButton.addActionListener(controller);

    JPanel histoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    histoLabel = new JLabel();
    histoLabel.setPreferredSize(new Dimension(500, 500));
    histoLabel.setBackground(Color.WHITE);
    histoLabel.setLocation(0, 0);

    histoPanel.setPreferredSize(new Dimension(600, 600));
    histoPanel.setOpaque(false);
    histoPanel.add(histoLabel);

    JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    imageLabel = new JLabel();

    imageLabel.setBackground(Color.WHITE);
    imageLabel.setLocation(0, 0);

    imagePanel.setOpaque(false);
    imagePanel.add(imageLabel);
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(600, 600));

    JPanel scrollPanel = new JPanel();
    scrollPanel.add(imageScrollPane);

    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.setPreferredSize(new Dimension(1000, 100));
    textBox = new JTextField("New File Name");
    textBox.addActionListener(controller);
    buttonPanel.add(textBox);
    buttonPanel.add(textBox);

    JButton fileSaveButton = new JButton("Save");
    fileSaveButton.setActionCommand("Save");
    fileSaveButton.addActionListener(controller);
    buttonPanel.add(fileSaveButton);

    newHisto = new JPanel();
    newHisto.setOpaque(true);
    newHisto.setBackground(Color.PINK);
    newHisto.setPreferredSize(new Dimension(255, 500));

    JPanel messagePanel = new JPanel();
    this.messageLabel = new JLabel();
    messagePanel.setLayout(new FlowLayout());
    messagePanel.setPreferredSize(new Dimension(250, 150));
    messagePanel.add(messageLabel);

    buttonPanel.setBackground(Color.WHITE);
    scrollPanel.setBounds(0, 500, 600, 600);
    messagePanel.setBounds(0, 1500, 250, 150);
    c.add(buttonPanel);
    c.add(scrollPanel);
    c.add(histoPanel);
    c.add(messageLabel);

    pack();
    setVisible(true);
  }

  @Override
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
    this.repaint();
  }

  @Override
  public JFrame getFrame() {
    return this;
  }

  @Override
  public void renderImage(IImage img) {
    HistogramPanel histogram;
    IPixel[][] data = img.getPixels();
    //this.renderMessage("render image called");

    //turn IPixel array into buffered Image
    BufferedImage bImg = new BufferedImage(data[0].length, data.length, TYPE_INT_BGR);
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[0].length; j++) {
        int r = data[i][j].getRed();
        int g = data[i][j].getGreen();
        int b = data[i][j].getBlue();
        int p = new Color(r, g, b).getRGB();
        bImg.setRGB(j, i, p);
      }
    }
    System.out.println("drawn");
    ImageIcon imageIcon = new ImageIcon(bImg);
    imageLabel.setIcon(imageIcon);

    histogram = new HistogramPanel(img);
    ImageIcon histoIcon = new ImageIcon(histogram.renderHistogram());
    histoLabel.setIcon(histoIcon);

    this.repaint();
  }

  @Override
  public String getTextBoxInput() throws IllegalArgumentException {
    String text = textBox.getText();
    return text;
  }

  @Override
  public String getBrightInput() throws IllegalArgumentException {
    String text = brightInput.getText();
    return text;
  }

}

