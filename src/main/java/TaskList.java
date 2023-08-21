public class TaskList {
    private static final int MAX_TASKS = 100;
    private String[] tasks;
    private int taskCount;

    public TaskList() {
        tasks = new String[MAX_TASKS];
        taskCount = 0;
    }

    public void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("added: " + task);
        } else {
            System.out.println("Sorry, the task list is full.");
        }
    }

    public void listTasks() {
        if (taskCount == 0) {
            System.out.println("The task list is empty.");
        } else {
            System.out.println("Here are the tasks:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }
}
