package duke.utils;

import duke.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    List<String> inputArray;
    public Parser(String input) {
        this.inputArray = Arrays.asList(input.split(" "));
    }
    public Parser() {
        this.inputArray = new ArrayList<>();
    }

    public Command getCommand() {
        Command keyword = Command.INVALID;
        try {
            keyword = Command.valueOf(inputArray.get(0).toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.INVALID;
        }
        return keyword;
    }

    public int getTaskNumber() throws DukeException {
        try {
            String input = String.join(" ", this.inputArray.subList(1, this.inputArray.size()));
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeMissingArgumentException("The task number must be an integer.");
        } catch (NullPointerException e) {
            throw new DukeMissingTaskException();
        }
    }

    public String getContentForTodo() throws DukeException {
        if (this.inputArray.size() <= 1) {
            throw new DukeMissingArgumentException("The description of a todo cannot be empty.");
        }
        return String.join(" ", this.inputArray.subList(1, this.inputArray.size()));
    }

    public String[] getContentForDeadline() throws DukeException {
        if (this.inputArray.size() <= 1) {
            throw new DukeMissingArgumentException("The description of a deadline cannot be empty.");
        }
        String input = String.join(" ", this.inputArray.subList(1, this.inputArray.size()));
        return input.split(" /by ");
    }

    public String[] getContentForEvent() throws DukeException {
        if (this.inputArray.size() <= 1) {
            throw new DukeMissingArgumentException("The description of an event cannot be empty.");
        }
        String input = String.join(" ", this.inputArray.subList(1, this.inputArray.size()));

        String[] split = input.split(" /from ");
        if (split.length != 2) {
            throw new DukeMissingArgumentException("The start and end time of an event cannot be empty.");
        }

        String[] split2 = split[1].split(" /to ");
        if (split2.length != 2) {
            throw new DukeMissingArgumentException("The start and end time of an event cannot be empty.");
        }
        return new String[] {split[0], split2[0], split2[1]};
    }

    public LocalDateTime parseDateTime(String dateTime) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (Exception e) {
            throw new DukeMissingArgumentException("The date and time must be in the format yyyy-MM-dd HHmm.");
        }
    }

    public String getSearchTerm() throws DukeException {
        try {
            return String.join(" ", this.inputArray.subList(1, this.inputArray.size()));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    public Task parseTask(String line) throws DukeException {
        String[] split = line.split("\\|");
        String type = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];
        Task task = null;

        switch (type) {
            case "T": {
                task = new Todo(description);
                if (isDone) task.markAsDone();
                break;
            }
            case "D": {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime by = LocalDateTime.parse(split[3], formatter);
                task = new Deadline(description, by);
                if (isDone) task.markAsDone();
                break;
            }
            case "E": {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime start = LocalDateTime.parse(split[3], formatter);
                LocalDateTime end = LocalDateTime.parse(split[4], formatter);
                task = new Event(description, start, end);
                if (isDone) task.markAsDone();
                break;
            }
            default:
                throw new DukeInvalidCommandException();
        }
        return task;
    }
}
