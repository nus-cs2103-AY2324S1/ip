package hachi;

import hachi.exceptions.HachiException;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * Implements a graphical user interface using Java FX.
 */
public class JavaFx extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Hachi hachi;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/person.png"));
    private Image hachiImage = new Image(this.getClass().getResourceAsStream("/images/akita.png"));
    private final double DEFAULT_WIDTH = 400.0;
    private final double DEFAULT_HEIGHT = 600.0;

    @Override
    public void start(Stage stage) {
        // creating all the required variables
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        hachi = new Hachi();

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout); // Setting the scene to be our layout

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        stage.setTitle("Hachi");
        stage.setResizable(false);
        stage.setMinHeight(DEFAULT_HEIGHT);
        stage.setMinWidth(DEFAULT_WIDTH);

        setDimensions(mainLayout);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Sets the dimensions of the application window.
     */
    private void setDimensions(AnchorPane layout) {
        layout.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        double scrollPaneWidth = 385;
        double scrollPaneHeight = 535;
        scrollPane.setPrefSize(scrollPaneWidth, scrollPaneHeight);
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
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getHachiDialog(dukeText, hachiImage)
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        try {
            return hachi.getInputResponse(input);
        } catch (HachiException e) {
            return e.getMessage();
        }
    }
}
