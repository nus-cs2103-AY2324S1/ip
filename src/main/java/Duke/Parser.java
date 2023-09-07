package duke;

import command.UserInterface;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Parser class will parse the input data and generate a taskList from the input the file.
 */
public class Parser {
    /**
     * parseCommand method reads the tasks in the input file and generates a taskList so that the chatbot
     * can load the tasks from input file.
     *
     * @param command       the command
     * @param taskManager   the task manager
     * @param userInterface the user interface
     */
    public static String parseCommand(String command, TaskManager taskManager, UserInterface userInterface, Storage storage) {
        String[] parts = command.split(" ", 2);
        String action = parts[0];
        StringBuilder output = new StringBuilder("");

        switch (action) {
        case "todo":
            if (parts.length < 2) {
                return userInterface.showError("OOPS!!! The description of a todo cannot be empty.");
            }
            output.append(userInterface.showCommandLine());
            taskManager.todo(parts[1]);
            output.append(userInterface.showTaskAddedMessage());
            output.append(userInterface.showCurrentStatus(taskManager.displayList()));
            output.append(userInterface.showCommandLine());
            break;

        case "deadline":
            if (parts.length < 2) {
                return userInterface.showError("OOPS!!! The description of a deadline cannot be empty.");
            }
            output.append(userInterface.showCommandLine());
            String[] fullDesc = parts[1].split(" /by ");
            String description = fullDesc[0];
            LocalDateTime by = LocalDateTime.parse(fullDesc[1]);
            taskManager.deadline(description, by);
            output.append(userInterface.showTaskAddedMessage());
            output.append(userInterface.showCurrentStatus(taskManager.displayList()));
            output.append(userInterface.showCommandLine());
            break;

        case "event":
            if (parts.length < 2) {
                return userInterface.showError("OOPS!!! The description of a event cannot be empty.");
            }
            output.append(userInterface.showCommandLine());
            fullDesc = parts[1].split(" /from | /to ");
            description = fullDesc[0];
            LocalDateTime from = LocalDateTime.parse(fullDesc[1]);
            LocalTime to = LocalTime.parse(fullDesc[2]);
            taskManager.event(description, from, to);
            output.append(userInterface.showTaskAddedMessage());
            output.append(userInterface.showCurrentStatus(taskManager.displayList()));
            output.append(userInterface.showCommandLine());
            break;

        case "delete":
            if (parts.length < 2) {
                return userInterface.showError("OOPS!!! Please specify the task number to delete.");
            }
            try {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex < 0 || taskIndex >= taskManager.displayList().size()) {
                    return userInterface.showError("OOPS!!! Invalid task number.");
                }
                taskManager.delete(taskIndex);
                output.append(userInterface.showTaskDeletedMessage());
                output.append(userInterface.showCurrentStatus(taskManager.displayList()));
            } catch (NumberFormatException e) {
                return userInterface.showError("OOPS!!! Please provide a valid task number.");
            }
            break;

        case "mark":
            if (parts.length < 2) {
                return userInterface.showError("OOPS!!! Please specify the task number to mark.");
            }
            try {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex < 0 || taskIndex >= taskManager.displayList().size()) {
                    return userInterface.showError("OOPS!!! Invalid task number.");
                }
                taskManager.mark(taskIndex);
                output.append(userInterface.showTaskMarkedMessage());
                output.append(userInterface.showCurrentStatus(taskManager.displayList()));
                output.append(userInterface.showCommandLine());
            } catch (NumberFormatException e) {
                return userInterface.showError("OOPS!!! Please provide a valid task number.");
            }
            break;

        case "umark":
            if (parts.length < 2) {
                return userInterface.showError("OOPS!!! Please specify the task number to unmark.");
            }
            try {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex < 0 || taskIndex >= taskManager.displayList().size()) {
                    return userInterface.showError("OOPS!!! Invalid task number.");
                }
                output.append(userInterface.showCommandLine());
                taskManager.unmark(taskIndex);
                output.append(userInterface.showTaskUnmarkedMessage());
                output.append(userInterface.showCurrentStatus(taskManager.displayList()));
                output.append(userInterface.showCommandLine());
            } catch (NumberFormatException e) {
                return userInterface.showError("OOPS!!! Please provide a valid task number.");
            }
            break;

        case "list":
            output.append(userInterface.showCommandLine());
            output.append(userInterface.showTaskList(taskManager.displayList()));
            output.append(userInterface.showCommandLine());
            break;

        case "find":
            output.append(userInterface.showCommandLine());
            output.append(userInterface.showFindTasks(taskManager.displayList()));
            output.append(userInterface.showCommandLine());
            break;


        case "bye":
            output.append(userInterface.showCommandLine());
            output.append(userInterface.showGoodbyeMessage());
            output.append(userInterface.showCommandLine());
            storage.save(taskManager.displayList());
            break;

        default:
            output.append(userInterface.showUnknownCommandMessage());
            break;
        }

        return output.toString();
    }
}

