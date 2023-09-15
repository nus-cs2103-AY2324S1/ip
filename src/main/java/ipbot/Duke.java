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
            this.taskList = this.storage.readTasksFromFile();
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

    /**
     * Get the bot response for a user input.
     */
    public String getResponse(String input) {
        Pair<String, Map<String, String>> commandResult = Parser.parseCommand(input);
        String response;
        try {
            if (commandResult.getFirst().length() == 0) {
                return "Nothing happened!";
            }
            String command = commandResult.getFirst();
            Command commandName = Command.commandEnum(command);
            if(commandName == null){
                return "Error: " + command + " is not a valid command!";
            }
            if (command.equalsIgnoreCase("bye")) {
                primaryStage.close();
                return "";
            }
            Map<String, String> commandArgs = commandResult.getSecond();
            switch (commandName) {
            case LIST:
                if (!commandArgs.get("").isEmpty()) {
                    LocalDateTime queryDate = LocalDateTime.parse(
                            commandArgs.get("") + " 0000", QUERY_DATE_TIME_FORMATTER);
                    response = taskList.listTasks(queryDate);
                }
                else{
                    response = taskList.listTasks();
                }
                break;
            case MARK:
                int markIndex = Parser.checkIndexArg(commandArgs.get(""), taskList.getTasksSize());
                if (markIndex == -1) {
                    throw new CommandArgumentException("Invalid task to mark: " + commandArgs);
                }
                Pair<Task, Boolean> taskMark = taskList.markTask(markIndex);
                if (taskMark.getSecond()) {
                    response = Ui.markTaskString(taskMark.getFirst(), true);
                } else {
                    response = Ui.alreadyMarkedTaskString(taskMark.getFirst(), true);
                }
                break;
            case UNMARK:
                int unmarkIndex = Parser.checkIndexArg(commandArgs.get(""), taskList.getTasksSize());
                if (unmarkIndex == -1) {
                    throw new CommandArgumentException("Invalid task to unmark: " + commandArgs);
                }
                Pair<Task, Boolean> taskUnmark = taskList.unmarkTask(unmarkIndex);
                if (taskUnmark.getSecond()) {
                    response = Ui.markTaskString(taskUnmark.getFirst(), false);
                } else {
                    response = Ui.alreadyMarkedTaskString(taskUnmark.getFirst(), false);
                }
                break;
            case TODO:
                if (commandArgs.isEmpty()) {
                    throw new CommandArgumentException("Task description cannot be empty!");
                }
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
                int deleteIndex = Parser.checkIndexArg(commandArgs.get(""), taskList.getTasksSize());
                if (deleteIndex == -1) {
                    throw new CommandArgumentException("Invalid task to delete: " + commandArgs);
                }
                Task task = taskList.deleteTask(deleteIndex);
                response = Ui.deletedItemString(task);
                break;
            case FIND:
                if (commandArgs.get("").isEmpty()) {
                    throw new CommandArgumentException("Find string cannot be empty!");
                }
                response = taskList.listTasks(commandArgs.get(""));
                break;
            default:
                response = "If you see this, there was an error!";
            }
        }
        catch (CommandArgumentException e) {
            return e.getMessage();
        }
        catch (DateTimeParseException e) {
            return e.getMessage();
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        this.storage.writeTasksToFile(taskList);
        return response;
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
