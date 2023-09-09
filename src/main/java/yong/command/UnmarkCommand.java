package yong.command;

import yong.tasklist.TaskList;

import yong.tasks.Task;

/**
 * Represents the actions needed if the user inputs an unmark command.
 */
public class UnmarkCommand extends Command {

    private String numberString;

    /**
     * Constructor for the unmark command.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     * @param numberString Parsed number input from the CLI.
     */
    public UnmarkCommand(TaskList taskList, String numberString) {
        super(taskList);
        this.numberString = numberString;
        assert (!numberString.equals(""));
    }

    /**
     * Method to be executed when this command is called.
     * Initializes a new Deadline object and adds it to the tasklist.
     */
    @Override
    public String execute() {
        Integer number = Integer.parseInt(this.numberString);

        Task task = taskList.unmark(number);

        outputString = "YONG has unmarked this task successfully! \n" + task.toString() + "\n";

        return outputString;
    }
}
