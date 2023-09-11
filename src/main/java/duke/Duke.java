package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A chatbot that provides a to-do list function.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor for the chatbot.
     */
    public Duke(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(this.storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns a reply to the user input.
     *
     * @param input the user input.
     * @return the reply to the user input.
     */
    public String reply(String input, Stage stage) {
        Parser parser = new Parser(input);
        String firstWord = parser.getCommand();

        switch (firstWord) {
        case "bye":
            try {
                this.storage.saveTasks(this.tasks.getTasks());
                return this.ui.printFarewell();
            } catch (IOException exception) {
                return exception.getMessage();
            } finally {
                stage.close();
            }
        // Display the stored commands
        case "list":
            if (this.tasks.getNumberOfTasks() == 0) {
                return "You do not have any tasks in the list.";
            }
            StringBuilder list = new StringBuilder();
            list.append("Here are the tasks in your list:\n");
            for (int i = 0; i < this.tasks.getNumberOfTasks(); i++) {
                list.append(i + 1);
                list.append(".");
                list.append(this.tasks.getTasks().get(i).toString());
                list.append("\n");
            }
            return list.toString();
        // Add task
        case "todo":
            try {
                return this.tasks.addTask(parser.getTodoTask(), "todo");
            } catch (DukeException exception) {
                return exception.getMessage();
            }
        case "deadline":
            try {
                return this.tasks.addTask(parser.getDeadlineTask(), "deadline");
            } catch (DukeException exception) {
                return exception.getMessage();
            }
        case "event":
            try {
                return this.tasks.addTask(parser.getEventTask(), "event");
            } catch (DukeException exception) {
                return exception.getMessage();
            }
        // Mark task as done
        case "mark":
            try {
                return this.tasks.markTask(parser.getTaskNumber(), "mark");
            } catch (DukeException exception) {
                return exception.getMessage();
            }
        // Mark task as not done
        case "unmark":
            try {
                return this.tasks.markTask(parser.getTaskNumber(), "unmark");
            } catch (DukeException exception) {
                return exception.getMessage();
            }
        // Remove task
        case "delete":
            try {
                return this.tasks.removeTask(parser.getTaskNumber());
            } catch (DukeException exception) {
                return exception.getMessage();
            }
        // Find task
        case "find":
            try {
                return this.tasks.findTask(parser.getSearchKeyword());
            } catch (DukeException exception) {
                return exception.getMessage();
            }
        // Update a task
        case "update":
            try {
                int taskNumber = parser.getTaskNumber();
                Task task = this.tasks.getTasks().get(taskNumber - 1);
                String taskType = task.getTaskType();
                String[] description;
                switch (taskType) {
                case "event":
                    description = parser.getEventTask();
                    break;
                case "deadline":
                    description = parser.getDeadlineTask();
                    break;
                case "todo":
                    description = parser.getTodoTask();
                    break;
                default:
                    throw new DukeException(ExceptionTypes.INVALIDCOMMAND);
                }
                if (description.length == 1 || description[1].isBlank()) {
                    throw new DukeException(ExceptionTypes.INCOMPLETEUPDATEDETAILS);
                }
                description[0] = description[0].split(" ", 2)[1];
                return this.tasks.updateTask(description, taskNumber);
            } catch (DukeException exception) {
                return exception.getMessage();
            }
        // Invalid command
        default:
            return this.ui.printAllCommands();
        }
    }

    /**
     * The main method of the chatbot.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
