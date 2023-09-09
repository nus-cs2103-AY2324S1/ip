package yong.command;

import yong.tasklist.TaskList;

import yong.tasks.Task;

/**
 * Represents the actions needed if the user inputs a mark command.
 */
public class MarkCommand extends Command {

    private String numberString;

    /**
     * Constructor for the mark command.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     * @param numberString Line of input from the CLI.
     */
    public MarkCommand(TaskList taskList, String numberString) {
        super(taskList);
        this.numberString = numberString;
        assert (!numberString.equals(""));
    }

    /**
     * Method to be executed when this command is called.
     * Marks the specified task as being completed.
     */
    @Override
    public String execute() {
        Integer number = Integer.parseInt(this.numberString);

        Task task = taskList.mark(number);

        outputString = "YONG has marked this task as completed! \n" + task.toString();

        return outputString;
    }
}
