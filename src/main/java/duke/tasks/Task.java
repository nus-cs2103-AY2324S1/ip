package duke.tasks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.parser.DukeParseException;
import duke.parser.Parser;

/**
 * Represents a Task.
 * This class is abstract because tasks should exist as a Todo, Deadline or Event.
 */
public abstract class Task {
    private final String desc;
    private boolean isMarked;

    /**
     * Creates a Task given its description.
     *
     * @param desc the description of the task
     */
    public Task(String desc) {
        this.desc = desc;
        isMarked = false;
    }

    /**
     * Decodes a task encoded string into a task.
     *
     * @param encodedTask the string representing the encoded task
     * @return the task retrieved after decoding
     */
    public static Task decode(String encodedTask) {
        Pattern pattern = Pattern.compile("^(?<type>.)\\|(?<mark>.)\\|(?<taskString>.+)$");
        Matcher matcher = pattern.matcher(encodedTask);
        if (!matcher.matches()) {
            return null;
        }
        String type = matcher.group("type");
        String mark = matcher.group("mark");
        String input = matcher.group("taskString");
        Task task;
        try {
            switch (type) {
            case "T":
                task = Parser.parseTodo(input);
                break;
            case "D":
                task = Parser.parseDeadline(input);
                break;
            case "E":
                task = Parser.parseEvent(input);
                break;
            default:
                return null;
            }
        } catch (DukeParseException e) {
            return null;
        }
        if (mark.equals("1")) {
            task.mark();
        }
        return task;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public String getDesc() {
        return desc;
    }

    public void mark() {
        isMarked = true;
    }

    public void unmark() {
        isMarked = false;
    }

    public String encode() {
        return String.format("%s|%s", isMarked ? "1" : "0", desc);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isMarked ? "X" : " ", desc);
    }
}
