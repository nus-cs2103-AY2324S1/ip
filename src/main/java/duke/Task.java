package duke;

import duke.parser.DukeParseException;
import duke.parser.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private final String desc;
    private boolean marked;

    public Task(String desc) {
        this.desc = desc;
        marked = false;
    }

    public static Task decode(String encodedTask) {
        Pattern pattern = Pattern.compile("^(?<type>.)\\|(?<mark>.)\\|(?<taskString>.+)$");
        Matcher matcher = pattern.matcher(encodedTask);
        String type = matcher.group("type");
        String mark = matcher.group("mark");
        String input = matcher.group("taskString");
        Task task;
        switch (type) {
            case "T":
                task = Parser.parseTodo(input);
                break;
            case "D":
                try {
                    task = Parser.parseDeadline(input);
                } catch (DukeParseException e) {
                    return null;
                }
                break;
            case "E":
                try {
                    task = Parser.parseEvent(input);
                } catch (DukeParseException e) {
                    return null;
                }
                break;
            default:
                return null;
        }
        if (mark.equals("1")) {
            task.mark();
        }
        return task;
    }

    public void mark() {
        marked = true;
    }

    public void unmark() {
        marked = false;
    }

    public String encode() {
        return String.format("%s|%s", marked ? "1" : "0", desc);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", marked ? "X" : " ", desc);
    }
}
