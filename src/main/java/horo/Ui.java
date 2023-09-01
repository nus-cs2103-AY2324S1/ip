package horo;

import horo.components.DialogBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Ui {
  private VBox dialogContainer;

  private Image user = new Image(this.getClass().getResourceAsStream("/images/profile.jpg"));

  public Ui(VBox dialogContainer) {
    this.dialogContainer = dialogContainer;

    horoOutput(getWelcome());
  }

  public void userOutput(String output) {
    dialogContainer.getChildren().addAll(
        DialogBox.getUserDialog(output, user));
  }

  public void horoOutput(String output) {
    dialogContainer.getChildren().addAll(
        DialogBox.getDukeDialog(output, user));
  }

  private String getWelcome() {
    String logo = " _    _ \n"
        + "| |  | |\n"
        + "| |__| | ___  _ __ ___\n"
        + "|  __  |/ _ \\| '__/ _ \\\n"
        + "| |  | | (_) | | | (_) |\n"
        + "|_|  |_|\\___/|_|  \\___/\n";

    String introduction = "Hello! I'm Horo\n"
        + "What can I do for you?\n"
        + "Usage: \n"
        + " todo <description>\n"
        + " deadline <description> /by <time>\n"
        + " event <description> /from <time> /to <time>\n"
        + " list\n"
        + " mark <number>\n"
        + " unmark <number>\n"
        + " delete <number>\n"
        + " bye\n";

    return logo + introduction;
  }

  public void handleExit() {
    System.exit(0);
  }
}
