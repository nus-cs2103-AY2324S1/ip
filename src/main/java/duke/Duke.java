package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * A chatbot that provides a to-do list function
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor for the chatbot
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
     * Run the chatbot
     */
    public void run() {
        this.ui.printGreeting();

        Scanner sc = new Scanner(System.in);
        // Only exit when user types the command bye
        label:
        while (true) {
            if (sc.hasNextLine()) {
                String input = sc.nextLine();
                Parser parser = new Parser(input);
                String firstWord = parser.getCommand();

                switch (firstWord) {
                case "bye":
                    try {
                        this.storage.saveTasks(this.tasks.getTasks());
                    } catch (IOException exception) {
                        System.out.println(exception.getMessage());
                    }
                    this.ui.printFarewell();
                    sc.close();
                    break label;
                // Display the stored commands
                case "list":
                    if (this.tasks.getNumberOfTasks() == 0) {
                        System.out.println("You do not have any tasks in the list.");
                        break;
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < this.tasks.getNumberOfTasks(); i++) {
                        System.out.println(i + 1 + "." + this.tasks.getTasks().get(i).toString());
                    }
                    break;
                // Add task
                case "todo":
                    try {
                        this.tasks.addTask(parser.getTodoTask(), "todo");
                    } catch (DukeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        this.tasks.addTask(parser.getDeadlineTask(), "deadline");
                    } catch (DukeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "event":
                    try {
                        this.tasks.addTask(parser.getEventTask(), "event");
                    } catch (DukeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                // Mark task as done
                case "mark":
                    try {
                        this.tasks.markTask(parser.getTaskNumber(), "mark");
                    } catch (DukeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                // Mark task as not done
                case "unmark":
                    try {
                        this.tasks.markTask(parser.getTaskNumber(), "unmark");
                    } catch (DukeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                // Remove task
                case "delete":
                    try {
                        this.tasks.removeTask(parser.getTaskNumber());
                    } catch (DukeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                // Find task
                case "find":
                    try {
                        this.tasks.findTask(parser.getSearchKeyword());
                    } catch (DukeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                // Invalid command
                default:
                    this.ui.printAllCommands();
                    break;
                }
            }
        }
    }

    /**
     * The main method to run the chatbot
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}
