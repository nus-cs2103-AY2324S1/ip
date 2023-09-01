package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.stream.Stream;

import duke.core.DukeException;
import duke.core.Parser;
import duke.core.Storage;
import duke.core.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Command to add a task to the task list.
 */
public class AddCommand extends Command {
    private TaskType taskType;

    /** Enum to represent the type of task to be added. */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * Constructor for AddCommand.
     * 
     * @param taskType Type of task to be added.
     */
    public AddCommand(TaskType taskType) {
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskToAdd = null;

        if (super.parameterMap == null) {
            throw new DukeException("Please enter additional arguments.");
        }

        try {
            switch (taskType) {
            case TODO:
                if (!super.parameterMap.containsKey("default")) {
                    throw new DukeException("No description specified. Please specify a description.");
                }

                String todoDescription = super.parameterMap.get("default");
                taskToAdd = new Todo(todoDescription);
                break;
            case DEADLINE:
                if (!super.parameterMap.containsKey("default")) {
                    throw new DukeException("No description specified. Please specify a description.");
                }
                if (!super.parameterMap.containsKey("by")) {
                    throw new DukeException("No due date/time specified. Please specify a due date/time.");
                }

                String deadlineDescription = super.parameterMap.get("default");
                TemporalAccessor dueDate = Parser.parseDateTimeInput(super.parameterMap.get("by"));
                taskToAdd = new Deadline(deadlineDescription, dueDate);
                break;
            case EVENT:
                if (!super.parameterMap.containsKey("default")) {
                    throw new DukeException("No description specified. Please specify a description.");
                }
                if (!super.parameterMap.containsKey("from")) {
                    throw new DukeException("No start date/time specified. Please specify a start date/time.");
                }
                if (!super.parameterMap.containsKey("to")) {
                    throw new DukeException("No end date/time specified. Please specify an end date/time.");
                }
                String eventDescription = super.parameterMap.get("default");
                TemporalAccessor eventStartDate = Parser.parseDateTimeInput(super.parameterMap.get("from"));
                TemporalAccessor eventEndDate = Parser.parseDateTimeInput(super.parameterMap.get("to"));

                if (eventStartDate instanceof LocalDate) {
                    if (!(eventEndDate instanceof LocalDate)) {
                        throw new DukeException("Please ensure that both arguments have the same format.");
                    }

                    LocalDate startDate = (LocalDate) eventStartDate;
                    LocalDate endDate = (LocalDate) eventEndDate;

                    if (startDate.isAfter(endDate)) {
                        throw new DukeException("Start date cannot be after the end date");
                    }
                }

                if (eventStartDate instanceof LocalDateTime) {
                    if (!(eventEndDate instanceof LocalDateTime)) {
                        throw new DukeException("Please ensure that both arguments have the same format.");
                    }

                    LocalDateTime startDate = (LocalDateTime) eventStartDate;
                    LocalDateTime endDate = (LocalDateTime) eventEndDate;

                    if (startDate.isAfter(endDate)) {
                        throw new DukeException("Start date cannot be after the end date");
                    }
                }

                taskToAdd = new Event(eventDescription, eventStartDate, eventEndDate);
                break;
            }

            if (taskToAdd != null && super.parameterMap.containsKey("completed")) {
                taskToAdd.markAsDone();
            }

            tasks.addTask(taskToAdd);

            if (super.parameterMap.containsKey("silent")) {
                return;
            }

            Ui.respond(Stream.of("Got it. I've added this task:",
                       String.format("  %s", taskToAdd.toString()),
                       String.format("Now you have %d tasks in the list.", tasks.size())));
            tasks.storeTasks();
        } catch (DukeException e) {
            if (super.parameterMap.containsKey("silent")) {
                return;
            }
            
            throw e;
        }

    }
}
