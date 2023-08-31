package commands;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import client.Rock;
import io.Parser;
import tasks.Task;

public class CommandTaskListByDate extends Command {
    public CommandTaskListByDate(Rock client) {
        super(client);
    }
    @Override
    public void accept(Parser input) throws IllegalArgumentException {
        LocalDate filterDate;
        try {
            filterDate = LocalDate.parse(input.getDefaultString());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Illegal Date");
        }
        List<Task> taskList = this.client.getTaskList().toList();
        String response = "";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDate().equals(filterDate)) {
                response += "\n" + taskList.get(i).toString();
            }
        }
        if (response == "") {
            this.client.getUi().respond("No tasks found!");
        } else {
            this.client.getUi().respond(response);
        }
    }
}
