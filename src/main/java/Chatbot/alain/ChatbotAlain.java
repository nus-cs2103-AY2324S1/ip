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
    private GUI_Ui guiUi;
    private Storage storage;
    private TaskList tasks;

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
        if (Pattern.matches("\\d+-\\d+-\\d+", inputTime)) {
            DateTimeFormatter inputPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.from(LocalDate.parse(inputTime, inputPattern));
            DateTimeFormatter outputPattern = DateTimeFormatter.ofPattern("MMMM dd yyyy", Locale.ENGLISH);
            String transformedTime = date.format(outputPattern);
            return transformedTime.toString();
        } else if (Pattern.matches("\\d+-\\d+-\\d+ .+", inputTime)) {
            String[] dateAndTime = inputTime.split(" ");
            String addMsg = "";
            for (int i = 1; i < dateAndTime.length; i++) {
                addMsg += dateAndTime[i];
            }
            DateTimeFormatter inputPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateAndTime[0], inputPattern);

            DateTimeFormatter outputPattern = DateTimeFormatter.ofPattern("MMMM dd yyyy", Locale.ENGLISH);
            String transformedTime = date.format(outputPattern);
            return transformedTime.toString() + " " + addMsg;
        } else if (inputTime.length() == 0) {
            throw new AlainException(" OOPS!!! The description of a alain.Task cannot be empty.");
        } else {
            return inputTime;
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
            try {
                String text = new String();
                text = s.nextLine();
                boolean isMatchMark = Pattern.matches("mark \\d+", text);
                boolean isMatchUnmark = Pattern.matches("unmark \\d+", text);
                boolean isDeadline = Pattern.matches("deadline .+", text);
                boolean isToDo = Pattern.matches("todo .+", text);
                boolean isEvent = Pattern.matches("event .+", text);
                boolean isDelete = Pattern.matches("delete .+", text);
                boolean isFind = Pattern.matches("find .+", text);
                if (isFind) {
                    String keyWord = text.substring(4);
                    TaskList tmpList = new TaskList();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.getTask(i).descriptionContain(keyWord)) {
                            tmpList.addTask(list.getTask(i));
                        }
                    }
                    ui.showListContainingKeyword(tmpList);
                    continue;
                }
                if (isDelete) {
                    String numericPart = text.substring(7);
                    int pos = Integer.parseInt(numericPart) - 1;
                    if (pos >= 0 && pos < list.size()) {
                        Task removedTask = list.removeTask(pos);
                        ui.showRemoveTask(removedTask, list);
                    } else {
                        throw new AlainException("Invalid task index.");
                    }
                    continue;
                }
                if (isToDo) {
                    String mission = text.substring(4);
                    if (mission.length() == 0) {
                        throw new AlainException("The description of a Todo cannot be empty.");
                    }
                    list.addTask(new ToDos(mission));
                    ui.showAddTask(list.getTask(list.size() - 1), list);
                    continue;
                }
                if (isDeadline) {
                    String mission = text.substring(8);
                    if (mission.length() == 0) {
                        throw new AlainException("The description of a Deadline cannot be empty.");
                    }
                    String[] parts = mission.split("/by ");
                    if (parts.length != 2) {
                        throw new AlainException("The description of a Deadline is invalid");
                    }
                    list.addTask(new Deadlines(parts[0], stringToTimeString(parts[1])));
                    ui.showAddTask(list.getTask(list.size() - 1), list);
                    continue;
                }
                if (isEvent) {
                    String mission = text.substring(5);
                    if (mission.length() == 0) {
                        throw new AlainException("The description of a Event cannot be empty.");
                    }
                    String[] parts = mission.split("/");
                    if (parts.length != 3) {
                        throw new AlainException("The description of a Event is invalid");
                    }
                    list.addTask(new Events(parts[0],
                            stringToTimeString(parts[1].substring(5)), stringToTimeString(parts[2].substring(3))));
                    ui.showAddTask(list.getTask(list.size() - 1), list);
                    continue;
                }
                if (text.equals("bye")) {
                    break;
                } else if (isMatchMark) {
                    String numericPart = text.substring(5);
                    list.getTask(Integer.parseInt(numericPart) - 1).markAsDone();
                    ui.showMarkTask(numericPart, list);
                    continue;
                } else if (isMatchUnmark) {
                    String numericPart = text.substring(7);
                    list.getTask(Integer.parseInt(numericPart) - 1).markAsUndone();
                    ui.showUnmarkTask(numericPart, list);
                    continue;
                } else if (text.equals("list")) {
                    ui.showList(list);
                    continue;
                }
                throw new AlainException("I'm sorry, but I don't know what that means :-(");
            } catch (AlainException e) {
                ui.showError(e.getMessage());
                storage.saveTasksToFile(null, "list.txt", true, e.getMessage());
            }
        }
        s.close();
        try {
            ui.showList(list);
            storage.saveTasksToFile(list, "list.txt", false, null);
        } catch (IOException e) {
            ui.showError("Error saving tasks to file");
        } finally {
            ui.showGoodbye();
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
    @Override
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
    private TaskList listGui = new TaskList();
    private String getResponse(String input) {
        guiUi = new GUI_Ui();
        String ai = "Ai: \n";
        try {
            String text = new String();
            text = input;
            boolean isMatchMark = Pattern.matches("mark \\d+", text);
            boolean isMatchUnmark = Pattern.matches("unmark \\d+", text);
            boolean isDeadline = Pattern.matches("deadline .+", text);
            boolean isToDo = Pattern.matches("todo .+", text);
            boolean isEvent = Pattern.matches("event .+", text);
            boolean isDelete = Pattern.matches("delete .+", text);
            boolean isFind = Pattern.matches("find .+", text);
            if (isFind) {
                String keyWord = text.substring(4);
                TaskList tmpList = new TaskList();
                for (int i = 0; i < listGui.size(); i++) {
                    if (listGui.getTask(i).descriptionContain(keyWord)) {
                        tmpList.addTask(listGui.getTask(i));
                    }
                }
                return ai + guiUi.showListContainingKeyword(tmpList);
            }
            if (isDelete) {
                String numericPart = text.substring(7);
                int pos = Integer.parseInt(numericPart) - 1;
                if (pos >= 0 && pos < listGui.size()) {
                    Task removedTask = listGui.removeTask(pos);
                    return ai + guiUi.showRemoveTask(removedTask, listGui);
                } else {
                    throw new AlainException("Invalid task index.");
                }
            }
            if (isToDo) {
                String mission = text.substring(4);
                if (mission.length() == 0) {
                    throw new AlainException("The description of a Todo cannot be empty.");
                }
                listGui.addTask(new ToDos(mission));
                return ai + guiUi.showAddTask(listGui.getTask(listGui.size() - 1), listGui);
            }
            if (isDeadline) {
                String mission = text.substring(8);
                if (mission.length() == 0) {
                    throw new AlainException("The description of a Deadline cannot be empty.");
                }
                String[] parts = mission.split("/by ");
                if (parts.length != 2) {
                    throw new AlainException("The description of a Deadline is invalid");
                }
                listGui.addTask(new Deadlines(parts[0], stringToTimeString(parts[1])));
                return ai + guiUi.showAddTask(listGui.getTask(listGui.size() - 1), listGui);
            }
            if (isEvent) {
                String mission = text.substring(5);
                if (mission.length() == 0) {
                    throw new AlainException("The description of a Event cannot be empty.");
                }
                String[] parts = mission.split("/");
                if (parts.length != 3) {
                    throw new AlainException("The description of a Event is invalid");
                }
                listGui.addTask(new Events(parts[0],
                        stringToTimeString(parts[1].substring(5)), stringToTimeString(parts[2].substring(3))));
                return ai + guiUi.showAddTask(listGui.getTask(listGui.size() - 1), listGui);
            }
            if (text.equals("bye")) {
                return ai + guiUi.showGoodbye();
            } else if (isMatchMark) {
                String numericPart = text.substring(5);
                listGui.getTask(Integer.parseInt(numericPart) - 1).markAsDone();
                return ai + guiUi.showMarkTask(numericPart, listGui);
            } else if (isMatchUnmark) {
                String numericPart = text.substring(7);
                listGui.getTask(Integer.parseInt(numericPart) - 1).markAsUndone();
                return ai + guiUi.showUnmarkTask(numericPart, listGui);
            } else if (text.equals("list")) {
                return ai + guiUi.showList(listGui);
            }
            throw new AlainException("I'm sorry, but I don't know what that means :-(");
        } catch (AlainException e) {
            return ai + guiUi.showError(e.getMessage());
            //storage.saveTasksToFile(null, "list.txt", true, e.getMessage());
        }
    }
}
