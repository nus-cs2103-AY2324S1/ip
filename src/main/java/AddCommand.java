import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    String fullCommand;
    char type;


    public AddCommand(String fullCommand, char type) {
        this.fullCommand = fullCommand;
        this.type = type;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        switch (type) {
            case 'T':
                String description = fullCommand.substring(5);
                Todo todo = new Todo(description);
                taskList.addTask(todo);
                ui.sendMessage("Got it. I've added this task:\n\t\t"
                        + todo
                        + "\n\tNow you have " + taskList.size() + " tasks in the list.");
                storage.updateFileContents(taskList);
                break;
            case 'D':
                String deadlineDescription = fullCommand.substring(9);
                String descriptionText = deadlineDescription.substring(0, deadlineDescription.indexOf("/by"));
                String dateTime = deadlineDescription.substring(deadlineDescription.indexOf("/by") + 4).trim();
                Deadline deadline = null;
                try {
                    DateTimeFormatter altInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime byDateTime = LocalDateTime.parse(dateTime, altInputFormatter);
                    deadline = new Deadline(descriptionText, byDateTime);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Invalid Date Time: " + e.getMessage());
                }
                taskList.addTask(deadline);
                ui.sendMessage("Got it. I've added this task:\n\t\t"
                        + deadline
                        + "\n\tNow you have " + taskList.size() + " tasks in the list.");
                storage.updateFileContents(taskList);
                break;
            case 'E':
                String eventDescription = fullCommand.substring(6);
                int indexFrom = eventDescription.indexOf("/from");
                int indexTo = eventDescription.indexOf("/to");

                String eventString = eventDescription.substring(0, indexFrom).trim();
                String startTime = eventDescription.substring(indexFrom + "/from".length(), indexTo).trim();
                String endTime = eventDescription.substring(indexTo + "/to".length()).trim();

                Event eventTask = new Event(eventString,
                        LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

                taskList.addTask(eventTask);
                ui.sendMessage("Got it. I've added this task:\n\t\t"
                        + eventTask
                        + "\n\tNow you have " + taskList.size() + " tasks in the list.");
                storage.updateFileContents(taskList);
                break;
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
