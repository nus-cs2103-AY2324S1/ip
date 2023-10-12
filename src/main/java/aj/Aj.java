package aj;

import java.io.IOException;

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
 * Aj chat-bot class.
 */
public class Aj extends Application {

    private Parser parser;
    private Storage storage;

    private TaskList taskList;

    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image ajImage = new Image(this.getClass().getResourceAsStream("/images/aj_bot.png"));

    private String latestCommand;
    private Task lastTaskRemoved;
    private String lastTaskRemovedEntry;

    /**
     * Takes in description portion of user input and mark the task specified in the taskList and database.
     *
     * @param outMsg     StringBuilder object to craft bot response.
     * @param descString Description portion of user input
     * @throws IndexOutOfRangeException If user gives an index bigger than taskList size.
     * @throws IOException              Arose if there is issue updating database.
     */
    public void markTask(StringBuilder outMsg, String descString) throws IndexOutOfRangeException, IOException {
        int idx = Integer.parseInt(descString.substring(1)) - 1; // this idx is idx of tasklst
        this.ui.checkIndex(idx);
        Task taskToMark = this.taskList.getTask(idx);
        outMsg.append(this.ui.horiLine());
        if (taskToMark.isCompleted()) {
            outMsg.append("You have already marked it!!!\n");
        } else {
            taskToMark.toggleComplete();
            this.storage.updateData(idx, true);
            outMsg.append("Nice! I've marked this task as done:\n");
            outMsg.append(taskToMark);
        }
    }

    /**
     * Takes in description portion of user input and unmark the task specified in the taskList and database.
     *
     * @param outMsg     StringBuilder object to craft bot response.
     * @param descString Description portion of user input
     * @throws IndexOutOfRangeException If user gives an index bigger than taskList size.
     * @throws IOException              Arose if there is issue updating database.
     */
    public void unmarkTask(StringBuilder outMsg, String descString) throws IndexOutOfRangeException, IOException {
        int idx = Integer.parseInt(descString.substring(1)) - 1;
        this.ui.checkIndex(idx);
        Task taskToUnmark = this.taskList.getTask(idx);
        outMsg.append(this.ui.horiLine());
        if (!taskToUnmark.isCompleted()) {
            outMsg.append("Task is already unmarked!!!\n");
        } else {
            taskToUnmark.toggleComplete();
            this.storage.updateData(idx, false);
            outMsg.append("OK, I've marked this task as not done yet:\n");
            outMsg.append(taskToUnmark);
        }
    }

    /**
     * Takes in user input and add the task in the taskList and database.
     *
     * @param outMsg     StringBuilder object to craft bot response.
     * @param cmdString  Command portion of user input.
     * @param descString Description portion of user input.
     * @param task       Task to be added in the taskList and database.
     * @throws IndexOutOfRangeException If user gives an index bigger than taskList size.
     * @throws IOException              Arose if there is issue updating database.
     */
    public void addTask(StringBuilder outMsg, String cmdString, String descString, Task task)
            throws IndexOutOfRangeException, IOException {
        this.storage.addData(cmdString + descString + ",false");
        this.taskList.addTask(task);
        outMsg.append("Got it. I've added this task:\n");
        outMsg.append(task).append("\n");
        outMsg.append(this.ui.getNumTasks());
    }

    /**
     * Takes in index of task in taskList to be deleted.
     *
     * @param outMsg     StringBuilder object to craft bot response.
     * @param descString Description portion of user input.
     * @throws IndexOutOfRangeException If user gives an index bigger than taskList size.
     * @throws IOException              Arose if there is issue updating database.
     */
    public void deleteTask(StringBuilder outMsg, String descString) throws IndexOutOfRangeException, IOException {
        int idx = Integer.parseInt(descString.substring(1)) - 1;
        this.ui.checkIndex(idx);
        outMsg.append(this.ui.horiLine());
        outMsg.append("Noted. I've removed this task:\n");
        Task removedTask = this.taskList.getTask(idx);
        this.taskList.removeTask(idx);
        this.lastTaskRemovedEntry = this.storage.deleteData(idx);
        this.lastTaskRemoved = removedTask;
        outMsg.append(removedTask).append("\n");
        outMsg.append(this.ui.getNumTasks());
    }

