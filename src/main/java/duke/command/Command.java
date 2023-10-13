package duke.command;

import duke.Storage;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidIndexException;
import duke.exception.NoSuchCommandException;
import duke.exception.UnmatchedArgumentException;
import duke.task.TaskList;

public abstract class Command {

    /*
        The details of the command.
     */
    protected String detail;

    /**
     * Creates a new Command object with the command and details passed in.
     *
     * @param detail The details of user input.
     */
    public Command(String detail) {
        this.detail = detail;
    }

    public Command() {}

    /**
     * Executes the function based on the command and details provided.
     *
     * @param tasks The task list for addition of task.
     * @param storage The storage object for reading or writing tasks into a specific file.
     * @return Task object based on the input command.
     * @throws NoSuchCommandException When an invalid command is inputted.
     * @throws InvalidIndexException If an invalid task index is inputted.
     * @throws EmptyDescriptionException If a task description is not inputted.
     * @throws UnmatchedArgumentException If unmatched arguments are inputted.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws NoSuchCommandException, InvalidIndexException,
            UnmatchedArgumentException, EmptyDescriptionException;
}
