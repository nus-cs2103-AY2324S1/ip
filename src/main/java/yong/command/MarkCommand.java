package yong.command;

import yong.tasklist.TaskList;

import yong.tasks.Task;

/**
 * Represents a command for marking a task as completed.
 */
public class MarkCommand extends Command {

    private String numberString;

    /**
     * Constructs a MarkCommand.
     *
     * @param taskList The TaskList object used to maintain the list of tasks in the chatbot.
     * @param numberString The input line from the command-line interface containing the task number to mark.
     */
    public MarkCommand(TaskList taskList, String numberString) {
        super(taskList);
        this.numberString = numberString;
        assert (!numberString.equals(""));
    }

    /**
     * Marks the specified task as completed.
     *
     * @return A message indicating that the task has been marked as completed.
     */
    @Override
    public String execute() {
        Integer number = Integer.parseInt(this.numberString);

        Task task = taskList.mark(number);

        outputString = "Task marked as completed! \n" + task.toString();

        return outputString;
    }
}
