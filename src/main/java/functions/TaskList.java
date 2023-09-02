package functions;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<String> taskDetails) {
        this.tasks = new ArrayList<>();
        for (String details : taskDetails) {
            loadTask(details);
        }
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private void loadTask(String input) {
        if (input.startsWith("[D]")) {
            int y = input.indexOf("(by: ");
            String desc = input.substring(7, y - 1);
            int end = input.indexOf(")");
            String by = input.substring(y + 5, end);
            LocalDateTime date = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            Task t = new Deadline(desc, date);
            tasks.add(t);
            if (input.substring(3, 5).equals("[X]")) {
                t.markAsDone();
            }
        } else if (input.startsWith("[T]")) {
            String desc = input.substring(7);
            Task t = new ToDo(desc);
            tasks.add(t);
            if (input.substring(3, 5).equals("[X]")) {
                t.markAsDone();
            }
        } else if (input.startsWith("[E]")) {
            int fromIndex = input.indexOf("(from: ");
            int toIndex = input.indexOf("to: ");
            String desc = input.substring(7, input.indexOf("(") - 1);
            String s = input.substring(fromIndex + 7, toIndex - 1).trim();
            String e = input.substring(toIndex + 4, input.indexOf(")")).trim();
            LocalDateTime start = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            LocalDateTime end = LocalDateTime.parse(e, DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            Task t = new Event(desc, start, end);
            tasks.add(t);
            if (input.substring(3, 5).equals("[X]")) {
                t.markAsDone();
            }
        }
    }

    public Task createTask(String desc, LocalDateTime first, LocalDateTime second) {
        Task t = null;
        if (first == null && second == null) {
            t = new ToDo(desc);
        } else if (second == null) {
            t = new Deadline(desc, first);
        } else {
            t = new Event(desc, first, second);
        }
        this.tasks.add(t);
        return t;
    }

    public Task markTask(int num) {
        Task t = this.tasks.get(num - 1);
        t.markAsDone();
        return t;
    }

    public Task unmarkTask(int num) {
        Task t = this.tasks.get(num - 1);
        t.unmarkAsDone();
        return t;
    }

    public Task deleteTask(int num) {
        return this.tasks.remove(num - 1);
    }

    public int numOfTasks() {
        return this.tasks.size();
    }

    public ArrayList<Task> showList() {
        return this.tasks;
    }

    public ArrayList<Task> searchTask(String description) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task match : this.tasks) {
            if (match.getDescription().contains(description)) {
                results.add(match);
            }
        }
        return results;
    }
}
