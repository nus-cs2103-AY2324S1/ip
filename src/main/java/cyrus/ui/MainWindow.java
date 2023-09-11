package cyrus.ui;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import cyrus.Cyrus;
import cyrus.commands.CommandError;
import cyrus.commands.CommandType;
import cyrus.parser.ParseInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Entry point for Cyrus Gui.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png"))
    );
    private final Image botImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png"))
    );

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Cyrus cyrus;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCyrus(Cyrus d) {
        cyrus = d;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        ParseInfo parseInfo = cyrus.parseInput(userText);
        if (parseInfo.equals(ParseInfo.EMPTY)) {
            putConversation(userText, "Missing input!", true);
            return;
        }

        String cyrusResponse = "";
        boolean isError = false;
        try {
            cyrusResponse = cyrus.dispatchAndExecute(parseInfo);
        } catch (CommandError e) {
            cyrusResponse = e.getMessage();
            isError = true;
        } finally {
            putConversation(userText, cyrusResponse, isError);
        }
        if (parseInfo.getCommandType() == CommandType.BYE) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 1000);
        }
    }

    private void putConversation(String userText, String cyrusText, boolean isError) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDialog(userText, "User", userImage),
                DialogBox.getDialog(cyrusText, "Cyrus", botImage, isError ? Color.RED : Color.BLACK)
        );
        userInput.clear();
    }
}
