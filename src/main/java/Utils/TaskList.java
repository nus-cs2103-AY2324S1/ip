package Utils;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    enum Type {
        MARK("mark"),
        UNMARK("unmark"),
        LIST("list"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        NOTFOUND("");

        private final String name;

        private Type(String name) {
            this.name = name;
        }

        protected static Type of(String name) {
            for (Type type : values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
            return NOTFOUND;
        }
    }

    protected TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    protected ArrayList<String> csvArray() {
        ArrayList<String> csv = new ArrayList<>();
        for (Task task : this.tasks) {
            csv.add(task.toCsv());
        }
        return csv;
    }
    
    protected Response execute(String input, String command) throws DukeException {
        Task task;

        switch(Type.of(command)) {
            case TODO:
                task = new Todo(Command.assertString(input,command));
                break;
            case DEADLINE:
                task = new Deadline(
                    Command.assertString(input,command), 
                    Command.assertString(input,"by")
                    );
                break;
            case EVENT:
                task = new Event(
                    Command.assertString(input, command), 
                    Command.assertString(input,"from"), 
                    Command.assertString(input,"to")
                    );
                break;
            case MARK:
                return this.mark(Command.assertInteger(input, command));
            case UNMARK:
                return this.unmark(Command.assertInteger(input, command));
            case DELETE:
                return this.delete(Command.assertInteger(input, command));
            case LIST:
                return this.list();
            default:
                throw new CommandNotFoundException();
        }
        this.tasks.add(task);
        return Response.generate(new String[]{
            "Got it. I've added this task:",
            "  " + task.toString(),
            String.format("Now you have %d tasks in the list.", tasks.size())
        });
    }

    protected Response list() {
        ArrayList<String> output = new ArrayList<>();
        output.add("Here are the tasks in your list:");
        int count = 0;
        for (Task task : this.tasks) {
            output.add(String.format("%d.%s",
                ++count,
                task.toString()
              ));
        }
        return Response.generate(output);
    }

    private boolean inRange(int idx) {
        return (idx > 0 && this.tasks.size() > --idx);
    }

    protected Response mark(int idx) throws DukeException{
        ArrayList<String> output = new ArrayList<>();
        if (!this.inRange(idx)) {
            throw new TaskNotFoundException();
        }
        Task task = this.tasks.get(--idx);
        task.mark();
        output.add("Nice! I've marked this task as done:");
        output.add("  " + task.toString());

        return Response.generate(output);
    }

    protected Response unmark(int idx) throws DukeException {
        ArrayList<String> output = new ArrayList<>();
        if (!this.inRange(idx)) {
            throw new TaskNotFoundException();
        }
        Task task = this.tasks.get(--idx);
        task.unmark();
        output.add("OK, I've marked this task as not done yet:");
        output.add("  " + task.toString());

        return Response.generate(output);
    }

    protected Response delete(int idx) throws DukeException {
        ArrayList<String> output = new ArrayList<>();
        if (!this.inRange(idx)) {
            throw new TaskNotFoundException();
        }
        Task task = this.tasks.get(--idx);
        output.add("Noted. I've removed this task:");
        output.add("  " + task.toString());
        this.tasks.remove(idx);
        output.add(String.format("Now you have %d tasks in the list.", tasks.size()));

        return Response.generate(output);
    }
}
