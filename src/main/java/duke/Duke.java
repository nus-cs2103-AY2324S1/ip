package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidNumberException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import java.util.Scanner;

/**
 * Represents Duke, a Personal Assistant Chatbot that helps a person to keep track of
 * various things. The name Duke was chosen as a placeholder name, in honor of Duke,
 * the Java Mascot. The current name of the Chatbot is John.
 */
public class Duke {
    // substring: begIndex (inclusive) up to the endIndex (exclusive)

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for a Duke object.
     * Initialises the ui, storage, and taskList.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        taskList = new TaskList(storage.retrieveData());
    }

    /**
     * Starts the Chatbot and continuously reads user input, processes commands,
     * and provides responses until the user exits.
     */
    public void run() {
        storage.createFile();
        ui.startMessage();

        Scanner sc = new Scanner(System.in);

        while (true) {
            ui.promptReply();
            String command = sc.nextLine();

            if (command.equals("bye")) {
                ui.endMessage();
                storage.save(taskList.getAllTasks());
                break;
            } else {
                try {
                    if (command.equals("list")) {
                        printList(taskList);
                    } else if (command.startsWith("mark ")) {
                        printMark(command, taskList);
                    } else if (command.startsWith("unmark ")) {
                        printUnmark(command, taskList);
                    } else if (command.startsWith("todo ")) {
                        addToDo(command, taskList);
                    } else if (command.startsWith("deadline ")) {
                        addDeadline(command, taskList);
                    } else if (command.startsWith("event ")) {
                        addEvent(command, taskList);
                    } else if (command.startsWith("delete ")) {
                        deleteTask(command, taskList);
                    } else {
                        if (command.startsWith("todo")) {
                            throw new InvalidDescriptionException("todo");
                        } else if (command.startsWith("deadline")) {
                            throw new InvalidDescriptionException("deadline");
                        } else if (command.startsWith("event")) {
                            throw new InvalidDescriptionException("event");
                        } else {
                            throw new InvalidCommandException();
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        sc.close();
    }

    /**
     * Our main method for the Chatbot to work. It starts the Chatbot by calling the run method.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The list of tasks to display.
     */
    public static void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 1; i <= taskList.getSize(); i++) {
            // Adding toString() to use the overridden one in duke.task.Task, etc.
            System.out.println(i + ". " + taskList.getTask(i - 1).toString());
        }
    }

    /**
     * Marks a task as done and displays a message to the user.
     *
     * @param command The user command to mark a task as done.
     * @param taskList The list of tasks to update.
     * @throws Exception If an invalid number is given.
     */
    public void printMark(String command, TaskList taskList) throws Exception {
        // Space behind is needed!, number index = 5
        // Convert substring to int, -1 for index
        int taskPos = Integer.parseInt(command.substring(5)) - 1;

        // Only numbers >= 0 and < total number are valid
        if (taskPos >= 0 && taskPos < taskList.getSize()) {
            taskList.getTask(taskPos).markAsDone();
            ui.showTaskMarked(taskList.getTask(taskPos));
        } else {
            throw new InvalidNumberException();
        }
    }

    /**
     * Unmarks a task as done and displays a message to the user.
     *
     * @param command The user command to mark a task as done.
     * @param taskList The list of tasks to update.
     * @throws InvalidNumberException If an invalid number is given.
     * @throws Exception If an error occurs during execution.
     */
    public void printUnmark(String command, TaskList taskList) throws Exception {
        // Number index = 7
        // Convert substring to int, -1 for index
        int taskPos = Integer.parseInt(command.substring(7)) - 1;

        // Only numbers >= 0 and < total number are valid
        if (taskPos >= 0 && taskPos < taskList.getSize()) {
            taskList.getTask(taskPos).unmark();
            ui.showTaskUnmarked(taskList.getTask(taskPos));
        } else {
            throw new InvalidNumberException();
        }
    }

    /**
     * Adds a new ToDo task to the task list and displays a message to the user.
     *
     * @param command The user command.
     * @param taskList The list of tasks to which the task will be added.
     * @throws InvalidDescriptionException If the description for the ToDo task is empty.
     * @throws Exception If an error occurs during execution.
     */
    public void addToDo(String command, TaskList taskList) throws Exception {
        // Description starting index = 5
        String description = command.substring(5);

        if (description.isEmpty()) {
            throw new InvalidDescriptionException("todo");
        }

        Task newTask = new ToDo(description);
        taskList.addTask(newTask);

        ui.showTaskAdded(newTask, taskList.getSize());

    }

    /**
     * Adds a new Deadline task to the task list and displays a message to the user.
     *
     * @param command The user command.
     * @param taskList The list of tasks to which the task will be added.
     * @throws InvalidDescriptionException If the description for the Deadline task is empty.
     * @throws InvalidDeadlineException If there is an invalid deadline task given. (without a due date/time)
     * @throws Exception If an error occurs during execution.
     */
    public void addDeadline(String command, TaskList taskList) throws Exception {
        // indexOf: searches for the substring and returns the index of the first character
        if (command.contains(" /by ")) {
            // Description starting index = 9
            String description = command.substring(9, command.indexOf(" /by "));
            // From " " to the specified date is 5
            String by = command.substring(command.indexOf(" /by ") + 5);

            if (description.isEmpty()) {
                throw new InvalidDescriptionException("deadline");
            }

            if (by.isEmpty()) {
                throw new InvalidDeadlineException();
            }
            Task newTask = new Deadline(description, by);
            taskList.addTask(newTask);

            ui.showTaskAdded(newTask, taskList.getSize());

        } else {
            throw new InvalidDeadlineException();
        }
    }

    /**
     * Adds a new Event task to the task list and displays a message to the user.
     *
     * @param command The user command.
     * @param taskList The list of tasks to which the task will be added.
     * @throws InvalidDescriptionException If the description for the Deadline task is empty.
     * @throws InvalidEventException If there is an invalid event task given. (without a start and/or end date/time)
     * @throws Exception If an error occurs during execution.
     */
    public void addEvent(String command, TaskList taskList) throws Exception {
        if (command.contains(" /from ") && command.contains(" /to ")) {
            // Description starting index = 6
            String description = command.substring(6, command.indexOf(" /from "));
            // From " " to 'from' date is 7
            String from = command.substring(command.indexOf(" /from ") + 7, command.indexOf(" /to "));
            // From " " to 'to' date is 5
            String to = command.substring(command.indexOf(" /to ") + 5);

            if (description.isEmpty()) {
                throw new InvalidDescriptionException("event");
            }

            if (from.isEmpty() || to.isEmpty()) {
                throw new InvalidEventException();
            }

            Task newTask = new Event(description, from, to);
            taskList.addTask(newTask);

            ui.showTaskAdded(newTask, taskList.getSize());

        } else {
            throw new InvalidEventException();
        }
    }

    /**
     * Deletes a task from the task list and displays a message to the user.
     *
     * @param command The user command.
     * @param taskList The list of tasks to which the task will be deleted from.
     * @throws InvalidNumberException If there is an invalid number given.
     * @throws Exception If an error occurs during execution.
     */
    public void deleteTask(String command, TaskList taskList) throws Exception {
        // Number index = 7
        // Convert substring to int, -1 for index
        int taskPos = Integer.parseInt(command.substring(7)) - 1;
        if (taskPos >= 0 && taskPos < taskList.getSize()) {
            Task deleted = taskList.getTask(taskPos);
            taskList.removeTask(taskPos);

            ui.showTaskDeleted(deleted, taskList.getSize());

        } else {
            throw new InvalidNumberException();
        }

    }

}