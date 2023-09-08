package jeeves.ui;

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
import jeeves.Jeeves;


/**
 * Ui is responsible for all visual interactions with the user.
 */
public class Ui extends Application {

    private static final String MESSAGE_WELCOME = "Greetings, Master. Jeeves at your service\n"
            + "How may I serve you today?\n";

    private final Image jeeves = new Image(this.getClass().getResourceAsStream("/images/jeeves.jpg"));
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private AnchorPane mainLayout;
    private Scene scene;

    /**
     * Constructor for a Ui object.
     * Currently, requires no arguments to initialize.
     */
    public Ui() {

    }

    @Override
    public void start(Stage stage) {
        setupUi();
        configureUi(stage);
        configureHandlers();

        // Displaying the pre-designed scene and welcome message
        dialogContainer.getChildren().addAll(
                DialogBox.getJeevesDialog(new Label(MESSAGE_WELCOME), new ImageView(jeeves)));
        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    private void setupUi() {
        // Setting up the default chat window
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
    }

    private void configureUi(Stage stage) {
        // Custom formatting for the window
        stage.setTitle("Jeeves Chatbot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385.0, 535.0);
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

        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
    }

    private void configureHandlers() {
        // Handlers to deal with input and other events
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label jeevesText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getJeevesDialog(jeevesText, new ImageView(jeeves))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return Jeeves.processInput(input);
    }

}
