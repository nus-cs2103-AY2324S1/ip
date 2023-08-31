package commands;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import client.Rock;
import io.Parser;
import tasks.Task;

/**
 * Representation of a command
 * to list all tasks in list
 * filtering by date.
 * 
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
    @Override
    /**
     * Lists all tasks with the 
     * corresponding date
     * @param client Chatbot object
     */
    public void accept(Parser input) throws IllegalArgumentException {
        LocalDate filterDate;
        try {
            filterDate = LocalDate.parse(input.getDefaultString());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Illegal Date");
        }
        List<Task> taskList = this.client.taskList.toList();
        String response = "";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDate().equals(filterDate)) {
                response += "\n" + taskList.get(i).toString();
            }
        }
        if (response == "") {
            this.client.ui.respond("No tasks found!");
        } else {
            this.client.ui.respond(response);
        }
    }
}
