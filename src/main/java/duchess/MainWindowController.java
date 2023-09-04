package duchess;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindowController extends VBox {
    private Image userImage  = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duchessImage = new Image(this.getClass().getResourceAsStream("/images/duchess.png"));

    @FXML
    ScrollPane scrollPane;

    @FXML
    VBox chat_vbox; 

    @FXML
    TextField userinput_textfield;

    private DuchessGUI duchessGUI;

    public void setDuchessGUI(DuchessGUI dGUI) {
        this.duchessGUI = dGUI;
    }

    public void loadTasksFromFile() {
        // Create the save file, if it does not exist.
        Storage.createSaveFile();

        // Load tasks from the save file.
        duchessGUI.setStoredTasks(Storage.loadTasksFromFile());
    }

    @FXML
    private void initialize() {
        chat_vbox.heightProperty().addListener(observable -> scrollPane.setVvalue(1D));

        chat_vbox.getChildren().addAll(
                DialogBox.getDuchessDialog(Ui.printGreetingGUI(), duchessImage)
                );

        userinput_textfield.clear();
    }

    @FXML
    private void handleUserInput() {
        String input = userinput_textfield.getText();
        String response = duchessGUI.getResponse(input);
        
        if (response.equals(Ui.printFarewellGUI())) {
            Platform.exit();
        }

        chat_vbox.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDuchessDialog(response, duchessImage)
                );
        userinput_textfield.clear();
    }

    public void saveTasksToFile() {
        Storage.saveTasksToFile(this.duchessGUI.getStoredTasks());
    }
}
