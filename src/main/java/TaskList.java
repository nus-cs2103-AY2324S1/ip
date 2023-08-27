import java.util.ArrayList;
import java.util.List;

class TaskList {
    private List<Task> taskList;

    TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    TaskList() {
        this(new ArrayList<>());
    }

    int getListSize() {
        return taskList.size();
    }

    void addTask(Task task) {
        taskList.add(task);
    }

    Task deleteTask(int index) throws DukeException {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException(String.format(" ☹ OOPS!!! Task %d does not exist.", index));
        }
        return taskList.remove(index - 1);
    }

    Task markTask(int index) throws DukeException {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException(String.format(" ☹ OOPS!!! Task %d does not exist.", index));
        }
        Task task = taskList.get(index - 1);
        task.markIsDone();
        return task;
    }

    Task unmarkTask(int index) throws DukeException {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException(String.format(" ☹ OOPS!!! Task %d does not exist.", index));
        }
        Task task = taskList.get(index - 1);
        task.markNotDone();
        return task;
    }

    String stringToFile() {
        StringBuilder msg = new StringBuilder();
        for (Task task : taskList) {
            msg.append(String.format("%s\n", task.stringToFile()));
        }
        return msg.toString();
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            if (i == 1) {
                msg.append("\n");
            }
            msg.append(String.format("%d.%s", i, taskList.get(i - 1)));
            if (i != taskList.size()) {
                msg.append("\n");
            }
        }
        return msg.toString();
    }
}
