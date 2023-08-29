import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Hashtable;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();
    private Hashtable<String, ArrayList<Task>> deadlinesDateTable = new Hashtable<>();
    private Hashtable<String, ArrayList<Task>> eventsStartDateTable = new Hashtable<>();
    private Hashtable<String, ArrayList<Task>> eventsEndDateTable = new Hashtable<>();
    private int size = 0;

    public TaskList() {}

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.size = list.size();
        for (Task task : list) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                addDeadlineDateToTable(deadline);
            } else if (task instanceof Event) {
                Event event = (Event) task;
                addEventDateToTable(event);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String getDeadlineDateTasks(String date) {
        String output = "";
        if (deadlinesDateTable.containsKey(date)) {
            output =  taskListToString(deadlinesDateTable.get(date));
        }
        if (output.isBlank()) {
            return "No tasks on this date";
        } else {
            return output;
        }
    }

    public String getEventStartDateTasks(String date) {
        String output = "";
        if (eventsStartDateTable.containsKey(date)) {
            output =  taskListToString(eventsStartDateTable.get(date));
        }
        if (output.isBlank()) {
            return "No tasks on this date";
        } else {
            return output;
        }
    }

    public String getEventEndDateTasks(String date) {
        String output = "";
        if (eventsEndDateTable.containsKey(date)) {
            output =  taskListToString(eventsEndDateTable.get(date));
        }
        if (output.isBlank()) {
            return "No tasks on this date";
        } else {
            return output;
        }
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
        Deadline task;
        try {
            task = new Deadline(taskName, dueDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date is not in the correct format YYYY-MM-DD");
        }
        addDeadlineDateToTable(task);
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
        Event task;
        try {
            task = new Event(taskName, dateFrom, dateTo);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date is not in the correct format YYYY-MM-DD");
        }
        addEventDateToTable(task);
        list.add(task);
        size++;
        return task;
    }

    private void addDeadlineDateToTable(Deadline deadline) {
        String dueDate = deadline.dueDate.toString();
        if (!deadlinesDateTable.containsKey(dueDate)) {
            deadlinesDateTable.put(dueDate, new ArrayList<>());
        }
        deadlinesDateTable.get(dueDate).add(deadline);
    }

    private void addEventDateToTable(Event event) {
        String dateFrom = event.dateFrom.toString();
        String dateTo = event.dateTo.toString();
        if (!eventsStartDateTable.containsKey(dateFrom)) {
            eventsStartDateTable.put(dateFrom, new ArrayList<>());
        }
        if (!eventsEndDateTable.containsKey(dateTo)) {
            eventsEndDateTable.put(dateTo, new ArrayList<>());
        }
        eventsStartDateTable.get(dateFrom).add(event);
        eventsEndDateTable.get(dateTo).add(event);
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
        return taskListToString(list);
    }

    private String taskListToString(ArrayList<Task> taskList) {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= taskList.size(); ++i) {
            output.append(String.format("%d. %s\n", i, taskList.get(i - 1)));
        }
        return output.toString();
    }
}
