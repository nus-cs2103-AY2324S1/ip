package ipbot.model;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task implements Serializable {
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "uuuu-MM-dd HHmm");
    public static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "dd LLL yyyy hh:mm a");
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public boolean markDone() {
        if(this.isDone){
            return false;
        }
        this.isDone = true;
        return true;
    }

    public boolean unmarkDone() {
        if(!this.isDone){
            return false;
        }
        this.isDone = false;
        return true;
    }

    public static Task fromString(String string) throws TaskFormatException, DateTimeParseException {
        String[] args = string.split(",");
        if (args.length < 2) {
            throw new TaskFormatException("Task string is too short!");
        }
        if (!args[0].matches("^[a-zA-Z]$")) {
            throw new TaskFormatException("Task string has incorrect format for type!");
        }
        char taskType = args[0].charAt(0);
        if (!args[1].matches("^[X ]$")) {
            throw new TaskFormatException("Task string has incorrect format for completion status!");
        }
        char completionStatus = args[1].charAt(0);
        Task retTask;
        switch (taskType) {
        case 'T':
            if (args.length != 3) {
                throw new TaskFormatException(
                        "Task string has wrong number of arguments for ToDo " + args.length + "!");
            }
            ToDo toDo = new ToDo(args[2]);
            retTask = toDo;
            break;
        case 'D':
            if (args.length != 4) {
                throw new TaskFormatException(
                        "Task string has wrong number of arguments for Deadline " + args.length + "!");
            }
            Deadline deadline = new Deadline(args[2], args[3]);
            retTask = deadline;
            break;
        case 'E':
            if (args.length != 5) {
                throw new TaskFormatException(
                        "Task string has wrong number of arguments for Event " + args.length + "!");
            }
            Event event = new Event(args[2], args[3], args[4]);
            retTask = event;
            break;
        default:
            throw new TaskFormatException("Task type is invalid!");
        }
        if (completionStatus == 'X') {
            retTask.markDone();
        }
        return retTask;
    }

    public String toCommaString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

