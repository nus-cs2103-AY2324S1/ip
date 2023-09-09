package yong.command;

import yong.tasklist.TaskList;

import yong.tasks.Task;

/**
 * Class representing actions run when the delete command is called by user.
 */
public class DeleteCommand extends Command {

    private String numberString;

    /**
     * Constructor for DeleteCommand
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     * @param numberString Parsed string input from the CLI, represents task to be deleted.
     */
    public DeleteCommand(TaskList taskList, String numberString) {
        super(taskList);
        this.numberString = numberString;
        assert (!numberString.equals(""));

    }

    /**
     * Method to be executed when this command is called.
     * Deletes the specified task by the user.
     */
    @Override
    public String execute() {
        Integer number = Integer.parseInt(numberString);

        Task task = taskList.delete(number);

        outputString = "YONG has deleted this task for you! \n" + task.toString();

        return outputString;

    }
}
