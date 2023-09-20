package kevin.evaluator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kevin.exception.KevinException;
import kevin.storage.FileStorage;
import kevin.tasklist.Deadline;
import kevin.tasklist.TaskList;
import kevin.ui.Logger;

/**
 * A class responsible for the logic for DEADLINE command.
 */
public class DeadlineStrategy extends BaseStrategy {
    /**
     * Constructor to initialize DeadlineStrategy.
     * @param taskList This is the TaskList where the tasks are stored and operations are defined.
     * @param arguments This is an ArrayList where all the needed arguments are stored.
     */
    DeadlineStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    /**
     * Handles the logic of the DEADLINE command.
     * @param logger This is the Logger that handles System.out.println.
     * @param fileStorage This is the FileStorage that handles the storage in the local computer.
     * @param isInFile This is the boolean to show whether the task is in the local computer's file.
     * @return Returns a boolean that determines the continuation of the evaluation
     * @throws KevinException On the detection of errors.
     */
    @Override
    public String evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        Boolean isDone = Boolean.parseBoolean(this.arguments.get(0));
        String name = this.arguments.get(1);
        String deadline = this.arguments.get(2);

        List<String> formatStrings = Arrays.asList("d/MM/yyyy HHmm", "dd/MM/yyyy HHmm");
        if (!isInFile) {
            LocalDateTime deadlineDate = null;
            try {
                for (String formatString : formatStrings) {
                    try {
                        System.out.println(deadline + formatString);
                        deadlineDate = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern(formatString));
                        break;
                    } catch (DateTimeParseException e) {
                        throw new KevinException("Ensure that the deadline is a valid date");
                    }
                }
            } catch (DateTimeParseException err) {
                throw new KevinException("Ensure that the deadline is a valid date");
            }

            Deadline newDeadline = taskList.addDeadline(isDone, name, deadlineDate);
            fileStorage.addDeadline(newDeadline);
            return logger.log(new StringBuilder().append("Got it. I've added this task: \n")
                    .append(newDeadline).append("\nNow you have ")
                    .append(taskList.size())
                    .append(" tasks in the list.")
                    .toString());
        } else {
            LocalDateTime deadlineDate;
            try {
                deadlineDate = LocalDateTime.parse(deadline);
            } catch (DateTimeParseException err) {
                throw new KevinException("Ensure that the deadline is a valid date");
            }

            taskList.addDeadline(isDone, name, deadlineDate);
            return "";
        }
    }
}
