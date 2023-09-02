import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getTasksSize() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void listTasks(Ui ui) {
        for (int index = 0; index < tasks.size(); index++) {
            ui.printTaskListFormat(tasks.get(index), index + 1);
        }
    }

    public void listTasks(Ui ui, LocalDateTime queryDate) {
        for (int index = 0; index < tasks.size(); index++) {
            if (queryDate != null) {
                Task currTask = tasks.get(index);
                if (currTask instanceof Event) {
                    Event event = (Event) currTask;
                    if (!event.isOngoing(queryDate)) {
                        continue;
                    }
                } else if (currTask instanceof Deadline) {
                    Deadline deadline = (Deadline) currTask;
                    if (!deadline.isDue(queryDate)) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            ui.printTaskListFormat(tasks.get(index), index + 1);
        }
    }

    public Pair<Task, Boolean> markTask(int index) {
        boolean wasNotMarked = tasks.get(index).markDone();
        return new Pair<>(tasks.get(index), wasNotMarked);
    }

    public Pair<Task, Boolean> unmarkTask(int index) {
        boolean wasNotUnmarked = tasks.get(index).unmarkDone();
        return new Pair<>(tasks.get(index), wasNotUnmarked);
    }

    public ToDo addToDoWithArgs(Map<String, String> commandArgs) {
        ToDo toDo = new ToDo(commandArgs.get(""));
        tasks.add(toDo);
        return toDo;
    }

    public Deadline addDeadlineWithArgs(Map<String, String> commandArgs) throws CommandArgumentException {
        if (commandArgs.get("").isEmpty()) {
            throw new CommandArgumentException("Task description cannot be empty!");
        }
        if (commandArgs.get("by") == null) {
            throw new CommandArgumentException("Deadline missing a /by argument!");
        }
        if (commandArgs.get("by").isEmpty()) {
            throw new CommandArgumentException("/by argument cannot be empty!");
        }
        Deadline deadline = new Deadline(commandArgs.get(""), commandArgs.get("by"));
        tasks.add(deadline);
        return deadline;
    }

    public Event addEventWithArgs(Map<String, String> commandArgs) throws CommandArgumentException {
        if (commandArgs.get("").isEmpty()) {
            throw new CommandArgumentException("Task description cannot be empty!");
        }
        if (commandArgs.get("from") == null) {
            throw new CommandArgumentException("Event missing a /from argument!");
        }
        if (commandArgs.get("from").isEmpty()) {
            throw new CommandArgumentException("/from argument cannot be empty!");
        }
        if (commandArgs.get("to") == null) {
            throw new CommandArgumentException("Event missing a /to argument!");
        }
        if (commandArgs.get("to").isEmpty()) {
            throw new CommandArgumentException("/to argument cannot be empty!");
        }
        Event event = new Event(commandArgs.get(""), commandArgs.get("from"), commandArgs.get("to"));
        tasks.add(event);
        return event;
    }

    public Task deleteTask(int index) {
        Task task = tasks.remove(index);
        return task;
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            string += tasks.get(i).toCommaString() + "\n";
        }
        return string;
    }
}
