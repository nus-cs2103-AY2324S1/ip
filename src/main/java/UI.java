public class UI {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void addTask(String taskName, int numOfTasks) {
        printLine();
        System.out.println("Got it. I've added the task:\n" + taskName);
        if (numOfTasks != 1) {
            System.out.println("Now you have " + numOfTasks + " tasks in your list, just like how I have 5 Ballon d'Ors.");
        } else {
            System.out.println("Now you have " + numOfTasks + " task in your list, just like how I have 5 Ballon d'Ors.");
        }
        printLine();
    }

    public static void deleteTask(String taskName, int numOfTasks) {
        UI.printLine();
        System.out.println("Removed task:\n" + taskName);
        System.out.println("Now you have " + numOfTasks + " tasks in your list.");
        UI.printLine();
    }

    public static void markTask(String taskName) {
        printLine();
        System.out.println("SIUUU! I've marked this task as done. We will make Saudi League number 1.\n [X] " + taskName);
        printLine();
    }

    public static void unMarkTask(String taskName) {
        printLine();
        System.out.println("OK, I've marked this task as not done.\n [ ] " + taskName);
        printLine();
    }
}
