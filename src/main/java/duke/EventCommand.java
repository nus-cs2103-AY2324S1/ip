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
    public static void execute(String input, TaskList tasks, Storage storage) {
        try {
            if (input.length() <= 6 || input.substring(6).isBlank()) {
                throw new EmptyDescriptionException();
            }
            String[] details = input.substring(6).split(" /from | /to ");
            if (details.length != 3 || !input.contains(" /from ") || !input.contains(" /to ")) {
                throw new DurationUnclearException();
            }
            LocalDateTime fromDateTime = LocalDateTime.parse(details[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime toDateTime = LocalDateTime.parse(details[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Task task = new Event(details[0], fromDateTime, toDateTime);
            tasks.add(task);
            storage.rewrite(tasks.fileList());
            Ui.addTask(task.toString(), tasks.size());
        } catch (EmptyDescriptionException e) {
            Ui.emptyDesc("event");
        } catch (DurationUnclearException e) {
            Ui.unclear("duration");
        } catch (DateTimeParseException e) {
            Ui.wrongDateTimeFormat();
        }
    }
}
