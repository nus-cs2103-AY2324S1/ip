package duke;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Used to parse user input into appropriate commands.
 *
 * @author Teo Kai Sheng
 */
public class Parser {
    private TaskList tasks;
    private boolean isFinished;

    /**
     * Constructor to create a Parser.
     *
     * @param tasks Instance of TaskList to handle commands.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.isFinished = false;
    }

    /**
     * Parses the user input and calls the appropriate task command.
     *
     * @param s User input picked up by the scanner.
     * @return The String representation of the output.
     */
    public String parse(String s) {
        String[] input = s.split(" ", 2);
        String command = input[0];
        String output = "";
        Ui.horizontalLine();
        switch (command) {
        case "list":
            output = tasks.showList(input);
            break;
        case "mark":
            output = tasks.markTask(input);
            break;
        case "unmark":
            output = tasks.unmarkTask(input);
            break;
        case "delete":
            output = tasks.deleteTask(input);
            break;
        case "deadline":
            output = tasks.addDeadline(input);
            break;
        case "event":
            output = tasks.addEvent(input);
            break;
        case "todo":
            output = tasks.addToDo(input);
            break;
        case "find":
            output = tasks.findTasks(input);
            break;
        case "update":
            output = tasks.updateTask(input);
            break;
        case "bye":
            this.isFinished = true;
            output = Ui.bye();
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            break;
        default:
            output = Ui.unknownCommandErrorMessage();
        }
        Ui.horizontalLine();
        assert output != null : "Output should not be null";
        return output;
    }

    /**
     * Indicates if parsing should stop.
     *
     * @return A boolean value to indicate when to stop asking for user input.
     */
    public boolean isDone() {
        return isFinished;
    }
}
