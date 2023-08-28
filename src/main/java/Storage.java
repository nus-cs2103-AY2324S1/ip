import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {
    private final ArrayList<Task> tasks;

    public Storage() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String description) {
        TaskType taskType = TaskType.valueOf(description.substring(0, description.indexOf(" ")).toUpperCase());
        boolean isDone = description.charAt(description.indexOf("|") + 2) == '1';
        String taskDescription = description.substring(description.indexOf("|") + 6);
        switch (taskType) {
        case TODO:
            this.tasks.add(new ToDo(taskDescription, isDone));
            break;
        case DEADLINE:
            String by = taskDescription.substring(taskDescription.indexOf("|") + 2);
            taskDescription = taskDescription.substring(0, taskDescription.indexOf("|") - 1);
            this.tasks.add(new Deadline(taskDescription, by, isDone));
            break;
        default:
            int secondDividerIndex = taskDescription.indexOf("|", taskDescription.indexOf("|") + 2);
            String from = taskDescription.substring(taskDescription.indexOf("|") + 2, secondDividerIndex - 1);
            String to = taskDescription.substring(secondDividerIndex + 2);
            taskDescription = taskDescription.substring(0, taskDescription.indexOf("|") - 1);
            this.tasks.add(new Event(taskDescription, from, to, isDone));
            break;
        }
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
            if (matcher.matches() && matcher.group(1).length() > 0 && matcher.group(2).length() > 0 &&
                    matcher.group(3).length() > 0) {
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

    public String getTasksTxt() {
        String result = "";
        for (int i = 0; i < tasks.size() - 1; i++) {
            result += tasks.get(i).toTxt() + System.lineSeparator();
        }
        return result + tasks.get(tasks.size() - 1).toTxt();
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
