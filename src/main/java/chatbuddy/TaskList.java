package chatbuddy;

import chatbuddy.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    private void checkValidTaskNumber(int taskNum) throws ChatBuddyException {
        if (taskNum > tasks.size()) {
            throw new ChatBuddyException("Please input a valid task number. There are only " +
                    getSize() + " tasks in the list.");
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskNum) throws ChatBuddyException {
        // check for valid task number
        checkValidTaskNumber(taskNum);

        // delete task from list
        int taskIndex = taskNum - 1;
        Task task = tasks.remove(taskIndex);
        return task;
    }

    public Task markTaskAsDone(int taskNum) throws ChatBuddyException {
        // check for valid task number
        checkValidTaskNumber(taskNum);

        // mark task as done
        int taskIndex = taskNum - 1;
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        return task;
    }

    public Task markTaskAsNotDone(int taskNum) throws ChatBuddyException {
        // check for valid task number
        checkValidTaskNumber(taskNum);

        // mark task as done
        int taskIndex = taskNum - 1;
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        return task;
    }

    public ArrayList<String> getTaskStringsToSave() {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task task : tasks) {
            String taskString = task.getInformationForSaving();
            taskStrings.add(taskString);
        }
        return taskStrings;
    }

    public ArrayList<String> getTaskStringsToPrint() {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task task : tasks) {
            taskStrings.add(task.toString());
        }
        return taskStrings;
    }

    /**
     * Finds a list of tasks containing the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A TaskList containing a list of tasks that contains the given keyword.
     */
    public TaskList getMatchingTasks(String keyword) {
        return new TaskList(tasks.stream()
                .filter(task -> task.containKeyword(keyword))
                .collect(Collectors.toCollection(ArrayList::new)));
    }
}
