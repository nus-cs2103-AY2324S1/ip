import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) throws IOException {
        tasks.add(task);
    }

    private boolean isInvalidTaskNumber(int taskNumber) {
        return (taskNumber <= 0) || (taskNumber > tasks.size());
    }

    public Task markTask(int taskNumber) throws DukeException {
        if (isInvalidTaskNumber(taskNumber)) {
            throw new DukeException(DukeExceptionType.INVALID_RANGE);
        } else if (tasks.get(taskNumber - 1).isDone()) {
            throw new DukeException(DukeExceptionType.TASK_ALREADY_MARKED);
        } else {
            tasks.get(taskNumber - 1).markAsDone();
            return tasks.get(taskNumber - 1);
        }
    }

    public Task unmarkTask(int taskNumber) throws DukeException, IOException {
        if (isInvalidTaskNumber(taskNumber)) {
            throw new DukeException(DukeExceptionType.INVALID_RANGE);
        } else if (!tasks.get(taskNumber - 1).isDone()) {
            throw new DukeException(DukeExceptionType.TASK_ALREADY_UNMARKED);
        } else {
            tasks.get(taskNumber - 1).markAsNotDone();
            return tasks.get(taskNumber - 1);
        }
    }

    public Task deleteTask(int taskNumber) throws DukeException {
        if (isInvalidTaskNumber(taskNumber)) {
            throw new DukeException(DukeExceptionType.INVALID_RANGE);
        } else {
            return tasks.remove(taskNumber - 1);
        }
    }

    public List<Task> showTasksDueOn(LocalDate date) {
        return tasks.stream()
                .filter(x -> x instanceof Deadline)
                .map(task -> (Deadline) task)
                .filter(deadline -> deadline.getBy().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }
}