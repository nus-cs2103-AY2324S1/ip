package duke.ui;

import duke.Duke;

import duke.data.exception.CCException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class MainWindow {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Duke duke;

    public MainWindow() {
        initialise();
    }

    private void initialise() {
        scrollPane = makeScrollPane();
        dialogContainer = makeDialogContainer();
        scrollPane.setContent(dialogContainer);

        userInput = makeUserInput();
        sendButton = makeSendButton();
        scene = makeScene();

        setAnchors();
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    public Scene getScene() {
        return scene;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            if (input.trim().isEmpty()) {
                return;
            }
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input),
                    DialogBox.getDukeDialog(response)
            );
            userInput.clear();
        } catch (CCException e) {
            System.err.println(e.getMessage());
        }
    }

    private ScrollPane makeScrollPane() {
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        return scrollPane;
    }

    private VBox makeDialogContainer() {
        VBox dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        return dialogContainer;
    }

    private TextField makeUserInput() {
        TextField userInput = new TextField();
        userInput.setPrefWidth(325.0);
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        return userInput;
    }

    private Button makeSendButton() {
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        return sendButton;
    }

    private Scene makeScene() {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);
        return new Scene(mainLayout);
    }

    private void setAnchors() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
}
