package ben;

import java.util.Arrays;

/**
 * Represents a file command.
 */
public class FindCommand extends Command {
    /**
     * The search string.
     */
    private String search;
    /**
     * The command string.
     */
    private String command;

    /**
     * Constructor taking in a command from the user.
     *
     * @param command The command from the user,
     */
    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Extracts the search portion of the Find command.
     *
     * @param command The command from the user.
     * @throws EmptyDescriptionException Error if description is empty.
     */
    public void extractSearch(String command) throws EmptyDescriptionException {
        String[] words = command.toLowerCase().split("\\s+");
        String search = String.join(" ", Arrays.copyOfRange(words, 1, words.length));

        if (search.isEmpty()) {
            throw new EmptyDescriptionException("Description cannot be empty");
        }

        this.search = search;
    }

    /**
     * Checks whether command causes chatbot to exit.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the find command.
     *
     * @param tasks The task list.
     * @param ui    The UI.
     * @throws EmptyDescriptionException Error thrown when description if empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws EmptyDescriptionException {
        TaskList filteredTasks = new TaskList();
        extractSearch(command);

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().toLowerCase().contains(search)) {
                filteredTasks.add(tasks.get(i), false);
            }
        }

        if (filteredTasks.size() == 0) {
            Ui.displayMessage("\n" + "Sorry, no tasks seems to match: " + search + "\n");
        } else {
            Ui.displayMessage("\n" + "Here are the matching tasks!" + "\n" + filteredTasks);
        }
    }
}
