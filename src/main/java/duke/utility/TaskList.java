package duke.utility;

import duke.exception.FailedSearchException;
import duke.exception.InvalidTaskException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private final ArrayList<Task> array;

    public TaskList() {
        this.array = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        // add new task into our array
        this.array.add(newTask);
    }

    public Task getTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber > this.array.size() || taskNumber == 0) {
            throw new InvalidTaskException();
        }
        return this.array.get(taskNumber - 1);
    }

    public int getLength() {
        return this.array.size();
    }

    public void deleteTask(Task task) {
        for (int i = 0; i < this.array.size(); i++) {
            if (this.array.get(i).equals(task)) {
                this.array.remove(i);
                break;
            }
        }
    }

    /**
     * Search the given keyword for all the task names in the task list
     * and returns the filtered task list.
     *
     * @param keyword Keyword to search for.
     * @return A new TaskList object containing all tasks that contains the given keyword.
     * @throws FailedSearchException if no task matches the given keyword.
     */
    public TaskList searchTask(String keyword) throws FailedSearchException {

        // copy all contents of task list into search list
        TaskList searchList = new TaskList();
        searchList.array.addAll(this.array);

        int originalSize = searchList.getLength();

        for (int i = 0; i < originalSize; i++) {
            for (int j = 0; j < searchList.getLength(); j++) {
                String taskName = searchList.array.get(j).getName();

                // remove tasks that do not contain the keyword
                if (!taskName.contains(keyword)) {
                    searchList.array.remove(j);
                    break;
                }
            }
        }

        // none of the tasks matches the keyword, throw an exception
        if (searchList.array.isEmpty()) {
            throw new FailedSearchException();
        }

        return searchList;
    }

    @Override
    public String toString() {
        // display in numerical pointers
        String list = "";
        for (int i = 0; i < this.array.size(); i++) {
            Task task = this.array.get(i);
            if (task == null) {
                break;
            } else {
                list += (i + 1) + "." + task + "\n";
            }
        }
        return list;
    }
}
