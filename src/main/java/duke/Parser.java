package duke;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javafx.application.Platform;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    // allows bot to process the command and acts accordingly
    public static Command parseCommand(String userInput) {
        String[] inputParts = userInput.split(" ", 2); // Split input into two parts: command and arguments
        String command = inputParts[0].toUpperCase(); // Convert command to uppercase for case-insensitivity


        switch (command) {
            case "TODO":
                return Command.TODO;
            case "DEADLINE":
                return Command.DEADLINE;
            case "EVENT":
                return Command.EVENT;
            case "LIST":
                return Command.LIST;
            case "DONE":
                return Command.DONE;
            case "DELETE":
                return Command.DELETE;
            case "FIND":
                return Command.FIND;
            case "BYE":
                return Command.EXIT;
            case "UNMARK":
                return Command.UNMARK;
            case "MARK":
                return Command.MARK;
            default:
                return Command.INVALID;
        }


    }

    //allows bot to process the description of any command
    public static String parseDescription(String userInput) {
        String[] parts = userInput.split(" ", 2); // Split input into command and arguments
        if (parts.length > 1) {
            return parts[1];
        }
        return "";
    }

    public static String handleUnmark(String description,TaskList tasks,Ui ui) {
        // if user inputs task number, check if it is even an integer, and whether it is within range
        try {
            int taskNumber = Integer.parseInt(description) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                return tasks.unmarkTask(taskNumber);
            } else {
                return ui.showError("Task number out of range.");
            }

        } catch (NumberFormatException e) {
            return ui.showError("Invalid task number. Please provide a valid integer.");
        }
    }

    public static String handleExit(Storage storage, Ui ui, TaskList tasks) {
        storage.save(tasks, "tasks.txt");
        ui.closeScanner();
        Timeline exitDelay = new Timeline(new KeyFrame(Duration.seconds(1), event -> Platform.exit()));
        exitDelay.setCycleCount(1); // Run the exit action only once
        exitDelay.play();
        return ui.showExitMessage();
    }

    public static String handleMark(String description,TaskList tasks,Ui ui) {
        // if user inputs task number, check if it is even an integer, and whether it is within range
        try {
            int taskNumber = Integer.parseInt(description) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                return tasks.markTaskAsDone(taskNumber);
            } else {
                return ui.showError("Task number out of range.");
            }
        } catch (NumberFormatException e) {
            return ui.showError("Invalid task number. Please provide a valid integer.");
        }
    }

    public static String handleList(Ui ui, TaskList tasks) {
        return ui.showTaskList(tasks);
    }

    public static String handleFind(String description,TaskList tasks) {
        return tasks.findTasksContainingKeyword(description);
    }

    public static String handleInvalid(Ui ui) {
        return ui.showError("Invalid command. Please try again.");
    }

    public static String handleDelete(String description,Ui ui, TaskList tasks) {
        try {
            int taskNumber = Integer.parseInt(description) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                return tasks.deleteTask(taskNumber);
            } else {
                return ui.showError("Task number out of range.");
            }
        } catch (NumberFormatException e) {
            return ui.showError("Invalid task number. Please provide a valid integer.");
        }
    }

    public static String handleEvent(String description,TaskList tasks) {
        if (description.isEmpty()) {
            return "OOPS!!! The description of an event cannot be empty.";
        } else {
            // Find the indices of the time separators
            int fromIndex = description.indexOf("/from");
            int toIndex = description.indexOf("/to");

            if (fromIndex != -1 && toIndex != -1) {
                // Extract the task description, startTime, and endTime
                String descriptionString = description.substring(0, fromIndex).trim();
                String startTime = description.substring(fromIndex + 5, toIndex).trim();
                String endTime = description.substring(toIndex + 3).trim();

                // Create a new Event object
                Event eventTask = new Event(descriptionString, false, startTime, endTime);
                return tasks.addTask(eventTask);

            } else {
                return "Invalid input format for event command.";
            }
        }
    }

    public static String handleDeadline(String description, TaskList tasks) {
        if (description.isEmpty()) {
            return "OOPS!!! The description of a deadline cannot be empty.";
        } else {
            // Find the index of the deadline separator "/"
            int separatorIndex = description.indexOf('/');

            if (separatorIndex != -1) { // Ensure the separator exists in the input
                // Extract the task description and deadline

                String descriptionString = description.substring(0, separatorIndex).trim();
                String deadline = description.substring(separatorIndex + 4).trim();
                String pattern = "\\d{4}/\\d{2}/\\d{2}";
                Pattern datePattern = Pattern.compile(pattern);
                Matcher matcher = datePattern.matcher(deadline);
                if (matcher.find()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate localDateDeadline = LocalDate.parse(deadline, formatter);
                    Deadline deadlineTask = new Deadline(descriptionString, false, localDateDeadline);
                    return tasks.addTask(deadlineTask);

                } else {
                    return "Please input your deadline in YYYY/MM/DD format";
                }
            } else {
                return "Invalid input format for deadline. Please input in the following format: <deadline> <description> /by <YYYY/MM/DD> ";
            }
        }
    }

    public static String handleTodo(String description,TaskList tasks) {
        if (description.isEmpty()) {
            return "OOPS!!! The description of a Todo cannot be empty.";
        } else {

            Todo todo = new Todo(description, false);
            return tasks.addTask(todo);
        }
    }

}
