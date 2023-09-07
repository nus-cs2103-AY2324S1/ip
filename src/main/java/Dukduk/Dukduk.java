package dukduk;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * The main class used for the Dukduk chatbot.
 */
public class Dukduk {

    private TaskList tasks;
    private Ui ui;
    private String filePath;

    /**
     * Constructs a new Dukduk chatbot with the specified file path.
     *
     * @param filePath The file path to load and save tasks.
     */
    public Dukduk(String filePath) {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.filePath = filePath;
        ArrayList<Task> loadedTasks = Storage.loadTasksFromFile(filePath);
        if (loadedTasks != null) {
            this.tasks.setTasks(loadedTasks);
        }
    }

    /**
     * Runs the Dukduk chatbot, handling user input and managing tasks.
     */
    public void run() {
        Ui.printGreetings();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(" ");
                String input = scanner.nextLine();
                Ui.printLine();

                if (input.equalsIgnoreCase("bye")) {
                    Ui.printExit();
                    scanner.close();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    if (this.tasks.getTaskCount() == 0) {
                        System.out.println(" No tasks added yet.");
                    } else {
                        Ui.printTasks(this.tasks.getTasks());
                    }
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    Task task = Parser.parseTask(input);
                    this.tasks.addTask(task);
                    Storage.saveTasksToFile(filePath, this.tasks.getTasks());
                    Ui.addTask(this.tasks.getTasks());
                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    this.tasks.markTaskAsDone(taskIndex);
                    Storage.saveTasksToFile(filePath, this.tasks.getTasks());
                    Ui.markAsDone(this.tasks.getTasks(), taskIndex);
                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    this.tasks.unMarkTask(taskIndex);
                    Storage.saveTasksToFile(filePath, this.tasks.getTasks());
                    Ui.markAsNotDone(this.tasks.getTasks(), taskIndex);
                } else if (input.startsWith("delete")) {
                    try {
                        String[] parts = input.split(" ");
                        if (parts.length != 2) {
                            throw new DukdukException("OOPS!!! Please specify the task number to delete.");
                        }
                        int taskIndex = Integer.parseInt(parts[1]) - 1;
                        this.tasks.deleteTask(taskIndex);
                        Storage.saveTasksToFile(filePath, this.tasks.getTasks());
                    } catch (DukdukException e) {
                        Ui.printErrorMsg(e);
                    }
                } else if (input.startsWith("find")) {
                    String keyword = input.substring(5).trim();
                    ArrayList<Task> matchingTasks = this.tasks.findTasks(keyword);
                    Ui.printTasksIfFound(matchingTasks);
                } else {
                    throw new DukdukException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                Ui.printLine();
            } catch (DukdukException e) {
                Ui.printErrorMsg(e);
                Ui.printLine();
            }
        }
    }

    /**
     * The main entry point for the Dukduk chatbot.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Dukduk("src/main/java/data/duke.txt").run();
    }
}
