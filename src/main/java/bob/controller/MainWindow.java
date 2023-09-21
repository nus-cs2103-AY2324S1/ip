package bob.controller;

import bob.Bob;
import bob.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This control represents a text window consisting of a scroll pane, user input text field,
 * a button to input text, and a dialog box.
 */
public class MainWindow extends GridPane {
    private final Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/DefaultUserProfilePic.jpg"));
    private final Image bobImage = new Image(this.getClass()
            .getResourceAsStream("/images/Bob.jpg"));

    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    private Bob bob;
    private Stage stage;

    /**
     * Constructor for the MainWindow Controller
     */
    public MainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert userInput != null : "User input has not been loaded properly from fxml file";
        assert sendButton != null : "Send button picture has not been loaded properly from fxml file";
        assert scrollPane != null : "Scroll pane has not been loaded properly from fxml file";
        assert dialogContainer != null : "Dialog Container picture has not been loaded properly from fxml file";
    }

    public void setBob(Bob b) {
        bob = b;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initialises the interactive components of the chat window.
     */
    public void initialise() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setOnAction((event) -> handleUserInput());
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        dialogContainer.getChildren().add(DialogBox.getBobDialog(bob.getBobWelcomeMessage(), bobImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bob's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bob.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBobDialog(response, bobImage)
        );
        userInput.clear();
        if (!bob.isActive()) {
            stage.close();
        }
    }
}
