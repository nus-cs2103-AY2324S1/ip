package simon;

import simon.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTask(String inData, boolean markAsDone) throws SimonException {
        int index = parseIndex(inData);
        validateIndex(index);

        if (markAsDone) {
            tasks.get(index).markAsDone();
        } else {
            tasks.get(index).markAsUndone();
        }
        return tasks.get(index);
    }

    public Task deleteTask(String inData) throws SimonException {
        int index = parseIndex(inData);
        validateIndex(index);

        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    private int parseIndex(String inData) throws SimonException {
        String[] split = inData.split(" ");

        try {
            return Integer.parseInt(split[1]) - 1;
        } catch (NumberFormatException e) {
            throw new SimonException("Please provide a valid task number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SimonException("Please provide a task number.");
        }
    }

    private void validateIndex(int index) throws SimonException {
        if (this.tasks.isEmpty()) {
            throw new SimonException("There are no tasks to modify.");
        }
        if (index < 0 || index >= this.tasks.size()) {
            throw new SimonException("Invalid task number. Choose a number between 1 and " + tasks.size() + ".");
        }
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(this.tasks);
    }

    /**
     * Parses the input to retrieve the keyword and finds tasks containing the keyword.
     *
     * @param inData The input command to be parsed.
     * @return A TaskList containing the matched tasks.
     * @throws SimonException If the input format is incorrect or no keyword is provided.
     */
    public TaskList findTasks(String inData) throws SimonException {
        String keyword = parseKeyword(inData);
        validateKeyword(keyword);
        return find(keyword);
    }

    /**
     * Parses the input to retrieve the keyword for the find command.
     *
     * @param inData The input command to be parsed.
     * @return The keyword from the input command.
     * @throws SimonException If no keyword is provided.
     */
    private String parseKeyword(String inData) throws SimonException {
        String[] split = inData.split(" ");
        try {
            return split[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SimonException("Please provide a keyword to search.");
        }
    }

    /**
     * Validates that the keyword is not empty or null.
     *
     * @param keyword The keyword to be validated.
     * @throws SimonException If the keyword is empty or null.
     */
    private void validateKeyword(String keyword) throws SimonException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new SimonException("Keyword cannot be empty. Please provide a valid keyword.");
        }
    }

    /**
     * Finds tasks that contain the given keyword.
     *
     * @param keyword The keyword to be searched in tasks.
     * @return A TaskList containing the matched tasks.
     */
    private TaskList find(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return new TaskList(matchedTasks);
    }


}
