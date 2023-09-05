package Utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class TaskList {
    private ArrayList<Task> tasks;
    enum Type {
        MARK,
        UNMARK,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        NOTFOUND
    }
    private final HashMap<String,Type> commandMap = new HashMap<>(Map.of(
        "mark", Type.MARK,
        "unmark", Type.UNMARK,
        "list", Type.LIST,
        "todo", Type.TODO,
        "deadline", Type.DEADLINE,
        "event", Type.EVENT,
        "delete", Type.DELETE
      ));

    protected TaskList() {
        this.tasks = new ArrayList<>();
    }

    protected Response execute(String input, String command) throws DukeException {
        Task task;
        Type commandVal = commandMap.get(command) == null ? Type.NOTFOUND : commandMap.get(command);

        switch(commandVal) {
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
