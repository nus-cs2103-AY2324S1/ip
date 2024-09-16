package bob.command;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.InvalidPriorityException;
import bob.exception.MissingDatesException;
import bob.exception.MissingTaskException;
import bob.exception.WrongInputException;

public class AddCommand extends Command {

    public AddCommand(String input) {
        super.input = input;
    }

    /**
     * Adds task to list based on user input.
     * Handles exceptions that may arise from bad user input
     * @param tasks from Bob class
     * @param ui from Bob class
     * @param storage from Bob class
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            try {
                String display = ui.stringFormat(tasks.addToList(input));
                storage.write(tasks.lst);
                return display;
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidPriorityException();
            } catch (DateTimeParseException e) {
                throw new MissingDatesException();
            }
        } catch (IOException e) {
            return ui.showLoadingError();
        } catch (WrongInputException e) {
            return ui.errorFormat(new String[]{e.message});
        } catch (MissingTaskException e) {
            return ui.errorFormat(new String[]{e.message});
        } catch (MissingDatesException e) {
            return ui.errorFormat(new String[]{e.message});
        } catch (InvalidPriorityException e) {
            return ui.errorFormat(new String[]{e.message});
        }
    }
}
