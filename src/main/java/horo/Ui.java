package horo;

import horo.commands.ExitCommand;
import horo.commands.expenses.AddExpenseCommand;
import horo.commands.expenses.DeleteExpenseCommand;
import horo.commands.expenses.ListExpenseCommand;
import horo.commands.tasks.AddDeadlineCommand;
import horo.commands.tasks.AddEventCommand;
import horo.commands.tasks.AddTodoCommand;
import horo.commands.tasks.DeleteTaskCommand;
import horo.commands.tasks.ListTaskCommand;
import horo.commands.tasks.MarkCommand;
import horo.commands.tasks.UnmarkCommand;
import horo.components.DialogBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Ui {
  private VBox dialogContainer;

  private Image userImage = new Image(this.getClass().getResourceAsStream("/images/profile.jpg"));
  private Image holoImage = new Image(this.getClass().getResourceAsStream("/images/holo.jpg"));

  public Ui(VBox dialogContainer) {
    this.dialogContainer = dialogContainer;

    horoOutput(getWelcome());
  }

  /**
   * Displays the string given as emitted from user.
   * 
   * @param output The string to display
   */
  public void userOutput(String output) {
    dialogContainer.getChildren().addAll(
        getVBoxComponent(DialogBox.getUserDialog(output, userImage)));
  }

  /**
   * Displays the string given as emitted from the program.
   * 
   * @param output The string to display
   */
  public void horoOutput(String output) {
    dialogContainer.getChildren().addAll(
        getVBoxComponent(DialogBox.getDukeDialog(output, holoImage)));
  }

  private AnchorPane getVBoxComponent(DialogBox box) {
    AnchorPane ap = new AnchorPane(box);
    AnchorPane.setLeftAnchor(box, 0.0);
    AnchorPane.setRightAnchor(box, 0.0);
    ap.maxWidth(Double.MAX_VALUE);
    return ap;
  }

  private String getWelcome() {
    String logo = ""
        + " _    _ \n"
        + "| |  | |\n"
        + "| |__| | ___  _ __ ___\n"
        + "|  __  |/ _ \\| '__/ _ \\\n"
        + "| |  | | (_) | | | (_) |\n"
        + "|_|  |_|\\___/|_|  \\___/\n";

    String introduction = ""
        + "Hello! I'm Horo\n"
        + "What can I do for you?\n"
        + "Commands: \n"
        + " ---Tasks---\n"
        + ListTaskCommand.DISPLAY_FORMAT + "\n"
        + AddTodoCommand.DISPLAY_FORMAT + "\n"
        + AddDeadlineCommand.DISPLAY_FORMAT + "\n"
        + AddEventCommand.DISPLAY_FORMAT + "\n"
        + MarkCommand.DISPLAY_FORMAT + "\n"
        + UnmarkCommand.DISPLAY_FORMAT + "\n"
        + DeleteTaskCommand.DISPLAY_FORMAT + "\n"
        + " ---Expenses---\n"
        + ListExpenseCommand.DISPLAY_FORMAT + "\n"
        + AddExpenseCommand.DISPLAY_FORMAT + "\n"
        + DeleteExpenseCommand.DISPLAY_FORMAT + "\n"
        + " ---Others---\n"
        + ExitCommand.DISPLAY_FORMAT + "\n";

    return logo + introduction;
  }

  public void handleExit() {
    System.exit(0);
  }
}
