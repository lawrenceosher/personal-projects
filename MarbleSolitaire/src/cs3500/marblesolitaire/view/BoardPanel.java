package cs3500.marblesolitaire.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cs3500.marblesolitaire.controller.IFeatures;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Custom Board Panel that is used for the view for a MarbleSolitaireModel.
 */
public class BoardPanel extends JPanel implements IPanel {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot;
  private Image marbleSlot;
  private Image blankSlot;
  private final int cellDimension;


  /**
   * Constructor for the BoardPanel that initializes the model and reads in the proper images
   * for each slot type.
   * @param state The state of the board.
   * @throws IllegalStateException Thrown if the images can't be read in.
   */
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
              new Dimension((this.modelState.getBoardSize() + 4) * cellDimension
                      , (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int originX;
    int originY;
    
    originX = (int) (this.getPreferredSize().getWidth() / 2
            - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() / 2
            - this.modelState.getBoardSize() * cellDimension / 2);


    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      for (int j = 0; j < this.modelState.getBoardSize(); j++) {
        switch (this.modelState.getSlotAt(i, j)) {
          case Invalid:
            g.drawImage(this.blankSlot, originX + this.cellDimension * i,
                    originY + this.cellDimension * j, null);
            break;
          case Marble:
            g.drawImage(this.marbleSlot, originX + this.cellDimension * i,
                    originY + this.cellDimension * j, null);

            break;
          case Empty:
            g.drawImage(this.emptySlot, originX + this.cellDimension * i,
                    originY + this.cellDimension * j, null);

            break;
          default:
            throw new IllegalArgumentException("Slot state is not applicable.");
        }
      }

    }



    //The originX and originY is the top-left of where the cell (0,0) should start
    //cellDimension is the width (and height) occupied by every cell
    
  }


  @Override
  public void accept(IFeatures feature) {
    MouseListener listen;


  }
}
