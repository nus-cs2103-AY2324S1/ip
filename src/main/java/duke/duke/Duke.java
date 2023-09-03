package duke.duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.util.Parser;
import duke.util.Storage;

import java.util.Scanner;
import java.util.List;

/**
 * The main class that executes the Duke bot.
 */
public class Duke {

    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a Duke object with the specified file path.
     * @param filePath The file path to the data storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    /**
     * Runs the duke bot.
     */
    public void run() {
        try {
            Scanner reader = new Scanner(System.in);
            TaskList taskList = new TaskList(100);
            List<Task> list = taskList.getList();
            ui.showWelcomeMessage();

            // Load tasks from a file
            storage.loadTasksFromFile(list);

            label:
            while (true) {
                String input = reader.nextLine();
                Parser parser = new Parser(input);
                String command = parser.getCommand();
                String inputDetails = parser.getInputDetails();
                switch (command) {
                case "bye":
                    ui.showByeMessage();
                    break label;
                case "list":
                    taskList.showList();
                    ui.showLine();
                    break;
                case "mark": {
                    if (!parser.checkInputDetailsPresent()) {
                        throw new DukeException("The task number to mark cannot be empty.");
                    }
                    int taskNumber = Integer.parseInt(inputDetails);
                    Task task = taskList.getTask(taskNumber);
                    System.out.printf(task.setDone());
                    break;
                }
                case "unmark": {
                    if (!parser.checkInputDetailsPresent()) {
                        throw new DukeException("The task number to unmark cannot be empty.");
                    }
                    int taskNumber = Integer.parseInt(inputDetails);
                    Task task = taskList.getTask(taskNumber);
                    System.out.printf(task.unsetDone());
                    break;
                }
                case "todo": {
                    if (!parser.checkInputDetailsPresent()) {
                        throw new DukeException("☹ OOPS!!! The description of a todo task cannot be empty.");
                    }
                    Task task = new Todo(inputDetails);
                    taskList.addTask(task);
                    storage.saveTask(inputDetails);
                    break;
                }
                case "deadline": {
                    String[] deadlineInfo = parser.parseInputDetailsDeadline();
                    String description = deadlineInfo[0];
                    String deadline = deadlineInfo[1];
                    Task task = new Deadline(description, deadline);
                    taskList.addTask(task);
                    storage.saveTask(description, deadline);
                    break;
                }
                case "event": {
                    String[] eventInfo = parser.parseInputDetailsEvent();
                    String description = eventInfo[0];
                    String from = eventInfo[1];
                    String to = eventInfo[2];
                    Task task = new Event(description, from, to);
                    taskList.addTask(task);
                    storage.saveTask(description, from, to);
                    break;
                }
                case "delete": {
                    if (!parser.checkInputDetailsPresent()) {
                        throw new DukeException("The task number to mark cannot be empty.");
                    }
                    int taskNumber = Integer.parseInt(inputDetails);
                    taskList.removeTask(taskNumber);
                    storage.deleteTaskFromFile(taskNumber);
                    break;
                }
                case "find": {
                    if (!parser.checkInputDetailsPresent()) {
                        throw new DukeException("The keyword to find cannot be empty");
                    }
                    taskList.findTasks(inputDetails);
                    break;
                }
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (Exception exception) {
            ui.showMessage(exception.getMessage());
        }
    }

    /**
     * The main method to run the duke.duke.Duke program, a task management application.
     * It reads user input and performs various tasks based on the input commands.
     *
     * @param args The command-line arguments (not used in this context).
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}
