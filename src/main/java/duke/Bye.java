package duke;

/**
 * Ends a session with the Duke bot.
 */
public class Bye extends Command {

    //The tasklist used to save all tasks.
    private TaskList taskList;

    /**
     * Instantiates a Bye command
     * @param taskList the tasklist used to save tasks.
     */
    public Bye(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Saves all tasks in the tasklist.
     * @return A message closing the session with the DukeBot.
     */
    @Override
    public String execute() {
        taskList.save();
        return Ui.bye();
    }
}
