package duke;

/**
 * Ends a session with the Duke bot.
 */
public class Bye extends Command {

    //The tasklist used to save all tasks.
    private TaskList tasks;

    //The trivialist used to save all trivia.
    private TriviaList trivia;

    /**
     * Instantiates a Bye command.
     * @param tasks the tasklist used to save tasks.
     */
    public Bye(TaskList tasks, TriviaList trivia) {
        this.tasks = tasks;
        this.trivia = trivia;
    }

    /**
     * Saves all tasks in the tasklist and trivialist.
     * @return A message closing the session with the DukeBot.
     */
    @Override
    public String execute() {
        tasks.save();
        trivia.save();
        return Ui.bye();
    }
}
