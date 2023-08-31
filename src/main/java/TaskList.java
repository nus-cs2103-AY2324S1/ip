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
    enum DateTimeFormat {
        FORMAT1("yyyy-MM-dd HH:mm"),
        FORMAT2("dd.MM.yyyy HH:mm"),
        FORMAT3("MM/dd/yyyy HH:mm"),
        FORMAT4("yyyy-MM-dd hh:mm a"),
        FORMAT5("dd.MM.yyyy hh:mm a"),
        FORMAT6("MM/dd/yyyy hh:mm a"),
        FORMAT7("yyyy-MM-dd HHmm"),
        FORMAT8("dd.MM.yyyy HHmm"),
        FORMAT9("MM/dd/yyyy HHmm");

        private final DateTimeFormatter formatter;

        DateTimeFormat(String pattern) {
            this.formatter = DateTimeFormatter.ofPattern(pattern);
        }

        public DateTimeFormatter getFormatter() {
            return formatter;
        }
    }

    public LocalDateTime parseDate(String dateString) {
        for (DateTimeFormat format : DateTimeFormat.values()) {
            try {
                LocalDateTime d = LocalDateTime.parse(dateString, format.getFormatter());
                return d;
            } catch (DateTimeParseException e) {
                // Try the next format
            }
        }
        // None of the formats matched
        return null;
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
}
