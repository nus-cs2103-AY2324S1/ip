import java.util.Scanner;

public class Bocchi {
    private static final String LINE_BREAK = "___________________________________________________";

    /**
     * Outputs greeting message.
     */
    private static void greet() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Bocchi");
        System.out.println("What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    /**
     * Outputs exit message.
     */
    private static void exit() {
        System.out.println(LINE_BREAK);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }

    /**
     * Adds the task to the current task list.
     * @param message Name of task
     * @param taskList Current list of tasks
     * @return Updated task list
     */
    private static TaskList addTask(String message, TaskList taskList) {
        System.out.println(LINE_BREAK);
        Task task = new Task(message);
        TaskList newTaskList = taskList.add(task);
        System.out.printf("added: %s%n", task);
        System.out.println(LINE_BREAK);
        return newTaskList;
    }

    /**
     * Outputs the current list of tasks to be done.
     * @param taskList Current list of tasks
     */
    private static void displayTasks(TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println(taskList);
        System.out.println(LINE_BREAK);
    }

    /**
    /**
     * Main method.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        greet();
        String activity = sc.nextLine();
        while (!activity.equals("bye")) {
            if (activity.equals("list")) {
                displayTasks(taskList);
            } else {
                taskList = addTask(activity, taskList);
            }
            activity = sc.nextLine();
        }
        sc.close();
        exit();
    }
}
