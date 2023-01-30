package cs3500.marblesolitaire.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class that extends MouseAdapter so we only need to worry about overriding the mouseClicked
 * method.
 */
public class ClickMove extends MouseAdapter {

  @Override
  public void mouseClicked(MouseEvent e) {
    e.getX();
    e.getY();
  }
}
