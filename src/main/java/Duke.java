import duke.DialogBox;
import duke.Parser;
import duke.TaskMaster;
import duke.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
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
import javafx.util.Duration;

public class Duke extends Application {
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    private Scene scene;
    private AnchorPane mainLayout = new AnchorPane();

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
        ui = new Ui();
    }

    /**
     * Initializes the primary stage and sets up the user interface components for the chatbot.
     * This method constructs the main chat window layout and binds actions to user input fields
     * and buttons. It also sets up the initial properties for the main window and UI components.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        TaskMaster.initialStorage("Data");

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        // set the scrollPane to always be at the bottom
        dialogContainer.heightProperty().addListener((observable) -> {
            scrollPane.setVvalue(1.0);
        });

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        addDukeMessage(this.ui.sayhi());

        //p3.0
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //p3.1
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates a label with the given text and enables word wrap.
     * This label is typically used for displaying messages in the chat interface.
     *
     * @param text The message text to be displayed in the label.
     * @return A new label containing the specified text with word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Handles user input by echoing it and displaying Duke's response.
     * After a user sends a message, this method processes the input, displays the user's message
     * and Duke's reply in the chat interface. If the user's command is "bye", it will also initiate
     * an exit sequence after a short delay.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        DialogBox userDialog = DialogBox.getUserDialog(userText, new ImageView(user));
        DialogBox dukeDialog = DialogBox.getDukeDialog(dukeText, new ImageView(duke));

        //set the padding between each dialogBox
        VBox.setMargin(userDialog, new Insets(15));
        VBox.setMargin(dukeDialog, new Insets(15));

        dialogContainer.getChildren().addAll(userDialog, dukeDialog);

        if (userInput.getText().equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit()); // Close the app after the delay
            delay.play();
        }
        userInput.clear();
    }

    /**
     * Generates a response based on user input.
     * This method checks for specific commands such as "bye" and returns a respective response from Duke.
     * For other inputs, it processes the command using the TaskMaster and Parser classes and returns the
     * corresponding response.
     *
     * @param input The command inputted by the user.
     * @return A string response based on the user input.
     */
    private String getResponse(String input) {
        if (input.equals("bye")) {
            return this.ui.saybye();
        }
        TaskMaster.masterTasks(input);
        return Parser.getResponse();
    }

    /**
     * Adds a message from Duke to the chat interface.
     * This method creates a dialog box containing Duke's message and displays it in the dialog container.
     *
     * @param text The message from Duke to be displayed.
     */
    private void addDukeMessage(String text) {
        Label dukeText = new Label(text);
        DialogBox dukeDialog = DialogBox.getDukeDialog(dukeText, new ImageView(duke));
        dialogContainer.getChildren().add(dukeDialog);
    }
}
