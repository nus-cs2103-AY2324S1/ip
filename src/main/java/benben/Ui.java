package benben;
public class Ui {
    private static final String LINE ="_______________________________________\n";

    public Ui() {
    }

    public void show(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.print(LINE);
    }

    public void showExit() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
    }

    public void showLoadingError() {
        System.out.println(LINE);
        System.out.println("An error occurred while we are loading your task list! A new list has been generated!");
        System.out.print(LINE);
    }

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm BenBen.\n" +
                "What can I do for you?");
        System.out.println(LINE);
    }

    public void showAdd(String msg, int size) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:\n");
        System.out.println(msg);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    public void showRemove(String msg, int size) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:\n");
        System.out.println(msg);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    public void showUnmark(String msg) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "    " + msg);
        System.out.println(LINE);
    }

    public void showMark(String msg) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "    " + msg);
        System.out.println(LINE);
    }

    public void showList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println(LINE);
            System.out.println("Your list is currently empty! Add a new task now!");
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
            System.out.println(LINE);
        }
    }
}
