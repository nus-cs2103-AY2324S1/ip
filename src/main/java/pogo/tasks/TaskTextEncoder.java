package pogo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import pogo.tasks.exceptions.PogoInvalidTaskException;

/**
 * Encodes tasks to text format.
 */
public class TaskTextEncoder implements TaskEncoder, TaskVisitor {
    private static final String SEPARATOR_ENCODING = " | ";
    private static final String SEPARATOR_DECODING = " \\| ";
    private static final String ID_DEADLINE = "D";
    private static final String ID_EVENT = "E";
    private static final String ID_TODO = "T";

    private static final int DEADLINE_EXPECTED_LENGTH =
            "D | isDone | description | by".split(SEPARATOR_DECODING).length;
    private static final int EVENT_EXPECTED_LENGTH =
            "E | isDone | description | from | to".split(SEPARATOR_DECODING).length;
    private static final int TODO_EXPECTED_LENGTH = "T | isDone | description".split(SEPARATOR_DECODING).length;
    private final StringBuilder tasksOut = new StringBuilder();

    /**
     * Encodes a deadline task to a string.
     * The task is encoded in the following format:
     * D | isDone | description | by
     *
     * @param deadline Deadline task to encode.
     */
    @Override
    public void visit(Deadline deadline) {
        String deadlineString =
                String.join(SEPARATOR_ENCODING, ID_DEADLINE, deadline.isDone() ? "1" : "0", deadline.getDescription(),
                        deadline.getDeadline());
        tasksOut.append(deadlineString).append(System.lineSeparator());
    }

    /**
     * Encodes an event task to a string.
     * The task is encoded in the following format:
     * E | isDone | description | from | to
     *
     * @param event Event task to encode.
     */
    @Override
    public void visit(Event event) {
        String eventString =
                String.join(SEPARATOR_ENCODING, ID_EVENT, event.isDone() ? "1" : "0", event.getDescription(),
                        event.getFrom(), event.getTo());
        tasksOut.append(eventString).append(System.lineSeparator());
    }

    /**
     * Encodes a ToDo task to a string.
     * The task is encoded in the following format:
     * T | isDone | description
     *
     * @param toDo ToDo task to encode.
     */
    @Override
    public void visit(ToDo toDo) {
        String toDoString = String.join(SEPARATOR_ENCODING, ID_TODO, toDo.isDone() ? "1" : "0", toDo.getDescription());
        tasksOut.append(toDoString).append(System.lineSeparator());
    }

    @Override
    public String encode(List<Task> tasks) {
        tasksOut.setLength(0);
        for (Task task : tasks) {
            task.accept(this);
        }
        return tasksOut.toString();
    }

    private static Task decodeDeadline(String[] split) throws PogoInvalidTaskException {
        if (split.length != DEADLINE_EXPECTED_LENGTH) {
            throw new PogoInvalidTaskException("Error parsing deadline task");
        }
        String description = split[2];
        LocalDateTime by = LocalDateTime.parse(split[3], Task.DATETIME_FORMAT);
        return new Deadline(description, by);
    }

    private static Task decodeEvent(String[] split) throws PogoInvalidTaskException {
        if (split.length != EVENT_EXPECTED_LENGTH) {
            throw new PogoInvalidTaskException("Error parsing event task");
        }
        String description = split[2];
        LocalDateTime from = LocalDateTime.parse(split[3], Task.DATETIME_FORMAT);
        LocalDateTime to = LocalDateTime.parse(split[4], Task.DATETIME_FORMAT);
        return new Event(description, from, to);
    }


    private static Task decodeToDo(String[] split) throws PogoInvalidTaskException {
        if (split.length != TODO_EXPECTED_LENGTH) {
            throw new PogoInvalidTaskException("Error parsing todo task");
        }
        String description = split[2];
        return new ToDo(description);
    }


    /**
     * Decodes a task from a string.
     * Throws PogoInvalidTaskException if the task is invalid.
     *
     * @param taskString Task string to decode.
     * @return Task decoded from the string.
     * @throws PogoInvalidTaskException If the task is invalid.
     */
    private Task decodeTask(String taskString) throws PogoInvalidTaskException {
        String[] split = taskString.split(SEPARATOR_DECODING);

        if (split.length == 0) {
            throw new PogoInvalidTaskException();
        }

        String taskId = split[0];

        Task task;
        switch (taskId) {
        case ID_DEADLINE:
            task = decodeDeadline(split);
            break;
        case ID_EVENT:
            task = decodeEvent(split);
            break;
        case ID_TODO:
            task = decodeToDo(split);
            break;
        default:
            throw new PogoInvalidTaskException("Error parsing task");
        }

        boolean isTaskDone = split[1].equals("1");
        if (isTaskDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Decodes a list of tasks from a string.
     * If a specific task is invalid, an error message is printed and the task is skipped.
     *
     * @param tasksString List of task Strings to decode.
     * @return List of tasks decoded from the string.
     */
    @Override
    public List<Task> decode(List<String> tasksString) {
        ArrayList<Task> tasks = new ArrayList<>();

        for (String taskString : tasksString) {
            try {
                Task task = decodeTask(taskString);
                tasks.add(task);
            } catch (PogoInvalidTaskException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing date");
            }
        }

        return tasks;
    }
}
