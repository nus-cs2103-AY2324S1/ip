package commands;

import data.Actions;
import duke.DukeException;
import parser.Parser;
import tasks.Event;
import ui.UI;

/**
 * Represents the command to add an event task.
 */
public class EventCommand extends Command {
    private final String input;
    private final Parser parser;

    /**
     * Initializes an EventCommand with the given input string and parser.
     *
     * @param input The input string.
     * @param parser The parser used to parse the input.
     */
    public EventCommand(String input, Parser parser) {
        this.input = input;
        this.parser = parser;
    }

    private Event parseEvent(String input) throws DukeException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new DukeException(" Event format incorrect. "
                    + "\n Format: event task /from d/M/yyyy HHmm /to d/M/yyyy HHmm");
        }
        String[] commandParts = parser.splitByKeyword(input, "/from");
        String[] eventParts = parser.splitByKeyword(commandParts[1], "/to");
        return new Event(commandParts[0].trim(), eventParts[0].trim(), eventParts[1].trim());
    }

    /**
     * Executes the EventCommand by adding an event task to the task list.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks.
     * @throws DukeException To handle incorrect input format.
     */
    @Override
    public String executeCommand(UI ui, Actions actionList) throws DukeException {
        Event event = parseEvent(input);
        actionList.add(event);
        return " Got it. I've added this task:\n  " + event + "\n Now you have "
                + actionList.size() + " tasks in the list.";
    }
}
