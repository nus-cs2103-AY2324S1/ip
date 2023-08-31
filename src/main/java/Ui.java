public class Ui {
    // Greets the user
    public static void greet() {
        System.out.println("I'm Jermbot. What you want?");
    }

    // Says goodbye to the user
    public static void goodbye() {
        System.out.println("Good riddance.");
    }

    // Lists out each task in the task list
    public static void listTasks(Tasklist tasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.print(i + 1);
            System.out.print(". " + tasks.getItemString(i) + "\n");
        }
    }

    // Notifies the user that a task has been marked as done
    public static void informTaskDone(Task task) {
        System.out.println("Ok good job lor you finished this task:");
        System.out.println("   " + task.toString());
    }

    // Notifies the user that a task has been marked as undone
    public static void informTaskUndone(Task task) {
        System.out.println("Wah why you never do this task:");
        System.out.println("   " + task.toString());
    }

    // Notifies the user that a task has been deleted
    public static void informTaskDeleted(Task task) {
        System.out.println("Ok slacker I've removed this task:");
        System.out.println("   " + task.toString());
    }

    // Notifies the user that a task has been added
    public static void informTaskAdded(Task task, int size) {
        System.out.printf("Haha now you have this task to do:\n   %s\nIn total you have %d things to do.\n", task, size);
    }

    // Informs the user about the number of tasks are on the list
    public static void informListSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list. Happy anot.");
    }
}
