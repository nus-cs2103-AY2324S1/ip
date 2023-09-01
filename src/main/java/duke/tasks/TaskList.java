package duke.tasks;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String getTaskAsString(int index) {
        return taskList.get(index).toString();
    }

    public void printTasksAsList() {
        int index = 1;
        for (Task task : taskList) {
            System.out.println(index + "." + task.toString());
            index++;
        }
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public void markTaskAsDone(int index, boolean done) {
        getTask(index).markTaskCompleted(done);
    }

    public String getTaskListData() {
        String data = "";
        for (Task task : taskList) {
            data = data.concat(task.toData() + "\n");
        }
        return data;
    }

    /**
     * Returns a TaskList only containing Tasks whose descriptions contain the specified keyword.
     * Search ignores the case of the keyword.
     *
     * @param keyword The keyword.
     * @return A TaskList containing Tasks with the keyword.
     */
    public TaskList findTasksByKeyword(String keyword) {
        TaskList output = new TaskList();
        for (Task task : taskList) {
            if (task.containsWord(keyword)) {
                output.addTask(task);
            }
        }
        return output;
    }
}
