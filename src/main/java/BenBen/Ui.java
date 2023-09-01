package BenBen;
public class Ui {
    private static final String line ="_______________________________________\n";

    public Ui() {
    }

    public void show(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.print(line);
    }

    public void showExit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }

    public void showLoadingError() {
        System.out.println(line);
        System.out.println("An error occurred while we are loading your task list! A new list has been generated!");
        System.out.print(line);
    }

    public void showWelcome() {
        System.out.println(line);
        System.out.println("Hello! I'm BenBen.\n" +
                "What can I do for you?");
        System.out.println(line);
    }

    public void showAdd(String msg, int size) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n");
        System.out.println(msg);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(line);
    }

    public void showRemove(String msg, int size) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:\n");
        System.out.println(msg);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(line);
    }

    public void showUnmark(String msg) {
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "    " + msg);
        System.out.println(line);
    }

    public void showMark(String msg) {
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "    " + msg);
        System.out.println(line);
    }

    public void showList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println(line);
            System.out.println("Your list is currently empty! Add a new task now!");
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
            System.out.println(line);
        }
    }
}
