package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
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
 * Creates the GUI
 */
public class Window {

    @FXML
    private static final ScrollPane scrollPane = new ScrollPane();
    @FXML
    private static final VBox dialogContainer = new VBox();
    @FXML
    private static final TextField userInput = new TextField();
    @FXML
    private static final Button sendButton = new Button("Send");
    private static Stage stage = new Stage();
    private static final AnchorPane mainLayout = new AnchorPane();
    private static final Scene scene = new Scene(mainLayout);
    private static final Image user = new Image(Window.class.getResourceAsStream("/images/DaUser.png"));
    private static final Image duke = new Image(Window.class.getResourceAsStream("/images/DaDuke.png"));
    private static Storage storage = null;
    private static Ui ui = null;

    public Window(Stage stage, Storage storage, Ui ui) {
        //scrollPane = new ScrollPane();
        //dialogContainer = new VBox();
        //userInput = new TextField();
        //sendButton = new Button("Send");
        //mainLayout = new AnchorPane();
        //mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        //scene = new Scene(mainLayout);
        //this.stage = stage;
        //this.storage = storage;
        //this.ui = ui;
    }

    public static void setParameters(Stage stage, Storage storage, Ui ui) {
        Window.stage = stage;
        Window.storage = storage;
        Window.ui = ui;
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
    }

    @FXML
    private static void handleUserInput() {
        Label userText = new Label(userInput.getText());
        String userInputText = userInput.getText();
        if (userInputText.equalsIgnoreCase("Bye")) {
            Platform.exit();
            storage.writeToStorage();
        }
        System.out.println(userText.getText());
        String responseText = Window.ui.startUi(userInputText);
        Label dukeText = new Label(responseText);

        ImageView userImageView = new ImageView(user);
        ImageView dukeImageView = new ImageView(duke);

        CircleClip clip = new CircleClip(40, 40, 40);
        CircleClip clip2 = new CircleClip(40, 40, 40);
        clip.clip(userImageView);
        clip2.clip(dukeImageView);

        DialogBox userDialog = DialogBox.getUserDialog(userText, userImageView, userInputText);
        DialogBox dukeDialog = DialogBox.getDukeDialog(dukeText, dukeImageView, responseText);

        dialogContainer.getChildren().addAll(
                userDialog, dukeDialog
        );
        userInput.clear();
    }

    /**
     * Initializes the GUI
     */
    public static void initializeWindow() {
        dialogContainer.setSpacing(20.0);
        scrollPane.setContent(dialogContainer);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Formats the GUI
     */
    public static void formatWindow() {
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
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Adds user input to the GUI
     */
    public static void addUserInput() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Prints out a welcome message
     */
    public static void welcomeMessage() {
        CircleClip clip = new CircleClip(40, 40, 40);
        ImageView dukeImageView = new ImageView(duke);
        clip.clip(dukeImageView);
        String welMessage = "Hello! I'm DukeBot\n" + "What can I do for you?\n";
        Label welcomeMessage = new Label(welMessage);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImageView, welMessage));
    }

    /**
     * Sends a custom message
     */
    public static void sendMessage(String message) {
        CircleClip clip = new CircleClip(40, 40, 40);
        ImageView dukeImageView = new ImageView(duke);
        clip.clip(dukeImageView);
        Label welcomeMessage = new Label(message);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImageView, message));
    }
    /**
     * Sends error message
     */
    public static void sendErrorMessage() {
        CircleClip clip = new CircleClip(40, 40, 40);
        ImageView dukeImageView = new ImageView(duke);
        clip.clip(dukeImageView);
        String welMessage = "Error! That is an invalid input! Try again.";
        Label welcomeMessage = new Label(welMessage);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImageView, welMessage));
    }

}
