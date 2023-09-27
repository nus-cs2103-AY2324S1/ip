package didier.controller;

import java.io.IOException;

import didier.Didier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the Main Window. Provides layout for other controls.
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

    private Didier didier;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image didierImage = new Image(this.getClass().getResourceAsStream("/images/didier.png"));
    private Stage stage;

    /**
     * Constructor for the MainWindow.
     * @param stage the stage where the MainWindow object is set.
     */
    public MainWindow(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stage = stage;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setOnAction(e -> handleUserInput());
        sendButton.setOnMouseClicked(e -> handleUserInput());
        dialogContainer.getChildren().add(DialogBox.getDidierDialog(Didier.getBotGreeting(), didierImage));
    }

    public void setDidier(Didier didier) {
        this.didier = didier;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Didier's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = didier.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getDidierDialog(response, didierImage));
        userInput.clear();
        if (!didier.getIsActive()) {
            this.stage.close();
        }
    }
}
