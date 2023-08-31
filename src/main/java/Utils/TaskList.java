package Utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.*;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> tasks;
    enum Type {
        MARK,
        UNMARK,
        LIST,
        TODO,
        DEADLINE,
        EVENT
    }
    private HashMap<String,Type> commandMap = new HashMap<>(Map.of(
        "mark", Type.MARK,
        "unmark", Type.UNMARK,
        "list", Type.LIST,
        "todo", Type.TODO,
        "deadline", Type.DEADLINE,
        "event", Type.EVENT
      ));

    protected TaskList() {
        this.tasks = new ArrayList<>();
    }

    private String getArg(String input, String targetArg) {
        String[] args = input.split("/");
        for (String arg : args) {
            String[] words = arg.split(" ");
            String argName = words[0];
            if (argName.equals(targetArg)) {
                return Arrays.stream(words).skip(1).collect(Collectors.joining(" "));
            }
        }
        return "";
    }

    protected Response execute(String input, String[] commandArgs) {
        String command = commandArgs[0];
        Task task;
        int idx;
        switch(commandMap.get(command)) {
            case TODO:
                task = new Todo(getArg(input,command));
                break;
            case DEADLINE:
                task = new Deadline(getArg(input,command), getArg(input,"by"));
                break;
            case EVENT:
                task = new Event(getArg(input,command), getArg(input,"from"), getArg(input,"to"));
                break;
            case MARK:
                idx = Integer.parseInt(commandArgs[1]);
                return this.mark(idx);
            case UNMARK:
                idx = Integer.parseInt(commandArgs[1]);
                return this.unmark(idx);
            case LIST:
                return this.list();
            default:
                return new InputResponse("No such Command");
        }
        InputResponse taskResponse = new InputResponse("Got it. I've added this task:");
        this.tasks.add(task);
        taskResponse.add("  " + task.toString());
        taskResponse.add(String.format("Now you have %d tasks in the list.", tasks.size()));
        return taskResponse;
    }

    protected Response list() {
        InputResponse taskResponse = new InputResponse();
        taskResponse.add("Here are the tasks in your list:");
        int count = 0;
        for (Task task : this.tasks) {
            taskResponse.add(String.format("%d.%s",
                ++count,
                task.toString()
              ));
        }
        return taskResponse;
    }

    private boolean inRange(int idx) {
        return (this.tasks.size() > --idx);
    }

    protected Response mark(int idx) {
        InputResponse taskResponse = new InputResponse();
        if (this.inRange(idx)) {
            Task task = this.tasks.get(--idx);
            task.mark();
            taskResponse.add("Nice! I've marked this task as done:");
            taskResponse.add("  " + task.toString());
        }
        return taskResponse;
    }

    protected Response unmark(int idx) {
        InputResponse taskResponse = new InputResponse();
        if (this.inRange(idx)) {
            Task task = this.tasks.get(--idx);
            task.unmark();
            taskResponse.add("OK, I've marked this task as not done yet:");
            taskResponse.add("  " + task.toString());
        }
        return taskResponse;
    }
}
