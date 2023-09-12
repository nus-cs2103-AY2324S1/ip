package seedu.duke;
import seedu.duke.Task;
import java.util.ArrayList;

/**
 * TaskList models the list of Tasks by sotring Task objects in an ArrayList
 *
 * @param input takes in a string of tasks to be parsed into Task in the list
 */
class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(String input) {
        this.tasks = new ArrayList<>();

        String[] lines = input.split("\n");

        for (String line : lines) {
            if (line.equals("")) {
                continue;
            }
            Task task = createTaskFromInput(line);
            if (task != null) {
                tasks.add(task);
            }
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    public Task get(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            System.out.println("Invalid index.");
            return null;
        }
    }

    public int size() {
        return tasks.size();
    }
    private Task createTaskFromInput(String input) {
        char type = input.charAt(1);
        boolean isDone = (input.charAt(4) == 'X');

        int openParenIndex = input.indexOf('('); // Find the index of the open parenthesis

        String description;

        if (openParenIndex != -1) {
            // If there is an open parenthesis, extract description till the open parenthesis
            description = input.substring(7, openParenIndex).trim();  // Trim whitespace
        } else {
            // If there's no parenthesis, use the entire string after the type marker
            description = input.substring(7).trim();  // Trim whitespace
        }

        Task task;

        if (type == 'T') {
            task = new Todo(description);
        } else if (type == 'D') {
            int byIndex = input.indexOf("(by: ");
            String by = input.substring(byIndex + 5, input.length() - 1).trim();  // Trim whitespace
            task = new Deadline(description, by);
        } else if (type == 'E') {
            int fromIndex = input.indexOf("(from: ");
            int toIndex = input.indexOf("to: ");
            String from = input.substring(fromIndex + 7, toIndex).trim();  // Trim whitespace
            String to = input.substring(toIndex + 4, input.length() - 1).trim();  // Trim whitespace
            task = new Event(description, from, to);
        } else {
            return null;
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    public TaskList findmatching(String filterWord) {
        TaskList filteredTaskList = new TaskList("");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String description = currentTask.toString();
            if (description.contains(filterWord)) {
                filteredTaskList.add(currentTask);
            }
        }
        return filteredTaskList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(task.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}