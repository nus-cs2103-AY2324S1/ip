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
        String response = "";

        List<Task> taskList = Rock.taskList.toList();
        taskList.removeIf(task -> !(task instanceof TaskDeadline));
        taskList.removeIf(task -> task.getDate() != filterDate);
        
        if (taskList.size() == 0) {
            Rock.respond("No tasks found!");
        } else {
            response = "Found tasks: ";
            for (int i = 0; i < taskList.size(); i++) {
                response += "\n" + taskList.get(i).toString();
            }
            Rock.respond(response);
        }
    }
}
