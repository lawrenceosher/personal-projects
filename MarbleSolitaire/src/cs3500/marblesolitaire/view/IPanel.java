package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.controller.IFeatures;

/**
 * Interface for different types of panels.
 */
public interface IPanel {

  /**
   * Accepts an object of the features interface.
   * @param feature Features object.
   */
  public void accept(IFeatures feature);
}
