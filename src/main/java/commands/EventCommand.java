package commands;

import data.Actions;
import duke.DukeException;
import parser.Parser;
import tasks.Event;
import ui.UI;

public class EventCommand extends Command {
    private final String input;
    private final Parser parser;

    public EventCommand(String input, Parser parser) {
        this.input = input;
        this.parser = parser;
    }

    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new DukeException("tasks.Event format incorrect. "
                    + "\nFormat: event task /from d/M/yyyy HHmm /to d/M/yyyy HHmm");
        }
        String[] commandParts = parser.splitByKeyword(input, "/from");
        String[] eventParts = parser.splitByKeyword(commandParts[1], "/to");
        Event event = new Event(commandParts[0].trim(), eventParts[0].trim(), eventParts[1].trim());
        actionList.add(event);
        ui.lineSandwich("Got it. I've added this task:\n " + event + "\nNow you have "
                + actionList.size() + " tasks in the list.");
    }
}
