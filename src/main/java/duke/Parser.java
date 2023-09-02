package duke;

import command.UserInterface;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Parser {
    public static void parseCommand(String command, TaskManager taskManager, UserInterface userInterface) {
        String[] parts = command.split(" ", 2);
        String action = parts[0];

        switch (action) {
            case "todo":
                if (parts.length < 2) {
                    userInterface.showError("OOPS!!! The description of a todo cannot be empty.");
                    return;
                }
                userInterface.showCommandLine();
                taskManager.todo(parts[1]);
                userInterface.showTaskAddedMessage();
                userInterface.showCurrentStatus(taskManager.displayList());
                userInterface.showCommandLine();
                break;

            case "deadline":
                if (parts.length < 2) {
                    userInterface.showError("OOPS!!! The description of a deadline cannot be empty.");
                    return;
                }
                userInterface.showCommandLine();
                String[] full_desc = parts[1].split(" /by ");
                String description = full_desc[0];
                LocalDateTime by = LocalDateTime.parse(full_desc[1]);
                taskManager.deadline(description, by);
                userInterface.showTaskAddedMessage();
                userInterface.showCurrentStatus(taskManager.displayList());
                userInterface.showCommandLine();
                break;

            case "event":
                if (parts.length < 2) {
                    userInterface.showError("OOPS!!! The description of a event cannot be empty.");
                    return;
                }
                userInterface.showCommandLine();
                full_desc = parts[1].split(" /from | /to ");
                description = full_desc[0];
                LocalDateTime from = LocalDateTime.parse(full_desc[1]);
                LocalTime to = LocalTime.parse(full_desc[2]);
                taskManager.event(description, from, to);
                userInterface.showTaskAddedMessage();
                userInterface.showCurrentStatus(taskManager.displayList());
                userInterface.showCommandLine();
                break;

            case "delete":
                if (parts.length < 2) {
                    userInterface.showError("OOPS!!! Please specify the task number to delete.");
                    return;
                }
                try {
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= taskManager.displayList().size()) {
                        userInterface.showError("OOPS!!! Invalid task number.");
                        return;
                    }
                    taskManager.delete(taskIndex);
                    userInterface.showTaskDeletedMessage();
                    userInterface.showCurrentStatus(taskManager.displayList());
                } catch (NumberFormatException e) {
                    userInterface.showError("OOPS!!! Please provide a valid task number.");
                }
                break;

            case "mark":
                if (parts.length < 2) {
                    userInterface.showError("OOPS!!! Please specify the task number to mark.");
                    return;
                }
                try {
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= taskManager.displayList().size()) {
                        userInterface.showError("OOPS!!! Invalid task number.");
                        return;
                    }
                    userInterface.showCommandLine();
                    taskManager.mark(taskIndex);
                    userInterface.showTaskMarkedMessage();
                    userInterface.showCurrentStatus(taskManager.displayList());
                    userInterface.showCommandLine();
                } catch (NumberFormatException e) {
                    userInterface.showError("OOPS!!! Please provide a valid task number.");
                }
                break;

            case "umark":
                if (parts.length < 2) {
                    userInterface.showError("OOPS!!! Please specify the task number to unmark.");
                    return;
                }
                try {
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= taskManager.displayList().size()) {
                        userInterface.showError("OOPS!!! Invalid task number.");
                        return;
                    }
                    userInterface.showCommandLine();
                    taskManager.unmark(taskIndex);
                    userInterface.showTaskUnmarkedMessage();
                    userInterface.showCurrentStatus(taskManager.displayList());
                    userInterface.showCommandLine();
                } catch (NumberFormatException e) {
                    userInterface.showError("OOPS!!! Please provide a valid task number.");
                }
                break;

            case "list":
                userInterface.showCommandLine();
                userInterface.showTaskList(taskManager.displayList());
                userInterface.showCommandLine();
                break;

            case "find":
                userInterface.showCommandLine();
                userInterface.showFindTasks(taskManager.find(parts[1]));
                userInterface.showCommandLine();
                break;

//            case "bye":
//                userInterface.showCommandLine();
//                userInterface.showGoodbyeMessage();
//                userInterface.showCommandLine();

            default:
                userInterface.showUnknownCommandMessage();
                break;
        }
    }
}

