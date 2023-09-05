package dot.controllers;

import java.io.IOException;

import dot.Dot;
import dot.commands.Command;
import dot.errors.DotException;
import dot.parser.Parser;
import dot.ui.Ui;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Controller for MainWindow.
 * Adapted from JavaFX tutorial.
 */
public class MainWindow extends AnchorPane {

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image dotImage = new Image(this.getClass().getResourceAsStream("/images/Dot.png"));
    private final Dot dot;
    private final Runnable handleExitApp;
    @FXML
    private TextField inputTextField;
    @FXML
    private Button submitButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox messageContainer;
    @FXML
    private Rectangle errorRectangle;
    @FXML
    private Label errorMessage;

    /**
     * Constructor for MainWindow.
     *
     * @param d       This is the dot instance.
     * @param handler This is the handler to call when we detect attempt to exit app.
     */
    public MainWindow(Dot d, Runnable handler) {
        dot = d;
        handleExitApp = handler;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes additional properties.
     */
    @FXML
    public void initialize() {
        // Note that we set appropriate nodes to be transparent, and set
        // MainWindow to have the image background. For ScrollPane, it is
        // set transparent only on show. Submit button is already made
        // translucent through the stylesheet.
        this.getStyleClass().add("image-background");

        inputTextField.getStyleClass().add("transculent-background");
        scrollPane.getStyleClass().add("transparent-background");

        // Set the scroll to the bottom of the ScrollPane
        scrollPane.vvalueProperty().bind(messageContainer.heightProperty());
        // Grow and shrink ScrollPane as per visibility of ErrorRectangle
        scrollPane.prefHeightProperty().bind(
                // Use Bindings, BooleanProperty <; ObservableBooleanValue
                Bindings.when(errorRectangle.visibleProperty())
                        .then(475)
                        .otherwise(555)
        );
        handleDotWelcome();
    }

    private Background getMessageBackground(Paint color,
                                            double cornerRadii,
                                            Insets insets) {
        return new Background(new BackgroundFill(
                color, new CornerRadii(cornerRadii), insets));
    }

    private void handleDotWelcome() {
        messageContainer.getChildren().add(
                new MessageBox(Pos.TOP_LEFT,
                        getMessageBackground(
                                Color.rgb(135, 206, 250, 0.9),
                                10.0,
                                new Insets(0)),
                        dotImage, Ui.getWelcomeMessage()));
    }

    private void handleDotOutput(String output) {
        messageContainer.getChildren().add(
                new MessageBox(Pos.TOP_LEFT,
                        getMessageBackground(
                                Color.rgb(135, 206, 250, 0.9),
                                10.0,
                                new Insets(0)),
                        dotImage, output));
    }

    private void handleErrorMessage(String output) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    errorMessage.setText(output);
                    errorMessage.setStyle("-fx-text-fill: white");
                    errorRectangle.setVisible(true);
                    errorMessage.setVisible(true);
                }),
                new KeyFrame(Duration.seconds(5.0), event -> {
                    errorRectangle.setVisible(false);
                    errorMessage.setVisible(false);
                })
        );
        timeline.play();
    }

    /**
     * Handles the user input by attempting to parse
     * it into a Command. If Command is valid, it
     * will be executed, else a DotException will be
     * handled.
     */
    @FXML
    private void handleUserInput() {
        String userMessage = inputTextField.getText();

        if (userMessage.isEmpty()) {
            handleErrorMessage("Input is empty o.o Enter something!");
            return;
        }

        messageContainer.getChildren().addAll(
                new MessageBox(Pos.TOP_RIGHT,
                        getMessageBackground(
                                Color.rgb(152, 251, 152, 0.9),
                                10.0,
                                new Insets(0)),
                        userMessage, userImage)
        );

        try {
            Command cmd = Parser.parseInputToCommand(userMessage,
                    dot.getDotTaskList());
            if (cmd.isTerminateCommand()) {
                handleExitApp.run();
            } else {
                cmd.execute(this::handleDotOutput);
            }
        } catch (DotException e) {
            // We can call handleErrorMessage here alternatively
            e.handleError(this::handleErrorMessage);
        }
        inputTextField.clear();
    }
}
