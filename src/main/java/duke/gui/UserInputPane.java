package duke.gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * The class for the user input panel
 */
public class UserInputPane extends Pane {
    private Main mainPanel;
    private TextField userInput;
    private Button sendButton;

    /**
     * Instantiates the UserInputPane
     *
     * @param mainPanel the mainPanel where the UserInputPane located
     */
    public UserInputPane(Main mainPanel) {
        this.mainPanel = mainPanel;
        userInput = new TextField();
        sendButton = new Button("Send");

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane inputContainer = new AnchorPane();
        inputContainer.getChildren().addAll(userInput, sendButton);


        // Position userInput in the bottom left corner
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Position sendButton in the bottom right corner
        AnchorPane.setRightAnchor(sendButton, -55.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);


        // Set the preferred height for inputContainer to accommodate both elements
        inputContainer.setPrefHeight(userInput.getPrefHeight());

        getChildren().add(inputContainer);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        mainPanel.addDialog(userText);
        /*
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        )
         */
        userInput.clear();
    }

    public TextField getUserInput() {
        return userInput;
    }

    public Button getSendButton() {
        return sendButton;
    }
}
