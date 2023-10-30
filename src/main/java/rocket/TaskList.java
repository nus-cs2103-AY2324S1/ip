package rocket;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Create new task list.
     * @param stringList list of strings representing tasks.
     * @throws RocketIllegalArgumentException if strings can't be converted into tasks (invalid strings).
     */
    public TaskList (ArrayList<String> stringList) throws RocketIllegalArgumentException {
        this.tasks = new ArrayList<>();
        for (String s : stringList) {
            this.tasks.add(stringToTask(s));
        }
    }

    /**
     * Create empty task list.
     */
    public TaskList () {
        this.tasks = new ArrayList<>();
    }

    private static Task stringToTask(String taskString) throws RocketIllegalArgumentException {
        char taskType = taskString.charAt(1);
        boolean isDone = taskString.charAt(4) == 'X';
        if (taskType == 'T') {
            return new Todo(taskString.substring(7), isDone);
        } else if (taskType == 'D') {
            String arguments = taskString.substring(7);

            Parser.DeadlineInfo deadlineInfo = Parser.parseDeadlineStr(arguments, Parser.prettyDateTimeFormatter);
            String description = deadlineInfo.getDescription();
            LocalDateTime deadline = deadlineInfo.getDeadline();

            return new Deadline(description, isDone, deadline);
        } else {
            String arguments = taskString.substring(7);
            Parser.EventInfo eventInfo = Parser.parseEventStr(arguments, Parser.prettyDateTimeFormatter);
            String description = eventInfo.getDescription();
            LocalDateTime startDate = eventInfo.getStartDate();
            LocalDateTime endDate = eventInfo.getEndDate();

            return new Event(description, isDone, startDate, endDate);
        }
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }
    public void remove(int i) {
        this.tasks.remove(i);
    }

    @Override
    public String toString(){
        StringBuilder tasksString = new StringBuilder();
        for (Task task: tasks) {
            tasksString.append(task);
            tasksString.append("\n");
        }
        return String.valueOf(tasksString);
    }
}
