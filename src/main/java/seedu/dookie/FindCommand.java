package seedu.dookie;

import java.util.ArrayList;

/**
 * Encapsulates the Find Command.
 */
public class FindCommand extends Command {
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new FindCommand instance.
     *
     * @param parser The parser being used.
     * @param tasks The accumulated tasks.
     * @param ui The Ui being used.
     */
    public FindCommand(Parser parser, TaskList tasks, Ui ui) {
        this.parser = parser;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Executes the code corresponding to a find command.
     *
     * @param cmd The user input.
     * @return A string containing the found results.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     */
    public String execute(String cmd) throws InvalidDescriptionException {
        if (parser.descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("find");
        }

        String keyword = cmd.split(" ", 2)[1];
        ArrayList<Task> results = tasks.find(keyword);
        return ui.getFindResults(results);
    }
}
