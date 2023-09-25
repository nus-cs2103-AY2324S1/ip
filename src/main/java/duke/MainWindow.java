package duke;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The MainWindow class represents the main graphical user interface (GUI) window for the Duke chatbot application.
 * It handles the initialization of GUI components, formatting, event handling, and user input processing.
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

    private Duke duke = new Duke();
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/girl.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));

    /**
     * Sets the Duke instance for this MainWindow.
     *
     * @param d The Duke instance to set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Initializes the main window of the Duke application.
     *
     * @param stage The JavaFX Stage to which the GUI components will be added.
     */
    @FXML
    public void initialize(Stage stage) {
        setupComponents(stage);
        formatWindow(stage);
        formatLayout(stage);
        setEventHandlers();
        scrollToEndOnHeightChange();
    }


    private void setupComponents(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 550.0);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    private void formatWindow(Stage stage) {
        stage.setTitle("Philip");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        scrollPane.setPrefSize(400, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }


    private void formatLayout(Stage stage) {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Ui.printWelcomeMessage(), dukeImage));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.loadTasks(), dukeImage));
    }

    private void setEventHandlers() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void scrollToEndOnHeightChange() {
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles user input by processing it through the Duke chatbot and updating the GUI with user and Duke responses.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
