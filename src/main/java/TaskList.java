import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private void addTodo(String[] input) throws BongoException {
        if (input.length <= 1)
            throw new BongoException("OOPS!!! The description of a todo cannot be empty.");
        String newTaskDesc = String.join(" ", input).substring(input[0].length() + 1);
        this.tasks.add(new Todo(newTaskDesc));
    }

    private void addDeadline(String[] input) throws BongoException {
        if (input.length <= 1)
            throw new BongoException("OOPS!!! The description of a deadline cannot be empty.");
        String taskInput = String.join(" ", input).substring(input[0].length() + 1);
        int index = taskInput.indexOf("/by");
        if (index == -1) throw new BongoException("OOPS!!! Please include the deadline.");
        String taskDesc = taskInput.substring(0, index - 1);
        String taskDeadline = taskInput.substring(index + 4);
        LocalDateTime deadline = formatDateTime(taskDeadline);
        this.tasks.add(new Deadline(taskDesc, deadline));
    }

    private void addEvent(String[] input) throws BongoException {
        if (input.length <= 1)
            throw new BongoException("OOPS!!! The description of an event cannot be empty.");
        String taskInput = String.join(" ", input).substring(input[0].length() + 1);
        int fromIndex = taskInput.indexOf("/from");
        int toIndex = taskInput.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1)
            throw new BongoException("OOPS!!! Please include the to and from date/time of the event.");
        String taskDesc = taskInput.substring(0, fromIndex - 1);
        String taskFrom = taskInput.substring(fromIndex + 6, toIndex - 1);
        String taskTo = taskInput.substring(toIndex + 4);
        LocalDateTime from = formatDateTime(taskFrom);
        LocalDateTime to = formatDateTime(taskTo);
        this.tasks.add(new Event(taskDesc, from, to));
    }

    private void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    private void markTaskDone(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
    }

    private void markTaskUndone(int taskIndex) {
        tasks.get(taskIndex).markAsUndone();
    }
}
