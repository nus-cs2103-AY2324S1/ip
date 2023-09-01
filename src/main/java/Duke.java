import ip.parser.Parser;
import ip.storage.Storage;
import ip.tasks.*;
import ip.exception.UnknownCommandException;
import ip.exception.EmptyDescriptionException;
import ip.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {
    private static final String DATA_PATH = "./data";
    private static final String FILE_PATH = DATA_PATH + "/duke.txt";
    private static final Ui ui = new Ui();
    private final Storage storage = new Storage(FILE_PATH);
    private final TaskList tasks;
    private static final String line = "_________________________\n";

    Duke() throws IOException {
        this.tasks = new TaskList(storage.loadTasks());
    }

    // Delete task
    public void deleteTask(String taskIndex) throws IOException {
        int index = Integer.parseInt(taskIndex) - 1;
        Task removedTask = tasks.removeTask(index); // Removes and retrieves the task from the list
        System.out.println(line +
                "Noted. I've removed this task:\n  " +
                removedTask +
                "\nNow you have " +
                tasks.getSize() +
                " tasks in the list.\n" +
                line);
        storage.saveTasks(tasks.getTasks());
    }

    public void addTodo(String task) throws IOException {
        Task newTask = new Todo(task);
        tasks.addTask(newTask);
        System.out.println(line + "Got it. I've added this task:\n  " +
                newTask +
                "\nNow you have " +
                tasks.getSize() +
                " tasks in the list.\n" +
                line);
        storage.saveTasks(tasks.getTasks());
    }

    public void addDeadline(String task) throws DateTimeParseException, IOException {
        String[] parts = task.split(" /by ");
        if (parts.length < 2) {
            System.out.println(line + "Error: Please use the format 'deadline <task description> " +
                    "/by yyyy-MM-dd'\n" + line);
            return;
        }
        LocalDate.parse(parts[1]); // This will throw an exception if the date format is invalid
        Task newTask = new Deadline(parts[0], parts[1]);
        tasks.addTask(newTask);
        System.out.println(line +
                "Got it. I've added this task:\n  " +
                newTask +
                "\nNow you have " +
                tasks.getSize() +
                " tasks in the list.\n" + line);
        storage.saveTasks(tasks.getTasks());
    }

    public void addEvent(String task) throws DateTimeParseException, IOException {
        String[] parts = task.split(" /from "); // second part will consist the timings
        if (parts.length < 2) {
            System.out.println(line + "Error: Please use the format 'event <event description> /from yyyy-MM-dd " +
                    "/to yyyy-MM-dd'\n" + line);
            return;
        }
        String[] times = parts[1].split(" /to ");
        if (times.length < 2) {
            System.out.println(line + "Error: Please use the format 'event <event description> /from yyyy-MM-dd " +
                    "/to yyyy-MM-dd'\n" + line);
            return;
        }
        // Throws exception if invalid format
        LocalDate.parse(times[0]);
        LocalDate.parse(times[1]);
        Task newTask = new Event(parts[0], times[0], times[1]);
        tasks.addTask(newTask);
        System.out.println(line +
                "Got it. I've added this task:\n  " +
                newTask +
                "\nNow you have " +
                tasks.getSize() +
                " tasks in the list.\n" +
                line);
        storage.saveTasks(tasks.getTasks());
    }

    public void listTasks() {
        System.out.println(line + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(line);
    }

    public void markTaskDone(String task) throws IOException {
        int index = Integer.parseInt(task) - 1; // string -> int
        Task taskMarked = tasks.markDone(index); // Update the task in the list
        System.out.println(line + "\nNice! I've marked this task as done:\n  " +
                taskMarked +
                "\n" +
                line);
        storage.saveTasks(tasks.getTasks());
    }

    public void unmarkTaskDone(String task) throws IOException {
        int index = Integer.parseInt(task) - 1; // string -> int
        Task taskUnmarked = tasks.unmarkDone(index); // Update the task in the list
        System.out.println(line +
                "\nOK, I've marked this task as not done yet:\n  " +
                taskUnmarked +
                "\n" +
                line);
        storage.saveTasks(tasks.getTasks());
    }

    public boolean processInput(String input) throws EmptyDescriptionException, UnknownCommandException, DateTimeParseException, IOException {
        String[] words = Parser.parseCommand(input);
        switch (words[0]) {
            case "bye":
                ui.printMessage(line + "Bye. Hope to see you again soon!\n" + line);
                return false;
            case "delete":
                deleteTask(words[1]); // refactored logic to a new method
                break;
            case "help":
                ui.printMessage(line + "\nCommands:\n" +
                        "- To add a todo: 'todo [description]'\n" +
                        "- To add a deadline: 'deadline [description] /by [date in format yyyy-MM-dd]'\n" +
                        "- To add an event: 'event [description] /from [start date in format yyyy-MM-dd] /to " +
                        "[end date in format yyyy-MM-dd]'\n" +
                        line);
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
            default:
                // Handle any other cases or errors
                break;
        }
        return true;
    }

    public static void main(String[] args) {
        String logo = ">. <\n";
        String name = "your father";
        System.out.println(logo +
                line +
                "Hello! I'm " + name + "\n" +
                "What can I do for you?\n" +
                line);

        Duke duke = null;
        try {
            duke = new Duke();
        } catch (IOException e) {
            ui.showError(e.getMessage());
            return; // Exit the program if there's an IOException when initializing Duke
        }

        boolean runningState = true;
        while (runningState) {
            try {
                String input = ui.readCommand();
                runningState = duke.processInput(input);
            } catch (UnknownCommandException | EmptyDescriptionException e) {
                ui.showError(line + e.getMessage() + line);
            } catch (DateTimeParseException e) {
                ui.showError(line + "Please enter the date in the format 'yyyy-MM-dd'.\n" + line);
            } catch (IOException e) {
                ui.showError(e.getMessage());
                runningState = false;  // Stop the loop if there's an IOException
            }
        }
    }
}