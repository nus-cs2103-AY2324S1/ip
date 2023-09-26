public class TaskList {
    private Task[] tasks;
    private int taskCount;

    public TaskList() {
        tasks = new Task[100];
        taskCount = 0;
    }

    public void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    public void deleteTask(int task) {
        Task[] newTasks = new Task[100];
        for (int i = 0; i < taskCount - 1; i++) {
            int j = i;
            if (i >= task) j = i + 1;
            newTasks[i] = tasks[j];
        }
        tasks = newTasks;
        taskCount--;
    }

    public void markTask(int task) {
        tasks[task].markAsDone();
    }

    public int getCount() {
        return taskCount;
    }

    public Task getTask(int i) {
        return tasks[i];
    }
}