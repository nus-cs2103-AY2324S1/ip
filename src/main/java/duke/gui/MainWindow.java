package duke.gui;

import duke.Duke;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Objects;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
  @FXML
  private ScrollPane scrollPane;
  @FXML
  private VBox dialogContainer;
  @FXML
  private TextField userInput;
  @FXML
  private Button sendButton;

  private Duke duke;

  private Image userImage = new Image(this.getClass().getResourceAsStream("images/DaUser.png"));
  private Image dukeImage = new Image(this.getClass().getResourceAsStream("images/DaDuke.png"));

  @FXML
  public void initialize() {
      scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
  }
  
  @FXML
  public void showWelcomeMessage() {
      String introduction = duke.showIntroduction(); // Assuming this method exists in your Duke class
      dialogContainer.getChildren().add(DialogBox.getDukeDialog(introduction, dukeImage));
  }
  

  public void setDuke(Duke d) {
    duke = d;
    showWelcomeMessage();
  }

  /**
   * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
   * the dialog container. Clears the user input after processing.
   */
  @FXML
  private void handleUserInput() {
    String input = userInput.getText();

    String response = duke.getResponse(input);

    // Aligned with SLAP principle
    printToTerminal(input, response);
    dialogContainer.getChildren().addAll(
        DialogBox.getUserDialog(input, userImage),
        DialogBox.getDukeDialog(response, dukeImage)
    );
    if (Objects.equals(input, "bye")) {
      // sleep
//      dialogContainer.getChildren().add(DialogBox.getDukeDialog("Exiting in 2 seconds", dukeImage));
      Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), event -> {Platform.exit();}));
      timeline.play();
    }
    userInput.clear();
  }

  private void printToTerminal(String input, String response) {
    System.out.println(input);
    System.out.println(response);
  }
}
