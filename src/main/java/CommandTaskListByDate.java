import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CommandTaskListByDate extends Command {
    @Override
    public void accept(Parser input) throws IllegalArgumentException {
        LocalDate filterDate;
        try {
            filterDate = LocalDate.parse(input.getDefaultString());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Illegal Date");
        }
        List<Task> taskList = Rock.taskList.toList();
        String response = "";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDate().equals(filterDate)) {
                response += "\n" + taskList.get(i).toString();
            }
        }
        if (response == "") {
            Rock.respond("No tasks found!");
        } else {
            Rock.respond(response);
        }
    }
}
