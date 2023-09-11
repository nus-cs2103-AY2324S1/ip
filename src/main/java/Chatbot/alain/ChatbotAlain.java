package chatbot.alain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    private Storage storage;
    private TaskList tasks;
    private TaskList listGui = new TaskList();

    /**
     * Default Constructor for ChatbotAlain
     */
    public ChatbotAlain() {
    }
    /**
     * Constructs a ChatbotAlain object.
     *
     * @param filePath The file path for loading tasks.
     */
    public ChatbotAlain(String filePath) throws AlainException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            if (!Files.exists(Paths.get(filePath))) {
                throw new AlainException("Error Occurs when loading tasks from file");
            } else {
                tasks = storage.loadTasksFromFile();
            }
        } catch (IOException | AlainException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Converts a string representing time to a different format.
     *
     * @param inputTime The input time string.
     * @return The transformed time string.
     * @throws AlainException If an exception occurs during the transformation.
     */
    public static String stringToTimeString(String inputTime) throws AlainException {
        DateTimeFormatter inputPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (Pattern.matches("\\d+-\\d+-\\d+", inputTime)) {
            LocalDate date = LocalDate.from(LocalDate.parse(inputTime, inputPattern));
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

            DateTimeFormatter outputPattern = DateTimeFormatter.ofPattern("MMMM dd yyyy", Locale.ENGLISH);
            String transformedTime = date.format(outputPattern);
            assert transformedTime != null : "Transformed time should not be null";
            return transformedTime + " " + addMsg;
        } else if (inputTime.length() == 0) {
            throw new AlainException(" OOPS!!! The description of a alain.Task cannot be empty.");
        } else {
            return inputTime;
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
    public static String proccessCommands(TaskList list, String text, Storage storage) {
        try {
            boolean isMatchMarkCommand = Pattern.matches("mark \\d+", text);
            boolean isMatchUnmarkCommand = Pattern.matches("unmark \\d+", text);
            boolean isDeadlineCommand = Pattern.matches("deadline .+", text);
            boolean isToDoCommand = Pattern.matches("todo .+", text);
            boolean isEventCommand = Pattern.matches("event .+", text);
            boolean isDeleteCommand = Pattern.matches("delete .+", text);
            boolean isFindCommand = Pattern.matches("find .+", text);
            if (isFindCommand) {
                String keyWord = text.substring(4);
                TaskList tmpList = new TaskList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.getTask(i).descriptionContain(keyWord)) {
                        tmpList.addTask(list.getTask(i));
                    }
                }
                Ui.showListContainingKeyword(tmpList);
                return GuiUi.showListContainingKeyword(tmpList);
            }
            if (isDeleteCommand) {
                String numericPart = text.substring(7);
                int pos = Integer.parseInt(numericPart) - 1;
                if (pos >= 0 && pos < list.size()) {
                    Task removedTask = list.removeTask(pos);
                    Assertions.assertDelete(list, removedTask);
                    Ui.showRemoveTask(removedTask, list);
                    return GuiUi.showRemoveTask(removedTask, list);
                } else {
                    throw new AlainException("Invalid task index.");
                }
            }
            if (isToDoCommand) {
                String mission = text.substring(4);
                if (mission.length() == 0) {
                    throw new AlainException("The description of a Todo cannot be empty.");
                }
                ToDos newTodo = new ToDos(mission);
                list.addTask(newTodo);
                Assertions.assertNewTodo(list, newTodo);
                Ui.showAddTask(list.getTask(list.size() - 1), list);
                return GuiUi.showAddTask(list.getTask(list.size() - 1), list);
            }
            if (isDeadlineCommand) {
                String mission = text.substring(8);
                if (mission.length() == 0) {
                    throw new AlainException("The description of a Deadline cannot be empty.");
                }
                String[] parts = mission.split("/by ");
                if (parts.length != 2) {
                    throw new AlainException("The description of a Deadline is invalid");
                }
                Deadlines newDeadline = new Deadlines(parts[0], stringToTimeString(parts[1]));
                list.addTask(newDeadline);
                Assertions.assertNewDeadline(list, newDeadline);
                Ui.showAddTask(list.getTask(list.size() - 1), list);
                return GuiUi.showAddTask(list.getTask(list.size() - 1), list);
            }
            if (isEventCommand) {
                String mission = text.substring(5);
                if (mission.length() == 0) {
                    throw new AlainException("The description of a Event cannot be empty.");
                }
                String[] parts = mission.split("/");
                if (parts.length != 3) {
                    throw new AlainException("The description of a Event is invalid");
                }
                Events newEvent = new Events(parts[0],
                        stringToTimeString(parts[1].substring(5)), stringToTimeString(parts[2].substring(3)));
                list.addTask(newEvent);
                Assertions.assertNewEvent(list, newEvent);
                Ui.showAddTask(list.getTask(list.size() - 1), list);
                return GuiUi.showAddTask(list.getTask(list.size() - 1), list);
            }
            if (text.equals("bye")) {
                if (storage != null) {
                    storage.sayBye();
                }
                return null;
            } else if (isMatchMarkCommand) {
                String numericPart = text.substring(5);
                list.getTask(Integer.parseInt(numericPart) - 1).markAsDone();
                Ui.showMarkTask(numericPart, list);
                return GuiUi.showMarkTask(numericPart, list);
            } else if (isMatchUnmarkCommand) {
                String numericPart = text.substring(7);
                list.getTask(Integer.parseInt(numericPart) - 1).markAsUndone();
                Ui.showUnmarkTask(numericPart, list);
                return GuiUi.showUnmarkTask(numericPart, list);
            } else if (text.equals("list")) {
                Ui.showList(list);
                return GuiUi.showList(list);
            }
            throw new AlainException("I'm sorry, but I don't know what that means :-(");
        } catch (AlainException e) {
            Ui.showError(e.getMessage());
            return GuiUi.showError(e.getMessage());
        }
    }

    /**
     * Runs the Alain chatbot.
     *
     * @throws AlainException If an exception occurs during chatbot execution.
     * @throws IOException If an I/O error occurs during chatbot execution.
     */
    public void run() throws AlainException, IOException {
        if (this.storage.isBye()) {
            return;
        }
        TaskList list = null;
        if (this.tasks == null) {
            list = new TaskList();
        } else {
            list = this.tasks;
        }
        Scanner s = new Scanner(System.in);
        while (true) {
            String text = new String();
            text = s.nextLine();
            if (proccessCommands(list, text, storage) == null) {
                break;
            }
        }
        s.close();
        try {
            Ui.showList(list);
            storage.saveTasksToFile(list, "list.txt", false, null);
        } catch (IOException e) {
            Ui.showError("Error saving tasks to file");
            storage.saveTasksToFile(null, "list.txt", true, e.getMessage());
        } finally {
            Ui.showGoodbye();
        }
    }

    /**
     * Main method to start the Alain chatbot.
     *
     * @param args Command-line arguments (not used).
     * @throws AlainException If an exception occurs during chatbot execution.
     * @throws IOException If an I/O error occurs during chatbot execution.
     */
    public static void main(String[] args) throws AlainException, IOException {
        //System.out.println("hi");
        new ChatbotAlain("tasks.txt").run();
    }

    /**
     * Initializes the primary stage for the application.
     *
     * <p>This method sets up the main layout comprising a scroll pane for dialogs,
     * a text field for user input, and a send button to trigger actions.
     * The layout is then added to the primary stage and displayed.</p>
     *
     * @param stage The primary stage of the application where all UI components are placed.
     */

    public void start(Stage stage) {
        // Step 1 code here
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
        //Step 2 code here
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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren()
                .addAll(DialogBox.getDukeDialog(new Label(GuiUi.showWelcome()), new ImageView(duke)));
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Alain's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
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
    private String getResponse(String input) {
        String ai = "Ai: \n";
        String text = input;
        String chatOutput = proccessCommands(listGui, text, null);
        if (chatOutput != null) {
            return chatOutput;
        } else {
            return GuiUi.showList(listGui) + GuiUi.showGoodbye();
        }
    }
}
