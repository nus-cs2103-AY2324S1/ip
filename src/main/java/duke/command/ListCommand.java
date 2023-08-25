package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.UI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ListCommand implements Command {

    private static final String commandString = "list";
    private final String argument;

    public ListCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void validate(String arguments) throws DukeException {
        if (arguments != null) {
            // Non null argument, check format
            if (arguments.equals("now")) {
                return;
            }

            // User Specified Date, check date
            try {
                LocalDate date = LocalDate.parse(arguments);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid Date Format Provided, expected either:\n\tlist\n\tlist now\n\tlist YYYY-MM-DD");
            }
        }
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        validate(this.argument);
        LocalDate date;
        if (this.argument != null) {
            if (this.argument.equals("now")) {
                date = LocalDate.now().plusWeeks(1L);
            } else {
                date = LocalDate.parse(this.argument);
            }
        } else {
            date = LocalDate.MAX;
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).isBefore(date)) {
                output.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
        }
        if (output.length() == 0) {
            UI.sendMessage("No Items in List");
        } else {
            UI.sendMessage(output.toString());
        }
    }

    @Override
    public String toString() {
        return commandString;
    }
}
