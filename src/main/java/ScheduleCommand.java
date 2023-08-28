import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScheduleCommand extends Command {
    protected static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("d/M/yyyy");

    public ScheduleCommand(String command) {
        super(command);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        String input = getCommand();
        LocalDate queryDateTime;
        System.out.println(input.substring(9));
        try {
            queryDateTime = LocalDate.parse(input.substring(9), DATE_FORMAT_OUTPUT);
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy");
        }
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.by.toLocalDate().equals(queryDateTime)) {
                    output += (i + 1) + ". " + deadline.toString() + "\n";
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (!event.startDate.toLocalDate().isAfter(queryDateTime) & !event.endDate.toLocalDate().isBefore(queryDateTime)) {
                    output += (i + 1) + ". " + event.toString() + "\n";
                }
            }
        }
        ui.showSchedule(output);
    }
}
