package ipbot;

import ipbot.controller.MainWindowController;
import ipbot.model.Command;
import ipbot.model.Deadline;
import ipbot.model.Event;
import ipbot.model.Pair;
import ipbot.model.Task;
import ipbot.model.TaskFormatException;
import ipbot.model.ToDo;
import ipbot.util.CommandArgumentException;
import ipbot.util.Parser;
import ipbot.util.Storage;
import ipbot.util.TaskList;
import ipbot.util.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class Duke extends Application {
    public static final DateTimeFormatter QUERY_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm");
    private static final String SAVE_FILE = "data/saved_tasks.csv";

    private Storage storage;
    private TaskList taskList;

    private Stage primaryStage;

    public Duke() {
        this.storage = new Storage(SAVE_FILE);

        try {
            this.taskList = this.storage.readTasksFromCSVFile();
        } catch (TaskFormatException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            MainWindowController controller = fxmlLoader.getController();
            controller.setDuke(this);
            primaryStage.show();
            this.primaryStage = primaryStage;
            controller.sendWelcome();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Command stringToCommand(String commandString) throws InvalidCommandException {
        if (commandString.length() == 0) {
            throw new InvalidCommandException("Nothing happened!");
        }
        Command command = Command.commandEnum(commandString);
        if(command == null){
            throw new InvalidCommandException(
                    "Error: " + commandString + " is not a valid command!");
        }
        return command;
    }

    public String handleMark(boolean isMark, Map<String, String> commandArgs) throws CommandArgumentException {  // TODO: private
        int markIndex = Parser.checkIndexArg(commandArgs.get(""), taskList.getTasksSize());
        if (markIndex == -1) {
            String markString = (isMark ? "mark" : "unmark");
            throw new CommandArgumentException("Invalid task to " + markString + ": " + commandArgs.get(""));
        }
        Pair<Task, Boolean> taskMark = (isMark ? taskList.markTask(markIndex) : taskList.unmarkTask(markIndex));
        if (taskMark.getSecond()) {
            return Ui.markTaskString(taskMark.getFirst(), isMark);
        } else {
            return Ui.alreadyMarkedTaskString(taskMark.getFirst(), isMark);
        }
    }

    public String handleDelete(Map<String, String> commandArgs) throws CommandArgumentException {  // TODO: Move to taskList
        int deleteIndex = Parser.checkIndexArg(commandArgs.get(""), taskList.getTasksSize());
        if (deleteIndex == -1) {
            throw new CommandArgumentException("Invalid task to delete: " + commandArgs);  // TODO: change commandArgs
        }
        Task task = taskList.deleteTask(deleteIndex);
        return Ui.deletedItemString(task);
    }

    /**
     * Get the bot response for a user input.
     *
     * @param input The input to the bot given.
     * @return The response of the bot to the input given.
     */
    public String getResponse(String input) {
        Pair<String, Map<String, String>> commandResult = Parser.parseCommand(input);
        String response;
        try {
            Command command = stringToCommand(commandResult.getFirst());
            Map<String, String> commandArgs = commandResult.getSecond();
            switch (command) {
            case LIST:
                if (!commandArgs.get("").isEmpty()) {
                    LocalDateTime queryDate = LocalDateTime.parse(
                            commandArgs.get("") + " 0000", QUERY_DATE_TIME_FORMATTER);
                    response = taskList.listTasks(queryDate);
                } else {
                    response = taskList.listTasks();
                }
                break;
            case MARK:
                response = handleMark(true, commandArgs);
                break;
            case UNMARK:
                response = handleMark(false, commandArgs);
                break;
            case TODO:
                ToDo toDo = taskList.addToDoWithArgs(commandArgs);
                response = Ui.addedItemString(toDo, "todo");
                break;
            case DEADLINE:
                Deadline deadline = taskList.addDeadlineWithArgs(commandArgs);
                response = Ui.addedItemString(deadline, "deadline");
                break;
            case EVENT:
                Event event = taskList.addEventWithArgs(commandArgs);
                response = Ui.addedItemString(event, "event");
                break;
            case DELETE:
                response = handleDelete(commandArgs);
                break;
            case FIND:
                if (commandArgs.get("").isEmpty()) {
                    throw new CommandArgumentException("Find string cannot be empty!");
                }
                response = taskList.listTasks(commandArgs.get(""));
                break;
            case RECUR:
                response = taskList.recurTask(commandArgs);
                break;
            case BYE:
                primaryStage.close();
                return "";
            default:
                response = "If you see this, there was an error!";
            }
        } catch (CommandArgumentException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return e.getMessage();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
        this.storage.writeTasksToCSVFile(taskList);
        return response;
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
