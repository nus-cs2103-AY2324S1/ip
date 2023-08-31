import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static Command parse(String userInput) throws DukeException {
        String commandType = getCommandType(userInput);

        switch (commandType) {
        case "bye":
        case "list":
            return new Command(commandType);

        case "mark":
        case "delete":
        case "unmark":
            return new Command(commandType, getTaskIndex(userInput)); // taskIndex is -1 if invalid

        case "todo":
            return new Command(commandType, getDescription(userInput));
        case "deadline":
            return new Command(commandType, getDescription(userInput), getDeadlineDate(userInput));
        case "event":
            LocalDate[] eventDates = getEventDates(userInput);
            LocalDate eventFromDate = eventDates[0];
            LocalDate eventToDate = eventDates[1];

            return new Command(commandType, getDescription(userInput), eventFromDate, eventToDate);

        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the data string from the storage file and returns the corresponding task.
     * example of data string:
     * T | 1 | read book
     * D | 0 | return book | June 6th
     * E | 0 | project meeting | Aug 6th 2-4pm
     * file corruption not handled
     *
     * @return The completion status of the task.
     */
    public static Task parseFileString(String taskData) {
        String[] parts = taskData.split(" \\| ");
        String type = parts[0].trim();
        String isDone = parts[1].trim();
        String description = parts[2].trim();

        Task task = null;

        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String by = parts[3].trim();
            task = new Deadline(description, by);
            break;
        case "E":
            String from = parts[3].trim();
            String to = parts[4].trim();
            task = new Event(description, from, to);
            break;
        }

        if (isDone.equals("1")) {
            assert task != null;
            task.markAsDone();
        }

        return task;
    }

    /**
     * reads the task and returns the corresponding data string to be written to the storage file.
     *
     * @param task The task to be read.
     * @return String to be written to the storage file.
     */
    public static String readTaskToFile(Task task) {
        StringBuilder data = new StringBuilder();

        if (task instanceof Todo) {
            data.append("T");
            data.append(" | ").append(task.isDone() ? "1" : "0");
            data.append(" | ").append(task.getDescription());
        } else if (task instanceof Deadline) {
            data.append("D");
            data.append(" | ").append(task.isDone() ? "1" : "0");
            data.append(" | ").append(task.getDescription());
            data.append(" | ").append(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            data.append("E");
            data.append(" | ").append(task.isDone() ? "1" : "0");
            data.append(" | ").append(task.getDescription());
            data.append(" | ").append(((Event) task).getFrom()).append(" | ").append(((Event) task).getTo());
        }

        return data.toString();
    }

    private static String getCommandType(String userInput) {
        String[] parts = userInput.split(" ");
        return parts.length > 0 ? parts[0] : "";
    }

    public static String getDescription(String userInput) {
        String[] parts = userInput.split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }

    public static int getTaskIndex(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length == 2) {
            try {
                return Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return -1;
    }

    private static LocalDate getDeadlineDate(String userInput) throws DeadlineException {
        String[] parts = userInput.split(" ", 2);
        String description = parts[1];
        String taskDescription;
        String deadlineDate;


        // make sure /by exist to avoid exception in "split() array"
        if (!description.contains("/by")) {
            throw new DeadlineException();
        } else {
            // split the description to task description and timing, with /by in between
            String[] desArray = description.split("/by", 2);
            taskDescription = desArray[0];
            deadlineDate = desArray[1];

            if (taskDescription.isEmpty() || deadlineDate.isEmpty()) {
                throw new DeadlineException();
            }

            try {
                // Validate date format and return LocalDate object
                return LocalDate.parse(deadlineDate.trim(), dataFormatter);
            } catch (DateTimeParseException e) {
                throw new DeadlineException();
            }
        }
    }

    private static LocalDate[] getEventDates(String userInput) throws EventException {
        String[] parts = userInput.split(" ", 2);
        String description = parts[1];
        String taskDescription;
        String eventFrom;
        String eventTo;
        LocalDate[] eventDates = new LocalDate[2];

        // make sure /from and /to exist to avoid exception in "split() array"
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new EventException();
        } else {
            // split the description to task description and timing, with /from in between
            String[] desArray = description.split("/from", 2);
            taskDescription = desArray[0];
            // when from /to comes before /from
            if (taskDescription.contains("/to")) {
                throw new EventException();
            }

            // split the timing description further, with /to in between
            String[] timingArr = desArray[1].split("/to", 2);
            eventFrom = timingArr[0];
            eventTo = timingArr[1];

            if (eventFrom.isEmpty() || eventTo.isEmpty()) {
                throw new EventException();
            }

            try {
                // validate format and return LocalDate object
                eventDates[0] = LocalDate.parse(eventFrom.trim(), dataFormatter);
                eventDates[1] = LocalDate.parse(eventTo.trim(), dataFormatter);
                return eventDates;
            } catch (DateTimeParseException e) {
                throw new EventException();
            }

        }
    }
}