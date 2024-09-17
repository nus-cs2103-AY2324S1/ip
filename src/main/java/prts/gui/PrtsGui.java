package prts.gui;

import java.util.Objects;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import prts.Prts;

/**
 * A class that manages the GUI-related elements of PRTS. Includes methods to set up the scene and draw
 * new objects within it.
 */
public class PrtsGui extends Application {

    private final Prts prts = new Prts();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private final Image doc = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/doc.jpg")));
    private final Image prtsImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/prts.jpg")));

    /**
     * Sets up the main layout, comprising a scroll window and a text input field.
     * @return The AnchorPane object containing the required nodes.
     */
    private AnchorPane initializeScene() {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        return mainLayout;

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing PRTS' reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), doc),
                DialogBox.getPrtsDialog(prts.getResponse(userInput.getText()), prtsImage)
        );
        userInput.clear();
    }

    /**
     * Sets the configurations of the given stage to be appropriate for display.
     * @param stage The stage used for PRTS.
     */
    private void stageSetup(Stage stage) {
        stage.setTitle("Prts");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    /**
     * Configures the main layout of the scene (anchor pane, dialog container, scroll pane, user input).
     * @param mainLayout The anchor pane holding the scene.
     */
    private void layoutSetup(AnchorPane mainLayout) {
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
     * Initializes the scene and shows it on the configured stage.
     * @param primaryStage the primary stage for this application, onto which
     *        the application scene can be set.
     *        Applications may create other stages, if needed, but they will not be
     *        primary stages.
     */
    @Override
    public void start(Stage primaryStage) {

        PrtsGui prtsGui = new PrtsGui();
        AnchorPane mainLayout = prtsGui.initializeScene();
        Scene scene = new Scene(mainLayout);

        primaryStage.setScene(scene);
        primaryStage.show();
        prtsGui.stageSetup(primaryStage);
        prtsGui.layoutSetup(mainLayout);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

}
