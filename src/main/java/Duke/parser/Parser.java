package Duke.parser;
import core.Duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Duke.tasks.Deadlines;
import Duke.tasks.Events;
import Duke.tasks.Task;
import Duke.tasks.ToDos;
import core.DukeException;
import core.Duke.CommandType;

public class Parser {
    public CommandType getCommandType(String userCommand) {
        if ("bye".equals(userCommand)) {
            return Duke.CommandType.BYE;
        } else if ("list".equals(userCommand)) {
            return Duke.CommandType.LIST;
        } else if (userCommand.startsWith("delete ")) {
            return Duke.CommandType.DELETE;
        } else if (userCommand.startsWith("mark ")) {
            return Duke.CommandType.MARK;
        } else if (userCommand.startsWith("unmark ")) {
            return Duke.CommandType.UNMARK;
        } else if (userCommand.startsWith("todo ")) {
            return Duke.CommandType.TODO;
        } else if (userCommand.startsWith("deadline ")) {
            return Duke.CommandType.DEADLINE;
        } else if (userCommand.startsWith("event ")) {
            return Duke.CommandType.EVENT;
        } else {
            return Duke.CommandType.UNKNOWN;
        }
    }

    public Task parseTaskFromFile(String line) throws DukeException {
        String[] parts = line.split("\\|");

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        Task task = null;
        String taskType = parts[0];
        boolean isCompleted = "1".equals(parts[1]);
        String description = parts[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = (parts.length >= 4 && !parts[3].isEmpty()) ? LocalDateTime.parse(parts[3], formatter)
                : null;
        LocalDateTime startDate = (parts.length >= 5 && !parts[4].isEmpty()) ? LocalDateTime.parse(parts[4], formatter)
                : null;
        LocalDateTime endDate = (parts.length == 6 && !parts[5].isEmpty()) ? LocalDateTime.parse(parts[5], formatter)
                : null;

        try {
            switch (taskType) {
                case "T":
                    task = new ToDos(description, isCompleted);
                    break;

                case "D":
                    task = new Deadlines(description, date, isCompleted);
                    break;

                case "E":
                    task = new Events(description, startDate, endDate, isCompleted);
                    break;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        if (task == null) {
            throw new DukeException("Invalid task format.");
        }

        return task;
    }

    public Duke.TaskType getTaskType(String str) {
        if (str.startsWith("T")) {
            return Duke.TaskType.TODO;
        } else if (str.startsWith("D")) {
            return Duke.TaskType.DEADLINE;
        } else if (str.startsWith("E")) {
            return Duke.TaskType.EVENT;
        } else {
            return Duke.TaskType.UNKNOWN;
        }
    }

    public Task parseTaskFromCommand(String userCommand) throws DukeException {
        String[] parts = userCommand.split(" ", 2);
        String commandType = parts[0].toLowerCase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Task task;
    
        switch (commandType) {
            case "event":
                if (parts.length < 2) {
                    throw new DukeException("Description for the event cannot be empty.");
                }
    
                String[] eventParts = parts[1].split(" /from ", 2);
                if (eventParts.length < 2) {
                    throw new DukeException("Event timing is missing.");
                }
    
                String[] eventTimes = eventParts[1].split(" /to ", 2);
                if (eventTimes.length < 2) {
                    throw new DukeException("End time for event is missing.");
                }
    
                LocalDateTime fromDate = LocalDateTime.parse(eventTimes[0], formatter);
                LocalDateTime toDate = LocalDateTime.parse(eventTimes[1], formatter);
    
                task = new Events(eventParts[0], fromDate, toDate);
                break;
    
            case "todo":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("Description for the todo cannot be empty.");
                }
    
                task = new ToDos(parts[1]);
                break;
    
            case "deadline":
                if (parts.length < 2) {
                    throw new DukeException("Description for the deadline cannot be empty.");
                }
    
                String[] deadlineParts = parts[1].split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    throw new DukeException("Deadline timing is missing.");
                }
    
                LocalDateTime deadlineDate = LocalDateTime.parse(deadlineParts[1], formatter);
                task = new Deadlines(deadlineParts[0], deadlineDate);
                break;
    
            default:
                throw new DukeException("Invalid command. Please enter a valid task type.");
        }
        return task; 
    }
    
}
