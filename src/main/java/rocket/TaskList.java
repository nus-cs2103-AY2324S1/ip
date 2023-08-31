package rocket;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList (ArrayList<String> stringList) throws RocketIllegalArgumentException {
        this.tasks = new ArrayList<>();
        for (String s : stringList) {
            this.tasks.add(stringToTask(s));
        }
    }

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
            int descriptionIndex = arguments.indexOf("by") - 2;
            String description = arguments.substring(0, descriptionIndex);
            String deadline = arguments.substring(descriptionIndex + 5)
                    .replace(')', ' ')
                    .trim();
            if (description.isEmpty()) {
                throw new RocketIllegalArgumentException("The description of a deadline");
            }
            if (deadline.isEmpty()) {
                throw new RocketIllegalArgumentException("The deadline of a deadline");
            }
            LocalDateTime by = LocalDateTime.parse(deadline, Rocket.prettyDateTimeFormatter);
            return new Deadline(description, isDone, by);
        } else {
            String arguments = taskString.substring(7);
            int descriptionIndex = arguments.indexOf("from") - 2;
            String description = arguments.substring(0, descriptionIndex);
            if (description.isEmpty()) {
                throw new RocketIllegalArgumentException("The description of an event.");
            }
            String duration = arguments.substring(descriptionIndex + 7)
                    .trim();
            if (duration.isBlank()) {
                throw new RocketIllegalArgumentException("The duration of an event");
            }
            int fromIndex = duration.indexOf("to") - 1;
            LocalDateTime from = LocalDateTime.parse(
                    duration.substring(0, fromIndex).trim(),
                    Rocket.prettyDateTimeFormatter
            );
            LocalDateTime to = LocalDateTime.parse(
                    duration.substring(fromIndex + 4)
                            .replace(')', ' ')
                            .trim(),
                    Rocket.prettyDateTimeFormatter
            );
            return new Event(description, isDone, from, to);
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
            tasksString.append(String.valueOf(task));
            tasksString.append("\n");
        }
        return String.valueOf(tasksString);
    }
}
