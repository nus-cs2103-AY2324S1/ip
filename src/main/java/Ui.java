

public class Ui {

    public static void greet() {
        System.out.println("Hello! I'm Gman! \nWhat can I do for you?");
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void listTasks(TaskList taskList) {
        try {
            if (taskList.getSize() == 0) {
                throw new GmanException("There's nothing to print in the list");
            }
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println((i + 1) + ". "  + taskList.getTask(i).toString());
            }
        } catch (GmanException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void numberOfTasks(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("There are no tasks in the list!");
        } else if (taskList.getSize() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
        }
    }

    public static void addedTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
    }

    public static void removedTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
    }

    public static void mark(String taskToString) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToString);
    }

    public static void unmark(String taskToString) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskToString);
    }



}
