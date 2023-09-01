package horo.components;

import horo.HoroException;
import horo.Parser;
import horo.Storage;
import horo.Ui;
import horo.commands.Command;
import horo.data.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

  private Storage storage;
  private TaskList taskList;
  private Ui ui;

  @FXML
  public void initialize() {
    scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
  }

  public void setUp(Storage storage) {
    this.storage = storage;
    this.taskList = storage.load();
    this.ui = new Ui(dialogContainer);
  }

  @FXML
  private void handleUserInput() {
    String input = userInput.getText();

    ui.userOutput(input);

    try {
      Command c = Parser.parse(input);
      c.execute(taskList, ui, storage);
    } catch (HoroException e) {
      ui.horoOutput(e.getMessage());
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }

    userInput.clear();
  }
}
