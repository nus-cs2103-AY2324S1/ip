package duke.task;

import duke.InvalidCommandException;
import duke.InvalidTaskCreationException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static final String dateFormat = "yyyy-MM-dd";
    public static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DTformatter = DateTimeFormatter.ofPattern(dateTimeFormat);

    public static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm, dd MMMM yyyy");

    public static Task taskCon(String userInput) throws InvalidCommandException, InvalidTaskCreationException, DateTimeParseException {
        if (userInput.startsWith("todo")) {
            return ToDo.ToDoCon(userInput.substring(5));
        } else if (userInput.startsWith("deadline")) {

            String[] splitInput = userInput.split("/by");
            String taskDescription = splitInput[0].substring(9).trim();
            String deadline = splitInput[1].trim();

            return Deadline.DeadlineCon(taskDescription, deadline);

        } else if (userInput.startsWith("event")) {

            String[] splitInput = userInput.split("/from");
            String taskDescription = splitInput[0].substring(6).trim();
            String[] eventDetails = splitInput[1].split("/to");
            String eventStartTime = eventDetails[0].trim();
            String eventEndTime = eventDetails[1].trim();

            return Event.EventCon(taskDescription, eventStartTime, eventEndTime);
        } else {
            throw new InvalidCommandException("Invalid command to add task!");
        }
    }

    public LocalDateTime getUrgencyDate() {
        return LocalDateTime.now();
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }
}
