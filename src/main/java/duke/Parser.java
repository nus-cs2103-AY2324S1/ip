package duke;


import javafx.application.Platform;

/**
 * Used to parse user input into appropriate commands.
 *
 * @author Teo Kai Sheng
 */
public class Parser {
    private Ui ui;
    private TaskList tasks;
    private boolean isFinished;

    /**
     * Constructor to create a Parser.
     *
     * @param ui Instance to handle the printing of user interface.
     * @param tasks Instance of TaskList to handle commands.
     */
    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
        this.isFinished = false;
    }

    /**
     * Parses the user input and calls the appropriate task command.
     *
     * @param s User input picked up by the scanner.
     */
    public String parse(String s) {
        String[] input = s.split(" ", 2);
        String command = input[0];
        ui.horizontalLine();
        if (command.equals("list")) {
            return tasks.showList(input);
        } else if (command.equals("mark")) {
            return tasks.markTask(input);
        } else if (command.equals("unmark")) {
            return tasks.unmarkTask(input);
        } else if (command.equals("delete")) {
            return tasks.deleteTask(input);
        } else if (command.equals("deadline")) {
            return tasks.addDeadline(input);
        } else if (command.equals("event")) {
            return tasks.addEvent(input);
        } else if (command.equals("todo")) {
            return tasks.addToDo(input);
        } else if (command.equals("find")) {
            return tasks.findTasks(input);
        } else if (command.equals("bye")) {
            this.isFinished = true;
            ui.bye();
            Platform.exit();
        } else {
            ui.printErrorMessage();
        }
        ui.horizontalLine();
        return ui.printErrorMessage();
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
