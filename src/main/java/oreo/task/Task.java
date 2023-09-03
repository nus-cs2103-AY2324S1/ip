package oreo.task;

import oreo.exception.IllegalCommandException;
import oreo.exception.IllegalDateTimeException;
import oreo.datetime.TimeParser;
import java.util.Scanner;

public abstract class Task implements Comparable<Task> {
    protected int id;
    protected String description;
    protected boolean completed;
    private static int idTracker = 0;

    public Task(String d) {
        this.id = idTracker;
        idTracker++;
        this.description = d;
        this.completed = false;
    }

    public static Task generateTask(String command, Scanner tokeniser) throws IllegalCommandException,
            IllegalDateTimeException {
        Task newTask;
        if (!command.equals("todo") && !command.equals("deadline")
            && !command.equals("event")) {
            throw new IllegalCommandException("do that");
        } else if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("process an empty task");
        }
        if (command.equals("todo")) {
            String contents = tokeniser.nextLine();
            if (contents.contains("/by")) {
                throw new IllegalCommandException("do that for a todo," +
                        "are you thinking of a deadline?");
            } else if (contents.contains("/from") || contents.contains("/to")) {
                throw new IllegalCommandException("do that for a todo," +
                        "are you thinking of an event?");
            }
            newTask = new ToDo(contents);
        } else if (command.equals("deadline")){
            String contents = tokeniser.nextLine();
            if (!contents.contains("/by")) {
                throw new IllegalCommandException("set a deadline wihtout a \"/by\"");
            } else if (contents.contains("/from") || contents.contains("/to")) {
                throw new IllegalCommandException("do that for a deadline," +
                        "are you thinking of an event?");
            }
            String[] parts = contents.split(" /by ", 2);
            String[] dateTime = TimeParser.parseInputOut(parts[1]);
            newTask = new Deadline(parts[0], dateTime[0], dateTime[1]);
        } else {
            String contents = tokeniser.nextLine();
            if (!contents.contains("/from") || !contents.contains("/to")) {
                throw new IllegalCommandException("set an event wihtout a \"/from\" and/or \"/to\"");
            } else if (contents.contains("/by")) {
                throw new IllegalCommandException("do that for an event," +
                        "are you thinking of a deadline?");
            }
            String[] message = contents.split(" /from ", 2);
            String[] fromto = message[1].split(" /to ", 2);
            String[] fromDateTime = TimeParser.parseInputOut(fromto[0]);
            String[] toDateTime = TimeParser.parseInputOut(fromto[1]);
            newTask = new Event(message[0], fromDateTime[0], fromDateTime[1],
                    toDateTime[0], toDateTime[1]);
        }
        return newTask;
    }

    public boolean isComplete() {
        return this.completed;
    }

    public void switchMark() {
        completed = !completed;
    }

    public static Task generateSavedTask(int id, boolean mark, String description) throws IllegalDateTimeException {
        switch (id) {
        case (1):
            return new ToDo(description, mark);
        case (2):
            String[] parts = description.split("/", 2);
            String[] dateTime = TimeParser.parseInputOut(parts[1]);
            return new Deadline(parts[0], dateTime[0], dateTime[1], mark);
        default:
            String[] message = description.split("/", 3);
            String[] fromDateTime = TimeParser.parseInputOut(message[1]);
            String[] toDateTime = TimeParser.parseInputOut(message[2]);
            return new Event(message[0], fromDateTime[0], fromDateTime[1],
                    toDateTime[0], toDateTime[1], mark);
        }
    }

    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    public abstract String writeToFile();

    @Override
    public abstract String toString();

    @Override
    public int compareTo(Task other) {
        return this.id - other.id;
    }
}