    /**
     * Takes in a string keyword from user input and find tasks with that keyword.
     *
     * @param outMsg     StringBuilder object to craft bot response.
     * @param descString Description portion of user input.
     */
    public void findTask(StringBuilder outMsg, String descString) {
        outMsg.append(this.ui.getTasksWithKeyword(descString.substring(1)));
        outMsg.append(this.ui.getNumTasks());
    }


    /**
     * Undo the last command given by user
     *
     * @param outMsg StringBuilder object to craft bot response.
     * @throws IndexOutOfRangeException If user gives an index bigger than taskList size.
     * @throws IOException              Arose if there is issue updating database.
     */
    public void undoCommand(StringBuilder outMsg) throws IndexOutOfRangeException, IOException {
        String[] cmdAndDesc = this.parser.parseCommand(this.latestCommand);
        String cmdString;
        if (cmdAndDesc.length != 0) { // if user input is not a single command
            cmdString = cmdAndDesc[0];
            String descString = cmdAndDesc[1];
            switch (cmdString) {
            case "mark":
                unmarkTask(outMsg, descString);
                break;
            case "unmark":
                markTask(outMsg, descString);
                break;
            case "delete": // adds the last task removed into taskList again
                this.storage.addData(this.lastTaskRemovedEntry); // add entry to database
                this.taskList.addTask(this.lastTaskRemoved); // add to tasklist
                outMsg.append("Readded task ").append(this.lastTaskRemoved).append("!!!");
                break;
            default: // latest command is add task
                outMsg.append("Noted. I've removed this task:\n");
                int idx = this.taskList.getSize() - 1;
                Task removedTask = this.taskList.getTask(idx);
                this.taskList.removeTask(idx);
                this.storage.deleteData(idx);
                outMsg.append(removedTask);
            }
        } else {
            outMsg.append("Latest command \"").append(this.latestCommand).append("\" is not undoable!");
        }
    }


    /**
     * Takes in command portion of userinput and carry out respective action.
     *
     * @param outMsg    StringBuilder object to craft bot response.
     * @param cmdString Command portion of user input.
     * @throws NoSuchCommandException    If command does not exist.
     * @throws EmptyDescriptionException If second part of user input does not exist.
     * @throws IndexOutOfRangeException  If user gives an index bigger than taskList size.
     * @throws IOException               Arose if there is issue updating database.
     */
    public void getSingleCommandResponse(StringBuilder outMsg, String cmdString)
            throws NoSuchCommandException, EmptyDescriptionException, IndexOutOfRangeException, IOException {
        switch (cmdString) {
        case "list":
            outMsg.append(this.ui.getListOfTasks());
            break;
        case "bye":
            outMsg.append(this.ui.exit());
            break;
        case "help":
            outMsg.append(this.ui.getHelpMessage());
            break;
        case "undo":
            undoCommand(outMsg);
            break;
        default:
            this.ui.checkCommand(cmdString);
            throw new NoSuchCommandException();
        }
    }

    /**
     * Takes in a full command (first part and description) of userinput and carry out respective action.
     *
     * @param outMsg     StringBuilder object to craft bot response.
     * @param cmdString  Command portion of user input.
     * @param descString Description portion of user input.
     * @return Task object specified in user input.
     * @throws NoSuchCommandException    If command does not exist.
     * @throws IndexOutOfRangeException  If user gives an index bigger than taskList size.
     * @throws IOException               Arose if there is issue updating database.
     * @throws WrongDescriptionException If task description is invalid.
     */
    public Task getFullCommandResponse(StringBuilder outMsg, String cmdString, String descString)
            throws NoSuchCommandException, IndexOutOfRangeException, IOException, WrongDescriptionException {
        switch (cmdString) {
        case "mark": {
            markTask(outMsg, descString);
            break;
        }
        case "unmark": {
            unmarkTask(outMsg, descString);
            break;
        }
        case "delete": {
            deleteTask(outMsg, descString);
            break;
        }
        case "find":
            findTask(outMsg, descString);
            break;
        case "todo":
            return this.parser.getTodoTask(descString, false);
        case "deadline":
            return this.parser.getDeadlineTask(descString, false);
        case "event":
            return this.parser.getEventTask(descString, false);
        default:
            throw new NoSuchCommandException();
        }
        return null;
    }


