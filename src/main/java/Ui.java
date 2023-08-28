import java.util.ArrayList;

public class Ui {
    static void printHorizontalLine() {
        System.out.println("_________________________________________________");
    }

    static void greet() {
        printHorizontalLine();
        System.out.println("Hello! I'm Glub!");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    static void sayGoodbye() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    static void printAddMsg(ArrayList<Task> taskList) {
        int size = taskList.size();
        printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.printf(" \t%s%n", taskList.get(size - 1));
        System.out.printf("Now you have %d %s in the list.%n", size, size == 1 ? "task" : "tasks");
        printHorizontalLine();
    }

    static void printDeleteMsg(ArrayList<Task> taskList, Task deleted) {
        int size = taskList.size();
        printHorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.printf("\t%s%n", deleted);
        System.out.printf("Now you have %d %s in the list.%n", size, size == 1 ? "task" : "tasks");
        printHorizontalLine();
    }

    static void printListMsg(TaskList taskList) {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.showList());
        printHorizontalLine();
    }

    static void printMarkMsg(Task task) {
        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("\t%s%n", task);
        printHorizontalLine();
    }

    static void printUnmarkMsg(Task task) {
        printHorizontalLine();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.printf("\t%s%n", task);
        printHorizontalLine();
    }

    static void printError(String msg) {
        System.err.println(msg);
    }


}
