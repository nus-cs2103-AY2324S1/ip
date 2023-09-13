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

public class Window {

    @FXML
    private final ScrollPane scrollPane;
    @FXML
    private final VBox dialogContainer;
    @FXML
    private final TextField userInput;
    @FXML
    private final Button sendButton;
    private final Scene scene;
    private final Stage stage;
    private final AnchorPane mainLayout;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private final Storage storage;
    private final Ui ui;

    public Window(Stage stage, Storage storage, Ui ui) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInput = new TextField();
        sendButton = new Button("Send");
        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        this.stage = stage;
        this.storage = storage;
        this.ui = ui;
    }

    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        String userInputText = userInput.getText();
        if (userInputText.equalsIgnoreCase("Bye")) {
            Platform.exit();
            storage.writeToStorage();
        }
        System.out.println(userText.getText());
        String responseText = this.ui.startUi(userInputText);
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

    public void initializeWindow() {
        dialogContainer.setSpacing(20.0);
        scrollPane.setContent(dialogContainer);
        stage.setScene(scene);
        stage.show();
    }

    public void formatWindow() {
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

    public void addUserInput() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    public void welcomeMessage() {
        CircleClip clip = new CircleClip(40, 40, 40);
        ImageView dukeImageView = new ImageView(duke);
        clip.clip(dukeImageView);
        String welMessage = "Hello! I'm DukeBot\n" + "What can I do for you?\n";
        Label welcomeMessage = new Label(welMessage);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImageView, welMessage));
    }

}
