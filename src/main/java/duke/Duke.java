package duke;

import design.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This represents the main entry point of the Duke program.
 */
public class Duke extends Application {
    /** The name of the bot. */
    private static final String NAME = "Nino!";

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private ScrollPane scrollPane;
    private VBox chatContainer;
    private TextField inputTextField;
    private Button sendButton;
    private Scene scene;

    private static UserInterface userInterface;

    /**
     * runs the Duke program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            userInterface = new UserInterface(new Storage(), new StoreList());

            System.out.println("Hello, my name is " + Duke.NAME);
            UserInterface.display("What can I do for you?");
            userInterface.start();
            userInterface.readCommandLine();
            userInterface.exit();

        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Starts the GUI.
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Duke");

        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        scrollPane = new ScrollPane();
        chatContainer = new VBox();
        scrollPane.setContent(chatContainer);

        inputTextField = new TextField();
        sendButton = new Button("SEND");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setPrefSize(385, 535);

        scrollPane.setPrefSize(385, 585);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1);
        scrollPane.setFitToHeight(true);

        chatContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        inputTextField.setPrefWidth(325);

        sendButton.setPrefWidth(55);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(inputTextField, 1.0);
        AnchorPane.setBottomAnchor(inputTextField, 1.0);

        mainLayout.getChildren().addAll(scrollPane, inputTextField, sendButton);
        scene = new Scene(mainLayout);

        sendButton.setOnMouseClicked((event -> {
            chatContainer.getChildren().add(getDialogLabel(inputTextField.getText()));
            inputTextField.clear();
        }));

        inputTextField.setOnAction(event -> {
            chatContainer.getChildren().add(getDialogLabel(inputTextField.getText()));
            inputTextField.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        inputTextField.setOnAction((event) -> {
            handleUserInput();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(inputTextField.getText());
        Label dukeText = new Label(getResponse(inputTextField.getText()));
        chatContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        inputTextField.clear();
    }

    private String getResponse(String input) {
        if (userInterface == null) {
            try {
                userInterface = new UserInterface(new Storage(), new StoreList());
            } catch (DukeException e) {
                return e.toString();
            }
        }
        return userInterface.parseLine(input);
    }
}
