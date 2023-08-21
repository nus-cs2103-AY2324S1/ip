public class TaskList {
    private static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int taskCount;

    public TaskList() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    public void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            String[] parts = task.split(" ", 2);
            String taskType = parts[0].toLowerCase();
            String taskDescription = (parts.length > 1) ? parts[1] : "";

            switch (taskType) {
                case "todo":
                    tasks[taskCount] = new ToDo(taskDescription);
                    break;
                case "deadline":
                    String[] deadlineParts = taskDescription.split(" /by ");
                    String deadlineDescription = deadlineParts[0];
                    String by = deadlineParts[1];
                    tasks[taskCount] = new Deadline(deadlineDescription, by);
                    break;
                case "event":
                    String[] eventParts = taskDescription.split(" /from | /to ");
                    String eventDescription = eventParts[0];
                    String from = eventParts[1];
                    String to = eventParts[2];
                    tasks[taskCount] = new Event(eventDescription, from, to);
                    break;
                default:
                    System.out.println("Please enter task type.");
                    return;
            }

            taskCount++;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[taskCount - 1]);
            if (taskCount == 1) {
                System.out.println("Now you have " + taskCount + " task in the list.");
            } else {
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }
        } else {
            System.out.println("Sorry, the task list is full.");
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

    public void markTaskAsDone(int index) {
        if (index >= 1 && index <= taskCount) {
            tasks[index - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[index - 1].toString());
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void markTaskAsNotDone(int index) {
        if (index >= 1 && index <= taskCount) {
            tasks[index - 1].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[index - 1].toString());
        } else {
            System.out.println("Invalid task index.");
        }
    }
}
