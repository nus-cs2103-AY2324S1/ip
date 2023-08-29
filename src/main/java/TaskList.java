import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();
    private int size = 0;

    public TaskList() {}

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.size = list.size();
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Task markDone(String options) throws DukeException {
        try {
            Integer.parseInt(options);
        } catch(NumberFormatException e) {
            throw new DukeException("!!!: Please provide a number for the task to be marked done");
        }
        int index = Integer.parseInt(options) - 1;
        if (index < 0 || index >= size) {
            throw new DukeException(String.format("!!!: Please provide a valid number, " +
                    "there are currently %d items in the list", size));
        }
        Task task = list.get(index);
        task.markDone();
        return task;
    }

    public Task unmarkDone(String options) throws DukeException {
        try {
            Integer.parseInt(options);
        } catch(NumberFormatException e) {
            throw new DukeException("!!!: Please provide a number for the task to be marked not done");
        }
        int index = Integer.parseInt(options) - 1;
        if (index < 0 || index >= size) {
            throw new DukeException(String.format("!!!: Please provide a valid number, " +
                    "there are currently %d items in the list", size));
        }
        Task task = list.get(index);
        task.unmarkDone();
        return task;
    }

    public Task addTodoToList(String taskName) throws DukeException {
        if (taskName.isBlank()) throw new DukeException("!!!: Please provide a task name");
        Task task = new Todo(taskName);
        list.add(task);
        size++;
        return task;
    }

    public Task addDeadlineToList(String line) throws DukeException {
        if (line.indexOf("/by") == 0) throw new DukeException("!!!: Please provide a task name");
        if (!line.contains(" /by ")) throw new DukeException("!!!: Please provide a by date using format \"/by YYYY-MM-DD\"");
        String taskName = line.substring(0, line.indexOf("/by") - 1);
        if (taskName.isBlank()) throw new DukeException("!!!: Please provide a task name");
        String dueDate = line.substring(line.indexOf("/by") + 4);
        if (dueDate.isBlank()) throw new DukeException("!!!: Please provide a due date");
        Task task;
        try {
            task = new Deadline(taskName, dueDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date is not in the correct format YYYY-MM-DD");
        }
        list.add(task);
        size++;
        return task;
    }

    public Task addEventToList(String line) throws DukeException {
        if (line.indexOf("/from") == 0) throw new DukeException("!!!: Please provide a task name");
        if (!line.contains(" /from ")) throw new DukeException("!!!: Please provide a from date using \"/from from_date\"");
        if (!line.contains(" /to ")) throw new DukeException("!!!: Please provide a to date using \"/to to_date\"");
        String taskName = line.substring(0, line.indexOf("/from") - 1);
        if (taskName.isBlank()) throw new DukeException("!!!: Please provide a task name");
        String dateFrom = line.substring(line.indexOf("/from") + 6, line.indexOf("/to") - 1);
        if (dateFrom.isBlank()) throw new DukeException("!!!: Please provide a from date");
        String dateTo = line.substring(line.indexOf("/to") + 4);
        if (dateTo.isBlank()) throw new DukeException("!!!: Please provide a to date");
        Task task;
        try {
            task = new Event(taskName, dateFrom, dateTo);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date is not in the correct format YYYY-MM-DD");
        }
        list.add(task);
        size++;
        return task;
    }

    public Task deleteFromList(String options) throws DukeException {
        try {
            Integer.parseInt(options);
        } catch(NumberFormatException e) {
            throw new DukeException("!!!: Please provide a number for the task to be deleted");
        }
        int index = Integer.parseInt(options) - 1;
        if (index < 0 || index >= size) {
            throw new DukeException(String.format("!!!: Please provide a valid number, " +
                    "there are currently %d items in the list", size));
        }
        Task task = list.get(index);
        list.remove(index);
        size--;
        return task;
    }

    public String toLogString() {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= list.size(); ++i) {
            output.append(list.get(i - 1).toLogString()).append("\n");
        }
        return output.toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= list.size(); ++i) {
            output.append(String.format("%d. %s\n", i, list.get(i - 1)));
        }
        return output.toString();
    }
}
