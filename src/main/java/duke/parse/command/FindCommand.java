package duke.parse.command;

import duke.Duke;

public class FindCommand implements Command {
    private String query;

    /**
     * Instantiates a find command with the given query.
     * @param query the search parameter
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Commands the bot to find the tasks matching the query.
     * @param bot the bot to execute the command
     * @return true, as it allows the user to continue the programme
     */
    @Override
    public boolean execute(Duke bot) {
        bot.find(this.query);
        return true;
    }
}
