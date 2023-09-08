package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DeadlineCommand class represents the command to create a deadline task.
 */
public class DeadlineCommand {

    /**
     * Runs the DeadlineCommand.
     *
     * @param input Input typed by user.
     * @param tasks List of tasks.
     * @param storage Stores the file and handles file methods.
     */
    public static String execute(String input, TaskList tasks, Storage storage) {
        try {
            if (input.length() <= 9 || input.substring(9).isBlank()) {
                throw new EmptyDescriptionException();
            }
            String[] details = input.substring(9).split(" /by ");
            if (details.length != 2) {
                throw new DeadlineUnclearException();
            }
            LocalDateTime dateTime = LocalDateTime.parse(details[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Task task = new Deadline(details[0], dateTime);
            tasks.add(task);
            storage.rewrite(tasks.fileList());
            return Ui.addTask(task.toString(), tasks.size());
        } catch (EmptyDescriptionException e) {
            return Ui.emptyDesc("deadline");
        } catch (DeadlineUnclearException e) {
            return Ui.unclear("deadline");
        } catch (DateTimeParseException e) {
            return Ui.wrongDateTimeFormat();
        }
    }
}
