package dukduk;

import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * The main class used for the Dukduk chatbot.
 */
public class Dukduk {

    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Dukduk chatbot with the specified file path.
     *
     */
    public Dukduk() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        Storage.createDataLocation();
        ArrayList<Task> loadedTasks = Storage.loadTasksFromFile();
        if (loadedTasks != null) {
            this.tasks.setTasks(loadedTasks);
        }
    }

    /**
     * Manages the reply for inputs to dukduk chatbot.
     */
    public String reply(String input, Stage stage) {
        try {
            Parser parser = new Parser(input);
            String firstInput = parser.getCommand();
            switch (firstInput) {
                case "bye":
                    Storage.saveTasksToFile(this.tasks.getTasks());
                    PauseTransition delay = new PauseTransition(Duration.seconds(1));
                    delay.setOnFinished(event -> {
                        Platform.runLater(() -> stage.close());
                    });
                    delay.play();
                    return this.ui.printExit();
                case "list":
                    if (this.tasks.getTaskCount() == 0) {
                        throw new DukdukException("QUACKKK!!! No tasks has been added yet.");
                    } else {
                        return this.ui.printTasks(this.tasks.getTasks());
                    }
                case "todo":
                case "deadline":
                case "event":
                    Task task = Parser.parseTask(input);
                    this.tasks.addTask(task);
                    Storage.saveTasksToFile(this.tasks.getTasks());
                    return this.ui.addTask(this.tasks.getTasks());
                case "mark":
                    if (input.length() <= firstInput.length()) {
                        throw new DukdukException("QUACKKK!!! Please provide additional information for the '" + firstInput + "' command.");
                    }
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    this.tasks.markTaskAsDone(taskIndex);
                    Storage.saveTasksToFile(this.tasks.getTasks());
                    return this.ui.markAsDone(this.tasks.getTasks(), taskIndex);
                case "unmark":
                    if (input.length() <= firstInput.length()) {
                        throw new DukdukException("QUACKKK!!! Please provide additional information for the '" + firstInput + "' command.");
                    }
                    int unmarkTaskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    this.tasks.unMarkTask(unmarkTaskIndex);
                    Storage.saveTasksToFile(this.tasks.getTasks());
                    return this.ui.markAsNotDone(this.tasks.getTasks(), unmarkTaskIndex);
                case "delete":
                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                            throw new DukdukException("QUACKKK!!! Please specify the task number to delete.");
                    }
                    int deleteTaskIndex = Integer.parseInt(parts[1]) - 1;
                    return this.tasks.deleteTask(deleteTaskIndex);
                case "find":
                    if (input.length() <= firstInput.length()) {
                        throw new DukdukException("QUACKKK!!! Please provide additional information for the '" + firstInput + "' command.");
                    }
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


