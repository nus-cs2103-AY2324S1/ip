package catbot.io;

import java.util.Objects;
import java.util.function.Consumer;

import catbot.bot.CommandArgumentStruct;
import catbot.internal.NamedParameterMap;
import catbot.internal.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class CatbotJavaFxController extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private final Parser parser = Parser.with(null);

    private Consumer<CommandArgumentStruct> commandConsumer;
    private StringBuilder queuedAssistantOutput = new StringBuilder();

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaDuke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    void attachConsumerForParsedCommands(Consumer<CommandArgumentStruct> consumer) {
        this.commandConsumer = consumer;
    }

    @FXML
    private void handleUserInput() {
        if (commandConsumer == null) {
            return;
        }

        String input = getUserInput();
        addUserDialog(input);
        CommandArgumentStruct command = parseStringToStruct(input);
        commandConsumer.accept(command);
        sendAssistantDialogue();
    }

    private String getUserInput() {
        String input = userInput.getText();
        userInput.clear();
        return input;
    }

    private CommandArgumentStruct parseStringToStruct(String commandString) {
        NamedParameterMap namedParameterMap = parser.parse(commandString);
        assert namedParameterMap.keySet().size() == 1;
        for (String command : namedParameterMap.keySet()) {
            return new CommandArgumentStruct(command, namedParameterMap.get(command));
        }
        return null;
    }

    void addUserDialog(String text) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(text, userImage));
    }

    void queueAssistantDialogue(String text) {
        queuedAssistantOutput.append("\n").append(text);
    }

    void sendAssistantDialogue() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(queuedAssistantOutput.toString(), dukeImage));
        queuedAssistantOutput = new StringBuilder();
    }
}
