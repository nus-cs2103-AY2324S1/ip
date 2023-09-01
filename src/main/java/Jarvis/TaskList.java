package Jarvis;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing a list of tasks.
 * <p>
 *     This class encapsulates the list of tasks and provides methods for adding, removing, and retrieving tasks.
 * </p>
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList object from saved data.
     *
     * @param tasks The string that holds the saved data.
     */
    public TaskList(String tasks) { // tasks is a string
        String[] stringTasks =  tasks.split("\n");
        Pattern todoPattern = Pattern.compile(
                "\\[T\\]\\[(X|\\s)\\] (.+)"); // [T][-] xxxx
        Pattern deadlinePattern = Pattern.compile(
                "\\[D\\]\\[(X|\\s)\\] (.+) \\(by: (.+)\\)"); // [D][-] xxxx (by: xxxxxx)
        Pattern eventPattern = Pattern.compile(
                "\\[E\\]\\[(X|\\s)\\] (.+) \\(from: (.+) to: (.+)\\)"); // [E][-] xxxx (from: xxxxxx to: xxxxxx)

        taskList = new ArrayList<>();
        for (int i = 0; i < stringTasks.length; i++) {
            Matcher todoMatcher = todoPattern.matcher(stringTasks[i]);
            Matcher deadlineMatcher = deadlinePattern.matcher(stringTasks[i]);
            Matcher eventMatcher = eventPattern.matcher(stringTasks[i]);

            Task newTask;
            if (todoMatcher.matches()) {
                newTask = new ToDo(todoMatcher.group(2));
                if (todoMatcher.group(1).equals("X")) {
                    newTask.setDone(true);
                }
            } else if (deadlineMatcher.matches()) {
                newTask = new Deadline(deadlineMatcher.group(2), deadlineMatcher.group(3));
                if (deadlineMatcher.group(1).equals("X")) {
                    newTask.setDone(true);
                }
            } else {
                newTask = new Event(eventMatcher.group(2), eventMatcher.group(3), eventMatcher.group(4));
                if (eventMatcher.group(1).equals("X")) {
                    newTask.setDone(true);
                }
            }

            taskList.add(newTask); // add the task to the task list
        }
    }

    public void appendTask(Task task) {
        this.taskList.add(task);
    }

    public void addTask(Task task, int index) {
        this.taskList.add(index, task);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public int countTask() {
        return taskList.size();
    }

    // function checks if task number is valid and throws Jarvis.InvalidTaskNumberException
    public boolean isValidTaskNumber(int taskNum) {
        final String line = "____________________________________________________________";
        boolean isValid = true;
        if (taskNum < 0 || taskNum > this.taskList.size()) { // check if task number is of valid range
            try {
                throw new InvalidTaskNumberException("Apologies Sir, the task number you provided is out of range.");
            } catch (InvalidTaskNumberException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println("    There are currently " + this.taskList.size() + " tasks in the list.");
                System.out.println(line);
                isValid = false;
            }
        }
        return isValid;
    }

    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task.description.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
