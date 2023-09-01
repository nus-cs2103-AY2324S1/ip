package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;

import java.util.Map;
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

public class AddCommand extends Command {
    private TaskType taskType;

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * Constructor for AddCommand.
     * 
     * @param parameterMap Map of parameters for the command.
     */
    public AddCommand(Map<String, String> parameterMap) {
        super(parameterMap);

        // Guard clause to prevent NullPointerException
        if (parameterMap.equals(null)) {
            return;
        }

        if (parameterMap.containsKey("event")) {
            this.taskType = TaskType.EVENT;
        } else if (parameterMap.containsKey("deadline")) {
            this.taskType = TaskType.DEADLINE;
        } else if (parameterMap.containsKey("todo")) {
            this.taskType = TaskType.TODO;
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskToAdd = null;

        try {
            switch (taskType) {
            case TODO:
                if (!super.getParameterMap().containsKey("default")) {
                    throw new DukeException("No description specified. Please specify a description.");
                }

                String todoDescription = super.getParameterMap().get("default");
                taskToAdd = new Todo(todoDescription);
                break;
            case DEADLINE:
                if (!super.getParameterMap().containsKey("default")) {
                    throw new DukeException("No description specified. Please specify a description.");
                }
                if (!super.getParameterMap().containsKey("by")) {
                    throw new DukeException("No due date/time specified. Please specify a due date/time.");
                }

                String deadlineDescription = super.getParameterMap().get("default");
                TemporalAccessor dueDate = Parser.parseDateTimeInput(super.getParameterMap().get("by"));
                taskToAdd = new Deadline(deadlineDescription, dueDate);
                break;
            case EVENT:
                if (!super.getParameterMap().containsKey("default")) {
                    throw new DukeException("No description specified. Please specify a description.");
                }
                if (!super.getParameterMap().containsKey("from")) {
                    throw new DukeException("No start date/time specified. Please specify a start date/time.");
                }
                if (!super.getParameterMap().containsKey("to")) {
                    throw new DukeException("No end date/time specified. Please specify an end date/time.");
                }
                String eventDescription = super.getParameterMap().get("default");
                TemporalAccessor eventStartDate = Parser.parseDateTimeInput(super.getParameterMap().get("from"));
                TemporalAccessor eventEndDate = Parser.parseDateTimeInput(super.getParameterMap().get("to"));

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

            if (!taskToAdd.equals(null) && super.getParameterMap().containsKey("completed")) {
                taskToAdd.markAsDone();
            }

            tasks.addTask(taskToAdd);

            if (super.getParameterMap().containsKey("silent")) {
                return;
            }

            Ui.respond(Stream.of("Got it. I've added this task:",
                       String.format("  %s", taskToAdd.toString()),
                       String.format("Now you have %d tasks in the list.", tasks.size())));
            tasks.storeTasks();
        } catch (DukeException e) {
            if (super.getParameterMap().containsKey("silent")) {
                return;
            }
            
            throw e;
        }

    }
}
