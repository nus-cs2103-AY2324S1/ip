package duke;

import javafx.application.Platform;

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
        if (command.equals("list")) {
            output = tasks.showList(input);
        } else if (command.equals("mark")) {
            output = tasks.markTask(input);
        } else if (command.equals("unmark")) {
            output = tasks.unmarkTask(input);
        } else if (command.equals("delete")) {
            output = tasks.deleteTask(input);
        } else if (command.equals("deadline")) {
            output = tasks.addDeadline(input);
        } else if (command.equals("event")) {
            output = tasks.addEvent(input);
        } else if (command.equals("todo")) {
            output = tasks.addToDo(input);
        } else if (command.equals("find")) {
            output = tasks.findTasks(input);
        } else if (command.equals("update")) {
            output = tasks.updateTask(input);
        } else if (command.equals("bye")) {
            this.isFinished = true;
            output = Ui.bye();
            Platform.exit();
        } else {
            output = Ui.unknownCommandErrorMessage();
        }
        Ui.horizontalLine();
        assert output != null;
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
