package cyrus.ui.components;

import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import cyrus.Cyrus;
import cyrus.commands.CommandError;
import cyrus.commands.CommandType;
import cyrus.parser.ParseInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Main chat window component for Cyrus.
 */
public class MainWindow extends AnchorPane {
    private static final String USER_TITLE = "User";
    private static final String BOT_TITLE = "Cyrus";
    private static final Color BOT_HELP_TEXT_COLOR = Color.rgb(184, 110, 240);
    private static final Color BOT_ERROR_TEXT_COLOR = Color.rgb(245, 66, 111);

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

    /**
     * Initializes MainWindow component.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDialog(
                        "Welcome to Cyrus!\nYou can use list, todo, deadline, event to get started!",
                        BOT_TITLE,
                        botImage,
                        BOT_HELP_TEXT_COLOR
                )
        );
    }

    /**
     * Sets window's {@code Cyrus} instance.
     *
     * @param cyrus {@code Cyrus} instance.
     */
    public void setCyrus(Cyrus cyrus) {
        this.cyrus = cyrus;
    }

    /**
     * Handles user input into {@code userInput} and displays the user's request and bot's response.
     *
     * <p>Special case when command is {@code statistics}, that will create a window above the chat to display the
     * statistics dashboard.</p>
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
            handleConversation(userText, cyrusResponse, isError, parseInfo);
        }
    }

    /**
     * Handles the UI transitions given user and bot texts.
     *
     * @param userText    user's request.
     * @param botResponse bot's response.
     * @param isError     if bot's response should be flagged as an error.
     * @param parseInfo   parsed details of command.
     */
    private void handleConversation(String userText, String botResponse, boolean isError, ParseInfo parseInfo) {
        putConversation(userText, botResponse, isError);

        if (parseInfo.getCommandType().equals(CommandType.VIEW_STATISTICS)) {
            // Open the statistics dashboard
            openStatisticsDashboard();
        }

        // Special handler because cannot close the window directly
        if (parseInfo.getCommandType().equals(CommandType.BYE)) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 1000);
        }
    }

    /**
     * Inserts the user request and bot response into the dialog window.
     *
     * @param userText  user's request.
     * @param cyrusText bot's response.
     * @param isError   if bot's response should be flagged as an error.
     */
    private void putConversation(String userText, String cyrusText, boolean isError) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDialog(userText, USER_TITLE, userImage),
                DialogBox.getDialog(cyrusText, BOT_TITLE, botImage, isError ? BOT_ERROR_TEXT_COLOR : Color.BLACK)
        );
        userInput.clear();
    }

    /**
     * Launches the statistics dashboard.
     *
     * <p>Launching from MainWindow since it is attached to the MainWindow.</p>
     */
    private void openStatisticsDashboard() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/StatisticsDashboard.fxml"));
            fxmlLoader.setController(
                    new StatisticsDashboard(
                            cyrus.getTaskList().getTaskDistribution(),
                            cyrus.getTaskList().getWeeklyTaskCompletionRate()
                    )
            );
            VBox window = fxmlLoader.load();
            Scene scene = new Scene(window);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Statistics Dashboard");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
