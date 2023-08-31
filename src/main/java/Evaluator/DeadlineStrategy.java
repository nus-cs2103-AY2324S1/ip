package Evaluator;

import Ui.Logger;
import Storage.FileStorage;
import TaskList.TaskList;
import TaskList.Deadline;
import Exception.KevinException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DeadlineStrategy extends BaseStrategy {
    DeadlineStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    @Override
    public boolean evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        Boolean isDone = Boolean.getBoolean(this.arguments.get(0));
        String name = this.arguments.get(1);
        String deadline = this.arguments.get(2);

        if (!isInFile) {
            LocalDateTime deadlineDate;
            try {
                deadlineDate = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern(" d/MM/yyyy HHmm"));
            } catch (DateTimeParseException err) {
                throw new KevinException("Ensure that the deadline is a valid date");
            }

            Deadline newDeadline = taskList.addDeadline(isDone, name, deadlineDate);
            fileStorage.addDeadline(newDeadline);
            logger.log("Got it. I've added this task: \n\t\t" + newDeadline +
                    "\n\tNow you have " + taskList.size() + " tasks in the list.");
        } else {
            LocalDateTime deadlineDate;
            try {
                deadlineDate = LocalDateTime.parse(deadline);
            } catch (DateTimeParseException err) {
                throw new KevinException("Ensure that the deadline is a valid date");
            }

            Deadline newDeadline = taskList.addDeadline(isDone, name, deadlineDate);
        }
        return true;
    }
}
