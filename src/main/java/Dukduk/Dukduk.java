package dukduk;

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
     * Manages the reply for inputs to dukduk chatbot.
     */
    public String reply(String input) {
        try {
            Parser parser = new Parser(input);
            String firstInput = parser.getCommand();
            switch (firstInput) {
                case "bye":
                    Storage.saveTasksToFile(filePath, this.tasks.getTasks());
                    return this.ui.printExit();
                case "list":
                    if (this.tasks.getTaskCount() == 0) {
                        System.out.println(" No tasks added yet.");
                    } else {
                        return this.ui.printTasks(this.tasks.getTasks());
                    }
                case "todo":
                case "deadline":
                case "event":
                    Task task = Parser.parseTask(input);
                    this.tasks.addTask(task);
                    Storage.saveTasksToFile(filePath, this.tasks.getTasks());
                    return this.ui.addTask(this.tasks.getTasks());
                case "mark":
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    this.tasks.markTaskAsDone(taskIndex);
                    Storage.saveTasksToFile(filePath, this.tasks.getTasks());
                    return this.ui.markAsDone(this.tasks.getTasks(), taskIndex);
                case "unmark":
                    int unmarkTaskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    this.tasks.unMarkTask(unmarkTaskIndex);
                    Storage.saveTasksToFile(filePath, this.tasks.getTasks());
                    return this.ui.markAsNotDone(this.tasks.getTasks(), unmarkTaskIndex);
                case "delete":
                    try {
                        String[] parts = input.split(" ");
                        if (parts.length != 2) {
                            throw new DukdukException("OOPS!!! Please specify the task number to delete.");
                        }
                        int deleteTaskIndex = Integer.parseInt(parts[1]) - 1;
                        return this.tasks.deleteTask(deleteTaskIndex);
                    } catch (DukdukException e) {
                        this.ui.printErrorMsg(e);
                    }
                case "find":
                    String keyword = input.substring(5).trim();
                    ArrayList<Task> matchingTasks = this.tasks.findTasks(keyword);
                    return this.ui.printTasksIfFound(matchingTasks);
                default:
                    return this.ui.printHelpMessage();
            }
        } catch (DukdukException e) {
            return this.ui.printErrorMsg(e);
        }
    }
}
