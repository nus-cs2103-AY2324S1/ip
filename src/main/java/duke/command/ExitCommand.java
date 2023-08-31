package duke.command;

import duke.tasklist.TaskList;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import duke.exception.DukeException;

/**
 * Represents the actions needed if the user inputs an Exit command.
 * User input is "bye"
 */
public class ExitCommand extends Command {

    /**
     * Constructor for the deadline command.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     */
    public ExitCommand(TaskList taskList) {
        super(taskList);
        isExitCommand = true;
    }

    /**
     * Method to be executed when this command is called.
     * Prints out the end statement of the chatbot.
     */
    public void execute() {
        System.out.println("Thank you and have a good day!");
    }
}
