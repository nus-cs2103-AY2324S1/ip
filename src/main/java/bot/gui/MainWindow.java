package bot.gui;

import bot.bot.Bot;
import bot.command.Command;
import bot.command.TerminateCommand;

import bot.exception.DateTimeParseBotException;
import bot.exception.FileErrorBotException;
import bot.exception.IllegalExpressionBotException;
import bot.exception.IncompleteBotException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
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

    private Bot bot;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    /**
     * Initializes the controller and binds the vertical scroll position of the scroll pane
     * to the height property of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the bot instance and initializes the dialog with the bot's greeting message.
     *
     * @param d The bot instance to be set.
     */
    public void setBot(Bot d) {
        bot = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getBotDialog(bot.getGreeting(), botImage)
        );
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command command = null;
        String response;
        try {
            command = bot.getCommand(input);
            response = command.execute();
        } catch (DateTimeParseBotException |
        IllegalExpressionBotException | IncompleteBotException |
                FileErrorBotException e) {
            response = e.toString();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        assert response != null : "response should not be null";
        if (command instanceof TerminateCommand) {
            Platform.exit();
        }
        userInput.clear();
    }
}