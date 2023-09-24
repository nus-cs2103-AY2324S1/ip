package commands;

import data.Actions;
import duke.DukeException;
import parser.Parser;
import tasks.Deadline;
import ui.UI;

/**
 * Represents the command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private final String input;
    private final Parser parser;

    /**
     * Initializes a DeadlineCommand with the given input string and parser.
     *
     * @param input The input string.
     * @param parser The parser used to parse the input.
     */
    public DeadlineCommand(String input, Parser parser) {
        this.input = input;
        this.parser = parser;
    }

    /**
     * Executes the DeadlineCommand by adding a deadline task to the task list.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks.
     * @throws DukeException To handle incorrect input format.
     */
    @Override
    public String executeCommand(UI ui, Actions actionList) throws DukeException {
        if (!input.contains("/by")) {
            throw new DukeException(" Deadline format incorrect. "
                    + "\n Format: deadline task /by d/M/yyyy HHmm");
        }
        String[] deadlineParts = parser.splitByKeyword(input, "/by");
        String description = deadlineParts[0].trim();
        Deadline deadline = new Deadline(description, deadlineParts[1].trim());
        actionList.add(deadline);
        return " Got it. I've added this task:\n  "
                + deadline + "\n Now you have " + actionList.size() + " tasks in the list.";
    }
}
