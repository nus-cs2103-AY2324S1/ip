package duke.command;

import duke.tasklist.TaskList;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import duke.exception.DukeException;

/**
 * Represents the actions needed if the user inputs a mark command.
 */
public class MarkCommand extends Command {

    String numberString;

    /**
     * Constructor for the mark command.

     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     * @param numberString Line of input from the CLI.
     */
    public MarkCommand(TaskList taskList, String numberString) {
        super(taskList);
        this.numberString = numberString;
    }

    /**
     * Method to be executed when this command is called.
     * Marks the specified task as being completed.
     */
    @Override
    public void execute() {
        Integer number = Integer.parseInt(this.numberString);

        Task task = taskList.mark(number);

        System.out.println("YONG has marked this task as completed! \n" + task.toString());
    }
}
