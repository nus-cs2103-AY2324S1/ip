public class TaskList {
    private static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int taskCount;

    public TaskList() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    public void addTask(Task task) throws DukeException {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[taskCount - 1]);
            if (taskCount == 1) {
                System.out.println("Now you have " + taskCount + " task in the list.");
            } else {
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! Sorry, the task list is full.");
        }
    }

    public void listTasks() {
        if (taskCount == 0) {
            System.out.println("The task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
        }
    }

    public void markTaskAsDone(int index) throws DukeException {
        if (index >= 1 && index <= taskCount) {
            tasks[index - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[index - 1].toString());
        } else {
            throw new DukeException("☹ OOPS!!! Invalid task index.");
        }
    }

    public void markTaskAsNotDone(int index) throws DukeException {
        if (index >= 1 && index <= taskCount) {
            tasks[index - 1].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[index - 1].toString());
        } else {
            throw new DukeException("☹ OOPS!!! Invalid task index.");
        }
    }
}
