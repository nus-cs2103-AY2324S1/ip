import duke.Parser;
import duke.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.tasks.Deadline;

import duke.exception.UnknownCommandException;
import duke.exception.EmptyDescriptionException;
import duke.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the Duke chat bot.
 * A <code>Duke</code> object corresponds to a chat bot that can process user input and respond accordingly.
 */
public class Duke {
    private static final String DATA_PATH = "./data";
    private static final String FILE_PATH = DATA_PATH + "/duke.txt";
    private static final Ui ui = new Ui();
    private final Storage storage = new Storage(FILE_PATH);
    private final TaskList tasks;
    private static final String LINE = "_________________________\n";

    /**
     * Constructs a <code>Duke</code> object.
     * @throws IOException If there is an error loading the tasks from the file.
     */
    Duke() throws IOException {
        this.tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Deletes a task from the list.
     * @param taskIndex The index of the task to be deleted.
     * @throws IOException If there is an error saving the tasks to the file.
     */
    public void deleteTask(String taskIndex) throws IOException {
        int index = Integer.parseInt(taskIndex) - 1;
        Task removedTask = tasks.removeTask(index); // Removes and retrieves the task from the list
        System.out.println(LINE
                + "Noted. I've removed this task:\n  " + removedTask
                + "\nNow you have " + tasks.getSize() + " tasks in the list.\n"
                + LINE);
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Adds a todo task to the list.
     * @param task The description of the todo task.
     * @throws IOException If there is an error saving the tasks to the file.
     */
    public void addTodo(String task) throws IOException {
        Task newTask = new Todo(task);
        tasks.addTask(newTask);
        System.out.println(LINE
                + "Got it. I've added this task:\n  " + newTask
                + "\nNow you have " + tasks.getSize() + " tasks in the list.\n"
                + LINE);
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Adds a deadline task to the list.
     * @param task The description of the deadline task.
     * @throws DateTimeParseException If the date format is invalid.
     * @throws IOException If there is an error saving the tasks to the file.
     */
    public void addDeadline(String task) throws DateTimeParseException, IOException {
        String[] parts = task.split(" /by ");
        if (parts.length < 2) {
            System.out.println(LINE + "Please use the format 'deadline <task description> "
                    + "/by yyyy-MM-dd'\n" + LINE);
            return;
        }
        LocalDate.parse(parts[1]); // This will throw an exception if the date format is invalid
        Task newTask = new Deadline(parts[0], parts[1]);
        tasks.addTask(newTask);
        System.out.println(LINE
                + "Got it. I've added this task:\n  " + newTask
                + "\nNow you have " + tasks.getSize() + " tasks in the list.\n"
                + LINE);
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Adds an event task to the list.
     * @param task The description of the event task.
     * @throws DateTimeParseException If the date format is invalid.
     * @throws IOException If there is an error saving the tasks to the file.
     */
    public void addEvent(String task) throws DateTimeParseException, IOException {
        String[] parts = task.split(" /from "); // second part will consist the timings
        if (parts.length < 2) {
            System.out.println(LINE + "Please use the format 'event <event description> /from yyyy-MM-dd "
                    + "/to yyyy-MM-dd'\n" + LINE);
            return;
        }
        String[] times = parts[1].split(" /to ");
        if (times.length < 2) {
            System.out.println(LINE + "Please use the format 'event <event description> /from yyyy-MM-dd "
                    + "/to yyyy-MM-dd'\n" + LINE);
            return;
        }
        // Throws exception if invalid format
        LocalDate.parse(times[0]);
        LocalDate.parse(times[1]);
        Task newTask = new Event(parts[0], times[0], times[1]);
        tasks.addTask(newTask);
        System.out.println(LINE +
                "Got it. I've added this task:\n  " + newTask
                + "\nNow you have " + tasks.getSize() + " tasks in the list.\n"
                + LINE);
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Lists all the tasks in the list.
     */
    public void listTasks() {
        System.out.println(LINE + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(LINE);
    }

    /**
     * Marks a task as done.
     * @param task The index of the task to be marked as done.
     * @throws IOException If there is an error saving the tasks to the file.
     */
    public void markTaskDone(String task) throws IOException {
        int index = Integer.parseInt(task) - 1; // string -> int
        Task taskMarked = tasks.markDone(index); // Update the task in the list
        System.out.println(LINE + "\nNice! I've marked this task as done:\n  " + taskMarked + "\n"
                + LINE);
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Marks a task as not done.
     * @param task The index of the task to be marked as not done.
     * @throws IOException If there is an error saving the tasks to the file.
     */
    public void unmarkTaskDone(String task) throws IOException {
        int index = Integer.parseInt(task) - 1; // string -> int
        Task taskUnmarked = tasks.unmarkDone(index); // Update the task in the list
        System.out.println(LINE
                + "\nOK, I've marked this task as not done yet:\n  " + taskUnmarked + "\n"
                + LINE);
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Finds and lists all tasks that contain the keyword.
     *
     * @param keyword The keyword to search for.
     */
    public void findAndListTasks(String keyword) {
        ArrayList<Task> matchedTasks = tasks.findTasks(keyword);
        if (matchedTasks.size() == 0) {
            System.out.println(LINE + "No tasks found with the keyword: " + keyword + "\n" + LINE);
            return;
        }
        System.out.println(LINE + "Here are the matching tasks in your list:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchedTasks.get(i));
        }
        System.out.println(LINE);
    }
  
    /**
     * Processes the user input and responds accordingly.
     * @param input The user input.
     * @return <code>true</code> if the program should continue running, <code>false</code> otherwise.
     * @throws EmptyDescriptionException If the user input is empty.
     * @throws UnknownCommandException If the user input is not recognized.
     * @throws DateTimeParseException If the date format is invalid.
     * @throws IOException If there is an error saving the tasks to the file.
     */
    public boolean processInput(String input) 
            throws EmptyDescriptionException, UnknownCommandException, DateTimeParseException, IOException {
        String[] words = Parser.parseCommand(input);
        switch (words[0]) {
        case "bye":
            ui.printMessage(LINE + "Bye. Hope to see you again soon!\n" + LINE);
            return false;
        case "delete":
            deleteTask(words[1]);
            break;
        case "help":
            ui.printMessage(LINE + "\nCommands:\n"
                    + "- To add a todo: 'todo [description]'\n"
                    + "- To add a deadline: 'deadline [description] /by [date in format yyyy-MM-dd]'\n"
                    + "- To add an event: 'event [description] /from [start date in format yyyy-MM-dd] /to "
                    + "[end date in format yyyy-MM-dd]'\n"
                    + LINE);
            break;
        case "todo":
            addTodo(words[1]);
            break;
        case "deadline":
            addDeadline(words[1]);
            break;
        case "event":
            addEvent(words[1]);
            break;
        case "list":
            listTasks();
            break;
        case "mark":
            markTaskDone(words[1]);
            break;
        case "unmark":
            unmarkTaskDone(words[1]);
            break;
        case "find":
            findAndListTasks(words[1]);
            break;
        default:
            // Handle any other cases or errors
            break;
        }
        return true;
    }

    /**
     * Runs the Duke chat bot.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        String logo = ">. <\n";
        String name = "your father";
        System.out.println(logo
                + LINE
                + "Hello! I'm " + name + "\n"
                + "What can I do for you?\n"
                + LINE);

        Duke duke;

        try {
            duke = new Duke();
        } catch (IOException e) {
            ui.showError(e.getMessage());
            return; // Exit the program if there's an IOException when initializing Duke
        }

        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.readCommand();
                isRunning = duke.processInput(input);
            } catch (UnknownCommandException | EmptyDescriptionException e) {
                ui.showError(LINE + e.getMessage() + LINE);
            } catch (DateTimeParseException e) {
                ui.showError(LINE + "Please enter the date in the format 'yyyy-MM-dd'.\n" + LINE);
            } catch (IOException e) {
                ui.showError(e.getMessage());
                isRunning = false;  // Stop the loop if there's an IOException
            }
        }
    }
}