package duke.task;

import java.util.ArrayList;
import duke.task.Task;
import duke.task.Todos;
import duke.task.Deadlines;
import duke.task.Events;
public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public int size() {
        return this.getTaskList().size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task task) {
        this.getTaskList().add(task);
    }

    public Task getTask(int index) {
        return this.getTaskList().get(index);
    }

    public void removeTask(int index) {
        this.getTaskList().remove(index);
        return;
    }

    public String convertToFileContent() {
        String result = "";
        String line = "";
        for (int i = 0; i < this.size(); i++) {
            Task currentTask = this.getTask(i);
            if (currentTask instanceof Todos) {
                String mark = currentTask.isDone ? "1" : "0";
                line = currentTask.getTypeIcon() + " | " + mark + " | " + currentTask.description;
            } else if (currentTask instanceof Deadlines) {
                String mark = currentTask.isDone ? "1" : "0";
                line = currentTask.getTypeIcon() + " | " + mark + " | " + currentTask.description + " | " +
                        ((Deadlines) currentTask).endDate;
            } else if (currentTask instanceof Events) {
                String mark = currentTask.isDone ? "1" : "0";
                line = currentTask.getTypeIcon() + " | " + mark + " | " + currentTask.description + " | " +
                        ((Events) currentTask).startDate + " | " + ((Events) currentTask).endDate;
            }
            result += line + "\n";
        }
        return result;
    }

}
