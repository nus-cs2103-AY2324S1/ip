import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    String header;

    public AddCommand(String header, String command) {
        super(command);
        this.header = header;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        Task task = null;
        switch (this.header) {
        case "todo":
            task = createToDoTask(commandDescription.replaceFirst("todo", "").trim());
            break;

        case "deadline":
            task = createDeadlineTask(commandDescription.replaceFirst("deadline", "").trim());
            break;

        case "event":
            task = createEventTask(commandDescription.replaceFirst("event", "").trim());
            break;
        }

        if (task != null) {
            taskList.add(task);
            ui.showAddedTask(task.toString(), taskList.size());
            storage.append(task.toSaveString());
        }
    }

    private Task createToDoTask(String prompt) throws TasketException {
        if (prompt.isEmpty()) {
            throw new TasketException("The description of todo cannot be empty");
        }

        return new ToDo(prompt.trim());
    }

    private Task createDeadlineTask(String prompt) throws TasketException {
        if(prompt.isEmpty()) {
            throw new TasketException("The description of deadline cannot be empty");
        }

        String[] arguments = prompt.replaceAll("/by", "|").split(" \\| ");
        if(arguments.length < 2) {
            throw new TasketException("The deadline cannot be empty");
        }

        return new Deadline(arguments[0].trim(), convertToDate(arguments[1].trim()));
    }

    private Task createEventTask(String prompt) throws TasketException {
        if(prompt.isEmpty()) {
            throw new TasketException("The description of event cannot be empty");
        }

        String[] arguments = prompt.replaceAll("/from", "|").replaceAll("/to", "|")
                .split(" \\| ");
        if (arguments.length < 2) {
            throw new TasketException("The start time cannot be empty");
        } else if (arguments.length < 3) {
            throw new TasketException("The end time cannot be empty");
        }

        return new Event(arguments[0].trim(), convertToDate(arguments[1].trim()), convertToDate(arguments[2].trim()));
    }

    private String convertToDate(String deadline) {
        try {
            LocalDate date = LocalDate.parse(deadline);
            return date.format(DateTimeFormatter.ofPattern("yyyy MMM d"));
        } catch (DateTimeParseException exception) {
            return deadline;
        }
    }
}
