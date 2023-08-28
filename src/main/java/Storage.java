import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {
    private final ArrayList<Task> tasks;

    public Storage() {
        this.tasks = new ArrayList<>();
    }

    public String addTask(TaskType taskType, String description) throws DukeException {
        description = description.trim();
        switch (taskType) {
        case TODO:
            if (description.length() > 0) {
                this.tasks.add(new ToDo(description));
            } else {
                throw new DukeException("todo error");
            }
            break;
        case DEADLINE:
            Pattern p = Pattern.compile("(.+) /by (.+)");
            Matcher m = p.matcher(description);
            if (m.matches() && m.group(1).length() > 0 && m.group(2).length() > 0) {
                this.tasks.add(new Deadline(m.group(1), m.group(2)));
            } else {
                throw new DukeException("deadline error");
            }
            break;
        default:
            Pattern pattern = Pattern.compile("(.+) /from (.+) /to (.+)");
            Matcher matcher = pattern.matcher(description);
            if (matcher.matches() && matcher.group(1).length() > 0 && matcher.group(2).length() > 0 && matcher.group(3).length() > 0) {
                this.tasks.add(new Event(matcher.group(1), matcher.group(2), matcher.group(3)));
            } else {
                throw new DukeException("event error");
            }
            break;
        }
        int size = this.tasks.size();
        String taskInTotal = size > 1 ? " tasks in total." : " task in total.";
        return "Task added:\n" + this.tasks.get(size - 1) + "\nNow you have " + size + taskInTotal + "\n\"Be here now.\"";
    }

    public String getTasks() {
        String result = "Here are your tasks:\n";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + " " + tasks.get(i) + "\n";
        }
        return result + "\"One thing at a time.\"";
    }

    public String markTask(int taskIndex, boolean isDone) throws DukeException {
        if (taskIndex > tasks.size() || taskIndex <= 0) {
            throw new DukeException("task not found");
        } else {
            tasks.get(taskIndex - 1).markAsDone(isDone);
            return "Here's your modified task:\n" + tasks.get(taskIndex - 1) + "\n\"Keep moving forward.\"";
        }
    }

    public String deleteTask(int taskIndex) throws DukeException {
        //TODO: double check if not completed
        if (taskIndex > tasks.size() || taskIndex <= 0) {
            throw new DukeException("task not found");
        } else {
            Task task = tasks.get(taskIndex - 1);
            tasks.remove(taskIndex - 1);
            int size = this.tasks.size();
            String taskInTotal = size > 1 ? " tasks in total." : " task in total.";
            return "I've successfully deleted this task:\n" + task + "\nNow you have " + size + taskInTotal + "\n\"Ride the waves.\"";
        }
    }
}
