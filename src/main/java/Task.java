import java.util.regex.Pattern;

public abstract class Task {
    private static Pattern toDoPattern = Pattern.compile("todo .+");
    private static Pattern deadlinePattern = Pattern.compile("deadline .+ /by .+");
    private static Pattern eventPattern = Pattern.compile("event .+ /from .+ /to .+");
    private boolean isDone = false;
    private String name;
    public Task(String name) {
        this.name = name;
    }
    protected boolean isDone() {
        return this.isDone;
    }
    protected String getName() {
        return this.name;
    }
    @Override
    public abstract String toString();
    public void mark() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }
    public static Task makeTask(String str) {
        if (toDoPattern.matcher(str).matches()) {
            Task newTask = makeToDo(str.substring(5));
            return newTask;
        } else if (deadlinePattern.matcher(str).matches()) {
            String[] comps = str.split("/");
            Task newTask = Task.makeDeadline(comps[0].substring(9),
                    comps[1].substring(3));
            return newTask;
        } else if (eventPattern.matcher(str).matches()) {
            String[] comps = str.split("/");
            Task newTask = Task.makeEvent(comps[0].substring(6),
                    comps[1].substring(5),
                    comps[2].substring(3));
            return newTask;
        }
        return null;
    }
    public static boolean canMakeTask(String input) {
        return toDoPattern.matcher(input).matches()
                || deadlinePattern.matcher(input).matches()
                || eventPattern.matcher(input).matches();
    }
    public static Task makeEvent(String name, String from, String to) {
        return new Event(name, from, to);
    }
    public static Task makeToDo(String name) {
        return new ToDo(name);
    }
    public static Task makeDeadline(String name, String by) {
        return new Deadline(name, by);
    }
    public static class ToDo extends Task {
        public ToDo(String name) {
            super(name);
        }
        @Override
        public String toString() {
            return "[T][" + (super.isDone() ? "X" : " ") + "] " + super.getName();
        }
    }
    public static class Deadline extends Task {
        private String by;
        public Deadline(String name, String by) {
            super(name);
            this.by = by;
        }
        @Override
        public String toString() {
            return "[D][" + (super.isDone() ? "X" : " ") + "] " + super.getName()
                    + "(by: " + this.by + ")";
        }
    }
    public static class Event extends Task {
        private String from;
        private String to;
        public Event(String name, String from, String to) {
            super(name);
            this.from = from;
            this.to = to;
        }
        @Override
        public String toString() {
            return "[E][" + (super.isDone() ? "X" : " ") + "] " + super.getName()
                    + "(from: " + this.from + " to: " + this.to + ")";
        }
    }
}
