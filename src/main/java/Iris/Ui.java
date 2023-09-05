package Iris;

public class Ui {
    private static final String HORIZONTAL_LINE = "      " +
            "_______________________________________________________________________________";

    public static void welcomeMsg() {
        String greetings = "Hello! I'm Iris! \n What can I do for you?";
        System.out.println(HORIZONTAL_LINE);
        System.out.println(greetings);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void exitMsg() {
        String byeMsg = "Bye. Hope to see you again soon!";
        System.out.println(HORIZONTAL_LINE);
        System.out.println(byeMsg);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void generalRespond(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTaskMsg(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done: \n" + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void unmarkTaskMsg(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Okay, I've marked this task as not done yet: \n" + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTasks(ToDoList toDoList) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(toDoList);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printLength(ToDoList toDoList) {
        int listSize = toDoList.size();
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }
}
