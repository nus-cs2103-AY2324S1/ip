package yong.command;

import yong.tasklist.TaskList;

import yong.tasks.Task;

/**
 * Represents a command for unmarking a completed task.
 */
public class UnmarkCommand extends Command {

    private String numberString;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param taskList The TaskList object used to maintain the list of tasks in the chatbot.
     * @param numberString The parsed number input from the command-line interface.
     */
    public UnmarkCommand(TaskList taskList, String numberString) {
        super(taskList);
        this.numberString = numberString;
        assert (!numberString.equals(""));
    }

    /**
     * Unmarks the specified task as not completed.
     *
     * @return A message indicating that the task has been unmarked successfully.
     */
    @Override
    public String execute() {
        Integer number = Integer.parseInt(this.numberString);

        Task task = taskList.unmark(number);

        outputString = "Task unmarked successfully! \n" + task.toString() + "\n";

        return outputString;
    }
}

