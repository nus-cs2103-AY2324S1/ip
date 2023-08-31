package duke.command;

import duke.tasklist.TaskList;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import duke.exception.DukeException;

import duke.command.Command;

/**
 * Class representing actions run when the delete command is called by user.
 */
public class DeleteCommand extends Command {

    String numberString;

    /**
     * Constructor for DeleteCommand
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     * @param numberString Parsed string input from the CLI, represents task to be deleted.
     */
    public DeleteCommand(TaskList taskList, String numberString) {
        super(taskList);
        this.numberString = numberString;

    }

    /**
     * Method to be executed when this command is called.
     * Deletes the specified task by the user.
     */
    @Override
    public void execute() {
        Integer number = Integer.parseInt(numberString);

        Task task = taskList.delete(number);

        System.out.println("YONG has deleted this task for you! \n" + task.toString());

    }
}
