package duke.gui;

import duke.Duke;
import duke.util.Response;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final int EXIT_DELAY = 1;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;
    private Image userImage = new Image(this.getClass()
                                            .getResourceAsStream("/images/User.png"));
    private Image botImage = new Image(this.getClass()
                                           .getResourceAsStream("/images/ChatBot404.png"));
    private Image botWaringImage = new Image(this.getClass()
                                                 .getResourceAsStream("/images/botWarning.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        assert d != null : "Duke should not be null";

        duke = d;
        Response initalBotRespond = duke.initialize();
        Image initialBotImage = initalBotRespond.isErrorResponse() ? botWaringImage : botImage;

        BotDialogBox initializeDialog = BotDialogBox.getBotDialog(initalBotRespond, initialBotImage);
        dialogContainer.getChildren().add(initializeDialog);
        if (initalBotRespond.isErrorResponse()) {
            BotDialogBox greetDialog = BotDialogBox.getBotDialog(duke.initialize(), botImage);
            dialogContainer.getChildren().add(greetDialog);
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and another
     * containing chatBots reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        Response response = duke.getResponse(input);
        Image image = response.isErrorResponse() ? botWaringImage : botImage;
        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input, userImage),
                BotDialogBox.getBotDialog(response, image)
        );
        userInput.clear();

        if (response.isExitResponse()) {
            PauseTransition pause = new PauseTransition(Duration.seconds(EXIT_DELAY));
            pause.setOnFinished(event -> {
                Platform.exit();
                System.exit(0);
            });
            pause.play();
        }
    }
}
