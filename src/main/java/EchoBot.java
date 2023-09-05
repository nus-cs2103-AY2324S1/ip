import java.util.ArrayList;
import java.util.Scanner;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * The main class that represents the EchoBot application.
 */
public class EchoBot {
    /**
     * The entry point of the EchoBot application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "./data/duke.txt"; // Default path
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        ui.showWelcomeMessage();

        try {
            tasks = storage.loadTasks();
        } catch (Exception e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }

        while (true) {
            // Read the user input
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                Ui.showByeMessage();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                Ui.showTasks(tasks);
            } else if (userInput.startsWith("todo")) {
                String taskDescription = Command.extractTaskDesc(userInput, "todo");
                Command addCommand = new AddCommand(Command.TaskType.TODO, taskDescription,
                        null, null);
                addCommand.doCommand(tasks, ui, storage);
            } else if (userInput.startsWith("deadline")) {
                String taskDescription = Command.extractTaskDesc(userInput, "deadline");
                int indexOfBy = taskDescription.indexOf("/by");
                String deadlineDescription = taskDescription.substring(0, indexOfBy).trim();
                String by = taskDescription.substring(indexOfBy + 3).trim();
                Command addCommand = new AddCommand(Command.TaskType.DEADLINE, deadlineDescription,
                        by, null);
                addCommand.doCommand(tasks, ui, storage);
            } else if (userInput.startsWith("event")) {
                String taskDescription = Command.extractTaskDesc(userInput, "event");
                int indexOfFrom = taskDescription.indexOf("/from");
                int indexOfTo = taskDescription.indexOf("/to");
                String eventDescription = taskDescription.substring(0, indexOfFrom).trim();
                String from = taskDescription.substring(indexOfFrom + 5, indexOfTo).trim();
                String to = taskDescription.substring(indexOfTo + 3).trim();
                Command addCommand = new AddCommand(Command.TaskType.EVENT, eventDescription, from, to);
                addCommand.doCommand(tasks, ui, storage);
            } else if (userInput.startsWith("mark")) {
                int taskNum = Command.extractTaskNum(userInput, "mark");
                Command markCommand = new MarkCommand(taskNum);
                markCommand.doCommand(tasks, ui, storage);
            } else if (userInput.startsWith("unmark")) {
                int taskNum = Command.extractTaskNum(userInput, "unmark");
                Command unmarkCommand = new UnmarkCommand(taskNum);
                unmarkCommand.doCommand(tasks, ui, storage);
            } else if (userInput.startsWith("delete")) {
                int taskNum = Command.extractTaskNum(userInput, "delete");
                Command deleteCommand = new DeleteCommand(taskNum);
                deleteCommand.doCommand(tasks, ui, storage);
            } else if (userInput.startsWith("find")) {
                String keyword = Command.extractTaskDesc(userInput, "find");
                Command findCommand = new FindCommand(keyword);
                findCommand.doCommand(tasks, ui, storage);
            } else {
                Ui.showHorizontalLine();
                System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
                Ui.showHorizontalLine();
            }
        }
    }
}
