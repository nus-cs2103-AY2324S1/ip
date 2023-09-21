package rock.logic.commands;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import rock.client.Rock;
import rock.logic.io.Parser;

/**
 * Representation of a command
 * to list all tasks in list
 * filtering by date.
 * @author Alvis Ng (supermii2)
 */
public class CommandTaskListByDate extends Command {
    /**
     * Constructor to create the
     * list by date commands
     * @param client Chatbot object
     */
    public CommandTaskListByDate(Rock client) {
        super(client);
    }
    /**
     * Lists all tasks with the
     * corresponding date
     * @param input Chatbot object
     */
    @Override
    public String apply(Parser input) throws IllegalArgumentException {
        LocalDate filterDate;
        try {
            filterDate = LocalDate.parse(input.getDefaultString());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Illegal Date");
        }
        return client.getTaskList().filteredSearch(task -> task.getDate() == filterDate);
    }
}
