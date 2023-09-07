package brandon.chatbot.commands;

import brandon.chatbot.tasks.TaskList;

/**
 * Represents the command that the user typed in.
 */
public class Command {

    protected TaskList tasks;

    /**
     * Constructs an instance of Command and assigns the parameter value to the tasks TaskList class member.
     *
     * @param tasks is a TaskList instance to be assigned to tasks class variable.
     */
    public Command(TaskList tasks) {
        this.tasks = tasks;
    }

    protected Command() {
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() throws Exception {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    /**
     * Assigns the TaskList parameter to the class variable tasks.
     * @param tasks
     */
    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }
}
