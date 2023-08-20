public abstract class Task {
    private boolean isDone = false;
    private final String name;
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
    public static Task makeTask(String str) throws InvalidTaskException {
        Task newTask;
        if (str.startsWith("todo")) {
            newTask = makeToDo(str);
        } else if (str.startsWith("deadline")) {
            newTask = makeDeadline(str);
        } else {
            newTask = makeEvent(str);
        }
        return newTask;
    }
    public static boolean isTaskCommand(String str) {
        if (str.length() < 4) {
            return false;
        }
        if (str.startsWith("todo")) {
            return true;
        }
        if (str.length() < 5) {
            return false;
        }
        if (str.startsWith("event")) {
            return true;
        }
        if (str.length() < 8) {
            return false;
        }
        return str.startsWith("deadline");
    }
    public static Task makeEvent(String str) throws InvalidTaskException {
        String[] comps = str.split("/");
        if (comps.length != 3) {
            throw new InvalidTaskException("Please make sure the event is written in the correct format:\n"
                    + "event ... /from ... /to ...");
        } else if (comps[0].trim().equals("event")) {
            throw new InvalidTaskException("Sorry, the event description can't be empty.");
        } else if (comps[1].trim().equals("from")) {
            throw new InvalidTaskException("Sorry, event start time can't be empty.");
        } else if (comps[2].trim().equals("to")) {
            throw new InvalidTaskException("Sorry, event end time can't be empty.");
        } else if (!comps[1].startsWith("from ") || !comps[2].startsWith("to ")) {
            throw new InvalidTaskException("Please make sure the event is written in the correct format:\n"
                    + "event ... /from ... /to ...");
        }
        return new Event(comps[0].substring(6),
                comps[1].substring(5),
                comps[2].substring(3));
    }
    public static Task makeToDo(String str) throws InvalidTaskException {
        String name = str.substring(4).trim();
        if (name.equals("")) {
            throw new InvalidTaskException("Sorry, the todo description can't be empty.");
        }
        return new ToDo(name);
    }
    public static Task makeDeadline(String str) throws InvalidTaskException {
        String[] comps = str.split("/");
        if (comps.length != 2) {
            throw new InvalidTaskException("Please make sure the deadline is written in the correct format:\n"
                    + "deadline ... /by ...");
        } else if (comps[0].trim().equals("deadline")) {
            throw new InvalidTaskException("Sorry, the deadline description can't be empty.");
        } else if (comps[1].trim().equals("by")) {
            throw new InvalidTaskException("Sorry, the deadline can't be empty.");
        } else if (!comps[1].startsWith("by")) {
            throw new InvalidTaskException("Please make sure the deadline is written in the correct format:\n"
                    + "deadline ... /by ...");
        }
        return new Deadline(comps[0].substring(9),
                comps[1].substring(3));
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
        private final String by;
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
        private final String from;
        private final String to;
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
