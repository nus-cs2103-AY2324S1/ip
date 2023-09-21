
package duck;

import duck.task.TaskList;
import duck.ui.DialogBox;
import duck.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
  * This class is command-line application responsible for managing tasks.
  */
public class Duck extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Ui ui = new Ui();
    private final TaskList taskList;
    private Parser parser = new Parser();

    private final String filePath = "data/duck.txt";
    private final Storage storage;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duckbot = new Image(this.getClass().getResourceAsStream("/images/duckbot.jpeg"));

    /**
     * Constructs a simple Duck Object..
     */
    public Duck() {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
    }

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        setElementProperties();
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        setElementActions();
    }

    /**
     * Sets properties of all elements in the GUI
     */
    private void setElementProperties() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setSpacing(10);
        dialogContainer.setPadding(new Insets(10));
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);
        scene = new Scene(mainLayout);
    }

    /**
     * Sets actions for all the elements in the GUI
     */
    private void setElementActions() {
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates a label with the specified text and configures it for word wrapping.
     * The created label is suitable for adding to the dialog container.
     *
     * @param text The text to be displayed on the label.
     * @return A label with the specified text and word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes: one for echoing user input and another for Duke's reply.
     * These dialog boxes are then appended to the dialog container, and user input is cleared.
     */
    private void handleUserInput() {
        if (userInput.getText().isBlank()) {
            return;
        }
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userInput.getText(), user),
            DialogBox.getDukeDialog(getResponse(userInput.getText()), duckbot)
        );
        userInput.clear();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    public String getResponse(String input) {
        return parser.parse(input, ui, taskList, storage).replace("\n", "\n\n");
    }
}
