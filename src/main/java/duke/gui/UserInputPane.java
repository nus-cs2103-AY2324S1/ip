package duke.gui;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


/**
 * The class for the user input panel
 */
public class UserInputPane extends Pane {
    private Image sendIcon = new Image(this.getClass().getResourceAsStream("/images/SendIcon.png"));
    private Main mainPanel;
    private TextField userInput;

    /**
     * Instantiates the UserInputPane
     *
     * @param mainPanel the mainPanel where the UserInputPane located
     */
    public UserInputPane(Main mainPanel) {
        this.mainPanel = mainPanel;
        userInput = new TextField();
        ImageView sendImage = new ImageView(sendIcon);

        sendImage.setFitWidth(30.0);
        sendImage.setFitHeight(25);
        userInput.setPrefWidth(345.0);

        AnchorPane inputContainer = new AnchorPane();
        inputContainer.getChildren().addAll(userInput, sendImage);


        // Position userInput in the bottom left corner
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Position sendImage in the bottom right corner
        AnchorPane.setRightAnchor(sendImage, -35.0);
        AnchorPane.setBottomAnchor(sendImage, 1.0);


        // Set the preferred height for inputContainer to accommodate both elements
        inputContainer.setPrefHeight(userInput.getPrefHeight());

        getChildren().add(inputContainer);

        sendImage.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        mainPanel.addDialog(userText);
        userInput.clear();
        if (userText.equals("bye")) {
            Platform.exit();
        }
    }

}
