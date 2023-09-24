package duchess;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindowController extends VBox {
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duchessImage = new Image(this.getClass().getResourceAsStream("/images/duchess.png"));

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox chatVbox;

    @FXML
    private TextField userinputTextfield;

    private DuchessGui duchessGui;

    public void setDuchessGui(DuchessGui dGui) {
        this.duchessGui = dGui;
    }

    public void loadTasksFromFile() {
        this.duchessGui.loadTasks();
    }

    @FXML
    private void initialize() {
        chatVbox.heightProperty().addListener(observable -> scrollPane.setVvalue(1D));

        chatVbox.getChildren().addAll(
                DialogBox.getDuchessDialog(Ui.printGreeting(), duchessImage)
        );

        userinputTextfield.clear();
    }

    @FXML
    private void handleUserInput() {
        String input = userinputTextfield.getText();
        String response = duchessGui.getResponse(input);

        if (Parser.isExitCommand(input)) {
            chatVbox.getChildren().addAll(
                    DialogBox.getDuchessDialog(Ui.printFarewell(), duchessImage)
            );
            userinputTextfield.clear();
            Platform.exit();
        }

        chatVbox.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDuchessDialog(response, duchessImage)
        );
        userinputTextfield.clear();
    }

    public void saveTasksToFile() {
        this.duchessGui.saveTasks();
    }
}
