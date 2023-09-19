package chatbot.alain;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

import chatbot.alain.commands.ByeCommand;
import chatbot.alain.commands.DeadlineCommand;
import chatbot.alain.commands.DeleteCommand;
import chatbot.alain.commands.EventCommand;
import chatbot.alain.commands.FindCommand;
import chatbot.alain.commands.ListCommand;
import chatbot.alain.commands.MarkCommand;
import chatbot.alain.commands.TodoCommand;
import chatbot.alain.commands.UnmarkCommand;
import chatbot.alain.tasks.Task;
import chatbot.alain.uis.GuiUi;
import chatbot.alain.uis.Ui;
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



/**
 * Represents the main class for the Alain chatbot.
 */
public class ChatbotAlain extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/image/User.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/image/Ai.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Ui ui;
    private Storage storageGui;
    private String filePath = "./data/notebook.txt";

    private TaskList tasksGui;
    /**
     * Constructs a ChatbotAlain object.
     */
    public ChatbotAlain() {
        storageGui = new Storage(filePath);
        try {
            tasksGui = storageGui.loadTasksFromFile();
        } catch (IOException | AlainException e) {
            Ui.showError(e.getMessage());
        }
    }

    /**
     * Converts a string representing time to a different format.
     *
     * @param inputTime The input time string.
     * @return The transformed time string.
     * @throws AlainException If an exception occurs during the transformation.
     */
    public static String stringToTimeString(Task task, String inputTime, boolean isBy) {
        try {
            DateTimeFormatter inputPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (Pattern.matches("\\d+-\\d+-\\d+", inputTime)) {
                LocalDate date = LocalDate.from(LocalDate.parse(inputTime, inputPattern));
                task.setTime(date, isBy);
                DateTimeFormatter outputPattern = DateTimeFormatter.ofPattern("MMMM dd yyyy", Locale.ENGLISH);
                String transformedTime = date.format(outputPattern);
                return transformedTime;
            } else if (Pattern.matches("\\d+-\\d+-\\d+ .+", inputTime)) {
                String[] dateAndTime = inputTime.split(" ");
                String addMsg = "";
                for (int i = 1; i < dateAndTime.length; i++) {
                    addMsg += dateAndTime[i];
                }
                LocalDate date = LocalDate.parse(dateAndTime[0], inputPattern);
                task.setTime(date, isBy);
                DateTimeFormatter outputPattern = DateTimeFormatter.ofPattern("MMMM dd yyyy", Locale.ENGLISH);
                String transformedTime = date.format(outputPattern);
                assert transformedTime != null : "Transformed time should not be null";
                return transformedTime + " " + addMsg;
            } else if (inputTime.length() == 0) {
                throw new AlainException(" OOPS!!! The description of a alain.Task cannot be empty.");
            } else {
                return inputTime;
            }
        } catch (AlainException e) {
            Ui.showError(e.getMessage());
            return null;
        }
    }

    /**
     * Processes the given command text, executing the appropriate action on the provided TaskList.
     *
     * @param list The TaskList on which the commands operate.
     * @param text The text command to be processed.
     * @return <code>true</code> if the application should continue processing commands;
     *      <code>false</code> if the "bye" command was received.
     * @throws AlainException If any user input error occurs, like invalid command or task details.
     * @throws IOException If there's any error related to file operations when saving tasks or errors.
     */
    public static String proccessCommands(TaskList list, String text) {
        try {
            boolean isMatchMarkCommand = Pattern.matches("mark \\d+", text);
            boolean isMatchUnmarkCommand = Pattern.matches("unmark \\d+", text);
            boolean isDeadlineCommand = Pattern.matches("deadline.+", text) || text.contains("deadline");
            boolean isToDoCommand = Pattern.matches("todo.+", text) || text.contains("todo");
            boolean isEventCommand = Pattern.matches("event.+", text) || text.contains("event");
            boolean isDeleteCommand = Pattern.matches("delete.+", text) || text.contains("delete");;
            boolean isFindCommand = Pattern.matches("find.+", text) || text.contains("find");;
            boolean isByeCommand = text.equals("bye");
            boolean isListCommand = text.equals("list");
            if (isFindCommand) {
                return new FindCommand(list, text).processCommand();
            }
            if (isDeleteCommand) {
                return new DeleteCommand(list, text).processCommand();
            }
            if (isToDoCommand) {
                System.out.println(text);
                return new TodoCommand(list, text).processCommand();
            }
            if (isDeadlineCommand) {
                System.out.println(text);
                return new DeadlineCommand(list, text).processCommand();
            }
            if (isEventCommand) {
                System.out.println(text);
                return new EventCommand(list, text).processCommand();
            }
            if (isByeCommand) {
                return new ByeCommand(list, text).processCommand();
            }
            if (isMatchMarkCommand) {
                return new MarkCommand(list, text).processCommand();
            }
            if (isMatchUnmarkCommand) {
                return new UnmarkCommand(list, text).processCommand();
            }
            if (isListCommand) {
                return new ListCommand(list, text).processCommand();
            }
            throw new AlainException("I'm sorry, but I don't know what that means :-(");
        } catch (AlainException e) {
            Ui.showError(e.getMessage());
            return GuiUi.showError(e.getMessage());
        }
    }
    /**
     * Initializes the primary stage for the application.
     *
     * This method sets up the main layout comprising a scroll pane for dialogs,
     * a text field for user input, and a send button to trigger actions.
     * The layout is then added to the primary stage and displayed.
     *
     * @param stage The primary stage of the application where all UI components are placed.
     */

    public void start(Stage stage) {
        // Step 1: Set up the main layout
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Step 2: Configure Stage and Layout settings
        stage.setTitle("Alain");
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

        // Customize the Send button and Input Field
        sendButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        userInput.setStyle("-fx-background-color: #EDEDED; -fx-border-radius: 15; -fx-padding: 5;");

        // Assuming you have an Image for the user as well.
        ImageView dukeImageView = new ImageView(duke);
        ImageView userImageView = new ImageView(user);

        final double imageSize = 50.0;
        dukeImageView.setFitWidth(imageSize);
        dukeImageView.setFitHeight(imageSize);
        userImageView.setFitWidth(imageSize);
        userImageView.setFitHeight(imageSize);


        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(new Label(GuiUi.showWelcome()), dukeImageView));

        // Step 3: Add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Alain's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws IOException {
        Label userText = new Label("Conan: \n\n" + userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) throws IOException {
        String ai = "Ai: \n";
        String text = input;
        String chatOutput = proccessCommands(tasksGui, text);
        if (chatOutput != null) {
            return ai + chatOutput;
        } else {
            try {
                storageGui.saveTasksToFile(tasksGui, filePath, false, null);
            } catch (IOException e) {
                storageGui.saveTasksToFile(null, filePath, true, e.getMessage());
            }
            return ai + GuiUi.showList(tasksGui) + GuiUi.showGoodbye();
        }
    }
}
