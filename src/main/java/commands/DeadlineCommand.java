package commands;

import data.Actions;
import duke.DukeException;
import parser.Parser;
import tasks.Deadline;
import ui.UI;

public class DeadlineCommand extends Command {
    private final String input;
    private final Parser parser;

    public DeadlineCommand(String input, Parser parser) {
        this.input = input;
        this.parser = parser;
    }

    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        if (!input.contains("/by")) {
            throw new DukeException(" tasks.Event format incorrect. "
                    + "\n Format: deadline task /by d/M/yyyy HHmm");
        }
        String[] deadlineParts = parser.splitByKeyword(input, "/by");
        String description = deadlineParts[0].trim();
        Deadline deadline = new Deadline(description, deadlineParts[1].trim());
        actionList.add(deadline);
        ui.lineSandwich("Got it. I've added this task:\n "
                + deadline + "\nNow you have " + actionList.size() + " tasks in the list.");
    }
}
