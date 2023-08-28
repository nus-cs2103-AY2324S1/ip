import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime dateTime;

    public AddDeadlineCommand(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (description.trim().isEmpty()) {
                throw new EmptyDescriptionException("deadline");
            }

            Deadline newDeadline = new Deadline(description, dateTime);
            taskList.add(newDeadline);
            ui.showAdd(newDeadline, taskList.getLength());

        } catch (EmptyDescriptionException e) {
            ui.showDukeException(e);
        } catch (DateTimeParseException e) {
            ui.showInvalidDateTimeFormat();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
