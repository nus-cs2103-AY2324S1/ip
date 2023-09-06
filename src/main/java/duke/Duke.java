package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeIndexOutOfBoundsException;
import duke.tasks.Task;

import java.time.format.DateTimeFormatter;

public class Duke {

    /** Time format for the Max bot */
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm");
    /** Ui object to interact with user */
    private Ui ui;
    /** TaskList object to store tasks */
    private TaskList taskList;
    /** Storage object to interact with data file(s) */
    private Storage storage;
    /** Parser object to parse user input */
    private Parser parser;

    /**
     * Constructs a bot.
     *
     * @param filePath Path of data file from root.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }

    /**
     * Initializes the bot.
     *
     * @param args Arguments to main.
     */
    public static void main(String[] args) {
        Duke max = new Duke("data/duke.txt");
        max.run();
    }

    /**
     * Runs the bot.
     */
    public void run() {
        ui.greet();
        try {
            while (true) {
                taskList = storage.readFromFile();
                String userInput = ui.getUserInput();

                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    ui.display("Here are the tasks in your list:\n" + taskList.toString());
                } else if (userInput.startsWith("mark")) {
                    // get index by splitting user input and get task at that index from list
                    int index = Integer.parseInt(userInput.split(" ")[1]);

                    if (index < 1 || index > taskList.getNumberOfTasks()) {
                        throw new DukeIndexOutOfBoundsException("marked");
                    }

                    Task toBeMarked = taskList.getTaskAt(index - 1);
                    toBeMarked.mark();
                    ui.display("Nice! I've marked this task as done:\n" + toBeMarked.toString());
                } else if (userInput.startsWith("unmark")) {
                    // get index by splitting user input and get task at that index from list
                    int index = Integer.parseInt(userInput.split(" ")[1]);

                    if (index < 1 || index > taskList.getNumberOfTasks()) {
                        throw new DukeIndexOutOfBoundsException("unmarked");
                    }

                    Task toBeUnmarked = taskList.getTaskAt(index - 1);
                    toBeUnmarked.unmark();
                    ui.display("OK, I've marked this task as not done yet:\n" + toBeUnmarked.toString());
                } else if (userInput.startsWith("delete")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]);

                    if (index < 1 || index > taskList.getNumberOfTasks()) {
                        throw new DukeIndexOutOfBoundsException("deleted");
                    }

                    Task toBeDeleted = taskList.getTaskAt(index - 1);
                    taskList.deleteTaskAt(index - 1);
                    ui.display("Noted. I've removed this task:\n" + toBeDeleted.toString()
                            + "\nNow you have " + taskList.getNumberOfTasks() + " tasks in the list.");
                } else if (userInput.startsWith("find")) {
                    TaskList filtered = parser.getTaskList(userInput, taskList);
                    ui.display("Here are the matching tasks in your list:\n" + filtered.toString());
                } else {
                    Task add = parser.getTask(userInput);
                    try {
                        taskList.addToList(add);
                        ui.display("Got it. I've added this task:\n" + add.toString()
                                + "\nNow you have " + taskList.getNumberOfTasks() + " tasks in the list.");
                    } catch (NullPointerException e) {
                        throw new DukeException("OOPS!!! Could not add task to the list");
                    }
                }

                storage.writeToFile(taskList);
            }
        } catch (DukeException e) {
            System.out.println("\n" + e.getMessage());
        } finally {
            ui.exit();
        }
    }
}
