import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public ToDo addToDoWithArgs(String commandArgs) {
        ToDo toDo = new ToDo(commandArgs);
        tasks.add(toDo);
        return toDo;
    }

    public Deadline addDeadlineWithArgs(String commandArgs) throws CommandArgumentException {
        String[] parsedArgs = Parser.parseDeadline(commandArgs);
        Deadline deadline = new Deadline(parsedArgs[0], parsedArgs[1]);
        tasks.add(deadline);
        return deadline;
    }

    public Event addEventWithArgs(String commandArgs) throws CommandArgumentException {
        String[] parsedArgs = Parser.parseEvent(commandArgs);
        Event event = new Event(parsedArgs[0], parsedArgs[1], parsedArgs[2]);
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
