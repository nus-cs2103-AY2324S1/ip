package duke;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Responsible for the miscellaneous console outputs.
 */
public class UI {

  /**
   * Creates a UI object that prints out miscellaneous statements.
   */
  public UI() {}


  /**
   * Greets the user.
   */
  public void greet(Pane pane, Image dukeImage) {
    pane.getChildren().add(DialogBox.getDukeDialog("Hello! I'm Duke\n" + "What can I do for you?", dukeImage));
  }

  /**
   * Says bye to the user.
   */
  public void bye(Pane pane, Image dukeImage) {
    pane.getChildren().add(DialogBox.getDukeDialog("Bye. Hope to see you again soon!", dukeImage));
  }

}
