public class Ui {
    private static final String LINE = "____________________________________________________________";
    public static void print(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void ask() {
        Ui.print("Hello I'm GBot!\nWhat can I do for you?");
    }

    public void end() {
        Ui.print("Bye. Hope to see you again soon!");
    }

    public static void showError(String message) {
        Ui.print(message);
    }
    public static void showTaskNumberError() {
        print("Please enter a task number.");
    }
    public static void showMissingTask() {
        print("Please input a task description.");
    }
}