    /**
     * Takes in user input and return the corresponding response.
     *
     * @param userInput User Input from GUI
     * @return true to exit program, else function will be continuously called.
     * @throws NoSuchCommandException    If command does not exist.
     * @throws EmptyDescriptionException If second part of user input does not exist.
     * @throws IndexOutOfRangeException  If user gives an index bigger than taskList size.
     * @throws IOException               Arose if there is issue updating database.
     * @throws WrongDescriptionException If task description is invalid.
     */
    public StringBuilder getBotResponse(String userInput)
            throws NoSuchCommandException, EmptyDescriptionException, IndexOutOfRangeException, IOException,
            WrongDescriptionException {
        StringBuilder outMsg = new StringBuilder();
        String[] cmdAndDesc = this.parser.parseCommand(userInput);
        String cmdString;
        if (cmdAndDesc.length == 0) { // if user input is a single command
            cmdString = userInput;
            getSingleCommandResponse(outMsg, cmdString);
        } else {
            cmdString = cmdAndDesc[0];
            String descString = cmdAndDesc[1];
            Task task = getFullCommandResponse(outMsg, cmdString, descString);
            if (task != null) { // add task to database and taskList
                addTask(outMsg, cmdString, descString, task);
            }
        }
        return outMsg;
    }


    /**
     * Initialises the necessary components of the AJ chat-bot
     *
     * @param filePath Filepath of database
     * @throws IOException Arose if there is issue updating database.
     */
    public void setUpBot(String filePath) throws IOException {
        this.parser = new Parser();
        this.storage = new Storage(this.parser, filePath);
        this.taskList = new TaskList(this.storage.initialiseData());
        this.ui = new Ui(taskList);
    }


    /**
     * Configures the stage with the necessary Graphical User Interface (GUI) controls
     *
     * @param stage      the primary stage for this application, onto which
     *                   the application scene can be set.
     * @param mainLayout control used to structure layout of GUI
     * @param sendButton control used to create event to send messages
     */
    public void configureStage(Stage stage, AnchorPane mainLayout, Button sendButton) {
        stage.setTitle("Aj-Bot");
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
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Initialise the stage with the necessary Graphical User Interface (GUI) controls
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     * @throws IOException Arose if there is issue updating database.
     */
    @Override
    public void start(Stage stage) throws IOException {
//        String fullFilePath = System.getProperty("user.dir") + "/src/main/data/actualData.txt";
//        String filePath = "./src/main/data/actualData.txt";
        String filePath = "./data/database.txt";

        setUpBot(filePath);
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        Button sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        configureStage(stage, mainLayout, sendButton);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren()
                .addAll(DialogBox.getAjDialog(getDialogLabel(this.ui.greet()), new ImageView(ajImage)));
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userLabel = getDialogLabel(userInput.getText()); // get userinput from textField
        Label ajLabel = getDialogLabel(getResponse(userInput.getText())); // get string, create label for dialogBox
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userLabel, new ImageView(userImage)),
                DialogBox.getAjDialog(ajLabel, new ImageView(ajImage)));
        userInput.clear();
    }

    /**
     * Gets response from bot to be displayed to user.
     *
     * @param userInput User's input into bot.
     * @return String response to be displayed to user.
     */
    public String getResponse(String userInput) {
        String outMsg = "";
        try {
            outMsg = getBotResponse(userInput).toString();
        } catch (AjException | IOException e) {
            outMsg = e.getMessage();
        }
        this.latestCommand = userInput; // set user input as latest command
        return outMsg;
    }


    public static void main(String[] args) {
        Application.launch(Aj.class, args);
    }

}
