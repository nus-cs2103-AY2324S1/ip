import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Thorndike thorndike;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    // public MainWindow(Thorndike thorndike) {
    // try {
    // FXMLLoader fxmlLoader = new
    // FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
    // fxmlLoader.setController(this);
    // fxmlLoader.setRoot(this);
    // fxmlLoader.load();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    // }

    public void setDuke(Thorndike d) {
        thorndike = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = thorndike.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }
}
