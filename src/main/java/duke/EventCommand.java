package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The EventCommand class represents the command to create an event task.
 */
public class EventCommand {

    /**
     * Runs the EventCommand.
     *
     * @param input Input typed by user.
     * @param tasks List of tasks.
     * @param storage Stores the file and handles file methods.
     */
    public static String execute(String input, TaskList tasks, Storage storage) {
        try {
            if (input.length() <= 6 || input.substring(6).isBlank()) {
                throw new EmptyDescriptionException();
            }
            String[] details = input.substring(6).split(" /from | /to ");
            if (details.length != 3 || !input.contains(" /from ") || !input.contains(" /to ")) {
                throw new DurationUnclearException();
            }
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime fromDateTime = LocalDateTime.parse(details[1], inputFormat);
            LocalDateTime toDateTime = LocalDateTime.parse(details[2], inputFormat);
            Task task = new Event(details[0], fromDateTime, toDateTime);
            tasks.add(task);
            storage.rewrite(tasks.fileList());
            return Ui.addTask(task.toString(), tasks.size());
        } catch (EmptyDescriptionException e) {
            return Ui.emptyDesc("event");
        } catch (DurationUnclearException e) {
            return Ui.unclear("duration");
        } catch (DateTimeParseException e) {
            return Ui.wrongDateTimeFormat();
        }
    }
}
