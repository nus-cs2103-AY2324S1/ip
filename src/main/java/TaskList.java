import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int taskNumber) throws ChatException {
        try {
            Task task = this.getTask(taskNumber);
            taskList.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("☹ OOPS!!! Please specify the correct task number.");
        }
    }

    public void markDone(int taskNumber) throws ChatException {
        try {
            Task task = taskList.get(taskNumber - 1);
            task.setTaskState(true);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("☹ OOPS!!! Please specify the correct task number.");
        }
    }

    public void markUndone(int taskNumber) throws ChatException {
        try {
            Task task = taskList.get(taskNumber - 1);
            task.setTaskState(false);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatException("☹ OOPS!!! Please specify the correct task number.");
        }
    }
}
