package duke.command;

import duke.OutOfRangeException;
import duke.SaveToFileException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.AlreadyMarkedException;
import duke.task.AlreadyUnmarkedException;

/**
 * The command for finding a string within tasks.
 */
public class FindCommand extends Command {

    private final String searchTerm;

    /**
     * Constructs a FindCommmand objet with the given search term.
     * The search term is copied verbatim from the user input.
     * Does not search for dates if the date is parsed into a Date.
     * @param searchTerm The term to search for.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveToFileException, OutOfRangeException, AlreadyMarkedException, AlreadyUnmarkedException {
        String results = tasks.find(searchTerm);
        ui.displayMessage(results);
    }

}
